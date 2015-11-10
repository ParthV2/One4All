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
    private Text p1LivesText, p2LivesText, p3LivesText, p4LivesText;
    private int p1LivesLeft, p2LivesLeft, p3LivesLeft, p4LivesLeft;
    public int timeLeft;

    private HUD minigameHUD;
    private Sprite back;
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
                    MinigameManager.getInstance().endCurrentMinigame();
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
        camera.setCenter(400,240);
    }

    private void createHUD(){
        minigameHUD = new HUD();

        timeLeft = MinigameManager.TIMER_SECONDS;
        timerText = new Text(0, 0, resourcesManager.font, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        timerText.setText(timeLeft + "");
        timerText.setAnchorCenter(0, 0);
        timerText.setPosition(5, camera.getHeight() - timerText.getHeight());

        minigameHUD.attachChild(timerText);

        p1LivesLeft = 10;
        p1LivesText = new Text(0, 0, resourcesManager.p1LivesFont, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        p1LivesText.setText(p1LivesLeft + "");
        p1LivesText.setAnchorCenter(0, 0);
        p1LivesText.setPosition(camera.getCenterX() - (p1LivesText.getWidth() / 2), camera.getHeight() - p1LivesText.getHeight());
        minigameHUD.attachChild(p1LivesText);
        camera.setHUD(minigameHUD);

        p2LivesLeft = 10;
        p2LivesText = new Text(0, 0, resourcesManager.p2LivesFont, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        p2LivesText.setText(p2LivesLeft + "");
        p2LivesText.setAnchorCenter(0, 0);
        p2LivesText.setPosition((camera.getCenterX() - (p2LivesText.getWidth()/2)-20), camera.getYMin()+3);
        minigameHUD.attachChild(p2LivesText);
        camera.setHUD(minigameHUD);

        p3LivesLeft = 10;
        p3LivesText = new Text(0, 0, resourcesManager.p3LivesFont, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        p3LivesText.setText(p3LivesLeft + "");
        p3LivesText.setAnchorCenter(0, 0);
        p3LivesText.setPosition(camera.getCenterX() - (p3LivesText.getWidth()/2), camera.getYMin()+3);
        minigameHUD.attachChild(p3LivesText);
        camera.setHUD(minigameHUD);

        p4LivesLeft = 10;
        p4LivesText = new Text(0, 0, resourcesManager.p4LivesFont, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        p4LivesText.setText(p4LivesLeft + "");
        p4LivesText.setAnchorCenter(0, 0);
        p4LivesText.setPosition((camera.getCenterX() - (p4LivesText.getWidth()/2)+20), camera.getYMin()+3);
        minigameHUD.attachChild(p4LivesText);
        camera.setHUD(minigameHUD);
    }
}
