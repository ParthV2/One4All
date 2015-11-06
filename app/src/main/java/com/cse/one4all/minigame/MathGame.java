package com.cse.one4all.minigame;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.ResourcesManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.text.Text;
import java.lang.Integer;

/**
 * Created by matthew on 11/6/2015.
 */
public class MathGame extends BaseMinigame
{
    String stringNum = "";
    private Text text, ans1, ans2, ans3;
    int answers[] = new int[3];
    int addition = 0;
    int subtraction = 0;

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


        int choice = random.nextInt(1) + 1;
        for(int i = 0; i < 3; i++)
        {
            int num = random.nextInt(50);
            addition += num;
            if(i == 0)
                subtraction += num;
            else
                subtraction -= num;

            stringNum += String.valueOf(num) + " ";


            nums.add(num);
            numString.add(stringNum);

        }

        if(choice == 1) //addition
        {
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
    public void onStart()
    {
        text = new Text(200, 200, ResourcesManager.getInstance().font, stringNum, scene.vbom);
        scene.attachChild(text);

        engine.registerUpdateHandler(new TimerHandler(5f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                scene.detachChild(text);
                ans1 = new Text(200, 200, ResourcesManager.getInstance().font, String.valueOf(answers[0]), scene.vbom);

                ans2 = new Text(300, 200, ResourcesManager.getInstance().font, String.valueOf(answers[1]), scene.vbom);
                ans3 = new Text(400, 200, ResourcesManager.getInstance().font, String.valueOf(answers[2]), scene.vbom);
                scene.attachChild(ans1);
                scene.attachChild(ans2);
                scene.attachChild(ans3);
            }
        }));



    }
}
