package ru.michael.game.jumper.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ru.michael.game.jumper.MyGdxGame;

/**
 * Created by michael on 05.07.2017.
 */

public class Branch {
    public static final int BRANCH_HEIGHT = 44;

    private Bird bird;
    private Nuts nuts;
    private Ladder ladder;
    private Texture leftBranchTexture, rightBranchTexture;
    private Vector2 leftPosBranch, rightPosBranch;
    private Rectangle leftBoundBranch, rightBoundBranch;
    //private Random rand;

    public Branch(float y){
        leftBranchTexture = new Texture("leftBranch.png");
        //rand = new Random();
        leftPosBranch = new Vector2(20,y);
        leftBoundBranch = new Rectangle(leftPosBranch.x, leftPosBranch.y, leftBranchTexture.getWidth(), leftBranchTexture.getHeight());

        bird = new Bird(y);
        nuts = new Nuts(y);
        ladder = new Ladder(y);
    }

    public Ladder getLadder(){
        return ladder;
    }

    public Bird getBird(){
        return bird;
    }

    public Nuts getNuts(){
        return nuts;
    }

    public Vector2 getPosition(){
        return leftPosBranch;
    }

    public Texture getbranch(){
        return leftBranchTexture;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(leftBoundBranch);
    }



    public void dispose(){
        leftBranchTexture.dispose();
        bird.dispose();
        nuts.dispose();
        ladder.dispose();
    }
}
