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

    private float heliPos = 0;
    private float heliVel = 0;
    private final float heliAcc = -1;

    public void updateHeli()
    {
        heliPos = heliPos + heliVel;
        heliVel = heliVel + heliAcc;
    }

    public void applyThrust()
    {
        heliVel = heliVel + 2;
    }

    @Override
    public void onStart()
    {
        engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback()
        {
            public void onTimePassed(final TimerHandler pTimerHandler)
            {
                //Updates Helicopters Position and Velocity
                updateHeli();
                helicopter.setPosition(camera.getCenterX(), camera.getCenterY() + heliPos);

                //Stops when helicopter hits the top or bottom of the screen
                if(heliPos >= camera.getCenterY() || heliPos <= camera.getCenterY() - 450 ){
                    engine.unregisterUpdateHandler(pTimerHandler);
                } else {
                    pTimerHandler.reset();
                }
            }
        }));
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

        //Helicopter Image
        helicopter = new Text(camera.getCenterX(), camera.getCenterY() + heliPos, ResourcesManager.getInstance().font, "Helicopter", scene.vbom);

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
