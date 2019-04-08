package com.stars.modules.mind.listener;

import com.stars.core.event.Event;
import com.stars.core.event.EventListener;
import com.stars.modules.MConst;
import com.stars.modules.mind.MindModule;
import com.stars.modules.redpoint.RedPointConst;

/**
 * Created by daiyaorong on 2017/7/20.
 */
public class UseToolListener implements EventListener {
    private MindModule module;

    public UseToolListener(MindModule module){
        this.module = module;
    }

    public void onEvent(Event event) {
        module.signCalRedPoint(MConst.Mind, RedPointConst.MIND_LVUP);
    }
}