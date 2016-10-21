package live.daniel.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Daniel on 21.01.2016.
 */
public class Bird {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velosity;
    private Rectangle bounds;
    private Texture bird;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);//3 кадра в 0.5 секунды
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if (position.y > 0 )
            velosity.add(0, GRAVITY, 0);
        velosity.scl(dt); //Умножаем вектор скорости на скаляр промежутка времени
        position.add(MOVEMENT * dt, velosity.y, 0); //Новое положение
        if (position.y < 110)
            position.y = 110;
        if (position.y > 376)
            position.y = 376;
        velosity.scl(1 / dt); //Изменение скрости при падении
        bounds.setPosition(position.x, position.y);
    }

    public void jump(){
        velosity.y = 250;
        flap.play();
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
