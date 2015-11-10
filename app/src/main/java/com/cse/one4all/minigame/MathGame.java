package com.cse.one4all.minigame;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.ResourcesManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import java.lang.Integer;

/**
 * Created by matthew on 11/6/2015.
 */
public class MathGame extends BaseMinigame
{
    String stringNum = "";
    private Text text1, text2, text3, ans1, ans2, ans3, message;
    private int answers[] = new int[3];
    private String strings[] = new String[3];
    private int addition = 0, answer;
    private int subtraction = 0, choice;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void createMinigameScene()
    {
        List<Integer> numPos = new ArrayList<Integer>(3);
        List<Integer> nums = new ArrayList<Integer>(6);
        List<String> numString = new ArrayList<String>(6);
        for(int i = 0; i < 3; i++){
            int pos;
            do {
                pos = random.nextInt((3));
            } while(numPos.contains(pos));
            numPos.add(pos);
        }

        int min = 1;
        int max = 2;
        choice = min + (int)(Math.random()*max);
//        choice = random.nextInt(1) + 1;
        for(int i = 0; i < 3; i++)
        {
            int num = random.nextInt(50);
            addition += num;
            if(i == 0)
                subtraction += num;
            else
                subtraction -= num;

//            stringNum += String.valueOf(num) + " ";
            nums.add(num);
//            numString.add(stringNum);
            strings[i] = String.valueOf(num);

        }

        if(choice == 1) //addition
        {
            answer = addition;
            int rand = random.nextInt(5);
            int minimum = rand * -1;
            int maximum = rand;
            int randomNum = minimum + (int)(Math.random()*maximum);
            answers[numPos.get(0)] = addition + randomNum;
            answers[numPos.get(1)] = addition;
            answers[numPos.get(2)] = addition - randomNum;

        }
        else
        {
            answer = subtraction;
            int rand = random.nextInt(5);
            int minimum = rand * -1;
            int maximum = rand;
            int randomNum = minimum + (int)(Math.random()*maximum);
            answers[numPos.get(0)] = subtraction + randomNum;
            answers[numPos.get(1)] = subtraction;
            answers[numPos.get(2)] = subtraction - randomNum;
        }

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

    @Override
    public void onFinish() {

    }

    @Override
    public void onStart()
    {
        message = new Text(240, 400, ResourcesManager.getInstance().font, "Remember the three numbers", scene.vbom);
        scene.attachChild(message);
        text1 = new Text(200, 200, ResourcesManager.getInstance().font, strings[0], scene.vbom);
        scene.attachChild(text1);
        text2 = new Text(400, 200, ResourcesManager.getInstance().font, strings[1], scene.vbom);
        scene.attachChild(text2);
        text3 = new Text(600, 200, ResourcesManager.getInstance().font, strings[2], scene.vbom);
        scene.attachChild(text3);

        engine.registerUpdateHandler(new TimerHandler(5f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                scene.detachChild(text1);
                scene.detachChild(text2);
                scene.detachChild(text3);
                scene.detachChild(message);
                if(choice == 1)
                {
                    Text add = new Text(350, 400, ResourcesManager.getInstance().font, "What is the sum of three integers?", scene.vbom);
                    scene.attachChild(add);
                }
                else
                {
                    Text subtract = new Text(330, 400, ResourcesManager.getInstance().font, "What is the difference of three integers?", scene.vbom);
                    scene.attachChild(subtract);
                }
                ans1 = new Text(200, 200, ResourcesManager.getInstance().font, String.valueOf(answers[0]), scene.vbom)
                {
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
                    {
                        int temp = Integer.parseInt(ans1.getText().toString());
                        if(temp == answer)
                            complete();
                        else
                            fail();

                       return true;
                    }
                };

                ans2 = new Text(300, 200, ResourcesManager.getInstance().font, String.valueOf(answers[1]), scene.vbom)
                {
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
                    {
                        int temp = Integer.parseInt(ans2.getText().toString());
                        if(temp == answer)
                            complete();
                        else
                            fail();
                        return true;
                    }
                };
                ans3 = new Text(400, 200, ResourcesManager.getInstance().font, String.valueOf(answers[2]), scene.vbom)
                {
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
                    {
                        int temp = Integer.parseInt(ans3.getText().toString());
                        if(temp == answer)
                            complete();
                        else
                            fail();
                        return true;
                    }
                };
                scene.attachChild(ans1);
                scene.registerTouchArea(ans1);
                scene.attachChild(ans2);
                scene.registerTouchArea(ans2);
                scene.attachChild(ans3);
                scene.registerTouchArea(ans3);
                engine.unregisterUpdateHandler(pTimerHandler);
            }
        }));



    }
}
