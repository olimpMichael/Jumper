package ru.michael.game.jumper.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Shape;

import ru.michael.game.jumper.MyGdxGame;
import ru.michael.game.jumper.states.PlayState;
import sun.rmi.runtime.Log;

/**
 * Created by michael on 17.06.2017.
 */

public class Jumper {

    private static int MOVEMENT = 350; // movement jumper
    public static final int RUN = 1;
    public static final int JUMP = 2;
    public static final int CLIMB = 3;
    public static final int LOSE = 4;

    private int currentState;
    //private int previousState;
    private Vector3 position; //position of jumper
    private Vector3 velosity; //speed of jumper
    private Vector3 gravity; //speed of gravity
    private Vector3 temp;

    private Rectangle bounds; //rectangle arround jumper
    private Shape bounds_Jump_Climb;
    private Texture texture;


    private float padding;
    private boolean direction;
    //private boolean justChangeDirection;

    private float yBeforeJump;
    private Animation jumperAnimation;
/*    private Animation birdAnimation;
    private Sound flap;*/

    public Jumper(int x, int y){
        yBeforeJump = y;
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        temp = new Vector3(0, 0, 0);
        gravity = new Vector3(0, -10, 0);
        texture = new Texture("Squirrel-sprites.png");
        jumperAnimation = new Animation(new TextureRegion(texture), 7, 0.8f);
        //bounds = new Rectangle(x, y, texture.getWidth() /7, texture.getHeight());
        //bounds_Jump_Climb = new Rectangle(x, y, texture.getWidth() /7 - 20, texture.getHeight());
        bounds = new Rectangle(x - 50, y, texture.getWidth() /7 - 20, texture.getHeight());

        //bounds.
        direction = true;
        //justChangeDirection = false;
        currentState = RUN;
        //previousState = RUN;
        padding = 40;



    }

    public int getCurrentState(){
        return currentState;
    }

    public void setCurrentState(int state){
        currentState = state;
    }

    public boolean getDirection(){
        return direction;
    }

    public void setPositionX(float x){
        position.x = x;
    }

    public TextureRegion getJumperTexture() {
        return jumperAnimation.getFrame(this);
    }

    public Vector3 getPosition(){
        return position;
    }

    public void jump(){
        if(currentState == JUMP || currentState == CLIMB){
            return;
        } else currentState = JUMP;
        yBeforeJump = position.y;
        velosity.y=800; //присваиваем значение 250 координате y вектора скорости
    }

    public void climb(){
        if(currentState == CLIMB){
            return;
        } else {
            currentState = CLIMB;
        }
    }

    public void update(float dt){
        jumperAnimation.update(this, dt);
        switch (currentState){
            case RUN:
                MOVEMENT = 350;
                updateDirection(dt);
                break;

            case JUMP:
                System.out.println("1) velosity.y = " + velosity.y);
                gravity.set(0, -40, 0);
                velosity.add(gravity);//устанавливаем гравитацию равную -10
                temp.set(velosity);
                velosity.scl(dt);

                //System.out.println("2) velosity.y = " + velosity.y);

                position.add(0, velosity.y, 0); //устанавливаем новые координаты
                velosity.set(temp);
                //System.out.println("3) velosity.y = " + velosity.y);
                //System.out.println("3) position.y = " + position.y);
                //System.out.println("###########");


                if (position.y <= yBeforeJump  ){
                    position.y = yBeforeJump;
                    velosity.y = 0;
                    yBeforeJump = 0;
                    this.setCurrentState(RUN);
                }
                MOVEMENT = 300;
                updateDirection(dt);
                break;

            case CLIMB:
                position.add(0,MOVEMENT * dt,0);
                if (position.y >= (yBeforeJump + PlayState.BRANCHES_SPACING + 44)  ) {
                    System.out.println("STOP climb ");
                    //position.y = yBeforeJump + PlayState.BRANCHES_SPACING;
                    velosity.y = 0;
                    position.y = yBeforeJump + PlayState.BRANCHES_SPACING + 44;
                    yBeforeJump = 0;
                    this.setCurrentState(RUN);
                    //System.out.println("climb = " + climb);
                }
                break;

            case LOSE:
                break;
        }

        bounds.setPosition(position.x, position.y); //set position of Rectangle arround jumper

    }

    private void updateDirection(float dt){
        if (direction){
            position.add(MOVEMENT * dt, 0, 0 ); //устанавливаем новые координаты
        } else {
            position.sub(MOVEMENT * dt, 0, 0 ); //устанавливаем новые координаты
        }
        //justChangeDirection = false;

        if (position.x >= MyGdxGame.WIDTH - (texture.getWidth()/7) - padding){
            //System.out.println("direction = " + direction + "Flip");
            direction = false; //direction of movement to left
            //justChangeDirection = true;
        } else if (position.x <= padding) {
           // System.out.println("direction = " + direction + "Flip");
            direction = true; //direction of movement to right
            //justChangeDirection = true;
        }
    }

    public Rectangle getBounds(){

        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }

}
