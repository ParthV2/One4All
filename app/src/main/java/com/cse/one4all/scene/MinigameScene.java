package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;

public class MinigameScene extends BaseScene {

    private Text timerText;
    public int timeLeft;

    private HUD minigameHUD;
    public TimerHandler handler;

    @Override
    public void createScene() {
        createHUD();
    }

    @Override
    public void populateScene() {
        timerText.setText(timeLeft + "");

        handler = new TimerHandler(1f, true, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                if (timeLeft <= 0) {
                    MinigameManager.getInstance().currentMinigame.fail();
                    engine.unregisterUpdateHandler(pTimerHandler);
                } else {
                    timeLeft--;
                    timerText.setText(timeLeft + "");
                }
            }
        });
        engine.registerUpdateHandler(handler);
    }



    @Override
    public void onBackKeyPressed() {
        MinigameManager.getInstance().endGame();
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MINIGAME;
    }

    @Override
    public void disposeScene() {
        camera.setHUD(null);
        camera.setCenter(400, 240);
    }

    private void createHUD(){
        minigameHUD = new HUD();

        timeLeft = MinigameManager.TIMER_SECONDS;
        timerText = new Text(0, 0, resourcesManager.font, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        timerText.setText(timeLeft + "");
        timerText.setAnchorCenter(0, 0);
        timerText.setPosition(5, camera.getHeight() - timerText.getHeight());

        minigameHUD.attachChild(timerText);

        camera.setHUD(minigameHUD);
    }
}
