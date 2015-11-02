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
    public BitmapTextureAtlas menuTA, splashTA, minigameMenuTA;
    public ITextureRegion mBtnPlayTexture, mBtnExitTexture, logoTexture, logoTexture2, mBtnCodeTexture, backTexture, logo2Texture;

    public Font font, menuFont, numberFont;

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
                createFromAsset(menuTA, activity, "buttonExit.png", 0, 64);
        mBtnCodeTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTA, activity, "buttonSP.png", 0, 256);
        logoTexture2 = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTA,activity, "Title2.png", 0, 128);



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
    public void loadMinigameMenuResources()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        minigameMenuTA = new BitmapTextureAtlas(activity.getTextureManager(), 500, 500, TextureOptions.BILINEAR);
        logo2Texture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(minigameMenuTA,activity, "Title1.png", 0, 0);
        minigameMenuTA.load();

    }

    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        FontFactory.setAssetBasePath("font/");
        splashTA = new BitmapTextureAtlas(activity.getTextureManager(), 2000, 700, TextureOptions.BILINEAR);
        logoTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(splashTA,activity, "Title2.png", 0, 64);
        backTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(splashTA,activity, "button.png", 0, 0);

        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(),
                "LeagueGothic-Regular.otf", 50, true, Color.WHITE);
        font.load();

        final ITexture numberFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        numberFont = FontFactory.createFromAsset(activity.getFontManager(), numberFontTexture, activity.getAssets(),
                "Radley-Regular.ttf", 75, true, Color.WHITE);
        numberFont.prepareLetters("69".toCharArray());
        numberFont.load();
        splashTA.load();
        loadMinigameMenuResources();
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
