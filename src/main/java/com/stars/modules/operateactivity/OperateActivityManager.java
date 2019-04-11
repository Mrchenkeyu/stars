package com.stars.modules.operateactivity;

import com.stars.modules.operateactivity.prodata.OperateActVo;
import com.stars.services.SConst;
import com.stars.util.LogUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by gaopeidian on 2016/12/5.
 */
public class OperateActivityManager {
    public static Map<Integer, Integer> operateActResignCost = new HashMap<Integer, Integer>();

    private static Map<Integer, OperateActVo> operateActVoMap = null;

    private static Map<Integer, String> opMoudleNameMap = new LinkedHashMap<Integer, String>();
    private static Map<Integer, String> opCheckMap = new LinkedHashMap<Integer, String>();

    // 按活动类型存储活动配置
    // <活动类型，该活动类型的活动配置列表>
    private static Map<Integer, List<OperateActVo>> operateActVoListMap = new HashMap<Integer, List<OperateActVo>>();

    // 活动状态记录
    // <活动类型,活动id>,活动id为-1时代表此类型无正在进行中的活动
    private static ConcurrentMap<Integer, Integer> curActivityIds = new ConcurrentHashMap<Integer, Integer>();

    public static void setOperateActVoMap(Map<Integer, OperateActVo> operateActVoMap) {
        Map<Integer, List<OperateActVo>> operateActVoListMap = new HashMap<Integer, List<OperateActVo>>();
        operateActVoListMap.clear();
        for (OperateActVo vo : operateActVoMap.values()) {
            int type = vo.getType();
            List<OperateActVo> list = operateActVoListMap.get(type);
            if (list == null) {
                list = new ArrayList<OperateActVo>();
                operateActVoListMap.put(type, list);
            }
            list.add(vo);
        }

        // 对所有list按活动id从小到大排列
        for (List<OperateActVo> list : operateActVoListMap.values()) {
            Collections.sort(list);
        }

        OperateActivityManager.operateActVoMap = operateActVoMap;
        OperateActivityManager.operateActVoListMap = operateActVoListMap;
    }

    public static Map<Integer, OperateActVo> getOperateActVoMap() {
        return operateActVoMap;
    }

    public static OperateActVo getOperateActVo(int operateActId) {
        return operateActVoMap.get(operateActId);
    }

    /**
     * 请注意：4=创角时间,格式为4|x+y,表示创角x~y天开放此活动意味着此活动是永久开启的，只和角色创建角色时间有关系。故在选择时间4类型时，请直接拿产品数据即可，无需走活动控制，
     * 务必不要使用getCurActivityId(int activityType)方法，否则同一type 4类型如果配置多个id的活动将会只能拿到一个，可调用以下方法List<OperateActVo> getOperateActVoListByType(int type)，
     * 在各自活动模块重写public int getCurShowActivityId()方法判断创建角色时间 选择合适的活动id即可。
     *
     * @param type
     * @return
     */
    public static List<OperateActVo> getOperateActVoListByType(int type) {
        List<OperateActVo> opList = operateActVoListMap.get(type);
        if (opList != null) {
            return opList;
        } else {
            return new ArrayList<OperateActVo>();
        }
    }

    public static int getFirstActIdbyActType(int type) {
        return operateActVoListMap.get(type).get(0).getOperateactid();
    }

    /**
     * 活动开放时间、活动重置检查注册(不建议用了，每个活动独立控制活动的开启和关闭，降低耦合度，提高可维护性，出了问题方便查找) 这里注册了的活动：
     * 1.能通过OperateActivityServiceActor.getCurActivityId(int activityType)获得当前活动
     * 2.可监听OperateActivityEvent事件，对活动开启、关闭和重置
     */
    public static void registerOpCheck() {
        registerOpCheck(OperateActivityConstant.ActType_OnlineReward, "");
        registerOpCheck(OperateActivityConstant.ActType_RetrieveReward, "");
        registerOpCheck(OperateActivityConstant.ActType_WordExchange, "");
        registerOpCheck(OperateActivityConstant.ActType_SevenDayGoal, "");
        registerOpCheck(OperateActivityConstant.ActType_NewServerRank, "");
        registerOpCheck(OperateActivityConstant.ActType_NewServerSign, "");
        registerOpCheck(OperateActivityConstant.ActType_NewServerMoney, "");
        registerOpCheck(OperateActivityConstant.ActType_NewServerFightScore, "");
        registerOpCheck(OperateActivityConstant.ActType_NewServerFamilyFightScore, "");
        registerOpCheck(OperateActivityConstant.ActType_NewServerFightScore0, "");
        registerOpCheck(OperateActivityConstant.ActType_ChargeBack, "");
        registerOpCheck(OperateActivityConstant.ActType_ServerFund, "");
        registerOpCheck(OperateActivityConstant.ActType_MonthCard, "");
        registerOpCheck(OperateActivityConstant.ActType_DailyCharge, "");
        registerOpCheck(OperateActivityConstant.ActType_EverydayCharge, "");
        registerOpCheck(OperateActivityConstant.ActType_GiftCome520, "");
        registerOpCheck(OperateActivityConstant.ActType_BestCP520, SConst.OpBestCpService);
        registerOpCheck(OperateActivityConstant.ActType_DragBoat, SConst.OpDragonBoatService);
        registerOpCheck(OperateActivityConstant.ActType_ChargeScore, "");
        registerOpCheck(OperateActivityConstant.ActType_KickBack, "");
        registerOpCheck(OperateActivityConstant.ActType_TencentVideo, "");
        registerOpCheck(OperateActivityConstant.ActType_WeeklyCharge, "");
        registerOpCheck(OperateActivityConstant.ActType_ChargeGift, "");
        registerOpCheck(OperateActivityConstant.ActType_Quwudu, "");
    }

    public static void registerOpCheck(int opType, String serviceName) {
        if (opCheckMap.containsKey(opType)) {
            LogUtil.info("OperateActivityManager.opCheckMap operateType is already exist");
            return;
        }
        opCheckMap.put(opType, serviceName);
    }

    public static Map<Integer, String> getOpCheckMap() {
        return opCheckMap;
    }

    /**
     * 活动显示控制注册 在这里注册，然后module再实现OpActivityModule接口
     * 实现getCurShowActivityId方法，控制是否要显示这个活动 实现getIsShowLabel()方法，控制是否要显示活动页签
     */
    public static void registerOpMoudle() {
    }

    public static void registerOpModule(int opType, String moudleName) {
        if (opMoudleNameMap.containsKey(opType)) {
            LogUtil.info("OperateActivityManager.registerModule operateType is already exist");
            return;
        }
        opMoudleNameMap.put(opType, moudleName);
    }

    public static Map<Integer, String> getOpMoudleNameMap() {
        return opMoudleNameMap;
    }

    public static void setCurActivityIds(ConcurrentHashMap<Integer, Integer> value) {
        curActivityIds = value;
    }

    /**
     * 只有走活动时间控制机制的活动，才需要用到
     */
    public static int getCurActivityId(int activityType) {
        if (curActivityIds.containsKey(activityType)) {
            return curActivityIds.get(activityType);
        } else {
            return -1;
        }
    }
}
