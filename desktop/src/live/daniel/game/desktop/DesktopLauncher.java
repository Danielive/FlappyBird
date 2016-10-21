package live.daniel.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import live.daniel.game.FlappyNigga;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyNigga.WIDTH;
		config.height = FlappyNigga.HEIGHT;
		config.title = FlappyNigga.TITLE;
		new LwjglApplication(new FlappyNigga(), config);
	}
}
