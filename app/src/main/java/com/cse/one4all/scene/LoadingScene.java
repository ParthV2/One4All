package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

public class LoadingScene extends BaseScene {

    @Override
    public void createScene() {
        setBackground(new Background(Color.BLACK));
        attachChild(new Text(camera.getCenterX(), camera.getCenterY(), resourcesManager.font, "Loading...", vbom));
    }

    @Override
    public void populateScene() {

    }

    @Override
    public void onBackKeyPressed() {
        return;
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.LOADING;
    }

    @Override
    public void disposeScene() {

    }
}
