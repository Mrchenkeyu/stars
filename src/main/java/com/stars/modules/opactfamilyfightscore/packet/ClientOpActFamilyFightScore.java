package com.stars.modules.opactfamilyfightscore.packet;

import com.stars.core.player.Player;
import com.stars.core.player.PlayerPacket;
import com.stars.modules.opactfamilyfightscore.OpActFamilyFightScorePacketSet;
import com.stars.network.server.buffer.NewByteBuffer;
import com.stars.util.LogUtil;

import java.util.Map;

/**
 * Created by chenkeyu on 2017-03-21 20:55
 */
public class ClientOpActFamilyFightScore extends PlayerPacket {
    private Map<String, String> dropMap;
    private String timeDesc;

    @Override
    public void writeToBuffer(NewByteBuffer buff) {
        buff.writeString(timeDesc);
        LogUtil.info("活动时间:{}", timeDesc);
        buff.writeByte((byte) dropMap.size());
        if (dropMap != null && dropMap.size() > 0) {
            for (Map.Entry<String, String> entry : dropMap.entrySet()) {
                buff.writeString(entry.getKey());//rankRange
                buff.writeString(entry.getValue());//reward
                LogUtil.info("rankRange:{},reward:{}", entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public void execPacket(Player player) {

    }

    @Override
    public short getType() {
        return OpActFamilyFightScorePacketSet.C_OpActFamilyFightScore;
    }

    public void setDropMap(Map<String, String> dropMap) {
        this.dropMap = dropMap;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }
}
