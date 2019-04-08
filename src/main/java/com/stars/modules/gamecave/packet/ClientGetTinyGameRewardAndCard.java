package com.stars.modules.gamecave.packet;

import com.stars.core.player.Player;
import com.stars.core.player.PlayerPacket;
import com.stars.modules.gamecave.GameCavePacketSet;
import com.stars.network.server.buffer.NewByteBuffer;

/**
 * Created by gaopeidian on 2016/6/21.
 */
public class ClientGetTinyGameRewardAndCard extends PlayerPacket {
	private byte reslut;
	
    public ClientGetTinyGameRewardAndCard() {
    	
    }

    @Override
    public void execPacket(Player player) {

    }

    @Override
    public short getType() {
        return GameCavePacketSet.C_GET_TINYGAME_REWARD_AND_CARD;
    }

    @Override
    public void writeToBuffer(NewByteBuffer buff) {
    	buff.writeByte(reslut);
    }
    
    public void setReslut(byte value){
    	reslut = value;
    }
}
