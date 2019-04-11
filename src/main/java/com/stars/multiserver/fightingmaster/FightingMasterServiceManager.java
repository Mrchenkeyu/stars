package com.stars.multiserver.fightingmaster;

import com.stars.bootstrap.BootstrapConfig;
import com.stars.bootstrap.ServerManager;
import com.stars.services.SConst;
import com.stars.services.ServiceManager;
import com.stars.services.fightServerManager.Conn2FightManagerServerCallBack;
import com.stars.services.fightServerManager.FSManagerService;
import com.stars.services.fightServerManager.FSManagerServiceActor;
import com.stars.services.fightServerManager.FSRPCNetExceptionTask;

/**
 * Created by zhouyaohui on 2016/11/1.
 */
public class FightingMasterServiceManager extends ServiceManager {

    @Override
    public void initSelfServices() throws Throwable {
        registerAndInit(SConst.FSManagerService, newService(new FSManagerServiceActor()));
    }

    @Override
    public void initRpc() throws Throwable {
        exportService(FSManagerService.class, getService(SConst.FSManagerService));
        initRpcHelper(FightingMasterRPC.class);
        int commonId = com.stars.bootstrap.ServerManager.getServer().getConfig().getServerId();
        int managerServerId = Integer.parseInt(ServerManager.getServer().getConfig().getProps().
        		get(com.stars.bootstrap.BootstrapConfig.FIGHTMANAGER).getProperty("serverId"));
        connectServer(com.stars.bootstrap.BootstrapConfig.FIGHTMANAGER,
        		new Conn2FightManagerServerCallBack(FightingMasterRPC.rmfsManagerService(), commonId, managerServerId),
        		new FSRPCNetExceptionTask(com.stars.bootstrap.BootstrapConfig.FIGHTMANAGER, BootstrapConfig.FIGHTMANAGER1,
        				FightingMasterRPC.rmfsManagerService()));//战斗管理服
    }

    @Override
    public void runScheduledJob() throws Throwable {
        /*ServiceHelper.fightingMasterService().save();
        ServiceHelper.fightingMasterService().reSendAward();*/
    }
}
