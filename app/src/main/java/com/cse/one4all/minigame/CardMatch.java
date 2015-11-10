package com.cse.one4all.minigame;

import android.graphics.Color;

import com.cse.one4all.base.BaseMinigame;
import com.cse.one4all.managers.ResourcesManager;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthew on 11/5/2015.
 */
public class CardMatch extends BaseMinigame
{
    public BitmapTextureAtlas gameTA;
    private ITextureRegion backTexture, twoTexture, threeTexture, fourTexture, fiveTexture, sixTexture,
            sevenTexture, eightTexture, nineTexture, tenTexture;
    private Sprite back1, back2, back3, back4, back5, back6, back7, back8;
    private ArrayList<Sprite> cards = new ArrayList<Sprite>(CARD_COUNT);
    private List<Integer> cardPos = new ArrayList<Integer>(CARD_COUNT);
    private List<Integer> nums = new ArrayList<Integer>(4);
    List<Integer> randomNumbers = new ArrayList<Integer>(4);
    private static final int ROWS = 2;
    private static final int COLUMNS = 4;
    private static final int CARD_COUNT = 8;
    private int x = 120;
    private int y = 380;
    private int count = 0;
    private ArrayList<ITextureRegion> textures = new ArrayList<ITextureRegion>(CARD_COUNT+1);

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void createMinigameScene()
    {


        textures.add(twoTexture);
        textures.add(threeTexture);
        textures.add(fourTexture);
        textures.add(fiveTexture);
        textures.add(sixTexture);
        textures.add(sevenTexture);
        textures.add(eightTexture);
        textures.add(nineTexture);
        textures.add(tenTexture);
        for(int i = 0; i < 4; i++){
            int pos = random.nextInt(ROWS * COLUMNS);
            cardPos.add(pos);
        }
//        for(int i = 0; i < 4; i++){
//            int pos = RANDOM.nextInt(3);
//            nums.add(pos);
//        }


        for (int i=0; i<4; i++)
        {
            int number;

            do number = random.nextInt();
            while (randomNumbers.contains(number));

            randomNumbers.add(number);
        }


//        while(count < 2)
//        {
//
//            game();
//        }
        game();
//        Text message = new Text(550, 75,  ResourcesManager.getInstance().font, "You're a genius!!", scene.vbom);
//        scene.attachChild(message);
//        if(count == 2)
//        {
//            remove();
//            game();
//
//        }


    }
    public void game()
    {
        back1 = new Sprite(x, y, backTexture, scene.vbom)
        {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                //                if (pSceneTouchEvent.isActionDown() == false)
                //                {
                //                    scene.attachChild(cards.get(1));
                //                }
                //                cards.get(cardPos.get(1)).setPosition(x,y);
                //                scene.detachChild(back1);
                //                Sprite temp = cards.get(cardPos.get(1));
                //                scene.attachChild(temp);
                count++;
                Sprite temp = new Sprite(120, y, textures.get(cardPos.get(randomNumbers.get(0))), scene.vbom);
                scene.detachChild(back1);
                scene.attachChild(temp);
                cards.add(temp);
                return true;
            }
        };
        x += 175;
        scene.attachChild(back1);
        scene.registerTouchArea(back1);
        back2 = new Sprite(x, y, backTexture, scene.vbom) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                count++;
                Sprite temp = new Sprite(295, y, textures.get(cardPos.get(randomNumbers.get(1))), scene.vbom);
                scene.detachChild(back2);
                scene.attachChild(temp);
                cards.add(temp);
                return true;
            }
        };
        x += 175;
        scene.attachChild(back2);
        scene.registerTouchArea(back2);
        back3 = new Sprite(x, y, backTexture, scene.vbom) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                count++;
                Sprite temp = new Sprite(470, y, textures.get(cardPos.get(randomNumbers.get(2))), scene.vbom);
                scene.detachChild(back3);
                scene.attachChild(temp);
                cards.add(temp);
                return true;
            }
        };
        x += 175;
        scene.attachChild(back3);
        scene.registerTouchArea(back3);
        back4 = new Sprite(x, y, backTexture, scene.vbom) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                count++;
                Sprite temp = new Sprite(645, y, textures.get(cardPos.get(randomNumbers.get(3))), scene.vbom);
                scene.detachChild(back4);
                scene.attachChild(temp);
                cards.add(temp);
                return true;
            }
        };
        scene.attachChild(back4);
        scene.registerTouchArea(back4);
    }

    public void remove()
    {
        for(int i =0; i < 4; i++)
        {
            scene.detachChild(cards.get(i));
        }
    }

    @Override
    public void disposeMinigameScene() {

    }

    @Override
    public void loadResources()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        gameTA = new BitmapTextureAtlas(activity.getTextureManager(), 1300, 1600, TextureOptions.BILINEAR);
        FontFactory.setAssetBasePath("font/");
        backTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "backside.png", 0, 256);
        twoTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "2diamonds.png", 0, 512);
        threeTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "3hearts.png", 256, 0);
        fourTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "4spades.png", 256, 256);
        fiveTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "5spades.png",256, 512);
        sixTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "6diamonds.png", 128, 0);
        sevenTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "7hearts.png", 128, 256);
        eightTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "8clubs.png", 128, 512);
        nineTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "9spades.png", 0, 768);
        tenTexture = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(gameTA,activity, "10diamonds.png", 0,0);

        gameTA.load();

    }

    @Override
    public void unloadResources() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onStart() {

    }
}
