package com.my.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.my.game.base.Sprite;
import com.my.game.math.Rect;
import com.my.game.screen.GameScreen;


public class Ship extends Sprite {

    private int pointer;
    private boolean pressed;
    public static float press;
   // Vector2 pre;
  //  Vector2 pre;
    static float znak;
   static float x ;
   static float y;
    Vector2 f = new Vector2();
    Vector2 pre = new Vector2();
    Vector2 v = new Vector2(0.01f, 0f);
    Vector2 r = new Vector2(-0.01f, 0f);
    Vector2 buf = new Vector2(0f, 0f);

    public Ship(TextureAtlas atlas, GameScreen actionListener) {
        super(atlas.findRegion("F5S3"));
        setHeightProportion(0.15f);
      //  pre.set(0.0f,0.00f);

       // setBottom(worldBounds.getBottom() + 0.02f);


    }

    @Override
    public void resize(Rect worldBounds) {
       setBottom(worldBounds.getBottom() + 0.02f);
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        // setRight(worldBounds.getHalfWidth());
        System.out.println(getBottom());

    }



    public static void tuchDown(Vector2 touch, int pointer) {

       x = touch.x;
       y = touch.y;

        }

    @Override
    public void update(float delta) {
        super.update(delta);
        pre.set(pos);
        buf.set(x, -0.4f);
        pos.set(pos.x, -0.4f);
        if (buf.x > pos.x+0.01||buf.x<pos.x-0.01 ) {
            if (buf.x > pos.x) {
                pos.add(v);
                System.out.println(buf.sub(pos).len());
            } else {
                pos.add(-0.01f, 0);
            }

        }else{pos.set(buf);}
    }
    public int keyDown(int keycode) {
        System.out.println(keycode);
        switch (keycode){
            case 21: x=x-0.02f;
            break;
            case 22: x=x+0.02f;
            break;
            default: break;
        }
        return keycode;
    }


//  Syst
    // em.out.println(pre);
    }
