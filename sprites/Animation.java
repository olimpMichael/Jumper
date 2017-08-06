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
        //int frameWidth = region.getRegionWidth() / frameCount; //ширина кадра
        /*for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }*/
        this.frameCount = 4;

        int frameWidth = (region.getRegionWidth() / frameCount) + 5; //ширина кадра
        for (int i = 0; i < this.frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        frames.add(new TextureRegion(region, 4 * frameWidth, 0, frameWidth, region.getRegionHeight()));
        frames.add(new TextureRegion(region, 5 * frameWidth, 0, frameWidth - 20, region.getRegionHeight()));
        frames.add(new TextureRegion(region, 6 * frameWidth - 20, 0, frameWidth-16, region.getRegionHeight()));



        maxFrameTime = cycleTime / this.frameCount;
        frame = 0;
    }
    public void update(Jumper jumper, float dt) {
        switch (jumper.getCurrentState()) {
            case Jumper.RUN:
                currentFrameTime += dt;
                //если длительность отображения текущего кадра больше максимальной, то смена кадра
                if (currentFrameTime > maxFrameTime) {
                    frame++;
                    currentFrameTime = 0;
                }
                if (frame >= frameCount)
                    frame = 0;
                break;

            case Jumper.LOSE:
                frame = 4;
                currentFrameTime = 0;
                break;

            case Jumper.CLIMB:
                frame = 5;
                currentFrameTime = 0;
                break;

            case Jumper.JUMP:
                frame = 6;
                currentFrameTime = 0;
                break;
        }
    }


    //метод получения текущего кадра анимации
    public  TextureRegion getFrame(Jumper jumper){
        TextureRegion region;
        region = frames.get(frame);
        if(!jumper.getDirection() && !region.isFlipX()){
            region.flip(true, false);
        } else if (jumper.getDirection() && region.isFlipX()){
            region.flip(true, false);
        }
        return region;
    }
}

