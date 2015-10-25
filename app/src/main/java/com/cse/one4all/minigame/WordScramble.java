package com.cse.one4all.minigame;

import android.graphics.Color;

import com.cse.one4all.Letter;
import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.entity.text.Text;

import java.util.ArrayList;

/**
 * Created by matthew on 10/25/2015.
 */
public class WordScramble extends BaseMinigame
{
    public BitmapTextureAtlas gameTA;
    private Font gameFont;
    private ITextureRegion mBtnPlayTexture;
    private Letter letterW, letterI, letterN1, letterN2, letterE, letterR;
    private Text clear;
    private ArrayList<Text> dashes = new ArrayList<Text>();
    private ArrayList<Text> dashes2 = new ArrayList<Text>();
    private int count = 0;
    @Override
    public void createMinigameScene()
    {
        float positionX = 200;
        for(int i = 0; i < 6; i++)
        {
            dashes.add(new Text(positionX, 320, gameFont, "__", scene.vbom));
            scene.attachChild(dashes.get(i));
            positionX+=75;
        }
        positionX = 200;
        for(int i = 0; i < 6; i++)
        {
            dashes2.add(new Text(positionX, 200, gameFont, "__", scene.vbom));
            scene.attachChild(dashes2.get(i));
            positionX+=75;
        }
        letterW = new Letter(275, 325, gameFont, "W", scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                count++;
                if(count == 1)
                {
                    letterW.setPosition(200,205);
                }
                else if(count == 2)
                {
                    letterW.setPosition(275,205);
                }
                else if(count == 3)
                {
                    letterW.setPosition(350,205);
                }
                else if(count == 4)
                {
                    letterW.setPosition(425,205);
                }
                else if(count == 5)
                {
                    letterW.setPosition(500,205);
                }
                else if(count == 6)
                {
                    letterW.setPosition(575,205);
                }
                return true;
            }
        };
        scene.attachChild(letterW);
        scene.registerTouchArea(letterW);
        letterI = new Letter(200, 325, gameFont, "I", scene.vbom)
        {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                count++;
                if (count == 1) {
                    letterI.setPosition(200, 205);
                } else if (count == 2) {
                    letterI.setPosition(275, 205);
                } else if (count == 3) {
                    letterI.setPosition(350, 205);
                } else if (count == 4) {
                    letterI.setPosition(425, 205);
                } else if (count == 5) {
                    letterI.setPosition(500, 205);
                } else if (count == 6) {
                    letterI.setPosition(575, 205);
                }
                return true;
            }
    };
        scene.attachChild(letterI);
        scene.registerTouchArea(letterI);
        letterN1 = new Letter(425, 325, gameFont, "N", scene.vbom)
        {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                count++;
                if (count == 1) {
                    letterN1.setPosition(200, 205);
                } else if (count == 2) {
                    letterN1.setPosition(275, 205);
                } else if (count == 3) {
                    letterN1.setPosition(350, 205);
                } else if (count == 4) {
                    letterN1.setPosition(425, 205);
                } else if (count == 5) {
                    letterN1.setPosition(500, 205);
                } else if (count == 6) {
                    letterN1.setPosition(575, 205);
                }
                return true;
            }
        };
        scene.attachChild(letterN1);
        scene.registerTouchArea(letterN1);
        letterN2 = new Letter(575, 325, gameFont, "N", scene.vbom)
        {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                count++;
                if (count == 1) {
                    letterN2.setPosition(200, 205);
                } else if (count == 2) {
                    letterN2.setPosition(275, 205);
                } else if (count == 3) {
                    letterN2.setPosition(350, 205);
                } else if (count == 4) {
                    letterN2.setPosition(425, 205);
                } else if (count == 5) {
                    letterN2.setPosition(500, 205);
                } else if (count == 6) {
                    letterN2.setPosition(575, 205);
                }
                return true;
            }
        };
        scene.attachChild(letterN2);
        scene.registerTouchArea(letterN2);
        letterE = new Letter(350, 325, gameFont, "E", scene.vbom)
        {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                count++;
                if (count == 1) {
                    letterE.setPosition(200, 205);
                } else if (count == 2) {
                    letterE.setPosition(275, 205);
                } else if (count == 3) {
                    letterE.setPosition(350, 205);
                } else if (count == 4) {
                    letterE.setPosition(425, 205);
                } else if (count == 5) {
                    letterE.setPosition(500, 205);
                } else if (count == 6) {
                    letterE.setPosition(575, 205);
                }
                return true;
            }
        };
        scene.attachChild(letterE);
        scene.registerTouchArea(letterE);
        letterR = new Letter(500, 325, gameFont, "R", scene.vbom)
        {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                count++;
                if (count == 1) {
                    letterR.setPosition(200, 205);
                } else if (count == 2) {
                    letterR.setPosition(275, 205);
                } else if (count == 3) {
                    letterR.setPosition(350, 205);
                } else if (count == 4) {
                    letterR.setPosition(425, 205);
                } else if (count == 5) {
                    letterR.setPosition(500, 205);
                } else if (count == 6) {
                    letterR.setPosition(575, 205);
                }
                return true;
            }
        };
        scene.attachChild(letterR);
        scene.registerTouchArea(letterR);
        clear = new Text(550, 75,  ResourcesManager.getInstance().font, "Clear", scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                letterW.setPosition(275, 325);
                letterI.setPosition(200, 325);
                letterN1.setPosition(425, 325);
                letterN2.setPosition(575, 325);
                letterE.setPosition(500, 325);
                letterR.setPosition(350, 325);
                count = 0;

                return true;
            }
        };
        scene.attachChild(clear);
        scene.registerTouchArea(clear);



    }

    @Override
    public void disposeMinigameScene()
    {
        scene.disposeScene();
    }

    @Override
    public void loadResources()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        gameTA = new BitmapTextureAtlas(activity.getTextureManager(), 1280, 1280, TextureOptions.BILINEAR);
        FontFactory.setAssetBasePath("font/");
        gameFont = FontFactory.createFromAsset(activity.getFontManager(), gameTA, activity.getAssets(), "Radley-Regular.ttf", 75, true, Color.WHITE);
        gameFont.load();
        mBtnPlayTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA, activity, "button.png", 0, 0);
        gameTA.load();

    }

    @Override
    public void unloadResources()
    {
        gameFont.unload();
        gameTA.unload();
    }

    @Override
    public void onStart()
    {
        if((letterW.getPx() == 200 && letterW.getPy() == 205) && (letterI.getPx() == 275 && letterI.getPy() == 205) &&
                ((letterN1.getPx() == 350 && letterN1.getPy() == 205) || (letterN2.getPx() == 350 && letterN2.getPy() == 205))
                && ((letterN2.getPx() == 425 && letterN2.getPy() == 205) || (letterN1.getPx() == 425 && letterN1.getPy() == 205))
                && (letterE.getPx() == 500 && letterE.getPy() == 205) && (letterR.getPx() == 575 && letterR.getPy() == 205))
        {
            //MinigameManager.getInstance().setRandomMinigame();
            Text message = new Text(200, 200,  ResourcesManager.getInstance().font, "You're a genius!!", scene.vbom);
            scene.attachChild(message);
        }
    }
}
