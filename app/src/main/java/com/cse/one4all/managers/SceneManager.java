package com.cse.one4all.managers;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.scene.MenuScene;
import com.cse.one4all.scene.SceneType;
import com.cse.one4all.scene.SplashScene;

import org.andengine.engine.Engine;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

public class SceneManager {

    private static final SceneManager INSTANCE = new SceneManager();

    private SceneType currentSceneType = SceneType.SPLASH;

    private BaseScene currentScene;

    private Engine engine = ResourcesManager.getInstance().engine;

    private SplashScene splashScene;
    private MenuScene menuScene;


    public void setScene(BaseScene scene){
        engine.setScene(scene);
        this.currentScene = scene;
        this.currentSceneType = scene.getSceneType();
    }

    public void setScene(SceneType sceneType){
        switch(sceneType){
            case SPLASH:
                setScene(splashScene);
                break;
            case MENU:
                setScene(menuScene);
                break;
        }
    }

    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }

    private void disposeSplashScene() {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }

    public void createMenuScene() {
        //ResourcesManager.getInstance().loadMenuResources();
        //menuScene = new MenuScene();

        menuScene = new MenuScene();

        SceneManager.getInstance().setScene(menuScene);
        disposeSplashScene();
    }

    public static SceneManager getInstance() {
        return INSTANCE;
    }


    public BaseScene getCurrentScene() {
        return currentScene;
    }

    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }
}
