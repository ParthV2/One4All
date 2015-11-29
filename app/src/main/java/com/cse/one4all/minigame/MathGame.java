package com.cse.one4all.minigame;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.ResourcesManager;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

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
    private int subtraction = 0, choice, i, x, y;
    private TimerHandler timer;
//    private List<Text> textAnswers = new ArrayList<Text>(3);

    private BitmapTextureAtlas mathGameTA;
    private ITextureRegion greenHoopTR, redHoopTR;
    private Sprite greenHoopSprite1, greenHoopSprite2, greenHoopSprite3,
            redHoopSprite1, redHoopSprite2, redHoopSprite3;

    @Override
    public String getName() {
        return "Math Game";
    }

    @Override
    public void createMinigameScene()
    {
        success = false;
        addition =0;
        subtraction=0;
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
            int num = random.nextInt(34);
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
            int rand = random.nextInt(5) + 1;
            int minimum = rand * -1;
            int maximum = rand;
            int randomNum = minimum + (int)(Math.random()*maximum);
            if(choice == 1)
            {
                answers[numPos.get(0)] = addition + randomNum;
                answers[numPos.get(1)] = addition;
                answers[numPos.get(2)] = addition - randomNum;
            }
            else
            {
                answers[numPos.get(0)] = addition + randomNum;
                answers[numPos.get(1)] = addition;
                answers[numPos.get(2)] = addition + (2) *randomNum;
            }


        }
        else
        {

            answer = subtraction;
            int rand = random.nextInt(5) +1;
            int minimum = rand * -1;
            int maximum = rand;
            int randomNum = minimum + (int)(Math.random()*maximum);
            if(choice == 1)
            {
                answers[numPos.get(0)] = subtraction + randomNum;
                answers[numPos.get(1)] = subtraction;
                answers[numPos.get(2)] = subtraction - randomNum;
            }
            else
            {
                answers[numPos.get(0)] = subtraction - 2*randomNum;
                answers[numPos.get(1)] = subtraction;
                answers[numPos.get(2)] = subtraction - randomNum;
            }


        }

    }

    @Override
    public void disposeMinigameScene() {
        engine.unregisterUpdateHandler(timer);

    }

    @Override
    public void loadResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        mathGameTA = new BitmapTextureAtlas(ResourcesManager.getInstance().activity.getTextureManager(),
                512,512, TextureOptions.BILINEAR);
        greenHoopTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mathGameTA,
                ResourcesManager.getInstance().activity, "circleGreen160.png", 0,0);
        redHoopTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mathGameTA,
                ResourcesManager.getInstance().activity, "circleRed160.png", 160,160);

        redHoopSprite1 = new Sprite(205, 200, redHoopTR, scene.vbom);
        redHoopSprite2 = new Sprite(405, 200, redHoopTR, scene.vbom);
        redHoopSprite3 = new Sprite(605, 200, redHoopTR, scene.vbom);

        greenHoopSprite1 = new Sprite(205, 200, greenHoopTR, scene.vbom);
        greenHoopSprite2 = new Sprite(405, 200, greenHoopTR, scene.vbom);
        greenHoopSprite3 = new Sprite(605, 200, greenHoopTR, scene.vbom);

        mathGameTA.load();

        minigame.attachChild(redHoopSprite1);
        minigame.attachChild(redHoopSprite2);
        minigame.attachChild(redHoopSprite3);

    }

    @Override
    public void unloadResources() {
        mathGameTA.unload();

        minigame.detachChild(greenHoopSprite1);
        minigame.detachChild(greenHoopSprite2);
        minigame.detachChild(greenHoopSprite3);
        minigame.detachChild(ans1);
        minigame.detachChild(ans2);
        minigame.detachChild(ans3);

        scene.unregisterTouchArea(ans1);
        scene.unregisterTouchArea(ans2);
        scene.unregisterTouchArea(ans3);
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onStart()
    {
//        x = 200;
//        y = 200;
        message = new Text(400, 375, ResourcesManager.getInstance().font, "Remember the three numbers", scene.vbom);
        minigame.attachChild(message);
        text1 = new Text(200, 200, ResourcesManager.getInstance().numberFont2, strings[0], scene.vbom);
        minigame.attachChild(text1);
        text2 = new Text(400, 200, ResourcesManager.getInstance().numberFont2, strings[1], scene.vbom);
        minigame.attachChild(text2);
        text3 = new Text(600, 200, ResourcesManager.getInstance().numberFont2, strings[2], scene.vbom);
        minigame.attachChild(text3);

        timer = new TimerHandler(5f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                minigame.detachChild(text1);
                minigame.detachChild(text2);
                minigame.detachChild(text3);
                minigame.detachChild(message);

                minigame.detachChild(redHoopSprite1);
                minigame.detachChild(redHoopSprite2);
                minigame.detachChild(redHoopSprite3);

                minigame.attachChild(greenHoopSprite1);
                minigame.attachChild(greenHoopSprite2);
                minigame.attachChild(greenHoopSprite3);

                if(choice == 1)
                {
                    Text add = new Text(400, 400, ResourcesManager.getInstance().font, "What is the sum of three integers?", scene.vbom);
                    minigame.attachChild(add);
                    Text temp = new Text(300, 350, ResourcesManager.getInstance().font, "Example: X + Y + Z", scene.vbom);
                    minigame.attachChild(temp);
                }
                else
                {
                    Text subtract = new Text(375, 400, ResourcesManager.getInstance().font, "What is the difference of three integers?", scene.vbom);
                    minigame.attachChild(subtract);
                    Text temp = new Text(240, 350, ResourcesManager.getInstance().font, "Example: X - (Y + Z)", scene.vbom);
                    minigame.attachChild(temp);
                }
//                for(i =0; i < 3; i++)
//                {
//                    ans1 = new Text(x, y, ResourcesManager.getInstance().font, String.valueOf(answers[i]), scene.vbom)
//                    {
//                        public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
//                        {
//                            int temp = Integer.parseInt(textAnswers.get(i).getText().toString());
//                            if(temp == answer)
//                                complete();
//                            else
//                                fail();
//
//                           return true;
//                        }
//                    };
//                    textAnswers.add(ans1);
//                    scene.attachChild(textAnswers.get(i));
//                    scene.registerTouchArea(textAnswers.get(i));
//                    x+=100;
//
//                }
                ans1 = new Text(200, 200, ResourcesManager.getInstance().numberFont2, String.valueOf(answers[0]), scene.vbom)
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

                ans2 = new Text(400, 200, ResourcesManager.getInstance().numberFont2, String.valueOf(answers[1]), scene.vbom)
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
                ans3 = new Text(600, 200, ResourcesManager.getInstance().numberFont2, String.valueOf(answers[2]), scene.vbom)
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
                minigame.attachChild(ans1);
                scene.registerTouchArea(ans1);
                minigame.attachChild(ans2);
                scene.registerTouchArea(ans2);
                minigame.attachChild(ans3);
                scene.registerTouchArea(ans3);

            }
        });
        engine.registerUpdateHandler(timer);



    }
}
