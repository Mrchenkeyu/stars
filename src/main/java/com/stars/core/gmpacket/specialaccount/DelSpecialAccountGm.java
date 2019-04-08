package com.stars.core.gmpacket.specialaccount;

import com.stars.server.main.gmpacket.GmPacketHandler;
import com.stars.server.main.gmpacket.GmPacketResponse;
import com.stars.util.ServerLogConst;

import java.util.HashMap;

/**
 * Created by chenkeyu on 2017-03-24 10:28
 */
public class DelSpecialAccountGm extends GmPacketHandler {
    int result = 1;

    @Override
    public String handle(HashMap args) {
        try {
            if (args.get("account") != null) {
                result = 0;
                String account = (String) args.get("account");
                SpecialAccountManager.delSpecialAccount(account);
            }
        } catch (Exception e) {
            result = 1;
            e.printStackTrace();
            ServerLogConst.console.error("删除特殊账号错误", e);
        }
        GmPacketResponse response = new GmPacketResponse(GmPacketResponse.SUC, 1, resultToJson(result));
        return response.toString();
    }
}
