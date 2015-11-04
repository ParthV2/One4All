package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;
import com.cse.one4all.minigame.ClickThe6Improved;
import com.cse.one4all.minigame.TapTheColor;
import com.cse.one4all.minigame.WordScramble;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

public class MinigameMenuScene extends BaseScene {

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

    private void createMenuScene() {
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
