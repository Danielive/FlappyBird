package live.daniel.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import live.daniel.game.FlappyNigga;

/**
 * Created by Daniel on 21.01.2016.
 */
public class GameOver extends State {

    private Texture background;
    private Texture gameover;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyNigga.WIDTH / 2, FlappyNigga.HEIGHT / 2);
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){ //Вызов по клику
            gsm.set(new PlayState(gsm)); //Добавляем новый экран на вершину стека
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth / 2), 0); //Текстура, положение, предел отрисовки
        sb.draw(gameover, camera.position.x - gameover.getWidth() / 2, camera.viewportHeight / 2 - gameover.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
    }
}
