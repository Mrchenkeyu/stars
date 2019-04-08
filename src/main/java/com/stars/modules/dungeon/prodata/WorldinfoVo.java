package com.stars.modules.dungeon.prodata;import com.stars.network.server.buffer.NewByteBuffer;import java.util.HashMap;import java.util.Map;/** * Created by liuyuheng on 2016/6/21. */public class WorldinfoVo {    private int worldId;// '章节列表id'    private int rank;// '排序字段'    private String title;// '章节序号名'    private String worldImage;// '章节背景'    private String name;// '章节名'    private byte sort;// '章节分类,普通填1;精英填2'    private String starAward;// '集星奖励'    private short reqStar;// '集星需求'    private String worldAward;// '章节产出显示'    /* 内存数据 */    private Map<Integer,Integer> starRewardMap = new HashMap<>();    private int starGroupId = 0;//集星奖励的groupId    public void writeToBuff(NewByteBuffer buff){        buff.writeInt(worldId);        buff.writeInt(rank);        buff.writeString(title);        buff.writeString(worldImage);        buff.writeString(name);        buff.writeByte(sort);        buff.writeString(starAward);        buff.writeShort(reqStar);        buff.writeString(worldAward);    }    public Map<Integer, Integer> getStarRewardMap() {        return starRewardMap;    }    public int getWorldId() {        return worldId;    }    public void setWorldId(int worldId) {        this.worldId = worldId;    }    public int getRank() {        return rank;    }    public void setRank(int rank) {        this.rank = rank;    }    public String getTitle() {        return title;    }    public void setTitle(String title) {        this.title = title;    }    public String getWorldImage() {        return worldImage;    }    public void setWorldImage(String worldImage) {        this.worldImage = worldImage;    }    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    public byte getSort() {        return sort;    }    public void setSort(byte sort) {        this.sort = sort;    }    public String getStarAward() {        return starAward;    }    public void setStarAward(String starAward) throws Exception {//        this.starAward = starAward;//        if (StringUtil.isEmpty(starAward) || "0".equals(starAward)) {//            return;//        }////        starRewardMap = StringUtil.parseStrToMap(starAward, "=", HashMap.class);//        starRewardMap = StringUtil.toMap(starAward, Integer.class, Integer.class, '=', '|');    	    	this.starAward = starAward;    	if (starAward == null || starAward.equals("") || starAward.equals("0")) {			return;		}    	    	this.starGroupId = Integer.parseInt(starAward);    }    public short getReqStar() {        return reqStar;    }    public void setReqStar(short reqStar) {        this.reqStar = reqStar;    }    public String getWorldAward() {        return worldAward;    }    public void setWorldAward(String worldAward) {        this.worldAward = worldAward;    }        public int getStarGroupId(){    	return this.starGroupId;    }}