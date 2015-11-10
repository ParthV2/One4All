package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.List;

public class MinigameMenuScene extends BaseScene {
    private List<String> names = new ArrayList<String>(MinigameManager.getInstance().minigames.size());
    private List<Text> text = new ArrayList<Text>(MinigameManager.getInstance().minigames.size());
    private int i;
    private Text temp;

    @Override
    public void createScene() {

        createBackground();
        createMenuScene();
    }

    @Override
    public void populateScene() {

    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().setScene(SceneType.MENU);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MINIGAMEMENU;
    }

    @Override
    public void disposeScene() {

    }

    private void createBackground(){
        setBackground(new Background(Color.BLACK));

        Sprite logo = new Sprite(400, 400, ResourcesManager.getInstance().logoTexture2,
                ResourcesManager.getInstance().vbom);

        attachChild(logo);
    }

    private void createMenuScene()
    {
//        List<String> names = new ArrayList<String>(MinigameManager.getInstance().minigames.size());
//        int y = 500;
//        for(i = 0; i < 4; i++)
//        {
//            names.add(MinigameManager.getInstance().minigames.get(i).getName());
//            temp = new Text(240, y, ResourcesManager.getInstance().font, MinigameManager.getInstance().minigames.get(i).getName(), vbom)
//            {
//                @Override
//                public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
//                {
//                    if(pTouchEvent.isActionDown()){
//
//                        SceneManager.getInstance().loadGameScene(engine);
//                        MinigameManager.getInstance().startMinigame(MinigameManager.getInstance().minigames.get(i).getName());
//                    }
//
//                    return true;
//                }
//            };
//            text.add(temp);
//            attachChild(text.get(i));
//            registerTouchArea(text.get(i));
//
//            y+=75;
//        }

        final Text clickThe6s = new Text(400, 270, ResourcesManager.getInstance().font, "Click The 6's", vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(pTouchEvent.isActionDown()){

                    SceneManager.getInstance().loadGameScene(engine);
                    MinigameManager.getInstance().startMinigame("Click The 6's");
                }

                return true;
            }
        };
        attachChild(clickThe6s);
        registerTouchArea(clickThe6s);
        final Text helicopter = new Text(400, 220, ResourcesManager.getInstance().font, "Helicopter Game", vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(pTouchEvent.isActionDown()){

                    SceneManager.getInstance().loadGameScene(engine);
                    MinigameManager.getInstance().startMinigame("Helicopter Game");
                }

                return true;
            }
        };
        attachChild(helicopter);
        registerTouchArea(helicopter);

        /*
        final Text wordScramble = new Text(400, 170, ResourcesManager.getInstance().font, "Word Scramble", vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(pTouchEvent.isActionDown()){

                    SceneManager.getInstance().loadGameScene(engine);
                    MinigameManager.getInstance().startMinigame("Word Scramble");
                }

                return true;
            }
        };
        attachChild(wordScramble);
        registerTouchArea(wordScramble);
        */

        final Text tapTheColor = new Text(400, 170, ResourcesManager.getInstance().font, "Tap The Color", vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(pTouchEvent.isActionDown()){

                    SceneManager.getInstance().loadGameScene(engine);
                    MinigameManager.getInstance().startMinigame("Tap The Color");
                }

                return true;
            }
        };
        attachChild(tapTheColor);
        registerTouchArea(tapTheColor);
    }
}
