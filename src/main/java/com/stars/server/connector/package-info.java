/**
 * todo: load balance - base on state(EMPTY, NORMAL, WARNING, OVERLOAD)
 * todo: config reload - check again
 * todo: eliminate ThreadLocal - merge them into one
 * todo: move ClientWarningPacket to core
 * todo: bug - stat
 * todo: bug - when there is no data in redis and multiple player login, there may be two player in different game server
 * todo: 现在有较多的MinorGC，需要检查是否对性能有影响
 *
 *
 * Created by zhaowenshuo on 2016/2/18.
 */
package com.stars.server.connector;

/**
 * 意义：解决掉线重连的问题；承载连接；简单负载均衡；
 *
 * 大纲
 * 关键点
 * A1 会话保持
 * A2 线程模型
 * A3 会话管理
 * A4 数据统计
 * A5 连接检测机制和重连机制
 * 关键流程
 * B1 启动流程
 * B2 客户端连接和游戏服连接的绑定流程
 * B3 数据包转发流程（客户端连接 -> 游戏服连接）
 * B4 数据包转发流程（游戏服连接 -> 客户端连接）
 * B5 游戏服连接关闭流程
 *
 * A1 会话保持
 *   A1.1 使用Redis保持玩家账号和游戏服ID，提供一个全局唯一的访问点
 *   A1.2 通过获取玩家账号和游戏服ID的对应关系，连接服可以将客户端连接绑定到上次使用的游戏服
 *   A1.3 Redis需要提供高可用性的服务，并保证较高的数据安全（暂定方案一）
 *     a) 方案一：Redis集群+主从；
 *       1) 优点：高可用；较高的数据安全
 *       2) 缺陷：数据写到主时，未必写到主；网络分区（如果出现这种情况，会导致同一账号多次登录；或会话保持失效）
 *     b) 方案二：Redis集群+fsyncalways
 *       1) 优点：最高级别的数据安全；通过集群减少redis实例宕机的影响
 *       2) 缺点：redis实例宕机，它所持有的玩家将受到影响（登录）；fsyncalways对性能有较大消耗（可通过集群改善到一个可接受程度）
 *     c) 方案三：Redis单点+fsyncalways+pipeline
 *       1) 优点：部署简单；最高级别的数据安全
 *       2) 缺点：单点；fsyncalways对性能有较大消耗（使用pipeline改善）
 *
 * A2 线程模型
 *   A2.1 所有重要操作都是在Netty IO线程上完成（除了部分统计功能）
 *     a) 所有耗时操作（拆包/粘包/加密/解密/压缩）从职能上都须要在Netty IO线程上完成
 *     b) 不需要做同步，可以简化处理过程，简化管理
 *   A2.2 一个Netty IO线程既承载了客户端连接，又承载了所有游戏服连接
 *     a) 一个IO线程与一个游戏服维护一条连接；假设有M个IO线程，N个游戏服，那么连接服就会有M*N条游戏服连接
 *     b) 数据包转发（客户端连接 -> 游戏服连接；游戏服连接 -> 客户端连接）不需要跨线程
 *     c) 以IO线程为单位管理客户端连接和游戏服连接
 *   A2.3 IO线程间通信是使用执行Runnable的形式（类似消息）
 *   A2.4 使用ThreadLocal（线程本地变量）保存IO线程需要的属性（因为Netty IO线程不能继承）
 *     a) 减少锁的使用
 *
 * A3 会话管理
 *   A3.1 BackendSession保存游戏服连接会话
 *   A3.2 FrontendSession保存客户端连接会话
 *     a) 持有一个连接ID（connectionId）
 *       1) 对IO线程来说是唯一的，对IO线程内的游戏服连接也是唯一的
 *       2) IO线程通过连接ID标识游戏服连接过来的数据包是发往哪个客户端连接
 *       3) 发往游戏服连接的数据包，和来自游戏服连接的数据包都会带有连接ID
 *       4) 游戏服的GameSession也会持有连接ID
 *   A3.3 在同一个Netty IO线程内，客户端连接是共用一条游戏服连接
 *
 * A4 数据统计（暂时屏蔽，会引起较多GC；考虑默认关闭，需要时再开启）
 *   A4.1 统计内容
 *     a) 客户端连接数量
 *     b) 数据包总个数
 *     c) 数据包总大小
 *     d) 每个协议对应数据包的个数
 *     e) 每个协议对应数据包的大小
 *   A4.2 实现思路（分而治之）：各个IO线程统计自身的数据，定时进行汇总
 *
 * A5 连接检测机制和重连机制
 *   A5.1 游戏服连接检测
 *     a) 连接建立时提交一个定时任务
 *     b) 该定时任务完成以下工作：
 *       1) 检查心跳是否超时（当前时间戳 - 心跳响应时间戳）
 *       2) 发送心跳请求包（收到响应包时会更新心跳响应时间戳）
 *     c) 连接关闭时取消这个定时任务
 *   A5.2 客户端连接检测
 *     a) 连接建立时会设置一个读取超时时间（读取配置）
 *     b) 客户端会定时发送心跳
 *     c) IO线程收到心跳直接丢弃，不处理
 *     d) 如果超过读取超时时间，连接将被断开
 *   A5.3 游戏服重连处理
 *     a) 游戏服连接关闭时（channelInactive）会向IO线程提交重建连接的任务
 *     b) IO线程执行这个重建连接的任务，直到重连成功
 *
 * B1 启动流程
 *   B1.1 加载本地配置
 *   B1.2 加载游戏服配置（从配置服读取配置）
 *   B1.3 初始化Redis工具类
 *   B1.4 初始化Netty IO线程，连接游戏服，并等待连接完成
 *   B1.5 设置统计任务定时运行（暂时屏蔽）
 *   B1.6 监听前端端口（开始接受客户端连接）
 *   B1.7 按配置，监听后门端口
 *   B1.8 初始化路由服客户端（用作监控）
 *   B1.9 添加配置更新处理方法
 *
 * B2 客户端连接和游戏服连接的绑定流程
 *   B2.1 客户端与游戏服建立连接，向该连接分配一个连接ID（connectionId）
 *   B2.2 客户端发送登录请求包，协议号为0x0003
 *   B2.3 连接服收到登录请求包，截取玩家账号，使用Redis异步Api获取对应的游戏服ID（异步请求），下面是回调处理部分
 *     *) 回调处理是在Redis异步Api的内部线程中使用
 *     a) 成功，获取游戏服ID（serverId），提交到Netty IO线程处理
 *     b) 失败，提交到Netty IO线程处理（关闭连接）（todo：暂缺）
 *   B2.4 客户端连接绑定游戏服连接
 *     a) 游戏服ID小于0（就是Redis中没有数据），则选择一个游戏服连接与客户端连接绑定（RoundRobin）
 *     b) 游戏服ID大于等于0，则选择对应的游戏服连接进行绑定；如果游戏服连接为NULL，则关闭客户端连接
 *   B2.5 绑定成功，把登录请求发往游戏服；绑定失败（会话为NULL，或绑定失败，或Channel为NULL），则关闭连接
 *
 * B3 数据包转发流程（客户端连接 -> 游戏服连接）
 *   B3.1 客户端发送数据包
 *   B3.2 连接服从客户端收包过程
 *     a) 根据长度进行拆包
 *     b) 解密
 *     c) 获取协议号，针对不同的协议号，进行不同的处理
 *       1) 登录协议（0x0003）：参照B2
 *       2) 心跳协议（0x0001）：丢弃心跳包
 *       3) 其他协议：根据绑定的游戏服ID，获取游戏服连接，并将数据包发往该连接（调用writeToBackend()）
 *   B3.3 连接服往游戏服发包过程
 *     a) 粘包（须要写入连接ID，connectionId，游戏服根据这个id来辨识数据包是来自哪个客户端的）
 *     b) 发送
 *
 * B4 数据包转发流程（游戏服连接 -> 客户端连接）
 *   B4.1 游戏服发送数据包
 *   B4.2 根据长度进行拆包
 *   B4.3 根据连接ID（connectionId），找到对应的客户端连接
 *   B4.4 往客户端连接发送数据包
 *     a) 如果数据包长度大于4K，进行压缩
 *     b) 加密
 *     c) 粘包
 *     d) 发送
 *
 * B5 游戏服连接关闭流程
 *   B5.1 取消连接检测任务
 *   B5.2 关闭与该游戏服连接绑定的客户端连接
 *   B5.3 提交一个重建连接的任务
 */