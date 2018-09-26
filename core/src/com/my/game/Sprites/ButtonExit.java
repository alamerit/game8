package com.my.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.my.game.base.ActionListener;
import com.my.game.base.ScaledTouchUpButton;
import com.my.game.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {

    public ButtonExit(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("btExit"), actionListener, 0.9f);
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
