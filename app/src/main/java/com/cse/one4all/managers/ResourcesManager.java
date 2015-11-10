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
    public ITextureRegion mBtnPlayTexture, mBtnExitTexture, logoTexture, logoTexture2, mBtnCodeTexture, backTexture, logo2Texture;

    public Font font, menuFont, numberFont, p1LivesFont, p2LivesFont, p3LivesFont, p4LivesFont;

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
                createFromAsset(splashTA, activity, "Title2.png", 0, 64);
        backTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(splashTA, activity, "buttonBack.png", 0, 0);



        splashTA.load();
    }

    public void unloadSplashScreen() {
        splashTA.unload();
    }

    //---------------------------------------------
    // MENU
    //---------------------------------------------

    public void loadMenuResources(){
        this.loadMenuGraphics();
        this.loadMenuAudio();
        this.loadMenuFonts();
    }

    public void loadMenuGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        mBtnPlayTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "buttonPlay.png");
        mBtnCodeTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "buttonSP.png");
        logoTexture2 = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTextureAtlas, activity, "Title2.png");

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

        font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(),
                "LeagueGothic-Regular.otf", 50, true, Color.WHITE);
        font.load();

        final ITexture mainFontTexture2 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        numberFont = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture2, activity.getAssets(),
                "LeagueGothic-Regular.otf", 128, true, Color.WHITE);
        numberFont.load();

        final ITexture mainFontTexture3 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        p1LivesFont = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture3, activity.getAssets(),
                "LeagueGothic-Regular.otf", 25, true, Color.CYAN);
        p1LivesFont.load();

        final ITexture mainFontTexture4 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        p2LivesFont = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture4, activity.getAssets(),
                "LeagueGothic-Regular.otf", 25, true, Color.RED);
        p2LivesFont.load();

        final ITexture mainFontTexture5 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        p3LivesFont = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture5, activity.getAssets(),
                "LeagueGothic-Regular.otf", 25, true, Color.YELLOW);
        p3LivesFont.load();

        final ITexture mainFontTexture6 = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        p4LivesFont = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture6, activity.getAssets(),
                "LeagueGothic-Regular.otf", 25, true, Color.rgb(0,255,0));
        p4LivesFont.load();
    }

    public void loadMainMenuTextures() {
        this.menuTextureAtlas.load();
    }

    public void unloadMainMenuTextures(){
        this.menuTextureAtlas.unload();
    }

    public void loadGameResources(){

        this.loadGameGraphics();
        this.loadGameFonts();
        this.loadGameAudio();
    }

    private void loadGameGraphics() {

    }

    private void loadGameFonts() {

    }

    private void loadGameAudio() {

    }







    public void loadMinigameMenuResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        minigameMenuTA = new BitmapTextureAtlas(activity.getTextureManager(), 500, 500, TextureOptions.BILINEAR);
        logo2Texture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(minigameMenuTA,activity, "Title1.png", 0, 0);
        minigameMenuTA.load();
    }

    public void unloadGameTextures(){

    }

    public void unloadMinigameMenuTextures(){
        minigameMenuTA.unload();
    }

    public void init(Engine engine, GameActivity activity, Camera camera, VertexBufferObjectManager vbom){
        INSTANCE.engine = engine;
        INSTANCE.activity = activity;
        INSTANCE.camera = camera;
        INSTANCE.vbom = vbom;
    }

}
