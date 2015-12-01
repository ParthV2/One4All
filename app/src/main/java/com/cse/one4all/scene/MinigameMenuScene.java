package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.PlayerManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.color.Color;


public class MinigameMenuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private MenuScene miniMenuChildScene;

    private static final int MENU_TAP_COLOR = 0;
    private static final int MENU_HELICOPTER = 1;
    private static final int MENU_CLICK6 = 2;
    private static final int MENU_MATH = 3;

    @Override
    public void createScene() {

        createBackground();
        createMenuChildScene();
    }

    @Override
    public void populateScene() {

    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().setScene(SceneType.MENU);
        PlayerManager.getInstance().disconnect();
        PlayerManager.getInstance().stopServer();
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MINIGAMEMENU;
    }

    @Override
    public void disposeScene() {

    }

    private void createBackground(){
        setBackground(new Background(Color.BLACK));

        Sprite logo = new Sprite(400, 400, ResourcesManager.getInstance().logoTexture2,
                ResourcesManager.getInstance().vbom);

        attachChild(logo);
    }

    private void createMenuChildScene()
    {
        miniMenuChildScene = new MenuScene(camera);
        miniMenuChildScene.setAnchorCenter(0, 0);
        miniMenuChildScene.setPosition(0, 0);

        final IMenuItem tapColorMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(
                MENU_TAP_COLOR, resourcesManager.mBtnTapColorTexture, vbom), 1.1f, 1);
        final IMenuItem helicopterMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(
                MENU_HELICOPTER, resourcesManager.mBtnHelicopterTexture, vbom), 1.1f, 1);
        final IMenuItem click6MenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(
                MENU_CLICK6, resourcesManager.mBtnClick6Texture, vbom), 1.1f, 1);
        final IMenuItem mathMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(
                MENU_MATH, resourcesManager.mBtnMathGameTexture, vbom), 1.1f, 1);

        miniMenuChildScene.addMenuItem(tapColorMenuItem);
        miniMenuChildScene.addMenuItem(helicopterMenuItem);
        miniMenuChildScene.addMenuItem(click6MenuItem);
        miniMenuChildScene.addMenuItem(mathMenuItem);

        miniMenuChildScene.buildAnimations();
        miniMenuChildScene.setBackgroundEnabled(false);

        tapColorMenuItem.setPosition(300, 150);
        helicopterMenuItem.setPosition(300, 250);
        click6MenuItem.setPosition(500, 150);
        mathMenuItem.setPosition(500, 250);

        miniMenuChildScene.setOnMenuItemClickListener(this);

        setChildScene(miniMenuChildScene);

    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {

        switch(pMenuItem.getID()){
            case MENU_TAP_COLOR:
                SceneManager.getInstance().loadGameScene(engine);
                MinigameManager.getInstance().startMinigame("Tap the Color");
                //MinigameManager.getInstance().startMinigame("Hexagons");
                return true;
            case MENU_HELICOPTER:
                SceneManager.getInstance().loadGameScene(engine);
                MinigameManager.getInstance().startMinigame("Helicopter Game");
                return true;
            case MENU_CLICK6:
                SceneManager.getInstance().loadGameScene(engine);
                MinigameManager.getInstance().startMinigame("Click the 6's");
                return true;
            case MENU_MATH:
                SceneManager.getInstance().loadGameScene(engine);
                MinigameManager.getInstance().startMinigame("Math Game");
                return true;
            default:
                return false;
        }
    }
}
