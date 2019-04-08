package com.stars.modules.offlinepvp.prodata;import com.stars.network.server.buffer.NewByteBuffer;import com.stars.util.StringUtil;import java.util.HashMap;import java.util.LinkedHashMap;import java.util.Map;/** * Created by liuyuheng on 2016/9/30. */public class OPRewardVo {    private int level;// '玩家等级'    private String reward;// '奖励物品'    private String onceReward;// '单次挑战成功奖励'    /* 内存数据 */    private Map<Byte, Map<Integer, Integer>> rewardMap = new LinkedHashMap<>();// 胜利次数奖励, <sucNum, <itemId, num>>    private Map<Integer, Integer> onceRewardMap = new HashMap<>();// 每次胜利奖励,<itemId, num>    public void writeToBuff(NewByteBuffer buff) {        buff.writeString(reward);// '奖励物品'        buff.writeString(onceReward);// '单次挑战成功奖励'    }    public Map<Integer, Integer> getRewardMap(byte index) {        return rewardMap.get(index);    }    public Map<Byte, Map<Integer, Integer>> getRewardMap() {        return rewardMap;    }    public Map<Integer, Integer> getOnceRewardMap() {        return onceRewardMap;    }    public int getLevel() {        return level;    }    public void setLevel(int level) {        this.level = level;    }    public String getReward() {        return reward;    }    public void setReward(String reward) throws Exception {        this.reward = reward;        if (StringUtil.isEmpty(reward) || "0".equals(reward))            return;        Map<Byte, Map<Integer, Integer>> map = new LinkedHashMap<>();        for (String temp : reward.split("\\|")) {            String[] delta = temp.split(",");            byte sucNum = Byte.parseByte(delta[0]);            Map<Integer, Integer> rewardMap = map.get(sucNum);            if (rewardMap == null) {                rewardMap = new HashMap<>();                map.put(sucNum, rewardMap);            }            for (int i = 1; i < delta.length; i++) {                rewardMap.putAll(StringUtil.toMap(delta[i], Integer.class, Integer.class, '+', ','));            }        }        rewardMap = map;    }    public String getOnceReward() {        return onceReward;    }    public void setOnceReward(String onceReward) throws Exception {        this.onceReward = onceReward;        if (StringUtil.isEmpty(onceReward) || "0".equals(onceReward))            return;        onceRewardMap = StringUtil.toMap(onceReward, Integer.class, Integer.class, '+', '|');    }}