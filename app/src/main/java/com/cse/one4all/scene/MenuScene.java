package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

public class MenuScene extends BaseScene {

    @Override
    protected void createScene()
    {
        setBackground(new Background(Color.RED));
        ButtonSprite button = new ButtonSprite(200, 250, ResourcesManager.getInstance().mBtnPlayTexture,
                ResourcesManager.getInstance().vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                    MinigameManager.getInstance().setRandomMinigame();

                return true;
            }
        };
        attachChild(button);
        registerTouchArea(button);
    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MENU;
    }

    @Override
    public void disposeScene() {

    }
}
