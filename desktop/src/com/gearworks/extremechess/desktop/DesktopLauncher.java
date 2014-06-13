package com.gearworks.extremechess.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gearworks.Client;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Client.V_WIDTH * Client.SCALE;
		config.height = Client.V_HEIGHT * Client.SCALE;
		new LwjglApplication(new Client(), config);
	}
}
