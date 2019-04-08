package com.stars.modules.tool.func.impl;

import com.stars.core.module.Module;
import com.stars.modules.MConst;
import com.stars.modules.demologin.packet.ClientText;
import com.stars.modules.tool.func.ToolFunc;
import com.stars.modules.tool.func.ToolFuncResult;
import com.stars.modules.tool.productdata.ItemVo;
import com.stars.modules.vip.VipModule;

import java.util.Map;

/**
 * 月卡道具
 * Created by huwenjun on 2017/4/8.
 */
public class MonthCardFunc extends ToolFunc {
    Integer days;

    public MonthCardFunc(ItemVo itemVo) {
        super(itemVo);
    }

    @Override
    public void parseData(String function) {
        String[] strings = function.split("\\|");
        days = Integer.parseInt(strings[1]);
    }

    @Override
    public ToolFuncResult check(Map<String, Module> moduleMap, int count, Object... args) {
        VipModule vipModule = (VipModule) moduleMap.get(MConst.Vip);
        if (!vipModule.isOpActMonthCardOpen()) {
            return new ToolFuncResult(false, new ClientText("不在运营活动时间内"));
        }
        if (count <= 0) {
            return new ToolFuncResult(false, new ClientText("道具数量为零"));
        }
        return new ToolFuncResult(true, null);
    }

    @Override
    public Map<Integer, Integer> use(Map<String, Module> moduleMap, int count, Object... args) {
        VipModule vipModule = (VipModule) moduleMap.get(MConst.Vip);
        vipModule.addMonthCardDays(days*count);

        return null;
    }
}
