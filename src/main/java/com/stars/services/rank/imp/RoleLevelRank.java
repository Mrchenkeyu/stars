package com.stars.services.rank.imp;

import com.stars.core.player.PlayerUtil;
import com.stars.modules.MConst;
import com.stars.modules.rank.packet.ClientRank;
import com.stars.modules.role.summary.RoleSummaryComponent;
import com.stars.services.ServiceHelper;
import com.stars.services.rank.AbstractRank;
import com.stars.services.rank.userdata.AbstractRankPo;
import com.stars.services.rank.userdata.RoleRankPo;
import com.stars.util.StringUtil;

import java.util.List;

/**
 * Created by liuyuheng on 2017/1/13.
 */
public class RoleLevelRank extends AbstractRank {
    @Override
    protected void sendRankList(long roleId, List<AbstractRankPo> list) {
        // 没有name数据从常用数据拿
        for (AbstractRankPo rankPo : list) {
            RoleRankPo roleRankPo = (RoleRankPo) rankPo;
            if (StringUtil.isEmpty(roleRankPo.getRoleName()) || roleRankPo.getRoleJobId() == 0) {
                RoleSummaryComponent roleSummary = (RoleSummaryComponent)
                        ServiceHelper.summaryService().getSummaryComponent(roleRankPo.getRoleId(), MConst.Role);
                if (StringUtil.isEmpty(roleRankPo.getRoleName())) {
                    roleRankPo.setRoleName(roleSummary.getRoleName());
                }
            }
        }
        // send to client
        ClientRank packet = new ClientRank(rankId);
        packet.setList(list);
        PlayerUtil.send(roleId, packet);
    }

}