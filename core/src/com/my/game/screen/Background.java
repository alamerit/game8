package com.my.game.screen;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.base.Sprite;
import com.my.game.math.Rect;

public class Background extends Sprite {
    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        System.out.println("krhjgoiege");
        return super.touchDown(touch, pointer);
    }
}