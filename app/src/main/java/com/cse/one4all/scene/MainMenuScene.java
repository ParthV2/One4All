package com.cse.one4all.scene;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import com.cse.one4all.Player;
import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.MinigameManager;
import com.cse.one4all.managers.PlayerManager;
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
    private static final int MENU_JOIN_GAME = 1;
    private static final int MENU_SINGLE_PLAYER = 2;


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
                //MinigameManager.getInstance().startGame();
                SceneManager.getInstance().setScene(SceneType.MULTIPLAYER);
                PlayerManager.getInstance().startMultiplayerGame();

                return true;
            case MENU_JOIN_GAME:
                showJoinGameDialog();
                return true;
            case MENU_SINGLE_PLAYER:
                SceneManager.getInstance().setScene(SceneType.MINIGAMEMENU);
                PlayerManager.getInstance().startMultiplayerGame();
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
        final IMenuItem joinGameMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_JOIN_GAME, resourcesManager.joinGameTexture, vbom), 1.1f, 1);
        final IMenuItem singlePlayerMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SINGLE_PLAYER, resourcesManager.mBtnCodeTexture, vbom), 1.1f, 1);

        menuChildScene.addMenuItem(playMenuItem);
        menuChildScene.addMenuItem(joinGameMenuItem);
        menuChildScene.addMenuItem(singlePlayerMenuItem);

        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);

        playMenuItem.setPosition(0, 10);
        joinGameMenuItem.setPosition(0, playMenuItem.getY() - playMenuItem.getHeight() - 20);
        singlePlayerMenuItem.setPosition(0, joinGameMenuItem.getY() - joinGameMenuItem.getHeight() - 20);

        menuChildScene.setOnMenuItemClickListener(this);

        setChildScene(menuChildScene);
    }

    private void onJoinGame(String gameCode){

        if(PlayerManager.getInstance().joinGame(gameCode)){
            SceneManager.getInstance().setScene(SceneType.MULTIPLAYER);
            PlayerManager.getInstance().player1 = new Player("Player 1");
            PlayerManager.getInstance().player2 = new Player("Player 2");
            PlayerManager.getInstance().player = PlayerManager.getInstance().player2;

            SceneManager.getInstance().multiplayerScene.onServerStart(gameCode);
            SceneManager.getInstance().multiplayerScene.onPlayerJoined("Player 1");
            SceneManager.getInstance().multiplayerScene.onPlayerJoined("Player 2");
        } else {

        }
    }

    private void showJoinGameDialog(){
        game.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final EditText portEditText = new EditText(game);
                portEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                AlertDialog.Builder alert = new AlertDialog.Builder(game);
                alert.setView(portEditText);
                alert.setTitle("Enter Game Code:");
                alert.setMessage("");
                alert.setCancelable(false);
                alert.setPositiveButton("Join", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        onJoinGame(portEditText.getText().toString());
                    }
                });
                alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface pDialog, final int pWhich) {

                    }
                });
                alert.show();
            }
        });
    }
}
