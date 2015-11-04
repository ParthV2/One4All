package com.cse.one4all.base;

import com.cse.one4all.GameActivity;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;
import com.cse.one4all.scene.MinigameScene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;

import java.util.Random;

public abstract class BaseMinigame {

    protected BaseScene scene;
    protected final Engine engine = ResourcesManager.getInstance().engine;
    protected final GameActivity activity = ResourcesManager.getInstance().activity;
    protected final Camera camera = ResourcesManager.getInstance().camera;

    protected static final Random random = new Random();

    public boolean completed = false;
    public boolean success = false;

    public void complete(){
        completed = true;
        success = true;
        engine.unregisterUpdateHandler(SceneManager.getInstance().minigameScene.handler);
        SceneManager.getInstance().loadResultScene();
    }

    public void fail(){
        completed = true;
        success = false;
        engine.unregisterUpdateHandler(SceneManager.getInstance().minigameScene.handler);
        SceneManager.getInstance().loadResultScene();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(BaseScene scene){
        this.scene = scene;
    }

    public abstract String getName();

    public abstract void createMinigameScene();

    public abstract void disposeMinigameScene();

    public abstract void loadResources();

    public abstract void unloadResources();

    public abstract void onStart();
}
