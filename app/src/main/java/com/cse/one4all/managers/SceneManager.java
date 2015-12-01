package com.cse.one4all.managers;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.scene.GameOverScene;
import com.cse.one4all.scene.LoadingScene;
import com.cse.one4all.scene.MainMenuScene;
import com.cse.one4all.scene.MinigameMenuScene;
import com.cse.one4all.scene.MinigameScene;
import com.cse.one4all.scene.MultiplayerScene;
import com.cse.one4all.scene.SceneType;
import com.cse.one4all.scene.SplashScene;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

public class SceneManager {

    private static final SceneManager INSTANCE = new SceneManager();

    private SceneType currentSceneType = SceneType.SPLASH;

    private BaseScene currentScene;

    private Engine engine = ResourcesManager.getInstance().engine;

    public SplashScene splashScene;
    public BaseScene mainMenuScene;
    public MinigameScene minigameScene;
    public BaseScene minigameMenuScene;
    public BaseScene loadingScene;
    public MultiplayerScene multiplayerScene;
    public BaseScene gameOverScene;

    public void setScene(BaseScene scene){
        engine.setScene(scene);

        this.currentScene = scene;
        this.currentSceneType = scene.getSceneType();

        scene.populateScene();
    }

    public void setScene(SceneType sceneType){
        switch(sceneType){
            case SPLASH:
                setScene(splashScene);
                break;
            case MENU:
                setScene(mainMenuScene);
                break;
            case MINIGAME:
                setScene(minigameScene);
                break;
            case MINIGAMEMENU:
                setScene(minigameMenuScene);
                break;
            case MULTIPLAYER:
                setScene(multiplayerScene);
                break;
            case GAMEOVER:
                setScene(gameOverScene);
                break;
        }
    }

    //---------------------------------------------
    // MENU
    //---------------------------------------------


    public void createMenuScene() {
        ResourcesManager.getInstance().loadMenuResources();
        ResourcesManager.getInstance().loadMinigameMenuResources();
        ResourcesManager.getInstance().loadMultiplayerMenuResources();
        mainMenuScene = new MainMenuScene();
        minigameMenuScene = new MinigameMenuScene();
        multiplayerScene = new MultiplayerScene();
        loadingScene = new LoadingScene();
        gameOverScene = new GameOverScene();

        SceneManager.getInstance().setScene(mainMenuScene);
        disposeSplashScene();
    }

    public void loadMenuScene(final Engine mEngine) {
        setScene(loadingScene);
        minigameScene.disposeScene();
        ResourcesManager.getInstance().unloadGameTextures();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadMainMenuTextures();
                setScene(mainMenuScene);
            }
        }));
    }

    public void loadGameScene(final Engine mEngine) {
        setScene(loadingScene);
        ResourcesManager.getInstance().unloadMainMenuTextures();

        ResourcesManager.getInstance().loadGameResources();
        minigameScene = new MinigameScene();
        setScene(minigameScene);

//        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
//            public void onTimePassed(final TimerHandler pTimerHandler) {
//                mEngine.unregisterUpdateHandler(pTimerHandler);
//
//            }
//        }));
    }

    public void createMinigameScene(){
        minigameScene = new MinigameScene();

        setScene(minigameScene);
    }

    public void disposeMinigameScene(){
        minigameScene.disposeScene();
        minigameScene = null;
    }

    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }

    private void disposeSplashScene() {
//        ResourcesManager.getInstance().unloadSplashScreen();
//        splashScene.disposeScene();
//        splashScene = null;
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
