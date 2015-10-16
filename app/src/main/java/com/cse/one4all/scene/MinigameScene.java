package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.text.Text;

public class MinigameScene extends BaseScene {

    @Override
    protected void createScene() {
        final Text text = new Text(200, 120, ResourcesManager.getInstance().font, "Minigame", vbom);
        attachChild(text);
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
