package com.cse.one4all.minigame;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

public class TestMinigame2 extends BaseMinigame {

    @Override
    public void createMinigameScene() {
        //scene.setBackground(new Background(Color.PINK));

        final Text text = new Text(400, 240, ResourcesManager.getInstance().font, "2", scene.vbom);
        scene.attachChild(text);

        engine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                engine.unregisterUpdateHandler(pTimerHandler);
                complete();
            }
        }));
    }

    @Override
    public void disposeMinigameScene() {

    }

    @Override
    public void loadResources() {

    }

    @Override
    public void unloadResources() {

    }
}
