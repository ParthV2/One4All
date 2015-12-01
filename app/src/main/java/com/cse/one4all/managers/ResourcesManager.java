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
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public class ResourcesManager {

    private static final ResourcesManager INSTANCE = new ResourcesManager();

    public Camera camera;
    public Engine engine;
    public GameActivity activity;
    public VertexBufferObjectManager vbom;
    public BitmapTextureAtlas splashTA, minigameMenuTA;
    public ITextureRegion mBtnPlayTexture, logoTexture, logoTexture2, mBtnCodeTexture,
            backTexture, logo2Texture, mBtnTapColorTexture, mBtnHelicopterTexture, mBtnMathGameTexture,
            mBtnClick6Texture;

    public Font font, numberFont, numberFont2, p1LivesFont;

    public BuildableBitmapTextureAtlas menuTextureAtlas;

    public static ResourcesManager getInstance(){
        return INSTANCE;
    }

    //---------------------------------------------
    // Splash
    //---------------------------------------------

    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        splashTA = new BitmapTextureAtlas(activity.getTextureManager(), 2000, 700, TextureOptions.BILINEAR);
        logoTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(splashTA, activity, "Title1.png", 0, 64);
        backTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(splashTA, activity, "buttonBack.png", 0, 0);



        splashTA.load();
    }

    public void unloadSplashScreen() {
        splashTA.unload();
    }

    //---------------------------------------------
    // MAIN MENU
    //---------------------------------------------

    public ITextureRegion joinGameTexture;

    public void loadMenuResources(){
        this.loadMenuGraphics();
        this.loadMenuAudio();
        this.loadMenuFonts();
    }

    public void loadMinigameMenuResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        minigameMenuTA = new BitmapTextureAtlas(activity.getTextureManager(), 512, 256, TextureOptions.BILINEAR);
        logo2Texture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(minigameMenuTA,activity, "Title1.png", 0, 0);
        minigameMenuTA.load();
    }

    public void loadMenuGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        mBtnPlayTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "buttonPlay.png");
        joinGameTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "buttonJoin.png");
        mBtnCodeTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "buttonSP.png");
        logoTexture2 = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "Title2.png");
        mBtnClick6Texture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "buttonClick6.png");
        mBtnHelicopterTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "buttonHeli.png");
        mBtnTapColorTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "buttonTapColor.png");
        mBtnMathGameTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "buttonMath.png");



        try {
            this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            this.menuTextureAtlas.load();
        } catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
    }

    private void loadMenuAudio() {

    }

    private void loadMenuFonts(){
        FontFactory.setAssetBasePath("font/");

        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture mainFontTexture2 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture mainFontTexture3 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture mainFontTexture4 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(),
                "LeagueGothic-Regular.otf", 50, true, Color.WHITE);
        numberFont = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture2, activity.getAssets(),
                "LeagueGothic-Regular.otf", 128, true, Color.WHITE);
        numberFont2 = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture3, activity.getAssets(),
                "LeagueGothic-Regular.otf", 128, true, Color.BLACK);
        p1LivesFont = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture4, activity.getAssets(),
                "LeagueGothic-Regular.otf", 32, true, Color.BLACK);

        font.load();
        numberFont.load();
        numberFont2.load();
        p1LivesFont.load();

    }

    public void loadMainMenuTextures() {
        this.menuTextureAtlas.load();
        this.minigameMenuTA.load();
        this.multiplayerMenuTA.load();
    }

    public void unloadMainMenuTextures(){
        this.menuTextureAtlas.unload();
        this.minigameMenuTA.unload();
        this.multiplayerMenuTA.unload();
    }

    //---------------------------------------------
    // GAME
    //---------------------------------------------

    public BitmapTextureAtlas resultTA;
    public ITextureRegion greenCheckTR, redX_TR;

    public void loadGameResources(){

        this.loadGameGraphics();
        this.loadGameFonts();
        this.loadGameAudio();

    }

    private void loadGameGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        resultTA = new BitmapTextureAtlas(ResourcesManager.getInstance().activity.getTextureManager(), 512,512);
        greenCheckTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(resultTA, ResourcesManager.getInstance().activity, "greenCheck.png", 0,0);
        redX_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(resultTA, ResourcesManager.getInstance().activity, "redX.png", 0, 256);

        resultTA.load();
    }

    private void loadGameFonts() {

    }

    private void loadGameAudio() {

    }

    public void unloadGameTextures(){
        resultTA.unload();
    }


    //---------------------------------------------
    // MULTIPLAYER MENU
    //---------------------------------------------

    private BuildableBitmapTextureAtlas multiplayerMenuTA;
    public ITextureRegion redPlayerTexture, bluePlayerTexture, yellowPlayerTexture;

    public void loadMultiplayerMenuResources(){
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/multiplayer/");
        multiplayerMenuTA = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        redPlayerTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(multiplayerMenuTA, activity, "redPlayer.png");
        bluePlayerTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(multiplayerMenuTA, activity, "bluePlayer.png");
        yellowPlayerTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(multiplayerMenuTA, activity, "yellowPlayer.png");
        try {
            this.multiplayerMenuTA.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            this.multiplayerMenuTA.load();
        } catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
    }

    public void init(Engine engine, GameActivity activity, Camera camera, VertexBufferObjectManager vbom){
        INSTANCE.engine = engine;
        INSTANCE.activity = activity;
        INSTANCE.camera = camera;
        INSTANCE.vbom = vbom;
    }

}
