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

    private TimerHandler timer;

    @Override
    public void onStart() {
        success = false;
        timer = new TimerHandler(2f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mCircle.setCurrentTileIndex(random.nextInt(4));
                currentTextName = random.nextInt(4);
                mText.setText(ColorNames.get(currentTextName));
                mText.setColor(Colors.get(random.nextInt(Colors.size())));

                if(completed){
                    engine.unregisterUpdateHandler(pTimerHandler);
                } else {
                    pTimerHandler.reset();
                }
            }
        });
        engine.registerUpdateHandler(timer);
    }

    @Override
    public String getName() {
        return "Tap The Color";
    }

    @Override
    public void createMinigameScene() {

        ColorNames.add("Red");
        ColorNames.add("Blue");
        ColorNames.add("Green");
        ColorNames.add("Yellow");

        Colors.add(Color.RED);
        Colors.add(Color.BLUE);
        Colors.add(Color.GREEN);
        Colors.add(Color.YELLOW);

        mCircle = new TiledSprite(camera.getCenterX(), camera.getCenterY(), mCircleTiledTextureRegion, scene.vbom)
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                if(pSceneTouchEvent.isActionDown() == false){
                    return false;
                }

                else if(currentTextName == mCircle.getCurrentTileIndex()){
                    //success = true;
                    complete();
                }

                else if(!(currentTextName == mCircle.getCurrentTileIndex())){
                    fail();
                }

                return true;
            }
        };

        mText = new Text(camera.getCenterX(), camera.getCenterY() + 128, ResourcesManager.getInstance().font, "RedBlueGreenYellow", scene.vbom);
        currentTextName = 3;
        mText.setText(ColorNames.get(currentTextName));
        mText.setColor(Color.BLUE);

        minigame.attachChild(mCircle);
        minigame.attachChild(mText);

        scene.registerTouchArea(mCircle);
    }

    @Override
    public void disposeMinigameScene() {
        engine.unregisterUpdateHandler(timer);
        scene.unregisterTouchArea(mCircle);

        mCircle = null;
        mText = null;
    }

    @Override
    public void loadResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        mTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        mCircleTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mTextureAtlas, activity, "circles128.png", 0, 0, 4, 1);
        mTextureAtlas.load();
    }

    @Override
    public void unloadResources() {
        mTextureAtlas.unload();
        mCircleTiledTextureRegion = null;
    }

    @Override
    public void onFinish() {

    }


}
