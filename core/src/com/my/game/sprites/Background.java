package com.my.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
}

