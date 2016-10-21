package live.daniel.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Daniel on 21.01.2016.
 */
public class GameStateManager {

    private Stack<State> states; //Массив стек состояний (вектор)

    public GameStateManager(){
        states = new Stack<State>(); //Создает пустой стек
    }

    public void push(State state){ //Помещаем в вершину стека
        states.push(state);
    }

    public void pop(State state){ //Извлекаем элемент удаляя его из стеке
        states.pop().dispose();
    }

    public void set(State state){ //Удаляет из стека верхний экран и помещает экран в вершину стека
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){ //Обновляет экран с верхним элементом стека
        states.peek().update(dt); //Возвращает верхний элемент не удаляя его из стека
    }

    public void render(SpriteBatch sb){ //Принимает состояние и отрисовывает его
        states.peek().render(sb);
    }
}
