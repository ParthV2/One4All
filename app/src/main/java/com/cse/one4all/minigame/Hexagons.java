package com.cse.one4all.minigame;

import com.cse.one4all.base.BaseMinigame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Cole on 11/16/2015.
 */
public class Hexagons extends BaseMinigame {

    public BitmapTextureAtlas gameTA;
    private ITextureRegion[] hexagonTexture = new ITextureRegion[3];

    private Sprite[][] hexagon = new Sprite[4][4];

    private int [][] hexagonOrientation = new int[4][4];
    private float [][] hexagonXPos = new float[4][4];
    private float [][] hexagonYPos = new float[4][4];

    public void updateHexagon (int xPos, int yPos) {
        if (hexagonOrientation[xPos][yPos] < 2)
        {
            hexagonOrientation[xPos][yPos] += 1;
        }
        else
        {
            hexagonOrientation[xPos][yPos] = 0;
        }
    }

    public boolean checkWin() {
        int firstValue = hexagonOrientation[0][0];

        for (int xPos = 0; xPos < 4; xPos++)
        {
            for (int yPos = 0; yPos < 4; yPos++)
            {
                if (firstValue != hexagonOrientation[xPos][yPos])
                {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String getName()
    {
        return "Hexagons";
    }

    @Override
    public void onStart()
    {
        for (int xPos = 0; xPos < 4; xPos++)
        {
            for (int yPos = 0; yPos < 4; yPos++)
            {
                hexagonOrientation[xPos][yPos] = random.nextInt(3);

                if (yPos % 2 == 0)
                {
                    hexagonXPos[xPos][yPos] = camera.getCenterX() - 126 + 56*xPos;
                    hexagonYPos[xPos][yPos] = camera.getCenterY() - 100 + 50*yPos;
                }
                else
                {
                    hexagonXPos[xPos][yPos] = camera.getCenterX() - 126 + 56*xPos + 28;
                    hexagonYPos[xPos][yPos] = camera.getCenterY() - 100 + 50*yPos;
                }
            }
        }
    }

    @Override
    public void createMinigameScene()
    {
        for (int xPos = 0; xPos < 4; xPos++)
        {
            for (int yPos = 0; yPos < 4; yPos++)
            {

                hexagon[xPos][yPos] = new Sprite(hexagonXPos[xPos][yPos], hexagonYPos[xPos][yPos], hexagonTexture[hexagonOrientation[xPos][yPos]], scene.vbom)
                {
                    @Override
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
                    {
                        this.setRotation((float) 120);

//                        updateHexagon((int) this.getX(), (int) this.getY());

                        if (checkWin())
                        {
                            complete();
                        }

                        return true;
                    }
                };

                minigame.attachChild(hexagon[xPos][yPos]);
                scene.registerTouchArea(hexagon[xPos][yPos]);
            }
        }
    }

    @Override
    public void loadResources()
    {

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        gameTA = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);

        hexagonTexture[0] = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "hex0.png", 0, 0);

        hexagonTexture[1] = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "hex1.png", 0, 128);

        hexagonTexture[2] = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "hex2.png", 0, 256);

        gameTA.load();

    }

    @Override
    public void disposeMinigameScene()
    {
        for (int xPos = 0; xPos < 4; xPos++)
        {
            for (int yPos = 0; yPos < 4; yPos++) {
                scene.unregisterTouchArea(hexagon[xPos][yPos]);
            }
        }
    }

    @Override
    public void unloadResources()
    {
        for (int xPos = 0; xPos < 4; xPos++)
        {
            for (int yPos = 0; yPos < 4; yPos++) {
                minigame.detachChild(hexagon[xPos][yPos]);
            }
        }
        gameTA.unload();
    }

    @Override
    public void onFinish()
    {

    }
}
