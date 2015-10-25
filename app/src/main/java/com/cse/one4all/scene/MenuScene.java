package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

public class MenuScene extends BaseScene {

    @Override
    protected void createScene()
    {
        setBackground(new Background(Color.BLACK));
        ButtonSprite button = new ButtonSprite(400, 275, ResourcesManager.getInstance().mBtnPlayTexture,
                ResourcesManager.getInstance().vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {

                    MinigameManager.getInstance().setRandomMinigame();

                return true;
            }
        };
        attachChild(button);
        registerTouchArea(button);
        Text play = new Text(400, 275,  ResourcesManager.getInstance().font, "Play", vbom);
        attachChild(play);
        ButtonSprite btnExit = new ButtonSprite(400, 190, ResourcesManager.getInstance().mBtnPlayTexture,
                ResourcesManager.getInstance().vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {

                System.exit(0);

                return true;
            }
        };
        attachChild(btnExit);
        registerTouchArea(btnExit);
        Text exit = new Text(400, 190,  ResourcesManager.getInstance().font, "Exit", vbom);
        attachChild(exit);
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
