package test;


/**
 * Created by zhouyaohui on 2016/10/12.
 */
public class AttributeTest {


    public static void main(String[] args) {
//        String str = "{"cmd":10010,"uid":"900086000145592435","sid":"60859f53158d03aec7d4d37e52247fba4105","server":"[{\"serverId\":15001,\"enterServerId\":\"15001\",\"name\":\"思美人1服\",\"ip\":\"182.254.217.217\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.96\"},{\"serverId\":15002,\"enterServerId\":\"15002\",\"name\":\"思美人2服\",\"ip\":\"182.254.138.193\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.223\"},{\"serverId\":15003,\"enterServerId\":\"15003\",\"name\":\"思美人3服\",\"ip\":\"182.254.242.221\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.12\"},{\"serverId\":15004,\"enterServerId\":\"15004\",\"name\":\"思美人4服\",\"ip\":\"115.159.95.213\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.77\"},{\"serverId\":15005,\"enterServerId\":\"15005\",\"name\":\"思美人5服\",\"ip\":\"115.159.30.169\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.37\"},{\"serverId\":15006,\"enterServerId\":\"15006\",\"name\":\"思美人6服\",\"ip\":\"115.159.154.172\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.187\"},{\"serverId\":15007,\"enterServerId\":\"15007\",\"name\":\"思美人7服\",\"ip\":\"115.159.52.222\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.161\"},{\"serverId\":15008,\"enterServerId\":\"15008\",\"name\":\"思美人8服\",\"ip\":\"115.159.188.145\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.235\"},{\"serverId\":15009,\"enterServerId\":\"15009\",\"name\":\"思美人9服\",\"ip\":\"115.159.97.232\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.186\"},{\"serverId\":15010,\"enterServerId\":\"15010\",\"name\":\"思美人10服\",\"ip\":\"115.159.71.78\",\"port\":7091,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.71\"},{\"serverId\":15011,\"enterServerId\":\"15011\",\"name\":\"思美人11服\",\"ip\":\"182.254.217.217\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.96\"},{\"serverId\":15012,\"enterServerId\":\"15012\",\"name\":\"思美人12服\",\"ip\":\"182.254.138.193\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.223\"},{\"serverId\":15013,\"enterServerId\":\"15013\",\"name\":\"思美人13服\",\"ip\":\"182.254.242.221\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.12\"},{\"serverId\":15014,\"enterServerId\":\"15014\",\"name\":\"思美人14服\",\"ip\":\"115.159.95.213\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.77\"},{\"serverId\":15015,\"enterServerId\":\"15015\",\"name\":\"思美人15服\",\"ip\":\"115.159.30.169\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.37\"},{\"serverId\":15016,\"enterServerId\":\"15016\",\"name\":\"思美人16服\",\"ip\":\"115.159.154.172\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.187\"},{\"serverId\":15017,\"enterServerId\":\"15017\",\"name\":\"思美人17服\",\"ip\":\"115.159.52.222\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.161\"},{\"serverId\":15018,\"enterServerId\":\"15018\",\"name\":\"思美人18服\",\"ip\":\"115.159.188.145\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.235\"},{\"serverId\":15019,\"enterServerId\":\"15019\",\"name\":\"思美人19服\",\"ip\":\"115.159.97.232\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.186\"},{\"serverId\":15020,\"enterServerId\":\"15020\",\"name\":\"思美人20服\",\"ip\":\"115.159.71.78\",\"port\":7092,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.71\"},{\"serverId\":15021,\"enterServerId\":\"15021\",\"name\":\"思美人21服\",\"ip\":\"182.254.217.217\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.96\"},{\"serverId\":15022,\"enterServerId\":\"15022\",\"name\":\"思美人22服\",\"ip\":\"182.254.138.193\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.223\"},{\"serverId\":15023,\"enterServerId\":\"15023\",\"name\":\"思美人23服\",\"ip\":\"182.254.242.221\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.12\"},{\"serverId\":15024,\"enterServerId\":\"15024\",\"name\":\"思美人24服\",\"ip\":\"115.159.95.213\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.77\"},{\"serverId\":15025,\"enterServerId\":\"15025\",\"name\":\"思美人25服\",\"ip\":\"115.159.30.169\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.37\"},{\"serverId\":15026,\"enterServerId\":\"15026\",\"name\":\"思美人26服\",\"ip\":\"115.159.154.172\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.187\"},{\"serverId\":15027,\"enterServerId\":\"15027\",\"name\":\"思美人27服\",\"ip\":\"115.159.52.222\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.161\"},{\"serverId\":15028,\"enterServerId\":\"15028\",\"name\":\"思美人28服\",\"ip\":\"115.159.188.145\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.235\"},{\"serverId\":15029,\"enterServerId\":\"15029\",\"name\":\"思美人29服\",\"ip\":\"115.159.97.232\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.186\"},{\"serverId\":15030,\"enterServerId\":\"15030\",\"name\":\"思美人30服\",\"ip\":\"115.159.71.78\",\"port\":7093,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.71\"},{\"serverId\":15031,\"enterServerId\":\"15031\",\"name\":\"思美人31服\",\"ip\":\"182.254.217.217\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.96\"},{\"serverId\":15032,\"enterServerId\":\"15032\",\"name\":\"思美人32服\",\"ip\":\"182.254.138.193\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.223\"},{\"serverId\":15033,\"enterServerId\":\"15033\",\"name\":\"思美人33服\",\"ip\":\"182.254.242.221\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.12\"},{\"serverId\":15034,\"enterServerId\":\"15034\",\"name\":\"思美人34服\",\"ip\":\"115.159.95.213\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.77\"},{\"serverId\":15035,\"enterServerId\":\"15035\",\"name\":\"思美人35服\",\"ip\":\"115.159.30.169\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.37\"},{\"serverId\":15036,\"enterServerId\":\"15036\",\"name\":\"思美人36服\",\"ip\":\"115.159.154.172\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.187\"},{\"serverId\":15037,\"enterServerId\":\"15037\",\"name\":\"思美人37服\",\"ip\":\"115.159.52.222\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.161\"},{\"serverId\":15038,\"enterServerId\":\"15038\",\"name\":\"思美人38服\",\"ip\":\"115.159.188.145\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.235\"},{\"serverId\":15039,\"enterServerId\":\"15039\",\"name\":\"思美人39服\",\"ip\":\"115.159.97.232\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.186\"},{\"serverId\":15040,\"enterServerId\":\"15040\",\"name\":\"思美人40服\",\"ip\":\"115.159.71.78\",\"port\":7094,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.71\"},{\"serverId\":15041,\"enterServerId\":\"15041\",\"name\":\"思美人41服\",\"ip\":\"182.254.217.217\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.96\"},{\"serverId\":15042,\"enterServerId\":\"15042\",\"name\":\"思美人42服\",\"ip\":\"182.254.138.193\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.223\"},{\"serverId\":15043,\"enterServerId\":\"15043\",\"name\":\"思美人43服\",\"ip\":\"182.254.242.221\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.12\"},{\"serverId\":15044,\"enterServerId\":\"15044\",\"name\":\"思美人44服\",\"ip\":\"115.159.95.213\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.77\"},{\"serverId\":15045,\"enterServerId\":\"15045\",\"name\":\"思美人45服\",\"ip\":\"115.159.30.169\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.37\"},{\"serverId\":15046,\"enterServerId\":\"15046\",\"name\":\"思美人46服\",\"ip\":\"115.159.154.172\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.187\"},{\"serverId\":15047,\"enterServerId\":\"15047\",\"name\":\"思美人47服\",\"ip\":\"115.159.52.222\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.161\"},{\"serverId\":15048,\"enterServerId\":\"15048\",\"name\":\"思美人48服\",\"ip\":\"115.159.188.145\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.235\"},{\"serverId\":15049,\"enterServerId\":\"15049\",\"name\":\"思美人49服\",\"ip\":\"115.159.97.232\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.186\"},{\"serverId\":15050,\"enterServerId\":\"15050\",\"name\":\"思美人50服\",\"ip\":\"115.159.71.78\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.71\"},{\"serverId\":15042,\"enterServerId\":\"15042\",\"name\":\"思美人42服\",\"ip\":\"182.254.138.193\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.223\"}]","serverPage":"[{\"id\":3,\"name\":\"41-48服\",\"serversList\":[15041,15042]},{\"id\":4,\"name\":\"33-40服\",\"serversList\":[15033,15034,15035,15036,15037,15038,15039,15040]},{\"id\":5,\"name\":\"25-32服\",\"serversList\":[15025,15026,15027,15028,15029,15030,15031,15032]},{\"id\":6,\"name\":\"17-24服\",\"serversList\":[15017,15018,15019,15020,15021,15022,15023,15024]},{\"id\":7,\"name\":\"9-16服\",\"serversList\":[15009,15010,15011,15012,15013,15014,15015,15016]},{\"id\":8,\"name\":\"1-8服\",\"serversList\":[15001,15002,15003,15004,15005,15006,15007,15008]}]","lastServer":"{\"serverId\":15042,\"enterServerId\":\"15042\",\"name\":\"思美人42服\",\"ip\":\"182.254.138.193\",\"port\":7095,\"status\":1,\"sort\":2,\"dbport\":3306,\"localip\":\"10.10.4.223\"}","regChannel":"15000","account":"900086000145592435#15000","isInetAccount":false,"content":"|95 -124 -47 67 -74 79 10 15 -69 23 63 -34 34 -34 -42 -68 14 100 -107 -97 39 117 2 2 32 82 -37 99 -128 17 73 -125 -114 106 -45 -25 46 32 -24 21 -107 101 15 15 -82 -121 54 -115 -48 44 3 15 -122 8 -82 85 115 116 94 -58 94 20 -32 96 -16 42 3 -111 58 -125 29 2 -40 -51 125 31 -44 75 113 -51 -61 57 96 50 37 81 -20 125 83 63 112 -124 119 7 42 125 35 -54 86 124 -115 117 -101 7 -86 78 -87 -19 75 78 43 55 -91 -2 -52 103 66 -119 127 38 115 69 68 -91 -76 -6 66 -1 ","newServers":"15042,15041","oneServer":0,"myServer":0,"accoutRegisterTime":"2017-06-13 22:14:01","isNewAccount":false,"maintenanceTips":"服务器维护中，预计10点开服","Gameboard":"[{\"boardId\":\"72\",\"label\":\"0\",\"title\":\"6月14日更新公告\",\"content\":\"亲爱的诗友您好：\\n  若有人兮山之阿，被薜荔兮带女萝\\n  《思美人》手游<color=#cc0000>6月14日5:00</color>将开启例行维护和更新，预计<color=#cc0000>12:00</color>完成更新维护。感谢您的耐心等待，更新完成后会安排对应补偿，请留意。\\n  本周更新后，将开启转职功能，并开启<color=#cc0000>新职业“女萝”</color>的转职。新职业“女萝”还带来了丰富的福利活动，一定要记得来参加哦！\\n\\n<color=#e15f01>-----------------【重要更新】-----------------</color>\\n  转职随心共狂欢，<color=#cc0000>转职功能</color>全新开启，通关关卡6-5即可获得转职资格，消耗一定金币或角色转职卡即可转职。转职后，对应装备及属性保持不变，神兵也会替换成对应职业的样式。\\n  娇俏可爱<color=#cc0000> “女萝”新职业</color>全新上线，可供转职哟！累计充值达到一定金额即可获得新职业“女萝”的转职卡，在人物界面即可转职化身为女萝啦！\\n  <color=#cc0000>三星奖励升级</color>！快来挑战主线关卡吧~之前已领过三星奖励的玩家，会统一发放三星奖励补偿，一定要记得查收哦~\\n\\n<color=#e15f01>-----------------【主题活动】 -----------------</color>\\n活动一：女萝邂逅任务\\n活动时间：6.14~6.27\\n参与条件：通关6-5开启\\n活动内容：新职业女萝从哪里来？她有什么故事？通关关卡6-5开启邂逅女萝支线任务，邂逅可爱小女萝，了解其他人眼里的她，还有女萝专属好礼等你哟！\\n奖励内容：装备宝箱、大量金币、 <color=#b746c9>橙装碎片、伙伴</color>等\\n\\n活动二：女萝童心结 \\n活动时间：6.14~6.20\\n参与条件：30级以上\\n活动内容：小“女萝”今年7岁啦！童心结可是她珍贵的信物。女萝的童心结丢失后非常着急，赶紧随她一起去寻找吧！沿路的宝箱可不要错过哦~\\n奖励内容：<color=#b746c9>童心结、装备锻造材料、</color>大量经验、强化石等 \\n\\n活动三：神射手比拼\\n活动时间：6.14~6.20\\n参与条件：30级以上\\n活动内容：想和女萝一样成为神射手吗？比试身手的时候到啦！拉动弓箭瞄准宝袋，射中的越多奖励越好哟！弓箭有限，考验技巧的时候到啦~\\n奖励内容： <color=#b746c9>童心结、橙装碎片、</color>驯化丹、伙伴经验丹等\\n\\n活动四：亲密大考验\\n活动时间：6.14~6.20\\n参与条件：30级以上\\n活动规则：莽莽红尘，相遇即是缘分，新职业女萝初临王城，受到所有职业的喜爱，他们都想和女萝成为最亲密的一对。那你最看好谁呢？使用女萝最喜爱的“童心结”支持他们，即可获得各种礼品。投票排名最高的玩家还能获得<color=#b746c9>元宝、天工钥</color>等珍贵道具哦！\\n奖励内容：\\n【累计投票奖励】大量金币、<color=#b746c9>门客碎片、伙伴悟性升级材料</color>\\n【投票排名奖励】<color=#b746c9>天工钥、元宝、橙装碎片</color>、大量金币、\\n\\n<color=#e15f01>-----------------【后续预告】-----------------</color>\\n1、等级解封\\n2、新特效装备\\n3、好友邀请\\n4、朋友圈分享\\n\\n<color=#e15f01>-----------------【优化内容】-----------------</color>\\n1、六国寻宝可全自动开启，全自动确认时间由5秒改为3秒\\n2、背包道具添加使用按钮直接跳转到对应功能\\n3、家族运镖到达目的地可选择快速传回起点\\n4、勇者宝石、富贵宝石、斗魂宝石等级解封到7级\\n5、三星奖励加量，已经通关并领取过的玩家上线后会收到对应奖励\\n6、日常5v5匹配优化\\n7、家族族长不在线，自动禅让时间改为3天\\n\",\"plateform\":\"\"}]"}";
    }
}
