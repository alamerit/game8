package com.my.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Sprites.Background;
import com.my.game.Sprites.Ship;
import com.my.game.Sprites.Star;
import com.my.game.base.Base2DScreen;
import com.my.game.math.Rect;

public class GameScreen extends Base2DScreen {

    private static final int STAR_COUNT = 64;

    Background background;
    Texture bg;
    TextureAtlas atlas;
    Ship ship;
    TextureAtlas hero;
    Vector2 presd;
    Star[] star;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        hero = new TextureAtlas("textures/ship8t.atlas");
        ship = new Ship(hero,this);
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlas);

        }
        ship = new Ship(hero,this);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
       // ship.setRight(Ship.press + 0.04f);
        ship.update(ship.getBottom());

        //ship.setRight(ship.getRight()+0f);
       // System.out.println(presd + "000000000000000");
      //  if(ship())



    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);

        }

    }

    public void checkCollisions() {

    }

    public void deleteAllDestroyed() {

    }

    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        ship.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        ship.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        ship.tuchDown(touch,pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        ship.keyDown(keycode);
        return super.keyDown(keycode);
    }

}

