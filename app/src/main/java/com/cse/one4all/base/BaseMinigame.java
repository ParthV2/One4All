package com.cse.one4all.base;

import com.cse.one4all.GameActivity;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;
import com.cse.one4all.scene.MinigameScene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;

import java.util.Random;

public abstract class BaseMinigame {

    protected Entity minigame;
    protected BaseScene scene;

    protected final Engine engine = ResourcesManager.getInstance().engine;
    protected final GameActivity activity = ResourcesManager.getInstance().activity;
    protected final Camera camera = ResourcesManager.getInstance().camera;

    protected static final Random random = new Random();

    public boolean completed = false;
    public boolean success = false;

    public void complete(){
        MinigameManager.getInstance().endMinigame(this, true);
    }

    public void fail(){
        MinigameManager.getInstance().endMinigame(this, false);
    }

    public void resetMinigame(){
        scene = SceneManager.getInstance().minigameScene;
        minigame = new Entity();
        completed = false;
    }

    public Entity getMinigame() {
        return minigame;
    }

    public abstract String getName();

    public abstract void createMinigameScene();

    public abstract void loadResources();

    public abstract void onStart();

    public abstract void disposeMinigameScene();

    public abstract void unloadResources();

    public abstract void onFinish();


}
