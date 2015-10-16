package com.cse.one4all.base;

import com.cse.one4all.GameActivity;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.scene.SceneType;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

public abstract class BaseScene extends Scene {

    public GameActivity game;
    public Engine engine;
    public Camera camera;
    public VertexBufferObjectManager vbom;
    public ResourcesManager resourcesManager;

    public BaseScene(){
        this.resourcesManager = ResourcesManager.getInstance();
        this.engine = resourcesManager.engine;
        this.game = resourcesManager.game;
        this.camera = resourcesManager.camera;
        this.vbom = resourcesManager.vbom;

        createScene();
    }

    protected abstract void createScene();

    public abstract void onBackKeyPressed();

    public abstract SceneType getSceneType();

    public abstract void disposeScene();
}
