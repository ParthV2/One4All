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

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

public class MultiplayerScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private Text startGame;
    private Text joinGame;
    private Text serverStarted;
    private Text gameCodeText;
    private Text waitingForPlayersText;

    private MenuScene menuChildScene;

    private static final int MENU_PLAY = 1;
    private static final int MENU_JOIN = 2;

    private Sprite player1;
    private Text player1Text;

    private Sprite player2;
    private Text player2Text;

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch(pMenuItem.getID()) {
            case MENU_PLAY:

                return true;
            case MENU_JOIN:

                return true;
            default:
                return false;
        }
    }

    @Override
    public void createScene() {
        createBackground();
        //createMenuChildScene();
    }

    @Override
    public void populateScene() {

    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().setScene(SceneType.MENU);
        if(PlayerManager.getInstance().isServerRunning()){
            PlayerManager.getInstance().stopServer();
        } else {
            PlayerManager.getInstance().disconnect();
        }
        reset();

        disposeScene();
    }

    @Override
    public void disposeScene() {
        if(gameCodeText != null){
            gameCodeText.detachSelf();
            gameCodeText.dispose();
            gameCodeText = null;
        }
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MULTIPLAYER;
    }

    private void createBackground(){
        setBackground(new Background(Color.BLACK));

        Sprite logo = new Sprite(400,400, ResourcesManager.getInstance().logoTexture2,
                ResourcesManager.getInstance().vbom);

        attachChild(logo);
    }

    public void reset(){
        gameCodeText.detachSelf();
        waitingForPlayersText.detachSelf();
        if(player1 != null){
            player1.detachSelf();
            player1Text.detachSelf();
        }
        if(player2 != null){
            player2.detachSelf();
            player2Text.detachSelf();
        }
        unregisterTouchArea(waitingForPlayersText);
    }

    public void onPlayerDisconnect(String playerName){
        if(playerName.equalsIgnoreCase("Player 1")){
            player1.detachSelf();
            player1Text.detachSelf();
        }

        if(playerName.equalsIgnoreCase("Player 2")){
            player2.detachSelf();
            player2Text.detachSelf();
        }

    }

    public void onPlayerJoined(String playerName){
        if(playerName.equalsIgnoreCase("Player 1")){
            player1 = new Sprite(120, 200, ResourcesManager.getInstance().redPlayerTexture, ResourcesManager.getInstance().vbom);
            player1Text = new Text(120, 120, resourcesManager.font, PlayerManager.getInstance().player.getPlayerName() == "Player 1" ? "You" : "Player 1", vbom);
            player1Text.setColor(Color.RED);
            attachChild(player1);
            attachChild(player1Text);

            waitingForPlayersText.setText("Press to start");
            waitingForPlayersText.setColor(Color.GREEN);
        }

        if(playerName.equalsIgnoreCase("Player 2")){
            player2 = new Sprite(260, 200, ResourcesManager.getInstance().yellowPlayerTexture, ResourcesManager.getInstance().vbom);
            player2Text = new Text(260, 120, resourcesManager.font, PlayerManager.getInstance().player.getPlayerName() == "Player 2" ? "You" : "Player 2", vbom);
            player2Text.setColor(Color.YELLOW);
            attachChild(player2);
            attachChild(player2Text);

            waitingForPlayersText.setText("Press to start");
            waitingForPlayersText.setColor(Color.GREEN);
        }
    }

    public void onServerStart(String gameCode){
        gameCodeText = new Text(camera.getCenterX(), 300, resourcesManager.font, "IP Address: " + gameCode, vbom);
        waitingForPlayersText = new Text(camera.getCenterX(), 50, resourcesManager.font, "Press to start", vbom){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown()) {

                    if(waitingForPlayersText.getText() == "Press to start" && PlayerManager.getInstance().player.getPlayerName().equalsIgnoreCase("Player 1")){
                        PlayerManager.getInstance().sendStartGame();
                    }

                    return true;
                }
                return false;
            }
        };
        waitingForPlayersText.setColor(Color.GREEN);

        attachChild(gameCodeText);
        attachChild(waitingForPlayersText);

        registerTouchArea(waitingForPlayersText);
    }
}
