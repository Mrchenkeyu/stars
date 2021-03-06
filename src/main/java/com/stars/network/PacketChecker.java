package com.stars.network;

import com.stars.modules.chat.ChatPacketSet;
import com.stars.modules.customerService.CustomerServicePacketSet;
import com.stars.modules.daily.DailyPacketSet;
import com.stars.modules.demologin.LoginPacketSet;
import com.stars.modules.dungeon.DungeonPacketSet;
import com.stars.modules.email.EmailPacketSet;
import com.stars.modules.family.FamilyPacketSet;
import com.stars.modules.foreshow.ForeShowPacketSet;
import com.stars.modules.friend.FriendPacketSet;
import com.stars.modules.friendInvite.InvitePacketSet;
import com.stars.modules.friendShare.SharePacketSet;
import com.stars.modules.getway.GetWayPacketSet;
import com.stars.modules.gm.GmPacketSet;
import com.stars.modules.induct.InductPacketSet;
import com.stars.modules.name.NamePacketSet;
import com.stars.modules.operateactivity.OperateActivityPacketSet;
import com.stars.modules.redpoint.RedPointPacketSet;
import com.stars.modules.role.RolePacketSet;
import com.stars.modules.scene.ScenePacketSet;
import com.stars.modules.skill.SkillPacketSet;
import com.stars.modules.system.SystemPacketSet;
import com.stars.modules.tool.ToolPacketSet;
import com.stars.modules.truename.TrueNamePacketSet;
import com.stars.modules.vip.VipPacketSet;
import com.stars.network.server.packet.Packet;

import java.util.*;

/**
 * <h1>数据包命名规范</h1>
 * <h2>服务间数据包（类命名）</h2>
 * <ul>
 * <li>*G2fPkt -- 游戏服到战斗服</li>
 * <li>*F2gPkt -- 战斗服到游戏服</li>
 * <li>*G2gPkt -- 游戏服到游戏服</li>
 * <li>*G2cPkt -- 游戏服到公共服</li>
 * <li>*C2gPkt -- 公共服到游戏服</li>
 * </ul>
 * <p>
 * <h2>客户端/服务端（包括公共服）数据包（类命名）</h2>
 * <ul>
 * <li>Server* -- 客户端请求服务端</li>
 * <li>Client* -- 服务端下发到客户端</li>
 * </ul>
 * <p>
 * <h1>PacketType编码规范：</h1>
 * <p>按模块区分，每个模块至少分配4个协议编码（编码范围0x0001-0x6FFF,从0x0001开始，按顺序递增，协议多的模块
 * 可分配n*4个,需在编码范围写清楚），没有用到的数字保留，其他模块不可占用，模块之间按顺序递增分配，
 * 不可跳过，模块协议前后需要写清楚模块开始和结束，编码范围,例子如下：</p>
 * <p>
 * <h1>分配规则</h1>
 * <ul>
 * <li>0x0001 - 0x000F 16  系统（心跳/通用提示/登录/创角）</li>
 * <li>0x0010 - 0x001F 16  人物</li>
 * <li>0x0020 - 0x002F 16  取名</li>
 * <li>0x0030 - 0x003F 16  道具</li>
 * <li>0x0040 - 0x004F 16  NPC</li>
 * <li>0x0050 - 0x005F 16  关卡</li>
 * <li>0x0060 - 0x006F 16  装备</li>
 * <p>
 * <li>0x7B00 - 0x7B3F 64  战斗</li>
 * <li>0x7C00 - 0x7CFF 256 保留</li>
 * <li>0x7D00 - 0x7DFF 256 GM</li>
 * <li>0x7E00 - 0x7EFF 256 测试</li>
 * <li>0x7F00 - 0x7FFF 256 底层</li>
 * </ul>
 */
public class PacketChecker {

    private static Set<Short> packetTypeWithoutCacheSet = new HashSet<>();

