package live.daniel.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import live.daniel.game.FlappyNigga;
import live.daniel.game.sprites.Bird;
import live.daniel.game.sprites.Tube;

/**
 * Created by Daniel on 21.01.2016.
 */
public class PlayState extends State {
    public static final int TUBE_SPACING = 125; //Расстояние между трубами
    public static final int TUBE_COUNT = 4; //Количество труб
    public static final int GROUND_Y_OFFSET = -0;
    private Texture ground;
    private Texture bg;
    private Bird bird;
    private Vector2 groundPos1, groundPos2;
    private Sound getOgly;
    private Sound yay;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyNigga.WIDTH / 2, FlappyNigga.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, 0);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        getOgly = Gdx.audio.newSound(Gdx.files.internal("getUgly.ogg"));
        yay = Gdx.audio.newSound(Gdx.files.internal("yay.ogg"));
        tubes = new Array<Tube>();

        for (int i = 0; i < TUBE_COUNT; i++){ //Создает трубы
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80; //Чтобы камера двигалась с птицей

        for (int i = 0; i < tubes.size; i++){ //Движение труб (камеры)
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())) {//Есть столкновение, перезапуск игры
                getOgly.play();
                gsm.set(new GameOver(gsm));
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined); //Матрица проекций для камеры
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0); //Расположение по центру
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube : tubes)
            tube.dispose();
    }

    public void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
