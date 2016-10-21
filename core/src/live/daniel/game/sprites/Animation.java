package live.daniel.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.awt.TextArea;

/**
 * Created by Daniel on 22.01.2016.
 */
public class Animation {
    private Array<TextureRegion> frames; //Кадры анимации
    private float maxFrameTime; //Длительность отображения одного кадра
    private float currentFrameTime; //Время отображения текущего кадра
    private int frameCount; //Количество кадров анимации
    private int frame; //Отдельный кадр анимации

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount; //Ширина кадров (Ширина 48 пикелей из 3 картинок = 48/3=16)
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt){
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime){ //Если длительность отображения текущего кадра больше максимального, увеличиваем номер кадра
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount)
            frame = 0;
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
