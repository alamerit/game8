package com.my.game.pool;

import com.badlogic.gdx.audio.Sound;
import com.my.game.base.SpritesPool;
import com.my.game.math.Rect;
import com.my.game.sprites.Enemy;
import com.my.game.sprites.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;
    private Sound shootSound;
    private MainShip mainShip;
    private ExplosionPool explosionPool;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.shootSound = shootSound;
        this.mainShip = mainShip;
        this.explosionPool = explosionPool;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, shootSound, mainShip);
    }
}
