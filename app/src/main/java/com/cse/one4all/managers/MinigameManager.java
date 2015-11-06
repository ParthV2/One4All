package com.cse.one4all.managers;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.minigame.ClickThe6Improved;
import com.cse.one4all.minigame.ClickThe6s;
import com.cse.one4all.minigame.Helicopter;
import com.cse.one4all.minigame.MathGame;
import com.cse.one4all.minigame.TapTheColor;
import com.cse.one4all.minigame.WordScramble;
import com.cse.one4all.scene.SceneType;

import org.andengine.entity.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinigameManager {

    private static final MinigameManager INSTANCE = new MinigameManager();

    private Random random = new Random();
    private List<BaseMinigame> minigames = new ArrayList<>();
    public BaseMinigame currentMinigame;

    private boolean isSinglePlayer;
    private boolean started = false;

    public static final int TIMER_SECONDS = 15;


    public MinigameManager(){

    }

    public void startGame(){

    }

    public void startCountdown(){

    }

    public void setRandomMinigame(){
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

    public void startMinigame(BaseMinigame minigame, boolean isSinglePlayer){
        this.isSinglePlayer = isSinglePlayer;

        minigame.setScene(SceneManager.getInstance().minigameScene);
        minigame.createMinigameScene();

        currentMinigame = minigame;

        SceneManager.getInstance().setScene(SceneType.MINIGAME);

        minigame.onStart();
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
        minigames.add(new ClickThe6Improved());
        //minigames.add(new WordScramble());
        minigames.add(new Helicopter());
    }

    public static MinigameManager getInstance(){
        return INSTANCE;
    }


    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }
}
