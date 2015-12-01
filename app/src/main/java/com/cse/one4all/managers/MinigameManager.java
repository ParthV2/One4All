package com.cse.one4all.managers;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.minigame.ClickThe6s;
import com.cse.one4all.minigame.Helicopter;
import com.cse.one4all.minigame.Hexagons;
import com.cse.one4all.minigame.MathGame;
import com.cse.one4all.minigame.TapTheColor;
import com.cse.one4all.scene.SceneType;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
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
    public List<BaseMinigame> minigames = new ArrayList<>();
    public BaseMinigame currentMinigame;

    private boolean isSinglePlayer;
    private boolean started = false;

    public static final int TIMER_SECONDS = 15;




    public void startGame(){
        started = true;
        SceneManager.getInstance().multiplayerScene.reset();
        SceneManager.getInstance().loadGameScene(engine);
        startRandomMinigame();
    }

    public void endGame(){
        started = false;
        SceneManager.getInstance().setScene(SceneType.GAMEOVER);

        if(currentMinigame != null){
            currentMinigame.getMinigame().detachSelf();
            currentMinigame.resetMinigame();

            currentMinigame.disposeMinigameScene();
            currentMinigame.onFinish();
            currentMinigame.unloadResources();
        }

        ResourcesManager.getInstance().engine.unregisterUpdateHandler(SceneManager.getInstance().minigameScene.handler);
        if(SceneManager.getInstance().minigameScene.gameClock != null){
            ResourcesManager.getInstance().engine.unregisterUpdateHandler(SceneManager.getInstance().minigameScene.gameClock);
        }


        PlayerManager.getInstance().stopServer();

        engine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                engine.unregisterUpdateHandler(pTimerHandler);
                SceneManager.getInstance().loadMenuScene(engine);
            }
        }));
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

    public void endCurrentMinigame() {
        endMinigame(currentMinigame, currentMinigame.success);
    }

    public void endMinigame(final BaseMinigame minigame, boolean success){
        if(success){
            PlayerManager.getInstance().sendMinigameComplete();
        }
        minigame.getMinigame().detachSelf();
        minigame.resetMinigame();

        minigame.disposeMinigameScene();
        minigame.onFinish();
        minigame.unloadResources();

        ResourcesManager.getInstance().engine.unregisterUpdateHandler(SceneManager.getInstance().minigameScene.handler);
        if(SceneManager.getInstance().minigameScene.gameClock != null){
            ResourcesManager.getInstance().engine.unregisterUpdateHandler(SceneManager.getInstance().minigameScene.gameClock);
        }




        final Sprite result = new Sprite(camera.getCenterX(), camera.getCenterY(), success ? ResourcesManager.getInstance().greenCheckTR : ResourcesManager.getInstance().redX_TR, vbom);

        if(PlayerManager.getInstance().player1.getPlayerHearts() <= 0 || (PlayerManager.getInstance().player2 != null && PlayerManager.getInstance().player2.getPlayerHearts() <= 0)){
            //MinigameManager.getInstance().endGame();
            //return;
        }

        SceneManager.getInstance().minigameScene.attachChild(result);

        ResourcesManager.getInstance().engine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                engine.unregisterUpdateHandler(pTimerHandler);
                result.detachSelf();

                if(isSinglePlayer){
                    PlayerManager.getInstance().stopServer();
                    SceneManager.getInstance().loadMenuScene(engine);
                } else {
                    startRandomMinigame();

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

                SceneManager.getInstance().minigameScene.timeLeft = TIMER_SECONDS;

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
        minigames.add(new Helicopter());
        //minigames.add(new Hexagons());
        minigames.add(new MathGame());

    }

    public static MinigameManager getInstance(){
        return INSTANCE;
    }


    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }

    public boolean isStarted(){ return this.started; }
}
