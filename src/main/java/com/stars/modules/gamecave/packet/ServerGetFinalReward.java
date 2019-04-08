package com.stars.modules.gamecave.packet;

import com.stars.core.player.Player;
import com.stars.core.player.PlayerPacket;
import com.stars.modules.MConst;
import com.stars.modules.gamecave.GameCaveModule;
import com.stars.modules.gamecave.GameCavePacketSet;
import com.stars.network.server.buffer.NewByteBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaopeidian on 2017/1/16.
 */
public class ServerGetFinalReward extends PlayerPacket {
	private List<Integer> chooseCardIds = new ArrayList<Integer>();
	
    @Override
    public short getType() {
        return GameCavePacketSet.S_GET_FINAL_REWARD;
    }

    @Override
    public void readFromBuffer(NewByteBuffer buff) {
    	short size = buff.readShort();
    	for (int i = 0; i < size; i++) {
			int cardId = buff.readInt();
			chooseCardIds.add(cardId);
		}
    }
    
    @Override
    public void execPacket(Player player) {
    	GameCaveModule gameCaveModule = (GameCaveModule) module(MConst.GameCave);
        gameCaveModule.getFinalReward(chooseCardIds);
    }
}
