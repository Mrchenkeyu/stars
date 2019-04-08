package com.stars.modules.friend.packet;

import com.stars.core.gmpacket.specialaccount.SpecialAccountManager;
import com.stars.core.player.Player;
import com.stars.core.player.PlayerPacket;
import com.stars.modules.MConst;
import com.stars.modules.demologin.packet.ClientText;
import com.stars.modules.friend.FriendPacketSet;
import com.stars.modules.role.RoleModule;
import com.stars.network.server.buffer.NewByteBuffer;
import com.stars.network.server.packet.PacketManager;
import com.stars.services.ServiceHelper;
import com.stars.util.LogUtil;

/**
 * Created by zhaowenshuo on 2016/8/13.
 */
public class ServerRecommendation extends PlayerPacket {

    public static final byte SUBTYPE_RECOMMENDATION = 0x00;
    public static final byte SUBTYPE_SEARCH = 0x01;

    private byte subtype;
    private String searchPattern;

    @Override
    public void execPacket(Player player) {
        if (SpecialAccountManager.isSpecialAccount(player.id())) {
            LogUtil.info("SpecialAccountPacketType:{}", String.format("0x%04X", FriendPacketSet.S_FRIEND_RECOMMENDATION));
            PacketManager.send(player.id(), new ClientText("common_tips_cantcontrol"));
            return;
        }
        switch (subtype) {
            case SUBTYPE_RECOMMENDATION:
                RoleModule module = (RoleModule) module(MConst.Role);
                ServiceHelper.friendService().sendRecommendationList(getRoleId(), module.getLevel());
                break;
            case SUBTYPE_SEARCH:
                ServiceHelper.friendService().searchRole(getRoleId(), searchPattern);
                break;
        }

    }

    @Override
    public short getType() {
        return FriendPacketSet.S_FRIEND_RECOMMENDATION;
    }

    @Override
    public void readFromBuffer(NewByteBuffer buff) {
        this.subtype = buff.readByte();
        switch (subtype) {
            case SUBTYPE_SEARCH:
                searchPattern = buff.readString();
                break;
        }
    }
}
