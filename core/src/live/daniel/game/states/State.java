package live.daniel.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by Daniel on 21.01.2016.
 */
public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput(); //Опрос ввода (был ли ввод с тача)
    public abstract void update(float dt); //Обновление картинки
    public abstract void render(SpriteBatch sb); //Отрисовка экрана
    public abstract void dispose(); //Освобождение ресурсов
}
