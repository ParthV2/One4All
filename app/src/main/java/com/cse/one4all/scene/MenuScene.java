package com.cse.one4all.scene;

import android.app.AlertDialog;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

public class MenuScene extends BaseScene {

    @Override
    protected void createScene()
    {
        setBackground(new Background(Color.BLACK));
        Sprite logo = new Sprite(400,400, ResourcesManager.getInstance().logoTexture2,
                ResourcesManager.getInstance().vbom);
        attachChild(logo);
        Sprite btnPlay = new Sprite(400, 300, ResourcesManager.getInstance().mBtnPlayTexture,
            ResourcesManager.getInstance().vbom)
    {
        @Override
        public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
        {

            MinigameManager.getInstance().setRandomMinigame();

            return true;
        }
    };
        attachChild(btnPlay);
        registerTouchArea(btnPlay);
        Sprite btnCode = new Sprite(400, 200, ResourcesManager.getInstance().mBtnCodeTexture,
                ResourcesManager.getInstance().vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {

                AlertDialog.Builder alert = new AlertDialog.Builder(ResourcesManager.getInstance().activity);
                alert.setTitle("Game Code");
                alert.setMessage("123456789");
                alert.show();
                Text message = new Text(550, 75,  ResourcesManager.getInstance().font, "You're a genius!!", vbom);
                attachChild(message);

                return true;
            }
        };
        attachChild(btnCode);
        registerTouchArea(btnCode);

       /*Sprite btnExit = new Sprite(400, 100, ResourcesManager.getInstance().mBtnExitTexture,
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
        registerTouchArea(btnExit);*/
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
