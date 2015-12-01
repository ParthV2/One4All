package com.cse.one4all;

import android.view.KeyEvent;

import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.PlayerManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;
import com.cse.one4all.utils.Logger;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;

import java.io.IOException;

public class GameActivity extends BaseGameActivity {

    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_HEIGHT = 480;

    private Camera camera;

    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions){
        Debug.d("Engine created");
        return new LimitedFPSEngine(pEngineOptions, 60);
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,  new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
        options.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        options.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return options;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
        }

        return false;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        ResourcesManager.getInstance().init(mEngine, this, camera, getVertexBufferObjectManager());
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        mEngine.registerUpdateHandler(new TimerHandler(5f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);

                //PlayerManager.getInstance().init();
                MinigameManager.getInstance().init();

                SceneManager.getInstance().createMenuScene();

                //ResourcesManager.getInstance().loadMenuResources();
                //SceneManager.getInstance().createMenuScene();



            }
        }));
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
