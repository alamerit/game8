package com.my.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.my.game.base.ActionListener;
import com.my.game.base.ScaledTouchUpButton;
import com.my.game.math.Rect;

public class ButtonPlay extends ScaledTouchUpButton {
    public ButtonPlay(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("btPlay"), actionListener, 0.9f);
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