    public static void check() throws Exception {

        Map<Short, Class<? extends com.stars.network.server.packet.Packet>> packetTypes = new HashMap<>();
        byte[] bitmap = new byte[Short.MAX_VALUE];

        /* 注意协议号的顺序（至少是4的倍数） */
        check((short) 0x0000, (short) 0x000F, packetTypes, bitmap, new LoginPacketSet()); // 登录
        check((short) 0x0010, (short) 0x0019, packetTypes, bitmap, new RolePacketSet()); // 人物

        check((short) 0x0020, (short) 0x0025, packetTypes, bitmap, new NamePacketSet());// 取名
        check((short) 0x0026, (short) 0x0029, packetTypes, bitmap, new ChatPacketSet());//聊天
        check((short) 0x002a, (short) 0x002d, packetTypes, bitmap, new DailyPacketSet());//活跃度
        check((short) 0x0031, (short) 0x0035, packetTypes, bitmap, new ToolPacketSet()); // 道具

        check((short) 0x0044, (short) 0x0049, packetTypes, bitmap, new SkillPacketSet());//技能

        check((short) 0x0050, (short) 0x0055, packetTypes, bitmap, new DungeonPacketSet()); // 关卡
        check((short) 0x0056, (short) 0x0059, packetTypes, bitmap, new InductPacketSet());// 引导


        check((short) 0x0160, (short) 0x0163, packetTypes, bitmap, new ForeShowPacketSet());//系统开放预告
        check((short) 0x017B, (short) 0x017F, packetTypes, bitmap, new RedPointPacketSet());//红点
        check((short) 0x0188, (short) 0x018F, packetTypes, bitmap, new VipPacketSet());    // vip(贵族系统)
        check((short) 0x0190, (short) 0x0197, packetTypes, bitmap, new OperateActivityPacketSet());    // 运营活动系统
        check((short) 0x0284, (short) 0x0287, packetTypes, bitmap, new SharePacketSet()); // 朋友圈分享
        check((short) 0x028A, (short) 0x028D, packetTypes, bitmap, new InvitePacketSet()); //好友邀请
        check((short) 0x028E, (short) 0x0291, packetTypes, bitmap, new TrueNamePacketSet()); //实名认证
        check((short) 0x0292, (short) 0x029F, packetTypes, bitmap, new PlaceholderPacketSet()); // 符文装备体验副本
        check((short) 0x02A4, (short) 0x02A7, packetTypes, bitmap, new GetWayPacketSet()); // 获取途径
        check((short) 0x02A8, (short) 0x02A9, packetTypes, bitmap, new CustomerServicePacketSet()); // vip玩家信息记录
        /* 公共业务 */
        check((short) 0x6000, (short) 0x6007, packetTypes, bitmap, new EmailPacketSet()); // 邮件
        check((short) 0x6008, (short) 0x601F, packetTypes, bitmap, new FriendPacketSet()); // 好友
        check((short) 0x6020, (short) 0x604F, packetTypes, bitmap, new FamilyPacketSet()); // 家族（基础）
        check((short) 0x6068, (short) 0x6167, packetTypes, bitmap, new PlaceholderPacketSet()); // 家族（活动）


		/* 特殊 */
        check((short) 0x7B00, (short) 0x7B3F, packetTypes, bitmap, new ScenePacketSet());// 场景
        //0x7C00 - 0x7CFF 登陆服


        check((short) 0x7D00, (short) 0x7DFF, packetTypes, bitmap, new GmPacketSet()); // GM

        // 0x7E00 - 0x7EFF 256 测试
        check((short) 0x7F00, (short) 0x7FFF, packetTypes, bitmap, new SystemPacketSet()); // 底层


		/*
         * 设置不需缓存的PacketType
		 */
        addPacketTypeWithoutCache(ChatPacketSet.Server_ChatMessage); // 聊天(上行)
        addPacketTypeWithoutCache(ChatPacketSet.Client_ChatMessage); // 聊天(下行)
        addPacketTypeWithoutCache(LoginPacketSet.S_RECONNECT); // 重连(上行)
        addPacketTypeWithoutCache(LoginPacketSet.C_RECONNECT); // 重连(下行)
    }


    public static void check(short min, short max, Map<Short, Class<? extends com.stars.network.server.packet.Packet>> packetTypes,
                             byte[] bitmap, PacketSet... sets) throws Exception {

        for (int i = min; i < max; i++) {
            if (bitmap[i] == 1) {
                throw new RuntimeException("预定PacketType范围有重复(" + min
                        + ", " + max + ")");
            }
            bitmap[i] = 1;
        }
        for (PacketSet set : sets) {
            List<Class<? extends com.stars.network.server.packet.Packet>> al = set.getPacketList();
            for (Class<? extends com.stars.network.server.packet.Packet> clazz : al) {
                com.stars.network.server.packet.Packet packet = clazz.newInstance();
                short type = packet.getType();
                Class<? extends Packet> oldClazz = packetTypes.get(type);
                if (oldClazz != null) {
                    throw new RuntimeException(
                            "协议类型重复: " + Integer.toHexString(type) + ", "
                                    + clazz.getSimpleName() + ", " + oldClazz.getSimpleName());
                }
                packetTypes.put(type, clazz);

                if (type < min || type > max) {
                    throw new RuntimeException("协议类型越界: " + clazz.getName() + "," + type + ", (" + min + "," + max + ")");
                }

            }
        }
    }

    public static void addPacketTypeWithoutCache(short packetType) {
        packetTypeWithoutCacheSet.add(packetType);
    }

    public static boolean needCache(short packetType) {
        return !packetTypeWithoutCacheSet.contains(packetType);
    }
}
