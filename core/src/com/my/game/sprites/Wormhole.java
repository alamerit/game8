package com.my.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.my.game.base.Sprite;
import com.my.game.math.Rect;
import com.my.game.math.Rnd;


public class Wormhole extends Sprite {
    private Vector2 v = new Vector2();
    private Rect worldBounds;
    public Wormhole(TextureAtlas atlas) {
        super(atlas.findRegion("blackhole"));
        v.set( 0.0005f, -0.005f);
        setHeightProportion(5f);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);

        checkAndHandleBounds();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX, posY);
    }
    private void checkAndHandleBounds() {
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
    }
}
