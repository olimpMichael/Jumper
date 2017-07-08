package ru.michael.game.jumper.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ru.michael.game.jumper.MyGdxGame;

/**
 * Created by michael on 17.06.2017.
 */

public class Ladder {
    private Texture ladderTexture;
    private Vector2 posLadder;
    private Rectangle boundLadder;
    private Random rand;

    public Ladder(float y){
        ladderTexture = new Texture("ladder.png");
        rand = new Random();
        posLadder = new Vector2(rand.nextInt(MyGdxGame.WIDTH),y);
        boundLadder = new Rectangle(posLadder.x, posLadder.y, ladderTexture.getWidth(), ladderTexture.getHeight());
    }

    public Vector2 getPosition(){
        return posLadder;
    }

    public Texture getLadder(){
        return ladderTexture;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundLadder);
    }

    public void dispose(){
        ladderTexture.dispose();
    }
}