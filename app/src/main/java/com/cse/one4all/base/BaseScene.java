package com.cse.one4all.base;

import com.cse.one4all.GameActivity;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.scene.SceneType;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class BaseScene extends Scene {

    public GameActivity game;
    public Engine engine;
    public Camera camera;
    public VertexBufferObjectManager vbom;
    public ResourcesManager resourcesManager;

    public BaseScene(){
        this.resourcesManager = ResourcesManager.getInstance();
        this.engine = resourcesManager.engine;
        this.game = resourcesManager.activity;
        this.camera = resourcesManager.camera;
        this.vbom = resourcesManager.vbom;

        createScene();
    }


    public abstract void createScene();

    public abstract void populateScene();

    public abstract void onBackKeyPressed();

    public abstract SceneType getSceneType();

    public abstract void disposeScene();


}
