package com.my.game.screen;
/**
 * @ Author  Shabikov Almir
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.my.game.Game;
import com.my.game.Star2DGame;
import com.my.game.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    SpriteBatch batch;
    Texture img;
    Texture img2;
    Vector2 pos;
    Vector2 v;
    Vector2 v9;

    public MenuScreen(com.badlogic.gdx.Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        img = new Texture("boll1.jpg");
    //    img2 = new Texture("kosm");

        //   img2 = new TextureRegion("kosm.jpg",300,100,100,100);
        pos = new Vector2(0f,0f);
        v = new Vector2();
        v9 = new Vector2();
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        pos.add(v);
        if(pos.len()+10>v9.len()&&pos.len()<v9.len()+15)
        {pos.set(v9);
        v.set(0.f,0.f);}
        v.set(v9.x/10 - pos.x/10,v9.y/10 - pos.y/10);
       // v.set(v9);
       // v.sub(pos);

        System.out.println(pos +" "+v9);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
       img2.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown screenX--------- = " + screenX + " screenY = "+ (Gdx.graphics.getHeight() - screenY));
        screenY = (Gdx.graphics.getHeight() - screenY);
      //   v.set((screenX/100),(screenY/100));
        v9.set(screenX,screenY);
     //   v.set(v.sub(pos.x/100,pos.y/100));
        System.out.println(v9 + " ++++ "+v+ "    pos "+pos.x+"---="+pos.y);



        return super.touchDown(screenX, screenY, pointer, button);
    }
    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        switch (keycode){
            case 19:
               v9.add(0f,10f);
                return false;
            case 20:
                v9.add(0f,-10f);
                return false;
            case 21:
                v9.add(-10f,0f);
                return false;
            case 22:
                v9.add(10f,0f);
                return false;
        }
        return false;
    }

    }

