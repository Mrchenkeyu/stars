package com.stars.modules.mind.packet;

import com.stars.core.player.Player;
import com.stars.core.player.PlayerPacket;
import com.stars.modules.MConst;
import com.stars.modules.mind.MindModule;
import com.stars.modules.mind.MindPacketSet;

/**
 * 客户端请求心法表数据
 * Created by gaopeidian on 2016/9/23.
 */
public class ServerMindVo  extends PlayerPacket {
    @Override
    public void execPacket(Player player) {
    	MindModule mindModule = (MindModule)this.module(MConst.Mind);
    	mindModule.sendAllMindVo();
    }

    @Override
    public short getType() {
        return MindPacketSet.S_MIND_VO;
    }  
}
