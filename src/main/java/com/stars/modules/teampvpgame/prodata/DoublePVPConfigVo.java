package com.stars.modules.teampvpgame.prodata;/** * Created by liuyuheng on 2016/12/20. */public class DoublePVPConfigVo {    private byte type;// '类型;1=本服配置,2=跨服配置'    private String signTime;// '报名时间段'    private String scoreBattleOpen;// '积分赛时间段'    private String teamBattleOpen;// '小组赛时间段'    private String finalFourBattleOpen;// '四强赛时间段'    private String competitionReset;// '比武大会的重置时间点'    private int remainInfoNum;// '比赛信息的保留场次'    private int scoreStageTime;// '积分赛的单场战斗时间'    private int teamStageTime;// '小组赛的单场战斗时间'    private int finalFourBattleTime;// '四强赛的单场战斗时间'    public byte getType() {        return type;    }    public void setType(byte type) {        this.type = type;    }    public String getSignTime() {        return signTime;    }    public void setSignTime(String signTime) {        this.signTime = signTime;    }    public String getScoreBattleOpen() {        return scoreBattleOpen;    }    public void setScoreBattleOpen(String scoreBattleOpen) {        this.scoreBattleOpen = scoreBattleOpen;    }    public String getTeamBattleOpen() {        return teamBattleOpen;    }    public void setTeamBattleOpen(String teamBattleOpen) {        this.teamBattleOpen = teamBattleOpen;    }    public String getFinalFourBattleOpen() {        return finalFourBattleOpen;    }    public void setFinalFourBattleOpen(String finalFourBattleOpen) {        this.finalFourBattleOpen = finalFourBattleOpen;    }    public String getCompetitionReset() {        return competitionReset;    }    public void setCompetitionReset(String competitionReset) {        this.competitionReset = competitionReset;    }    public int getRemainInfoNum() {        return remainInfoNum;    }    public void setRemainInfoNum(int remainInfoNum) {        this.remainInfoNum = remainInfoNum;    }    public int getScoreStageTime() {        return scoreStageTime;    }    public void setScoreStageTime(int scoreStageTime) {        this.scoreStageTime = scoreStageTime;    }    public int getTeamStageTime() {        return teamStageTime;    }    public void setTeamStageTime(int teamStageTime) {        this.teamStageTime = teamStageTime;    }    public int getFinalFourBattleTime() {        return finalFourBattleTime;    }    public void setFinalFourBattleTime(int finalFourBattleTime) {        this.finalFourBattleTime = finalFourBattleTime;    }}