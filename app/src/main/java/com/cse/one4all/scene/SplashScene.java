package com.cse.one4all.scene;

import com.cse.one4all.base.BaseScene;
import com.cse.one4all.managers.ResourcesManager;
import com.cse.one4all.managers.SceneManager;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

public class SplashScene extends BaseScene {

    @Override
    public void createScene() {
        setBackground(new Background(Color.BLACK));
        Sprite logo = new Sprite(400, 240, ResourcesManager.getInstance().logoTexture, ResourcesManager.getInstance().vbom);
        attachChild(logo);
        //final Text text = new Text(400, 240, this.resourcesManager.font, "One 4 All", vbom);
        //text.setPosition(getX() - (text.getWidth() / 2f), getY() - (text.getHeight() / 2f));
        //attachChild(text);
    }

    @Override
    public void populateScene() {

    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SPLASH;
    }

    @Override
    public void disposeScene() {
        this.detachSelf();
        this.dispose();
    }

}
