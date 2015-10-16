package com.cse.one4all.base;

import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.scene.MinigameScene;
import com.cse.one4all.scene.SceneType;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.util.adt.color.Color;

public abstract class BaseMinigame {

    protected BaseScene scene;

    protected Engine engine;

    private boolean complete = false;

    public void complete(){
        complete = true;
        scene.dispose();
        disposeMinigameScene();

        MinigameManager.getInstance().setRandomMinigame();
    }

    public void createScene(){
        scene = new MinigameScene();
        scene.createScene();
        engine = scene.engine;
        createMinigameScene();
    }

    public BaseScene getScene() {
        return scene;
    }

    public abstract void createMinigameScene();

    public abstract void disposeMinigameScene();

    public abstract void loadResources();

    public abstract void unloadResources();
}
