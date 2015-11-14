package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.sprite.Sprite;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.adt.align.HorizontalAlign;

public class MinigameScene extends BaseScene {

    private Text timerText;
    private Text p1LivesText, p2LivesText, p3LivesText, p4LivesText;
    private int p1LivesLeft, p2LivesLeft, p3LivesLeft, p4LivesLeft;
    public int timeLeft;

    private HUD minigameHUD;
    public TimerHandler handler;

    private BitmapTextureAtlas HUDTextureAtlas;
    private ITextureRegion blueHeartTR, redHeartTR, greenHeartTR, yellowHeartTR, timerTR;
    private Sprite blueHeartSprite, redHeartSprite, greenHeartSprite, yellowHeartSprite, timerSprite;


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

        //Textures
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        HUDTextureAtlas = new BitmapTextureAtlas(ResourcesManager.getInstance().activity.getTextureManager(), 256,256);

        blueHeartTR =  BitmapTextureAtlasTextureRegionFactory.createFromAsset(HUDTextureAtlas,
                ResourcesManager.getInstance().activity, "heartBlue.png", 0,0);
        redHeartTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(HUDTextureAtlas,
                ResourcesManager.getInstance().activity, "heartRed.png", 0,32);
        greenHeartTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(HUDTextureAtlas,
                ResourcesManager.getInstance().activity, "heartGreen.png", 32,0);
        yellowHeartTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(HUDTextureAtlas,
                ResourcesManager.getInstance().activity, "heartYellow.png", 32,32);
        timerTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(HUDTextureAtlas,
                ResourcesManager.getInstance().activity, "timer.png", 0, 64);

        redHeartSprite = new Sprite(camera.getCenterX(), camera.getHeight() - 16, redHeartTR, vbom);
        blueHeartSprite = new Sprite(camera.getCenterX() - 40, camera.getYMin() + 16, blueHeartTR, vbom);
        greenHeartSprite = new Sprite(camera.getCenterX() , camera.getYMin() + 16, greenHeartTR, vbom);
        yellowHeartSprite = new Sprite(camera.getCenterX() + 40, camera.getYMin() + 16, yellowHeartTR, vbom);
        timerSprite = new Sprite(16, camera.getHeight() - 32, timerTR, vbom);



        //Timer
        timeLeft = MinigameManager.TIMER_SECONDS;
        timerText = new Text(0, 0, resourcesManager.font, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        timerText.setText(timeLeft + "");
        timerText.setAnchorCenter(0, 0);
        timerText.setPosition(35, camera.getHeight() - timerText.getHeight());


        //Player lives
        p1LivesLeft = 10;
        p2LivesLeft = 10;
        p3LivesLeft = 10;
        p4LivesLeft = 10;

        p1LivesText = new Text(redHeartSprite.getX(), redHeartSprite.getY(),
                resourcesManager.p1LivesFont, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        p2LivesText = new Text(blueHeartSprite.getX(), blueHeartSprite.getY(),
                resourcesManager.p1LivesFont, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        p3LivesText = new Text(greenHeartSprite.getX(), greenHeartSprite.getY(),
                resourcesManager.p1LivesFont, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        p4LivesText = new Text(yellowHeartSprite.getX(), yellowHeartSprite.getY(),
                resourcesManager.p1LivesFont, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);

        p1LivesText.setText(p1LivesLeft + "");
        p2LivesText.setText(p2LivesLeft + "");
        p3LivesText.setText(p3LivesLeft + "");
        p4LivesText.setText(p4LivesLeft + "");


        //Attach
        HUDTextureAtlas.load();
        minigameHUD.attachChild(blueHeartSprite);
        minigameHUD.attachChild(redHeartSprite);
        minigameHUD.attachChild(greenHeartSprite);
        minigameHUD.attachChild(yellowHeartSprite);
        minigameHUD.attachChild(timerSprite);

        minigameHUD.attachChild(timerText);
        minigameHUD.attachChild(p1LivesText);
        minigameHUD.attachChild(p2LivesText);
        minigameHUD.attachChild(p3LivesText);
        minigameHUD.attachChild(p4LivesText);

        camera.setHUD(minigameHUD);

    }
}
