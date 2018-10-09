package com.my.game.pool;

import com.my.game.base.SpritesPool;
import com.my.game.sprites.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }


}
