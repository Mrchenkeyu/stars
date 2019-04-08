package com.stars.modules.buddy.prodata;import com.stars.core.attr.Attribute;import com.stars.network.server.buffer.NewByteBuffer;import java.util.HashMap;import java.util.Map;/** * Created by liuyuheng on 2016/8/5. */public class BuddyLevelVo {    private int buddyId;// '伙伴id'    private int level;// '等级'    private int reqExp;// '升级所需经验'    private int reqRoleLv;// '所需角色等级'    private int hp;// '生命'    private int attack;// '攻击'    private int defense;// '防御'    private int hit;// '命中'    private int avoid;// '闪避'    private int crit;// '暴击'    private int anticrit;// '抗暴'    /* 内存数据 */    private Attribute attribute = new Attribute();    private Map<Integer, Integer> reqItemMap = new HashMap<>();// 升级消耗    public void writeToBuff(NewByteBuffer buff) {        buff.writeInt(level);        buff.writeInt(reqExp);        buff.writeInt(reqRoleLv);        buff.writeInt(hp);        buff.writeInt(attack);        buff.writeInt(defense);        buff.writeInt(hit);        buff.writeInt(avoid);        buff.writeInt(crit);        buff.writeInt(anticrit);    }    public Attribute getAttribute() {        return attribute;    }    public Map<Integer, Integer> getReqItemMap() {        return reqItemMap;    }    public int getBuddyId() {        return buddyId;    }    public void setBuddyId(int buddyId) {        this.buddyId = buddyId;    }    public int getLevel() {        return level;    }    public void setLevel(int level) {        this.level = level;    }    public int getReqExp() {        return reqExp;    }    public void setReqExp(int reqExp) {        this.reqExp = reqExp;    }    public int getReqRoleLv() {        return reqRoleLv;    }    public void setReqRoleLv(int reqRoleLv) {        this.reqRoleLv = reqRoleLv;    }    public int getHp() {        return hp;    }    public void setHp(int hp) {        this.hp = hp;        attribute.setHp(hp);        attribute.setMaxhp(hp);    }    public int getAttack() {        return attack;    }    public void setAttack(int attack) {        this.attack = attack;        attribute.setAttack(attack);    }    public int getDefense() {        return defense;    }    public void setDefense(int defense) {        this.defense = defense;        attribute.setDefense(defense);    }    public int getHit() {        return hit;    }    public void setHit(int hit) {        this.hit = hit;        attribute.setHit(hit);    }    public int getAvoid() {        return avoid;    }    public void setAvoid(int avoid) {        this.avoid = avoid;        attribute.setAvoid(avoid);    }    public int getCrit() {        return crit;    }    public void setCrit(int crit) {        this.crit = crit;        attribute.setCrit(crit);    }    public int getAnticrit() {        return anticrit;    }    public void setAnticrit(int anticrit) {        this.anticrit = anticrit;        attribute.setAnticrit(anticrit);    }}