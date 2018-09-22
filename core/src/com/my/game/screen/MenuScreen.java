package com.my.game.screen;
/**
 * @ Author  Shabikov Almir
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Game;
import com.my.game.Star2DGame;
import com.my.game.base.Base2DScreen;
import com.my.game.base.Sprite;
import com.my.game.math.Rect;

public class MenuScreen extends Base2DScreen {

    Background background;
    Texture bg;
    Vector2 pos;
    Texture img;
    Exit exit;


    public MenuScreen(com.badlogic.gdx.Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("kosm1.jpg");
        img = new Texture("badlogic.jpg");
        pos = new Vector2(0f,0f);
        background = new Background(new TextureRegion(bg));
        exit = new Exit(new TextureRegion(img));
        exit.setHeightProportion(0.1f);
        exit.setBottom(0.4f);
        exit.setRight(0.5f);

    }


    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0.4f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        exit.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        background.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {

        return super.touchDown(touch, pointer);
    }
}
