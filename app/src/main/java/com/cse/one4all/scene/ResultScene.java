package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.SceneManager;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

public class ResultScene extends BaseScene {

    @Override
    public void createScene() {
        setBackground(new Background(Color.BLACK));
        attachChild(new Text(camera.getCenterX(), camera.getCenterY(), resourcesManager.font, MinigameManager.getInstance().currentMinigame.success ? "Completed!" : "Failed!", vbom));
    }

    @Override
    public void populateScene() {
        engine.registerUpdateHandler(new TimerHandler(3f, true, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                engine.unregisterUpdateHandler(pTimerHandler);
                if(MinigameManager.getInstance().isSinglePlayer()){
                    dispose();
                    SceneManager.getInstance().loadMenuScene(engine);

                } else {
                    SceneManager.getInstance().createMinigameScene();
                    //MinigameManager.getInstance().setRandomMinigame();
                }
            }
        }));
    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public SceneType getSceneType() {
        return null;
    }

    @Override
    public void disposeScene() {

    }
}
