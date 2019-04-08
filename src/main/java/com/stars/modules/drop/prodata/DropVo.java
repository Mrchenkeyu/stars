package com.stars.modules.drop.prodata;import com.stars.util.StringUtil;import java.util.*;/** * Created by liuyuheng on 2016/6/30. */public class DropVo {    public static final byte NORMAL_GROUP = 1;      //正常掉落组    public static final byte SPECIAL_GROUP = 2;     //特殊掉落组,需要进过职业、等级等条件的筛选    private int dropId;// '掉落id'    private int groupId;  //掉落组id    private byte groupType; //掉落组类型: 1=普通组，不判断职业等级 2=特殊组，需要进过职业、等级等条件的筛选    private int jobId;    private String levelRange;  //等级范围    private int minLevel;    private int maxLevel;    private String fightRange;    //战力范围    private int minFight;    private int maxFight;    private String showItem;    //展示道具    private Map<Integer,Integer> showItemMap;    private byte randType;// '随机类型'    private int repeat;// '重复次数'    private byte removeRepeat;// '是否去除抽样'    private String reward;// '奖励'    private String reportAward;    private int reportCode;    private int reportCount;    private String reportDesc;    private List<DropRewardVo> rewardList = new LinkedList<>();// 奖励list    private Set<Integer> reawardItemIdSet = new HashSet<>();// 掉落组可能获得的所有道具Id,用set去重    public List<DropRewardVo> getRewardList() {        return rewardList;    }    private void parse(String reward) {        if (StringUtil.isEmpty(reward) || reward.equals("0")) {            return;        }        String[] temp = reward.split("\\|");        for (String str : temp) {            rewardList.add(new DropRewardVo(str));        }    }    public int getDropId() {        return dropId;    }    public void setDropId(int dropId) {        this.dropId = dropId;    }    public byte getRandType() {        return randType;    }    public void setRandType(byte randType) {        this.randType = randType;    }    public int getRepeat() {        return repeat;    }    public void setRepeat(int repeat) {        this.repeat = repeat;    }    public byte getRemoveRepeat() {        return removeRepeat;    }    public void setRemoveRepeat(byte removeRepeat) {        this.removeRepeat = removeRepeat;    }    public String getReward() {        return reward;    }    public void setReward(String reward) {        this.reward = reward;        parse(reward);    }    public Set<Integer> getReawardItemIdSet() {        return reawardItemIdSet;    }    public void setReawardItemIdSet(Set<Integer> reawardItemIdSet) {        this.reawardItemIdSet = reawardItemIdSet;    }    public int getGroupId() {        return groupId;    }    public void setGroupId(int groupId) {        this.groupId = groupId;    }    public byte getGroupType() {        return groupType;    }    public void setGroupType(byte groupType) {        this.groupType = groupType;    }    public int getJobId() {        return jobId;    }    public void setJobId(int jobId) {        this.jobId = jobId;    }    public String getLevelRange() {        return levelRange;    }    public void setLevelRange(String levelRange) {        this.levelRange = levelRange;        if(StringUtil.isEmpty(levelRange) || "0".equals(levelRange)) return;        String[] arr = levelRange.split("\\+");        this.minLevel = Integer.parseInt(arr[0]);        this.maxLevel = Integer.parseInt(arr[1]);    }    public String getFightRange() {        return fightRange;    }    public void setFightRange(String fightRange) {        this.fightRange = fightRange;        if(StringUtil.isEmpty(fightRange) || "0".equals(fightRange)) return;        String[] arr = fightRange.split("\\+");        this.minFight = Integer.parseInt(arr[0]);        this.maxFight = Integer.parseInt(arr[1]);    }    /**     * 匹配所以条件     */    public boolean isMatchCondition(int roleJob,int roleLevel,int fighting){        if(groupType != SPECIAL_GROUP) return true;        return isMatchJob(roleJob) && isMatchLevel(roleLevel) && isMatchFightScore(fighting);    }    /**     * 匹配职业     */    public boolean isMatchJob(int roleJob){        if(groupType != SPECIAL_GROUP) return true;        if(jobId == 0) return true;        return roleJob == jobId;    }    /**     * 匹配等级     */    public boolean isMatchLevel(int roleLevel){        if(groupType != SPECIAL_GROUP) return true;        if(minLevel == 0 && maxLevel == 0) return true;        return minLevel <= roleLevel && maxLevel >= roleLevel;    }    /**     * 匹配战力     */    public boolean isMatchFightScore(int fighting){        if(groupType != SPECIAL_GROUP) return true;        if(minFight == 0 && maxFight == 0) return true;        return minFight <= fighting && maxFight >= fighting;    }    public String getShowItem() {        return showItem;    }    public void setShowItem(String showItem) {        this.showItem = showItem;        if(StringUtil.isEmpty(showItem) || "0".equals(showItem)) return;        this.showItemMap = StringUtil.toMap(showItem, Integer.class, Integer.class, '+', ',');    }    public Map<Integer, Integer> getShowItemMap() {        return showItemMap;    }    public void setShowItemMap(Map<Integer, Integer> showItemMap) {        this.showItemMap = showItemMap;    }    public String getReportDesc() {        return reportDesc;    }    public void setReportDesc(String reportDesc) {        this.reportDesc = reportDesc;    }    public int getReportCode() {        return reportCode;    }    public int getReportCount() {        return reportCount;    }    public String getReportAward() {        return reportAward;    }    public void setReportAward(String reportAward) {        this.reportAward = reportAward;        if(StringUtil.isEmpty(reportAward) || "0".equals(reportAward)) return;        String[] arr = reportAward.split("\\+");        reportCode = Integer.parseInt(arr[0]);        reportCount = Integer.parseInt(arr[1]);    }}