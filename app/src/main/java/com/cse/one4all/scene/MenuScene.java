package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;

import org.andengine.entity.scene.background.Background;
import org.andengine.util.adt.color.Color;

public class MenuScene extends BaseScene {

    @Override
    protected void createScene() {
        setBackground(new Background(Color.RED));
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
