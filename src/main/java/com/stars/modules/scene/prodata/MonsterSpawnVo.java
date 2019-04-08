package com.stars.modules.scene.prodata;import com.stars.modules.scene.SceneManager;import com.stars.network.server.buffer.NewByteBuffer;import com.stars.util.LogUtil;import com.stars.util.StringUtil;import java.util.*;/** * Created by liuyuheng on 2016/7/5. */public class MonsterSpawnVo {    private int monsterSpawnId;//    private String spawnCondition;// 刷怪条件:1=0:无条件刷;2=坐标参数:坐标触发刷    private String spawnDelay;// 延迟刷出时间    private List<Integer> spawnDelayList;    private int nextSpawnId;// 下一刷怪组Id    private String nextCondition;// 下一组刷新条件:0+0:全部死亡;1+n:剩余n个    private String monsterGroup;//    private String awake;// 激活条件    private String destroymonster;//销毁怪物    /* 内存数据 */    private byte spawnType;// 刷怪条件类型,1=无条件,2=区域刷怪    private String conditionParam;// 刷怪条件参数    private List<MonsterAttributeVo> monsterAttrList = new LinkedList<>();// 怪物属性list    private byte nextConType;// 下一波刷怪条件类型,0=全部死亡;1=指定数量死亡    private String nextConParam;    private List<Integer> destroymonsterIdList = new ArrayList<Integer>();    public void writeToBuff(NewByteBuffer buff) {        buff.writeInt(monsterSpawnId);        buff.writeString(conditionParam);    }    /**     * 获取怪物的类型和数量的字符串;     * @return     */    public String getMonsterTypeCountStr(){        StringBuilder sb = new StringBuilder();        HashMap<Byte, Integer> monsterTypeCountMap = new HashMap<>();        byte monsterType = 0;        for(int i = 0, len = monsterAttrList.size(); i<len; i++){            monsterType = monsterAttrList.get(i).getMonsterVo().getType();            if(!monsterTypeCountMap.containsKey(monsterType)){                monsterTypeCountMap.put(monsterType, 1);            }else{                monsterTypeCountMap.put(monsterType, monsterTypeCountMap.get(monsterType)+1);            }        }        Set<Byte> keySets = monsterTypeCountMap.keySet();        int length = keySets.size();        int index = 0;        for(Byte bkey : keySets){            sb.append(bkey);            sb.append("+");            sb.append(monsterTypeCountMap.get(bkey));            if(index + 1 < length){                sb.append(",");            }            index++;        }        return sb.toString();    }    public byte getSpawnType() {        return spawnType;    }    public void setSpawnType(byte spawnType) {        this.spawnType = spawnType;    }    public List<MonsterAttributeVo> getMonsterAttrList() {        return monsterAttrList;    }    public void setMonsterAttrList(List<MonsterAttributeVo> monsterAttrList) {        this.monsterAttrList = monsterAttrList;    }    public int getMonsterSpawnId() {        return monsterSpawnId;    }    public void setMonsterSpawnId(int monsterSpawnId) {        this.monsterSpawnId = monsterSpawnId;    }    public String getSpawnCondition() {        return spawnCondition;    }    public void setSpawnCondition(String spawnCondition) {        this.spawnCondition = spawnCondition;        if (StringUtil.isEmpty(spawnCondition) || "0".equals(spawnCondition)) {            return;        }        String[] temp = spawnCondition.split("\\|");        spawnType = Byte.parseByte(temp[0]);        if (spawnType == SceneManager.SPAWNTYPE_AREA) {            this.conditionParam = temp[1];        }    }    public String getSpawnDelay() {        return spawnDelay;    }    public void setSpawnDelay(String spawnDelay) {        this.spawnDelay = spawnDelay;        this.spawnDelayList = new ArrayList<>();        if (StringUtil.isEmpty(spawnDelay) || "0".equals(spawnDelay)) {            return;        }        String[] array = spawnDelay.split("\\+");        for(String str:array){            spawnDelayList.add(Integer.parseInt(str));        }    }    public int getSpawnDelayByIndex(int index){        if(StringUtil.isEmpty(spawnDelayList)) return 0;        if(index >= spawnDelayList.size() || index < 0) return spawnDelayList.get(0);        return spawnDelayList.get(index);    }    public String getMonsterGroup() {        return monsterGroup;    }    public void setMonsterGroup(String monsterGroup) throws Exception {        this.monsterGroup = monsterGroup;        if (StringUtil.isEmpty(monsterGroup) || "0".equals(monsterGroup)) {            return;        }//        for (int stageMonsterId : StringUtil.parseArrayIntList(monsterGroup, "\\+")) {        for (int stageMonsterId : StringUtil.toArrayList(monsterGroup, Integer.class, '+')) {            MonsterAttributeVo monsterAttrVo = SceneManager.getMonsterAttrVo(stageMonsterId);            if (monsterAttrVo != null) {                monsterAttrList.add(monsterAttrVo);            } else {                LogUtil.error("找不到monsterattribute数据id=" + stageMonsterId, new IllegalArgumentException());            }        }    }    public String getNextCondition() {        return nextCondition;    }    public void setNextCondition(String nextCondition) {        this.nextCondition = nextCondition;        if (StringUtil.isEmpty(nextCondition) || "0".equals(nextCondition)) {            return;        }        String[] temp = nextCondition.split("\\+");        nextConType = Byte.parseByte(temp[0]);        nextConParam = temp[1];    }    public int getNextSpawnId() {        return nextSpawnId;    }    public void setNextSpawnId(int nextSpawnId) {        this.nextSpawnId = nextSpawnId;    }    public String getAwake() {        return awake;    }    public void setAwake(String awake) {        this.awake = awake;    }        public String getDestroyMonster() {        return destroymonster;    }    public void setDestroyMonster(String destroymonster) {        this.destroymonster = destroymonster;                if (destroymonster == null || destroymonster.equals("") || destroymonster.equals("0")) {			return;		}		String sts[] = destroymonster.split("\\+");		for (String str : sts) {			destroymonsterIdList.add(Integer.parseInt(str));		}    }    public byte getNextConType() {        return nextConType;    }    public String getNextConParam() {        return nextConParam;    }        public List<Integer> getDestroyMonsterIdList(){    	return destroymonsterIdList;    }}