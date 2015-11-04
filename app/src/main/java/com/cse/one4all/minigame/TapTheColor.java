package com.cse.one4all.minigame;

import android.graphics.Color;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import java.util.ArrayList;
import java.util.List;

public class TapTheColor extends BaseMinigame {

    private BitmapTextureAtlas mTextureAtlas;
    private ITiledTextureRegion mCircleTiledTextureRegion;
    private TiledSprite mCircle;
    private Text mText;

    private int currentTextName = 0;
    private static final List<String> ColorNames = new ArrayList<>();
    private static final List<Integer> Colors = new ArrayList<>();

    @Override
    public void onStart() {
        engine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mCircle.setCurrentTileIndex((mCircle.getCurrentTileIndex() + 1) % 2);
                currentTextName = random.nextInt(ColorNames.size());
                mText.setText(ColorNames.get(currentTextName));
                mText.setColor(Colors.get(random.nextInt(Colors.size())));

                if(completed){
                    engine.unregisterUpdateHandler(pTimerHandler);
                } else {
                    pTimerHandler.reset();
                }
            }
        }));
    }

    @Override
    public String getName() {
        return "Tap The Color";
    }

    @Override
    public void createMinigameScene() {

        ColorNames.add("Red");
        ColorNames.add("Blue");

        Colors.add(Color.RED);
        Colors.add(Color.BLUE);

        mCircle = new TiledSprite(camera.getCenterX(), camera.getCenterY(), mCircleTiledTextureRegion, scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(currentTextName == mCircle.getCurrentTileIndex())
                {
                    complete();
                }
                return true;
            }
        };

        mText = new Text(camera.getCenterX(), camera.getCenterY() + 64, ResourcesManager.getInstance().font, "RedBlue", scene.vbom);
        mText.setText(ColorNames.get(currentTextName));
        mText.setColor(Color.BLUE);

        scene.attachChild(mCircle);
        scene.attachChild(mText);


        scene.registerTouchArea(mCircle);
    }

    @Override
    public void disposeMinigameScene() {
        scene.disposeScene();
    }

    @Override
    public void loadResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        mTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
        mCircleTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mTextureAtlas, activity, "circles_64.png", 0, 0, 2, 1);
        mTextureAtlas.load();
    }

    @Override
    public void unloadResources() {
        mTextureAtlas.unload();
        mCircleTiledTextureRegion = null;
    }


}
