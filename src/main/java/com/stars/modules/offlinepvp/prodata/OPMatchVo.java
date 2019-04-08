package com.stars.modules.offlinepvp.prodata;import com.stars.modules.offlinepvp.OfflinePvpManager;import com.stars.util.StringUtil;import java.util.HashMap;import java.util.Map;/** * Created by liuyuheng on 2016/9/30. */public class OPMatchVo {    private int matchId;// '匹配id'    private int jobId;// '职业id'    private String levelRange;// '角色等级范围'    private String selfFight;// '角色战力范围'    private String matchFight;// '匹配战力范围'    private String enhanceBuff;// '战力成长buff'    /* 内存数据 */    private int[] levelArray;// 角色等级范围    private int[] fightScoreArray;// 角色战力范围    private Map<Byte, int[]> matchMap = new HashMap<>();// 匹配战力范围, <index, [min, max]>    private int[] buffCondtion;// 获得buff条件:[1]=战力成长百分比;[2]=充值金额    private Map<Integer, Integer> growBuffMap = new HashMap<>();// 成长buff,<buffId,level>    public int getLevelMin() {        return levelArray[0];    }    public int getLevelMax() {        return levelArray[1];    }    public int getFightScoreMin() {        return fightScoreArray[0];    }    public int getFightScoreMax() {        return fightScoreArray[1];    }    public int[] getMatchFightScore(byte index) {        return matchMap.get(index);    }    public int getBuffCondPercent() {        return buffCondtion[0];    }    public int getBuffCondCharge() {        return buffCondtion[1];    }    public Map<Integer, Integer> getGrowBuffMap() {        return growBuffMap;    }    public int getMatchId() {        return matchId;    }    public void setMatchId(int matchId) {        this.matchId = matchId;    }    public int getJobId() {        return jobId;    }    public void setJobId(int jobId) {        this.jobId = jobId;    }    public String getLevelRange() {        return levelRange;    }    public void setLevelRange(String levelRange) throws Exception {        this.levelRange = levelRange;        if (StringUtil.isEmpty(levelRange) || "0".equals(levelRange)) {            throw new IllegalArgumentException("offlinematch表levelrange字段配置格式错误");        }        int[] array = StringUtil.toArray(levelRange, int[].class, '+');        if (array[0] > array[1])            throw new IllegalArgumentException("offlinematch表levelrange字段配置格式错误");        levelArray = array;    }    public String getSelfFight() {        return selfFight;    }    public void setSelfFight(String selfFight) throws Exception {        this.selfFight = selfFight;        if (StringUtil.isEmpty(selfFight) || "0".equals(selfFight)) {            throw new IllegalArgumentException("offlinematch表selffight字段配置格式错误");        }        int[] array = StringUtil.toArray(selfFight, int[].class, '+');        if (array[0] > array[1])            throw new IllegalArgumentException("offlinematch表selffight字段配置格式错误");        fightScoreArray = array;    }    public String getMatchFight() {        return matchFight;    }    public void setMatchFight(String matchFight) throws Exception {        this.matchFight = matchFight;        if (StringUtil.isEmpty(matchFight) || "0".equals(matchFight)) {            return;        }        Map<Byte, int[]> map = new HashMap<>();        String[] matchs = matchFight.split(",");        byte index = 1;        for (String temp : matchs) {            int[] delta = StringUtil.toArray(temp, int[].class, '+');            if (delta[0] > delta[1]) {                throw new IllegalArgumentException("offlinematch表matchfight字段配置格式错误");            }            map.put(index, delta);            index++;        }        if (matchs.length != OfflinePvpManager.matchEnemyNum)            throw new IllegalArgumentException("offlinematch表matchfight字段配置与commondefine匹配对手个数不一致");        matchMap = map;    }    public String getEnhanceBuff() {        return enhanceBuff;    }    public void setEnhanceBuff(String enhanceBuff) throws Exception {        this.enhanceBuff = enhanceBuff;        if (StringUtil.isEmpty(enhanceBuff) || "0".equals(enhanceBuff)) {            return;        }        int[] temp = StringUtil.toArray(enhanceBuff, int[].class, '+');        buffCondtion = new int[]{temp[0], temp[1]};        Map<Integer, Integer> map = new HashMap<>();        map.put(temp[2], temp[3]);        growBuffMap = map;    }}