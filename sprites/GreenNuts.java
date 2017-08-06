package ru.michael.game.jumper.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ru.michael.game.jumper.MyGdxGame;

/**
 * Created by michael on 03.07.2017.
 */

public class GreenNuts {
    private Texture nutsTexture;
    private Vector2 posNuts;
    private Random rand;
    private Rectangle boundNuts;

    public GreenNuts(float y){
        nutsTexture = new Texture("nuts-green.png");
        setPosition(y);
        /*rand = new Random();
        posNuts = new Vector2(MyGdxGame.WIDTH_TRUNK + rand.nextInt(MyGdxGame.WIDTH -
                MyGdxGame.WIDTH_TRUNK - MyGdxGame.WIDTH_TRUNK),
                y + MyGdxGame.HEIGHT_BRANCH + rand.nextInt(MyGdxGame.HEIGHT_LEVEL));
        boundNuts = new Rectangle(posNuts.x, posNuts.y, nutsTexture.getWidth(), nutsTexture.getHeight());*/
    }

    public void setPosition(float y){
        rand = new Random();
        posNuts = new Vector2(MyGdxGame.WIDTH_TRUNK + rand.nextInt(MyGdxGame.WIDTH -
                MyGdxGame.WIDTH_TRUNK - MyGdxGame.WIDTH_TRUNK),
                y + MyGdxGame.HEIGHT_BRANCH + rand.nextInt(MyGdxGame.HEIGHT_LEVEL));
        boundNuts = new Rectangle(posNuts.x, posNuts.y, nutsTexture.getWidth(), nutsTexture.getHeight());
    }

    public Texture getNutsTexture(){
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


    // This method use only test
    public Rectangle getBounds(){
        return boundNuts;
    }

}
