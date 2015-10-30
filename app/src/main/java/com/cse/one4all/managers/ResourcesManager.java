package com.cse.one4all.managers;

import android.graphics.Color;

import com.cse.one4all.GameActivity;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ResourcesManager {

    private static final ResourcesManager INSTANCE = new ResourcesManager();

    public Camera camera;
    public Engine engine;
    public GameActivity activity;
    public VertexBufferObjectManager vbom;
    public BitmapTextureAtlas menuTA, splashTA;
    public ITextureRegion mBtnPlayTexture, mBtnExitTexture, logoTexture, logoTexture2, mBtnCodeTexture;

    public Font font, menuFont;

    public static ResourcesManager getInstance(){
        return INSTANCE;
    }

    public void loadMenuResources(){
        this.loadMenuGraphics();
        this.loadMenuAudio();
    }

    public void loadGameResources(){

        MinigameManager.getInstance().loadResources();

        this.loadGameGraphics();
        this.loadGameFonts();
        this.loadGameAudio();
    }

    public void loadMenuGraphics()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        menuTA = new BitmapTextureAtlas(activity.getTextureManager(), 1200, 500, TextureOptions.BILINEAR);
        mBtnPlayTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTA,activity, "buttonPlay.png", 0, 0);
        mBtnExitTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTA, activity, "buttonExit.png", 0, 0);
        mBtnCodeTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTA, activity, "button.png", 0, 0);
        logoTexture2 = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTA,activity, "Title2.png", 0, 0);



        menuTA.load();
    }

    private void loadMenuAudio() {

    }

    private void loadGameGraphics() {

    }

    private void loadGameFonts() {

    }

    private void loadGameAudio() {

    }

    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        FontFactory.setAssetBasePath("font/");
        splashTA = new BitmapTextureAtlas(activity.getTextureManager(), 800, 128, TextureOptions.BILINEAR);
        logoTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(splashTA,activity, "Title2.png", 0, 0);
        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "Radley-Regular.ttf", 50, true, Color.WHITE);
        font.load();

        final ITexture numberFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        numberFont = FontFactory.createFromAsset(activity.getFontManager(), numberFontTexture, activity.getAssets(), "Radley-Regular.ttf", 75, true, Color.WHITE);
        numberFont.prepareLetters("69".toCharArray());
        numberFont.load();
        splashTA.load();
    }

    public void unloadSplashScreen() {

    }

    public void init(Engine engine, GameActivity activity, Camera camera, VertexBufferObjectManager vbom){
        INSTANCE.engine = engine;
        INSTANCE.activity = activity;
        INSTANCE.camera = camera;
        INSTANCE.vbom = vbom;
    }

}
