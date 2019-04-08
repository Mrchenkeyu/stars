package com.stars.modules.role.userdata;import com.stars.core.attr.Attribute;import com.stars.db.DBUtil;import com.stars.db.DbRow;import com.stars.db.SqlUtil;import com.stars.modules.role.RoleManager;import com.stars.modules.scene.SceneManager;import com.stars.modules.tool.ToolManager;import com.stars.util.StringUtil;import java.sql.SQLException;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;/** * Created by liuyuheng on 2016/6/16. */public class Role extends DbRow {    private long roleId;//    private int sex;//    private String name;//    private int level;//    private int jobId;    private int safeStageId;// 所在场景Id(只记录安全区场景)    private int[] position = new int[3]; // 记录角色在场景中的位置（只用于安全区）    private int titleId;// 使用称号Id    private int[] resource;    private String rewardIds;// 战力奖励领取Id记录    private long vigorRecoveryTimestamp; // 体力恢复时间    private int vigorBuyCount; // 体力购买次数    private String reviveInfo;// 已复活次数,stageType=次数,格式:1=1;2=2;    private byte freeBuyMoneyCount;// 免费购买金币次数    private byte payBuyMoneyCount;// 付费购买金币次数    private int rideLevelId;//坐骑等级Id,对应ridelevel表    private int dailyThrowGoldCount;    //家族篝火每日投元宝次数    private long createTime = System.currentTimeMillis();//角色创建时间,创角色的时候时间下发    private String lastEnterWeddingSceneId;    private int feats;//功勋    private int reputation;//声望    private int babyStage;//宝宝阶段;    private int babyLevel;//宝宝等级    private long babyEnergyRecTimestamp;//宝宝精力恢复时间    private int curFashionCardId;    /* 内存数据 */    private Attribute totalAttr = new Attribute();// 总属性    private Map<String, Attribute> attrMap = new HashMap<>();// 各部分属性 key-attr    private int fightScore;// 总战力    private Map<String, Integer> fightScoreMap = new HashMap<>();// 各部分战力    private List<Integer> rewardIdList = new ArrayList<>();// 已领取战力奖励Id    private int vigorMax; // 最大体力    private Map<Byte, Byte> reviveMap = new HashMap<>();// 已复活次数,<stageType, number>    public Role() {        resource = new int[RoleManager.MaxRoleResourceCount];    }    public Role(long roleId) throws SQLException {        String roleSelectSql = "select * from `role` where `roleid`=" + roleId;        // 装备        Role rowRole = DBUtil.queryBean(DBUtil.DB_USER, Role.class, roleSelectSql);        this.roleId = roleId;        if (rowRole != null) {            this.sex = rowRole.getSex();            this.name = rowRole.getName();            this.level = rowRole.getLevel();            this.jobId = rowRole.getJobId();        } else {            this.sex = 1;            this.name = "测试名字" + this.roleId;            this.level = RoleManager.ROLE_LEVEL_INIT;            this.jobId = 1;        }        resource = new int[RoleManager.MaxRoleResourceCount];        safeStageId = SceneManager.initSafeStageId;        titleId = 0;        this.rewardIds = "";        vigorBuyCount = 0;        reviveInfo = "";//        setInsertStatus();    }    public void addExp(int addValue) {        resource[ToolManager.EXP - 1] = resource[ToolManager.EXP - 1] + addValue;        int needExp = RoleManager.getRequestExpByJobLevel(jobId, level + 1);        while (resource[ToolManager.EXP - 1] >= needExp) {            // 达到最大等级            if (needExp == -1) {                resource[ToolManager.EXP - 1] = 0;                break;            }            level = level + 1;// 升级            resource[ToolManager.EXP - 1] = resource[ToolManager.EXP - 1] - needExp;            needExp = RoleManager.getRequestExpByJobLevel(jobId, level + 1);        }//        setUpdateStatus();    }    @Override    public String getChangeSql() {        return SqlUtil.getSql(this, DBUtil.DB_USER, "role", " roleid='" + this.getRoleId() + "'");    }    @Override    public String getDeleteSql() {        return SqlUtil.getDeleteSql("role", " roleid='" + this.getRoleId() + "'");    }    /**     * 获得总属性     *     * @return     */    public Attribute getTotalAttr() {        return totalAttr;    }    public void setTotalAttr(Attribute totalAttr) {        this.totalAttr = totalAttr;        this.totalAttr.setMaxhp(this.totalAttr.getHp());    }    /**     * 增加奖励记录,调用前必须经过判断     *     * @param rewardId     */    public void addRewardId(int rewardId) {        rewardIdList.add(rewardId);        this.rewardIds = StringUtil.isEmpty(rewardIds) ? "" + rewardId : rewardIds + "," + rewardId;    }    /**     * 判断奖励Id是否领取过     *     * @param rewardId     * @return     */    public boolean isRewarded(int rewardId) {        return rewardIdList.contains(rewardId);    }    /**     * 根据副本类型获得复活次数     *     * @param stageType     * @return     */    public byte getReviveNum(byte stageType) {        return reviveMap.containsKey(stageType) ? reviveMap.get(stageType) : 0;    }    /**     * 增加已复活次数     *     * @param stageType     */    public void addReviveNum(byte stageType) {        if (!reviveMap.containsKey(stageType)) {            reviveMap.put(stageType, (byte) 0);        }        reviveMap.put(stageType, (byte) (reviveMap.get(stageType) + 1));        reviveNumToString();    }    /**     * 直接重置某个场景类型的复活次数;     *     * @param stageType     */    public void resetReviveNum(byte stageType) {        byte reviveNum = getReviveNum(stageType);        if (reviveNum != 0) {            if (!reviveMap.containsKey(stageType)) {                reviveMap.put(stageType, (byte) 0);            } else {                reviveMap.put(stageType, (byte) 0);            }            reviveNumToString();        }    }    public void resetReviveNum() {        for (byte stageType : reviveMap.keySet()) {            reviveMap.put(stageType, (byte) 0);        }        reviveNumToString();    }    private void reviveNumToString() {        StringBuilder builder = new StringBuilder("");        for (Map.Entry<Byte, Byte> entry : reviveMap.entrySet()) {            builder.append(entry.getKey())                    .append("=")                    .append(entry.getValue())                    .append(";");        }        int index = builder.lastIndexOf(";");        if (index != -1) {            builder.deleteCharAt(index);        }        this.reviveInfo = builder.toString();    }    public List<Integer> getRewardIdList() {        return rewardIdList;    }    public long getRoleId() {        return roleId;    }    public void setRoleId(long roleId) {        this.roleId = roleId;    }    public int getSex() {        return sex;    }    public void setSex(int sex) {        this.sex = sex;    }    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    public int getLevel() {        return level;    }    public void setLevel(int level) {        this.level = level;    }    public int getExp() {        return resource[ToolManager.EXP - 1];    }    public void setExp(int exp) {        this.resource[ToolManager.EXP - 1] = exp;    }    public int getJobId() {        return jobId;    }    public void setJobId(int jobId) {        this.jobId = jobId;    }    public void setGold(int gold) {        resource[ToolManager.GOLD - 1] = gold;    }    public void setMoney(int money) {        resource[ToolManager.MONEY - 1] = money;    }    public void setBandGold(int bandGold) {        resource[ToolManager.BANDGOLD - 1] = bandGold;    }    public void setVigor(int vigor) {        resource[ToolManager.VIGOR - 1] = vigor;    }    public int getGold() {        return resource[ToolManager.GOLD - 1];    }    public int getMoney() {        return resource[ToolManager.MONEY - 1];    }    public int getBandGold() {        return resource[ToolManager.BANDGOLD - 1];    }    public void setBabyEnergy(int babyEnergy) {        resource[ToolManager.BABY_ENERGY - 1] = babyEnergy;    }    public int getBabyEnergy() {        return resource[ToolManager.BABY_ENERGY - 1];    }    public int getVigor() {        return resource[ToolManager.VIGOR - 1];    }    public void setSkillPoints(int skillPoints) {        resource[ToolManager.SKILLPOINTS - 1] = skillPoints;    }    public int getSkillPoints() {        return resource[ToolManager.SKILLPOINTS - 1];    }    public int getFeats() {        return resource[ToolManager.FEATS - 1];    }    public void setFeats(int feats) {        resource[ToolManager.FEATS - 1] = feats;    }    public int getReputation() {        return resource[ToolManager.REPUTATION - 1];    }    public void setReputation(int reputation) {        resource[ToolManager.REPUTATION - 1] = reputation;    }    public void addResource(byte resourceId, int count) {        resource[resourceId - 1] = Math.max(0, resource[resourceId - 1] + count);    }    public int getResource(byte resourceId) {        return resource[resourceId - 1];    }    public int getSafeStageId() {        return safeStageId;    }    public void setSafeStageId(int safeStageId) {        this.safeStageId = safeStageId;    }    public int getFightScore() {        return fightScore;    }    public void setFightScore(int fightScore) {        this.fightScore = fightScore;    }    public int getTitleId() {        return titleId;    }    public void setTitleId(int titleId) {        this.titleId = titleId;    }    public String getLastEnterWeddingSceneId() {        return lastEnterWeddingSceneId;    }    public void setLastEnterWeddingSceneId(String lastEnterWeddingSceneId) {        this.lastEnterWeddingSceneId = lastEnterWeddingSceneId;    }    public Map<String, Attribute> getAttrMap() {        return attrMap;    }    public Map<String, Integer> getFightScoreMap() {        return fightScoreMap;    }    public String getRewardIds() {        return rewardIds;    }    public void setRewardIds(String rewardIds) throws Exception {        this.rewardIds = rewardIds;        if (StringUtil.isEmpty(rewardIds)) {            this.rewardIds = "";            return;        }//        rewardIdList = StringUtil.parseArrayIntList(rewardIds, ",");        rewardIdList = StringUtil.toArrayList(rewardIds, Integer.class, ',');    }    public long getVigorRecoveryTimestamp() {        return vigorRecoveryTimestamp;    }    public void setVigorRecoveryTimestamp(long vigorRecoveryTimestamp) {        this.vigorRecoveryTimestamp = vigorRecoveryTimestamp;    }    public int getVigorBuyCount() {        return vigorBuyCount;    }    public void setVigorBuyCount(int vigorBuyCount) {        this.vigorBuyCount = vigorBuyCount;    }    public int getVigorMax() {        return vigorMax;    }    public void setVigorMax(int vigorMax) {        this.vigorMax = vigorMax;    }    public String getPositionStr() {        Integer[] temp = new Integer[3];        temp[0] = position[0];        temp[1] = position[1];        temp[2] = position[2];        return StringUtil.makeString(temp, '+');    }    public void setPositionStr(String p) {        if (StringUtil.isEmpty(p)) {            position[0] = 0;            position[1] = 0;            position[2] = 0;        } else {            String[] arrP = p.split("[+]");            position[0] = Integer.valueOf(arrP[0]);            position[1] = Integer.valueOf(arrP[1]);            position[2] = Integer.valueOf(arrP[2]);        }    }    public int[] getPosition() {        return position;    }    /**     * 二级资源数据库存储内容     */    public String getOtherres() {        StringBuffer bf = new StringBuffer();        for (int i = 5; i < resource.length; i++) {            if (bf.length() > 0) {                bf.append("&");            }            bf.append(resource[i]);        }        return bf.toString();    }    public void setOtherres(String otherres) {        if (otherres == null || otherres.equals("")) {            return;        }        String s[] = otherres.split("[&]");        int index = 5;        for (String ss : s) {            resource[index] = Integer.parseInt(ss);            index++;        }    }    public String getReviveInfo() {        return reviveInfo;    }    public void setReviveInfo(String reviveInfo) throws Exception {        this.reviveInfo = reviveInfo;        if (StringUtil.isEmpty(reviveInfo)) {            this.reviveInfo = "";            return;        }        reviveMap = StringUtil.toMap(reviveInfo, Byte.class, Byte.class, '=', ';');    }    public Map<Byte, Byte> getReviveMap() {        return reviveMap;    }    public int getGloryPoints() {        return resource[ToolManager.GLORYPOINTS - 1];    }    public byte getFreeBuyMoneyCount() {        return freeBuyMoneyCount;    }    public void setFreeBuyMoneyCount(byte freeBuyMoneyCount) {        this.freeBuyMoneyCount = freeBuyMoneyCount;    }    public byte getPayBuyMoneyCount() {        return payBuyMoneyCount;    }    public void setPayBuyMoneyCount(byte payBuyMoneyCount) {        this.payBuyMoneyCount = payBuyMoneyCount;    }    public int getRideLevelId() {        return rideLevelId;    }    public void setRideLevelId(int rideLevelId) {        this.rideLevelId = rideLevelId;    }    public long getCreateTime() {        return createTime;    }    public void setCreateTime(long createTime) {        this.createTime = createTime;    }    public int getDailyThrowGoldCount() {        return dailyThrowGoldCount;    }    public void setDailyThrowGoldCount(int dailyThrowGoldCount) {        this.dailyThrowGoldCount = dailyThrowGoldCount;    }    public void addDailyThrowGoldCount() {        this.dailyThrowGoldCount++;    }    public int getBabyStage() {        return babyStage;    }    public void setBabyStage(int babyStage) {        this.babyStage = babyStage;    }    public int getBabyLevel() {        return babyLevel;    }    public void setBabyLevel(int babyLevel) {        this.babyLevel = babyLevel;    }    public long getBabyEnergyRecTimestamp() {        return babyEnergyRecTimestamp;    }    public void setBabyEnergyRecTimestamp(long babyEnergyRecTimestamp) {        this.babyEnergyRecTimestamp = babyEnergyRecTimestamp;    }    public int getCurFashionCardId() {        return curFashionCardId;    }    public void setCurFashionCardId(int curFashionCardId) {        this.curFashionCardId = curFashionCardId;    }}