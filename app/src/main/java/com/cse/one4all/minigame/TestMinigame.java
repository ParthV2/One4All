package com.cse.one4all.minigame;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

public class TestMinigame extends BaseMinigame {


    @Override
    public void createMinigameScene() {
        //scene.setBackground(new Background(Color.GREEN));

        final Text text = new Text(400, 240, ResourcesManager.getInstance().font, "1", scene.vbom);
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
