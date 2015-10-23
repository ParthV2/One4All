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
    public BitmapTextureAtlas menuTA;
    public ITextureRegion mBtnPlayTexture;

    public Font font;

    private BitmapTextureAtlas splashTextureAtlas;

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
        menuTA = new BitmapTextureAtlas(activity.getTextureManager(), 600, 200, TextureOptions.BILINEAR);
        mBtnPlayTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(menuTA,activity, "button.png", 0, 0);


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
        FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "Radley-Regular.ttf", 50, true, Color.WHITE);
        font.load();
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
