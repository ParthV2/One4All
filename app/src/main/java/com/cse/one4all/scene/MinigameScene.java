package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

public class MinigameScene extends BaseScene {

    @Override
    protected void createScene() {
        final Text text = new Text(200, 120, ResourcesManager.getInstance().font, "Minigame", vbom);
        attachChild(text);
        final Sprite back = new Sprite(700, 75, ResourcesManager.getInstance().backTexture, vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                SceneManager.getInstance().createMinigameMenu();

                return true;
            }
        };
        attachChild(back);
        registerTouchArea(back);

    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MINIGAME;
    }

    @Override
    public void disposeScene() {

    }
}
