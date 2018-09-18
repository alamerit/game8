package com.my.game;
/**
 * @ Author  Shabikov Almir
 * */
        import com.badlogic.gdx.Game;
        import com.my.game.screen.MenuScreen;

public class Star2DGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
