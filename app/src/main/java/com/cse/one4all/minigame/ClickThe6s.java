package com.cse.one4all.minigame;

import android.graphics.Color;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import java.util.ArrayList;
import java.util.List;

public class ClickThe6s extends BaseMinigame {

    private List<Text> numbers;

    private static final int ROWS = 3;
    private static final int COLUMNS = 12;

    private static final int SIX_COUNT = 6;

    private static final int START_X = 100;
    private static final int START_Y = 25;

    private static final int PADDING_X = 5;
    private static final int PADDING_Y = 0;

    private int sixClicked = 0;

    @Override
    public String getName() {
        return "Click The 6's";
    }

    @Override
    public void createMinigameScene() {
        success = false;
        numbers = new ArrayList<>();

        sixClicked = 0;
        List<Integer> numPos = new ArrayList<Integer>(SIX_COUNT);
        for(int i = 0; i < SIX_COUNT; i++){
            int pos;
            do {
                pos = random.nextInt((ROWS * COLUMNS));
            } while(numPos.contains(pos));
            numPos.add(pos);
        }


        int index = 0;
        for(int yIndex = 0; yIndex < ROWS; yIndex++){
            for(int xIndex = 0; xIndex < COLUMNS; xIndex++){
                //search the random positions array
                //if it contains pos index, then print 6 else 9
                String number = numPos.contains(index) ? "6" : "9";
                Text text = new Text(0, 0, ResourcesManager.getInstance().numberFont, number, scene.vbom){
                    @Override
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                        if(pSceneTouchEvent.isActionDown()){
                            if (this.getText() == "6") {
                                this.setText("9");
                                this.setColor(Color.GREEN);
                                sixClicked++;
                            }

                            if(sixClicked == SIX_COUNT){
                                complete();
                            }
                        }
                        return true;
                    }
                };


                text.setAnchorCenter(0, 0);
                text.setPosition(START_X + (xIndex * (PADDING_X + text.getWidth())), START_Y + (yIndex * (PADDING_Y + text.getHeight())));

                numbers.add(text);

                minigame.attachChild(text);
                scene.registerTouchArea(text);

                index++;
            }
        }
    }

    @Override
    public void disposeMinigameScene() {
        for(Text text : numbers){
            scene.unregisterTouchArea(text);
        }
        numbers = null;
    }

    @Override
    public void loadResources() {

    }

    @Override
    public void unloadResources() {

    }

    @Override
    public void onFinish() {
        sixClicked = 0;
    }

    @Override
    public void onStart() {

    }
}
