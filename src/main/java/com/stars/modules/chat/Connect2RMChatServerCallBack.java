package com.stars.modules.chat;

import com.stars.bootstrap.BootstrapConfig;
import com.stars.core.rpc2.RpcClientConnectedCallback;
import com.stars.multiserver.MainRpcHelper;

import java.util.Properties;

public class Connect2RMChatServerCallBack implements RpcClientConnectedCallback {

    @Override
    public void ontCalled(int serverId) {
        BootstrapConfig config = com.stars.bootstrap.ServerManager.getServer().getConfig();
        Properties props = config.getProps().get("rmchat");
        MainRpcHelper.rmChatService().registerChatServer(Integer.parseInt(props.getProperty("serverId")),
                com.stars.bootstrap.ServerManager.getServer().getConfig().getServerId());
    }

}
