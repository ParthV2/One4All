package com.cse.one4all.minigame;

import android.graphics.Color;
import android.util.Log;

import com.cse.one4all.GameActivity;
import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClickThe6Improved extends BaseMinigame {

    private Font numberFont;

    private List<Text> numbers = new ArrayList<>();

    private static final int ROWS = 3;
    private static final int COLUMNS = 6;
    private static final int SIX_COUNT = 5;

    private static final int START_X = 200;
    private static final int START_Y = 100;
    private static final int PADDING_X = 0;
    private static final int PADDING_Y = 0;

    private int sixClicked = 0;

    @Override
    public void createMinigameScene() {

        List<Integer> numPos = new ArrayList<Integer>(SIX_COUNT);
        for(int i = 0; i < SIX_COUNT; i++){
            int pos = RANDOM.nextInt(ROWS * COLUMNS);
            numPos.add(pos);
        }


        float xPos = -1;
        float yPos = -1;
        int index = 0;
        for(int yIndex = 0; yIndex < ROWS; yIndex++){
            for(int xIndex = 0; xIndex < COLUMNS; xIndex++){
                //search the random positions array
                //if it contains pos index, then print 6 else 9
                String number = numPos.contains(index) ? "6" : "9";
                Text text = new Text(0, 0, ResourcesManager.getInstance().numberFont, number, scene.vbom){
                    @Override
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                        if(pSceneTouchEvent.isActionDown() == false){
                            return true;
                        }

                        if (this.getText() == "6") {
                            this.setText("9");
                            sixClicked++;
                            this.setPosition(this.getX(), this.getY() + 20);
                        }

                        if(sixClicked == SIX_COUNT){
                            pass();
                        }

                        return true;
                    }
                };

                if(xPos == -1 || yPos == -1){
                    float fullTextWidth = ((text.getWidth() + PADDING_X) * COLUMNS);
                    float fullTextHeight = ((text.getHeight() + PADDING_Y) * ROWS);

                    //xPos = camera.getCenterX() - (fullTextWidth / 2);
                    //yPos = camera.getCenterY() - (fullTextHeight / 2);
                    xPos = 0;
                    yPos = 0;

                }

                text.setAnchorCenter(0, 0);
                text.setPosition(xPos + (xIndex * (PADDING_X + text.getWidth())), yPos + (yIndex * (PADDING_Y + 70 - (text.getText() == "6" ? 20 : 0))));

                numbers.add(text);

                scene.attachChild(text);
                scene.registerTouchArea(text);

                index++;
            }
        }
    }

    @Override
    public void disposeMinigameScene() {

    }

    @Override
    public void loadResources() {

    }

    @Override
    public void unloadResources() {

    }

    @Override
    public void onStart() {

    }
}
