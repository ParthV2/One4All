package com.cse.one4all.scene;

import android.app.AlertDialog;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

public class MainMenuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private MenuScene menuChildScene;

    private static final int MENU_PLAY = 0;
    private static final int MENU_SINGLE_PLAYER = 1;
    private static final int MENU_EXIT = 2;

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
        System.exit(0);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MENU;
    }

    @Override
    public void disposeScene() {

    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch(pMenuItem.getID()) {
            case MENU_PLAY:
                SceneManager.getInstance().loadGameScene(engine);
                MinigameManager.getInstance().setRandomMinigame();
                return true;
            case MENU_SINGLE_PLAYER:
                SceneManager.getInstance().setScene(SceneType.MINIGAMEMENU);
                return true;
            default:
                return false;
        }
    }

    private void createBackground(){
        setBackground(new Background(Color.BLACK));

        Sprite logo = new Sprite(400,400, ResourcesManager.getInstance().logoTexture2,
                ResourcesManager.getInstance().vbom);

        attachChild(logo);
    }

    private void createMenuChildScene() {
        menuChildScene = new MenuScene(camera);
        menuChildScene.setPosition(camera.getCenterX(), camera.getCenterY());


        final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.mBtnPlayTexture, vbom), 1.1f, 1);
        final IMenuItem singlePlayerMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SINGLE_PLAYER, resourcesManager.mBtnCodeTexture, vbom), 1.1f, 1);

        menuChildScene.addMenuItem(playMenuItem);
        menuChildScene.addMenuItem(singlePlayerMenuItem);

        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);

        playMenuItem.setPosition(0, 0);
        singlePlayerMenuItem.setPosition(0, playMenuItem.getY() - playMenuItem.getHeight() - 20);

        menuChildScene.setOnMenuItemClickListener(this);

        setChildScene(menuChildScene);
    }
}
