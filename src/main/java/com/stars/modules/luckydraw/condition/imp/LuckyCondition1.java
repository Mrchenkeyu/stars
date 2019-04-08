package com.stars.modules.luckydraw.condition.imp;

import com.stars.modules.luckydraw.condition.LuckyCondition;
import com.stars.modules.luckydraw.userdata.RoleLuckyDrawPo;
import com.stars.modules.luckydraw.userdata.RoleLuckyDrawTimePo;

/**
 * Created by huwenjun on 2017/8/10.
 */
public class LuckyCondition1 extends LuckyCondition {

    public LuckyCondition1(int time) {
        super(time);
    }

    @Override
    public Boolean canDraw(RoleLuckyDrawPo roleLuckyDrawPo, RoleLuckyDrawTimePo roleLuckyDrawTimePo) {
        if (roleLuckyDrawTimePo.getTotalDrawTime() >= getTime()) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean mustHit(RoleLuckyDrawPo roleLuckyDrawPo, RoleLuckyDrawTimePo roleLuckyDrawTimePo) {
        return null;
    }

}
