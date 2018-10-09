package com.my.game.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.my.game.base.ActionListener;
import com.my.game.base.Base2DScreen;
import com.my.game.base.Font;
import com.my.game.math.Rect;
import com.my.game.pool.BulletPool;
import com.my.game.pool.EnemyPool;
import com.my.game.pool.ExplosionPool;
import com.my.game.sprites.Background;
import com.my.game.sprites.Bullet;
import com.my.game.sprites.ButtonNewGame;
import com.my.game.sprites.ButtonPlay;
import com.my.game.sprites.Enemy;
import com.my.game.sprites.Explosion;
import com.my.game.sprites.MainShip;
import com.my.game.sprites.MessageGameOver;
import com.my.game.sprites.Star;
import com.my.game.sprites.Wormhole;
import com.my.game.utils.EnemiesEmitter;

import java.util.List;

public class GameScreen extends Base2DScreen implements ActionListener {

    private static final int STAR_COUNT = 64;

    private static final String FRAGS = "Frags: ";
    private static final String HP = "HP: ";
    private static final String LEVEL = "Level: ";
    protected float reloadTimer;
    protected float reloadInterval = 0.1f;
    protected float color = 1;
    private float r =2;
    private enum State { PLAYING, GAME_OVER }

    Background background;
    Texture bg;
    TextureAtlas atlas;
    TextureAtlas atlasBoss;

    Star[] star;

    Wormhole wormhole;

    MainShip mainShip;

    BulletPool bulletPool;

    Music music;
    Sound laserSound;
    Sound bulletSound;
    Sound explosionSound;

    EnemyPool enemyPool;
    EnemiesEmitter enemiesEmitter;

    ExplosionPool explosionPool;

    State state;

    MessageGameOver messageGameOver;
    ButtonNewGame buttonNewGame;

    int frags;

    Font font;
    StringBuilder sbFrags = new StringBuilder();
    StringBuilder sbHP = new StringBuilder();
    StringBuilder sbLevel = new StringBuilder();

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
        font = new Font("font/font.fnt", "font/font.png");
        font.setFontSize(0.03f);
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        bg = new Texture("bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/ships.atlas");        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlas);
        }
        wormhole = new Wormhole(atlas);
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, explosionSound);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, laserSound);
        enemyPool = new EnemyPool(bulletPool, explosionPool, bulletSound, mainShip);
        enemiesEmitter = new EnemiesEmitter(enemyPool, atlas, worldBounds);
        messageGameOver = new MessageGameOver(atlas);
        buttonNewGame = new ButtonNewGame(atlas, this);
        startNewGame();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
        wormhole.update(delta);
        explosionPool.updateActiveObjects(delta);
        bulletPool.updateActiveObjects(delta);
        if (mainShip.isDestroyed()) {
            state = State.GAME_OVER;
        }
        switch (state) {
            case PLAYING:
                batch.setColor(1,1,1,1);
                mainShip.update(delta);
                enemyPool.updateActiveObjects(delta);
                enemiesEmitter.generateEnemies(delta, frags);
                break;
            case GAME_OVER:
               reloadTimer += delta;
                if (reloadTimer >= reloadInterval) {
                    reloadTimer = 0f;
                    if (color > 0.03){ color = color - delta;}
                        else {color = 0;}
               }
                batch.setColor(color,color,color,1);


                break;
        }
    }

    public void checkCollisions() {
        // Столкновение кораблей
        List<Enemy> enemyList = enemyPool.getActiveObjects();
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            float minDist = enemy.getHalfWidth() + mainShip.getHalfWidth();
            if (enemy.pos.dst2(mainShip.pos) < minDist * minDist) {
                enemy.destroy();
                enemy.boom();
                mainShip.damage(10 * enemy.getBulletDamage());
                return;
            }
        }

        // Попадание пули во вражеский корабль
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            for (Bullet bullet : bulletList) {
                if (bullet.getOwner() != mainShip || bullet.isDestroyed()) {
                    continue;
                }
                if (enemy.isBulletCollision(bullet)) {
                    bullet.destroy();
                    enemy.damage(bullet.getDamage());
                    if (enemy.isDestroyed()) {
                        frags++;
                    }
                }
            }
        }

        // Попадание пули в игровой корабль
        for (Bullet bullet : bulletList) {
            if (bullet.getOwner() == mainShip || bullet.isDestroyed()) {
                continue;
            }
            if (mainShip.isBulletCollision(bullet)) {
                mainShip.damage(bullet.getDamage());
                bullet.destroy();
            }
        }
    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0.4f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        wormhole.setAngle(r = r-0.01f);
        wormhole.draw(batch);
        mainShip.draw(batch);
        bulletPool.drawActiveObjects(batch);
        enemyPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        if (state == State.GAME_OVER) {
            batch.setColor(1,1,1,1);
            messageGameOver.draw(batch);
            buttonNewGame.draw(batch);

        }
        printInfo();
        batch.end();
    }

    private void printInfo() {
        sbFrags.setLength(0);
        font.draw(batch, sbFrags.append(FRAGS).append(frags), worldBounds.getLeft(), worldBounds.getTop() - 0.01f, Align.left);
        sbHP.setLength(0);
        font.draw(batch, sbHP.append(HP).append(mainShip.getHp()), worldBounds.pos.x, worldBounds.getTop() - 0.01f, Align.center);
        sbLevel.setLength(0);
        font.draw(batch, sbLevel.append(LEVEL).append(enemiesEmitter.getLevel()), worldBounds.getRight(), worldBounds.getTop() - 0.01f, Align.right);

    }

    @Override
    protected void resize(Rect worldBounds) {
        System.out.println("resize");
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        wormhole.resize(worldBounds);
        mainShip.resize(worldBounds);

    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        wormhole.destroy();
        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        music.dispose();
        laserSound.dispose();
        font.dispose();
        super.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyDown(keycode);
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyUp(keycode);
        }
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        if (state == State.GAME_OVER) {
            buttonNewGame.touchDown(touch, pointer);
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
        if (state == State.GAME_OVER) {
            buttonNewGame.touchUp(touch, pointer);
        }
        return super.touchUp(touch, pointer);
    }

    private void startNewGame() {
        state = State.PLAYING;

        mainShip.startNewGame();
        frags = 0;

        enemiesEmitter.setLevel(1);
        enemiesEmitter.setBoss(1);

        bulletPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == buttonNewGame) {
            startNewGame();
        }
    }
}
