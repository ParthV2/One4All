package com.cse.one4all.minigame;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

/**
 * Created by Cole on 10/26/2015.
 */

public class Helicopter extends BaseMinigame {
    private Text helicopter;
    private Text thrust;
    private Text[] obstacle = new Text[4];

    private float heliXPos;
    private float heliYPos;
    private float heliVel;
    private float heliAcc;

    private int[] obstacleXPos = new int[4];
    private int[] obstacleYPos = new int[4];
    private int[] obstacleVel = new int[4];

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
            fail();
        }
        //Death if you come within 10 units of the middle of an obstacle
        for (int i = 0; i < 4; i++)
        {
            if (heliXPos >= obstacleXPos[i] - 10 && heliXPos <= obstacleXPos[i] + 10 && heliYPos >= obstacleYPos[i] - 10 && heliYPos <= obstacleYPos[i] + 10)
            {
                fail();
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


        engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler)
            {
                //Updates Objects
                updateHeli();
                updateObstacles();
                updateScreen();
                checkDeath();

                //If condition is met: exit loop, else continue loop.
                if (completed)
                {
                    engine.unregisterUpdateHandler(pTimerHandler);
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
        thrust = new Text(camera.getCenterX() - 200, camera.getCenterY(), ResourcesManager.getInstance().font, "Thrust", scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                applyThrust();
                return true;
            }
        };

        //Object Images
        helicopter = new Text(camera.getCenterX() + heliXPos, camera.getCenterY() + heliYPos, ResourcesManager.getInstance().font, "H", scene.vbom);
        for (int i = 0; i < 4; i++)
        {
            obstacle[i] = new Text(camera.getCenterX() + obstacleXPos[i], camera.getCenterY() + obstacleYPos[i], ResourcesManager.getInstance().font, "" + i + "", scene.vbom);
            scene.attachChild(obstacle[i]);
        }

        scene.attachChild(helicopter);
        scene.attachChild(thrust);
        scene.registerTouchArea(thrust);
    }

    @Override
    public void disposeMinigameScene()
    {
        scene.disposeScene();
    }


    @Override
    public void loadResources()
    {

    }

    @Override
    public void unloadResources()
    {

    }

}
