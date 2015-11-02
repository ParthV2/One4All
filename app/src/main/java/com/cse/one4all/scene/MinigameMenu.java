package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;
import com.cse.one4all.minigame.ClickThe6Improved;
import com.cse.one4all.minigame.TapTheColor;
import com.cse.one4all.minigame.WordScramble;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

/**
 * Created by matthew on 10/31/2015.
 */
public class MinigameMenu extends BaseScene
{
    protected void createScene()
    {
        Sprite logo = new Sprite(650, 150, ResourcesManager.getInstance().logo2Texture, vbom);
        attachChild(logo);
        final Text text = new Text(400, 450, ResourcesManager.getInstance().font, "Game Menu", vbom);
        attachChild(text);
        final Text clickThe6s = new Text(400, 350, ResourcesManager.getInstance().font, "Click The 6's", vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {

                //MinigameManager.getInstance().setMinigame(new ClickThe6Improved());

                return true;
            }
        };
        attachChild(clickThe6s);
        registerTouchArea(clickThe6s);
        final Text helicopter = new Text(400, 300, ResourcesManager.getInstance().font, "Helicopter Game", vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {

                //MinigameManager.getInstance().setMinigame(new Helicopter());

                return true;
            }
        };
        attachChild(helicopter);
        registerTouchArea(helicopter);
        final Text wordScramble = new Text(400, 250, ResourcesManager.getInstance().font, "Word Scramble", vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {

                //MinigameManager.getInstance().setMinigame(new WordScramble());

                return true;
            }
        };
        attachChild(wordScramble);
        registerTouchArea(wordScramble);
        final Text tapTheColor = new Text(400, 200, ResourcesManager.getInstance().font, "Tap The Color", vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                //MinigameManager.getInstance().setMinigame(new TapTheColor());

                return true;
            }
        };
        attachChild(tapTheColor);
        registerTouchArea(tapTheColor);
        final Sprite back = new Sprite(200, 75, ResourcesManager.getInstance().backTexture, vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                ResourcesManager.getInstance().loadMenuResources();
                SceneManager.getInstance().createMenuScene();

                return true;
            }
        };
        attachChild(back);
        registerTouchArea(back);

    }
    @Override
    public void onBackKeyPressed()
    {

    }

    @Override
    public SceneType getSceneType()
    {
        return SceneType.MINIGAMEMENU;
    }

    @Override
    public void disposeScene()
    {

    }
}
