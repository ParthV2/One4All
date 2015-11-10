package com.cse.one4all.managers;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.minigame.ClickThe6s;
import com.cse.one4all.minigame.Helicopter;
import com.cse.one4all.minigame.MathGame;
import com.cse.one4all.minigame.TapTheColor;
import com.cse.one4all.scene.SceneType;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinigameManager {

    private static final MinigameManager INSTANCE = new MinigameManager();

    private Engine engine = ResourcesManager.getInstance().engine;
    private Camera camera = ResourcesManager.getInstance().camera;
    private VertexBufferObjectManager vbom = ResourcesManager.getInstance().vbom;

    private Random random = new Random();
    private List<BaseMinigame> minigames = new ArrayList<>();
    public BaseMinigame currentMinigame;

    private boolean isSinglePlayer;
    private boolean started = false;

    public static final int TIMER_SECONDS = 15;

    public void startGame(){
        started = true;
        SceneManager.getInstance().loadGameScene(engine);
        startRandomMinigame();
    }

    public void endGame(){
        started = false;
        SceneManager.getInstance().loadMenuScene(engine);

        if(currentMinigame != null){
            currentMinigame.getMinigame().detachSelf();
            currentMinigame.resetMinigame();

            currentMinigame.disposeMinigameScene();
            currentMinigame.onFinish();
            currentMinigame.unloadResources();
        }

        ResourcesManager.getInstance().engine.unregisterUpdateHandler(SceneManager.getInstance().minigameScene.handler);
    }


    public void startRandomMinigame(){
        startMinigame(getRandomMinigame(), false);
    }

    public BaseMinigame startMinigame(String name){
        for(BaseMinigame m : minigames){
            if(name.equalsIgnoreCase(m.getName())){
                startMinigame(m, true);
                return m;
            }
        }
        return null;
    }

    public void endMinigame(final BaseMinigame minigame, boolean success){
        minigame.getMinigame().detachSelf();
        minigame.resetMinigame();

        minigame.disposeMinigameScene();
        minigame.onFinish();
        minigame.unloadResources();

        ResourcesManager.getInstance().engine.unregisterUpdateHandler(SceneManager.getInstance().minigameScene.handler);
        final Text result = new Text(camera.getCenterX(), camera.getCenterY(),
                ResourcesManager.getInstance().font, success ? "Completed!" : "Failed!", vbom);

        SceneManager.getInstance().minigameScene.attachChild(result);

        ResourcesManager.getInstance().engine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                engine.unregisterUpdateHandler(pTimerHandler);
                result.detachSelf();

                if(isSinglePlayer){
                    SceneManager.getInstance().loadMenuScene(engine);
                } else {
                    startRandomMinigame();

                    SceneManager.getInstance().minigameScene.timeLeft = TIMER_SECONDS;
                    SceneManager.getInstance().minigameScene.populateScene();
                }

            }
        }));
    }

    public void startMinigame(final BaseMinigame minigame, boolean isSinglePlayer){
        this.isSinglePlayer = isSinglePlayer;

        currentMinigame = minigame;

        minigame.resetMinigame();
        minigame.loadResources();

        ResourcesManager.getInstance().engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                ResourcesManager.getInstance().engine.unregisterUpdateHandler(pTimerHandler);

                minigame.createMinigameScene();
                SceneManager.getInstance().minigameScene.attachChild(minigame.getMinigame());
                minigame.onStart();
            }
        }));
    }

    public BaseMinigame getRandomMinigame(){
        List<BaseMinigame> minigameScenes = new ArrayList<>(minigames);
        if(currentMinigame != null){
            minigameScenes.remove(currentMinigame);
        }
        return minigameScenes.get(random.nextInt(minigameScenes.size()));
    }

    public void loadResources(){
        for (BaseMinigame m : minigames) {
            m.loadResources();
        }
    }

    public void init(){
        minigames.add(new TapTheColor());
        minigames.add(new ClickThe6s());
//        minigames.add(new WordScramble());
//        minigames.add(new Helicopter());
//        minigames.add(new MathGame());

    }

    public static MinigameManager getInstance(){
        return INSTANCE;
    }


    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }
}
