package com.my.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.base.ActionListener;
import com.my.game.base.ScaledTouchUpButton;
import com.my.game.base.Sprite;
import com.my.game.math.Rect;

public class ButtonNewGame extends ScaledTouchUpButton {


    public ButtonNewGame(TextureAtlas atlas, ActionListener actionListener) {
            super(atlas.findRegion("button_new_game"), actionListener, 0.9f);
            setHeightProportion(0.07f);
        }
    @Override
    public void resize(Rect worldBounds) {
        setBottom(-0.2f);

    }


    }

