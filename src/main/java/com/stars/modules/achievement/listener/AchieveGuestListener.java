package com.stars.modules.achievement.listener;

import com.stars.core.event.Event;
import com.stars.core.module.AbstractEventListener;
import com.stars.core.module.Module;
import com.stars.modules.achievement.AchievementHandler;
import com.stars.modules.achievement.AchievementModule;

/**
 * Created by wuyuxing on 2017/2/9.
 */
public class AchieveGuestListener extends AbstractEventListener {
    public AchieveGuestListener(Module module) {
        super(module);
    }

    @Override
    public void onEvent(Event event) {
        AchievementModule achievementModule = (AchievementModule) module();
        achievementModule.triggerCheck(AchievementHandler.TYPE_GUEST, event);
    }
}
