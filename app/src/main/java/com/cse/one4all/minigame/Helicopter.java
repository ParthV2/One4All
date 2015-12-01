package com.cse.one4all.minigame;

import com.cse.one4all.base.BaseMinigame;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Cole on 10/26/2015.
 */

public class Helicopter extends BaseMinigame {

    public BitmapTextureAtlas gameTA;
    private ITextureRegion heliTexture;
    private ITextureRegion obstacleTexture;
    private ITextureRegion thrustTexture;

    private boolean localFail;
    private Sprite helicopter;
    private Sprite thrust;
    private Sprite[] obstacle = new Sprite[4];

    private float heliXPos;
    private float heliYPos;
    private float heliVel;
    private float heliAcc;

    private int[] obstacleXPos = new int[4];
    private int[] obstacleYPos = new int[4];
    private int[] obstacleVel = new int[4];

    private TimerHandler timer;

    public void updateHeli()
    {
        heliYPos = heliYPos + heliVel;
        heliVel = heliVel + heliAcc;
    }

    public void updateObstacles()
    {
        for (int i = 0; i < 4; i++)
        {
            if (obstacleXPos[i] < -500) {
                obstacleXPos[i] = 500;
                obstacleYPos[i] = random.nextInt(600) - 300;
            } else {
                obstacleXPos[i] = obstacleXPos[i] + obstacleVel[i];
            }
        }
    }

    public void updateScreen()
    {
        helicopter.setPosition(camera.getCenterX() + heliXPos, camera.getCenterY() + heliYPos);
        for (int i = 0; i < 4; i++)
        {
            obstacle[i].setPosition(camera.getCenterX() + obstacleXPos[i], camera.getCenterY() + obstacleYPos[i]);
        }
    }

    public void checkDeath()
    {
        //Death if you hit the bottom or the top
        if (heliYPos >= camera.getCenterY() || heliYPos <= camera.getCenterY() - 450) {
            localFail = true;
        }
        //Death if you come within 10 units of the middle of an obstacle
        for (int i = 0; i < 4; i++)
        {
            if (heliXPos >= obstacleXPos[i] - 55 && heliXPos <= obstacleXPos[i] + 55 && heliYPos >= obstacleYPos[i] - 50 && heliYPos <= obstacleYPos[i] + 50)
            {
                localFail = true;
            }
        }
    }

    public void applyThrust()
    {
        heliVel = heliVel + 2;
    }

    @Override
    public void onStart()
    {
        //Initial Value of variables
        success = true;
        localFail = false;
        heliXPos = 0;
        heliYPos = 0;
        heliVel = 0;
        heliAcc = -1;

        for (int i = 0; i < 4; i++)
        {
            obstacleXPos[i] = 450 + i*250;
            obstacleYPos[i] = random.nextInt(600) - 300;
            obstacleVel[i] = -10;
        }


        engine.registerUpdateHandler(timer = new TimerHandler(0.1f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler)
            {
                //Updates Objects
                updateHeli();
                updateObstacles();
                updateScreen();
                checkDeath();

                //If condition is met: exit loop, else continue loop.
                if (localFail)
                {
                    engine.unregisterUpdateHandler(pTimerHandler);
                    fail();
                } else {
                    pTimerHandler.reset();
                }
            }
        }));
    }

    @Override
    public String getName() {
        return "Helicopter Game";
    }

    @Override
    public void createMinigameScene()
    {
        //When clicked applies thrust to the Helicopter, in the form of increasing the Helicopter's Velocity
        thrust = new Sprite(camera.getCenterX() - 200, camera.getCenterY(), thrustTexture, scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                applyThrust();
                return true;
            }
        };

        //Object Images
//        helicopter = new Text(camera.getCenterX() + heliXPos, camera.getCenterY() + heliYPos, ResourcesManager.getInstance().font, "H", scene.vbom);
        helicopter = new Sprite(camera.getCenterX() + heliXPos, camera.getCenterY() + heliYPos, heliTexture, scene.vbom);
        for (int i = 0; i < 4; i++)
        {
            obstacle[i] = new Sprite(camera.getCenterX() + obstacleXPos[i], camera.getCenterY() + obstacleYPos[i], obstacleTexture, scene.vbom);
            minigame.attachChild(obstacle[i]);
        }

        minigame.attachChild(helicopter);
        minigame.attachChild(thrust);
        scene.registerTouchArea(thrust);
    }

    @Override
    public void disposeMinigameScene()
    {

        scene.unregisterTouchArea(thrust);

        minigame.detachChild(helicopter);
        minigame.detachChild(thrust);
    }


    @Override
    public void loadResources()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        gameTA = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);

        heliTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "helicopter.png", 0, 0);

        obstacleTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "rock1.png", 0, 256);

        thrustTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "buttonFly.png", 256, 0);

        gameTA.load();

    }

    @Override
    public void unloadResources()
    {
        gameTA.unload();
    }

    @Override
    public void onFinish() {
        engine.unregisterUpdateHandler(timer);
    }

}
