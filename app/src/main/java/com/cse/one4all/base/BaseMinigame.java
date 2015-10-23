package com.cse.one4all.base;

import com.cse.one4all.GameActivity;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.scene.MinigameScene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;

import java.util.Random;

public abstract class BaseMinigame {

    protected BaseScene scene;
    protected final Engine engine = ResourcesManager.getInstance().engine;
    protected final GameActivity activity = ResourcesManager.getInstance().activity;
    protected final Camera camera = ResourcesManager.getInstance().camera;

    protected final Random random = new Random();

    protected boolean completed = false;

    public void complete(){
        completed = true;
        scene.disposeScene();
        disposeMinigameScene();

        MinigameManager.getInstance().setRandomMinigame();
    }

    public void createScene(){
        completed = false;
        scene = new MinigameScene();
        scene.createScene();
        createMinigameScene();
    }

    public BaseScene getScene() {
        return scene;
    }

    public abstract void createMinigameScene();

    public abstract void disposeMinigameScene();

    public abstract void loadResources();

    public abstract void unloadResources();

    public abstract void onStart();
}
