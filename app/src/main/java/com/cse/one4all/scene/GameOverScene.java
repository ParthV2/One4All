package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

/**
 * Created by chewb on 11/30/2015.
 */
public class GameOverScene extends BaseScene {

    private Text gameOverText;

    @Override
    public void createScene() {
        setBackground(new Background(Color.BLACK));

        gameOverText = new Text(camera.getCenterX(), camera.getCenterY(), resourcesManager.font, "Game Over!", vbom);
        gameOverText.setColor(Color.RED);

        attachChild(gameOverText);
    }

    @Override
    public void populateScene() {

    }

    @Override
    public void onBackKeyPressed() {
        return;
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.GAMEOVER;
    }

    @Override
    public void disposeScene() {
        gameOverText.detachSelf();
        gameOverText.dispose();
        this.detachSelf();
        this.dispose();
    }
}
