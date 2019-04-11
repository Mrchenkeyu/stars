package com.stars.multiserver.fightingmaster;

import com.stars.multiserver.fightManager.RMFSManagerService;
import com.stars.services.fightbase.FightBaseService;
import com.stars.services.localservice.LocalService;
import com.stars.services.role.RoleService;

/**
 * Created by zhouyaohui on 2016/11/8.
 */
public class FightingMasterRPC {

    static FightBaseService fightBaseService;
    static RoleService roleService;
    static LocalService localService;
    static RMFSManagerService rmfsManagerService;


    public static FightBaseService fightBaseService() {
        return fightBaseService;
    }

    public static RoleService roleService() {
        return roleService;
    }

    public static LocalService localService() {
        return localService;
    }
    public static RMFSManagerService rmfsManagerService(){
		return rmfsManagerService;
	}
    
}
