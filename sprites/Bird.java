package ru.michael.game.jumper.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import ru.michael.game.jumper.MyGdxGame;

/**
 * Created by michael on 29.06.2017.
 */

public class Bird {
    private Texture birdTexture;
    private Vector2 posBird;

    private Random rand;
    private Rectangle boundBird;

    public Bird(float y, float ladderPosx){
        birdTexture = new Texture ("bird.png");
        //float ladderPosx = 0;
        setPosition(y, ladderPosx);
        /*rand = new Random();
        posBird = new Vector2(MyGdxGame.WIDTH_TRUNK + rand.nextInt(MyGdxGame.WIDTH -
                MyGdxGame.WIDTH_TRUNK - MyGdxGame.WIDTH_TRUNK), y + MyGdxGame.HEIGHT_BRANCH);
        boundBird = new Rectangle(posBird.x, posBird.y, birdTexture.getWidth(), birdTexture.getHeight());*/
    }

    public void setPosition(float y, float ladderPosX){
        int x;
        do {
            rand = new Random();
            x = MyGdxGame.WIDTH_TRUNK + rand.nextInt(MyGdxGame.WIDTH -
                    MyGdxGame.WIDTH_TRUNK - MyGdxGame.WIDTH_TRUNK);
            System.out.println("do {x = " + x + "} while (" + ladderPosX + ")");
        } while (!(((float)x <= (float) (ladderPosX - 30.0))
                || ((float)x >= (float) (ladderPosX + 30.0))));
        posBird = new Vector2(x, y + MyGdxGame.HEIGHT_BRANCH);
        boundBird = new Rectangle(posBird.x, posBird.y, birdTexture.getWidth(), birdTexture.getHeight());
    }

    public Texture getBirdTexture(){
        return birdTexture;
    }

    public Vector2 getPosition(){
        return posBird;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundBird);
    }

    public void dispose(){
        birdTexture.dispose();
    }


}
