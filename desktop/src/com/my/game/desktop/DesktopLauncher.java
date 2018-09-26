package com.my.game.desktop;
/**
 * @ Author  Shafikov Almir
 * */
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.my.game.Star2DGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.resizable = false;
		new LwjglApplication(new Star2DGame(), config);
	}
}
