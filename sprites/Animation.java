package ru.michael.game.jumper.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by michael on 23.07.2017.
 */

public class Animation {
    private Array<TextureRegion> frames; //Массив текстур для хранения кадров анимации
    private float maxFrameTime; //длительность отображения одного кадра
    private float currentFrameTime; //время отображения текущего кадра
    private int frameCount; //количество кадров анимации
    private int frame; // отдельный кадр анимации

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount; //ширина кадра
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }
    public void update(float dt){
        currentFrameTime += dt;
        //если длительность отображения текущего кадра больше максимальной, то смена кадра
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount)
            frame = 0;
    }

    //метод получения текущего кадра анимации
    public  TextureRegion getFrame(){
        return frames.get(frame);
    }
}

