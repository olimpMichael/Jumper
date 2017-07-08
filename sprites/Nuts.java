package ru.michael.game.jumper.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ru.michael.game.jumper.MyGdxGame;

/**
 * Created by michael on 03.07.2017.
 */

public class Nuts {
    private Texture nutsTexture;
    private Vector2 posNuts;
    private Random rand;
    private Rectangle boundNuts;

    public Nuts(){
        nutsTexture = new Texture("nuts.png");
        rand = new Random();
        posNuts = new Vector2(rand.nextInt(MyGdxGame.WIDTH), rand.nextInt(MyGdxGame.HEIGHT_LEVEL));
        boundNuts = new Rectangle(posNuts.x, posNuts.y, nutsTexture.getWidth(), nutsTexture.getHeight());
    }

    public Texture getNuts(){
        return nutsTexture;
    }

    public Vector2 getPosition(){
        return posNuts;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundNuts);
    }

    public void dispose(){
        nutsTexture.dispose();
    }

}
