package com.cse.one4all.managers;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.minigame.ClickThe6s;
import com.cse.one4all.minigame.TapTheColor;
import com.cse.one4all.minigame.TestMinigame;
import com.cse.one4all.minigame.WordScramble;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinigameManager {

    private static final MinigameManager INSTANCE = new MinigameManager();

    private Random random = new Random();
    private List<BaseMinigame> minigames = new ArrayList<>();
    private BaseMinigame currentMinigame;

    public MinigameManager(){

    }

    public void setRandomMinigame(){
        setMinigame(getRandomMinigame());
    }

    public void setMinigame(BaseMinigame minigame){
        minigame.createScene();
        currentMinigame = minigame;
        SceneManager.getInstance().setScene(minigame.getScene());
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
        minigames.add(new TestMinigame());
        minigames.add(new TapTheColor());
        minigames.add(new ClickThe6s());
        minigames.add(new WordScramble());
        minigames.add(new ClickThe6Improved());
        minigames.add(new Helicopter());

        loadResources();
    }

    public static MinigameManager getInstance(){
        return INSTANCE;
    }


}
