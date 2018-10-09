package com.my.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.my.game.Star2DGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//float aspect = 3f/4f;
		config.resizable = true;
		//config.width = 300;
		//config.height = (int) (config.width / aspect);
		new LwjglApplication(new Star2DGame(), config);
	}
}
