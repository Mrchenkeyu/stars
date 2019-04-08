package com.stars.modules.familyactivities.invade.prodata;import com.stars.util.LogUtil;import com.stars.util.StringUtil;/** * Created by liuyuheng on 2016/10/17. */public class FamilyInvadeVo {    private int invadeId;// '唯一id'    private String levelSection;// '等级分段'    private byte teamType;// '组队类型'    private byte monsterType;// '怪物难度'    private int stageId;// '战斗场景id'    private int award;// '奖励(指向掉落id)'    /* 内存数据 */    private int[] levelLimit;    public int[] getLevelLimit() {        return levelLimit;    }    public int getInvadeId() {        return invadeId;    }    public void setInvadeId(int invadeId) {        this.invadeId = invadeId;    }    public String getLevelSection() {        return levelSection;    }    public void setLevelSection(String levelSection) throws Exception {        this.levelSection = levelSection;        if (StringUtil.isEmpty(levelSection) || "0".equals(levelSection)) {            LogUtil.error("familyinvade表levelsection字段配置错误", new IllegalArgumentException());            levelLimit = new int[]{0, 0};            return;        }        this.levelLimit = StringUtil.toArray(levelSection, int[].class, '+');    }    public byte getTeamType() {        return teamType;    }    public void setTeamType(byte teamType) {        this.teamType = teamType;    }    public byte getMonsterType() {        return monsterType;    }    public void setMonsterType(byte monsterType) {        this.monsterType = monsterType;    }    public int getStageId() {        return stageId;    }    public void setStageId(int stageId) {        this.stageId = stageId;    }    public int getAward() {        return award;    }    public void setAward(int award) {        this.award = award;    }}