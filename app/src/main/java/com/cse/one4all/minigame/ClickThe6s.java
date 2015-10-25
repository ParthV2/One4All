package com.cse.one4all.minigame;

import android.graphics.Color;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;

/**
 * Created by matthew on 10/23/2015.
 */
public class ClickThe6s extends BaseMinigame
{
    public BitmapTextureAtlas gameTA;
    private ArrayList<Text> nums = new ArrayList<Text>();
    private Font gameFont;
    private Text num3, num5, num9, num11, num14;

    @Override
    public void createMinigameScene()
    {
        //scene.setBackground(new Background(Color.GREEN));

        final Text text = new Text(240, 400, ResourcesManager.getInstance().font, "Click the 6s", scene.vbom);
        scene.attachChild(text);

        float positionX = 200;
        float positionY = 200;

        for(int i = 1; i<19; i++)
        {
            if (i == 3 || i == 5 || i == 9 || i == 11 || i == 14)
            {
                nums.add(new Text(positionX, positionY, gameFont, "", scene.vbom));
            }

            else
                nums.add(new Text(positionX, positionY, gameFont, "9", scene.vbom));
            if (i == 6)
            {
                positionX = 170;
                positionY = 250;
            }
            else if(i==12)
            {
                positionX = 170;
                positionY = 300;
            }

            positionX+=30;
            scene.attachChild(nums.get(i - 1));

        }
        num3 = new Text(260, 188, gameFont, "6", scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(num3.getText().equals("6"))
                {
                    num3.setPosition(260,200);
                    num3.setText("9");
                }
                return true;
            }
        };
        scene.attachChild(num3);
        scene.registerTouchArea(num3);
        num5 = new Text(320, 188, gameFont, "6", scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(num5.getText().equals("6"))
                {
                    num5.setPosition(320,200);
                    num5.setText("9");
                }
                return true;
            }
        };
        scene.attachChild(num5);
        scene.registerTouchArea(num5);
        num9 = new Text(260, 238, gameFont, "6", scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(num9.getText().equals("6"))
                {
                    num9.setPosition(260,250);
                    num9.setText("9");
                }
                return true;
            }
        };
        scene.attachChild(num9);
        scene.registerTouchArea(num9);
        num11 = new Text(320, 238, gameFont, "6", scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(num11.getText().equals("6"))
                {
                    num11.setPosition(320,250);
                    num11.setText("9");
                }
                return true;
            }
        };
        scene.attachChild(num11);
        scene.registerTouchArea(num11);
        num14 = new Text(230, 288, gameFont, "6", scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(num14.getText().equals("6"))
                {
                    num14.setPosition(230,300);
                    num14.setText("9");
                }
                return true;
            }
        };
        scene.attachChild(num14);
        scene.registerTouchArea(num14);




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
        if(num3.getText().equals("9") && num5.getText().equals("9") &&
                num9.getText().equals("9")&& num11.getText().equals("9") && num14.getText().equals("9"))
        {
            //MinigameManager.getInstance().setRandomMinigame();
            Text message = new Text(550, 75,  ResourcesManager.getInstance().font, "You're a genius!!", scene.vbom);
            scene.attachChild(message);
        }

    }
}
