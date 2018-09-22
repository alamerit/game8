package com.my.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.base.Sprite;

import javax.swing.plaf.synth.Region;

public class Exit extends Sprite {
    Texture img2;
    Vector2 pos1;
    Sprite region1;
    TextureRegion region;

    public Exit(TextureRegion textureRegion) {
        super(textureRegion);
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        System.out.println("aaaaaaaaaaaaaaaaa");
        return super.touchDown(touch, pointer);
    }
}
