package ru.michael.game.jumper.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ru.michael.game.jumper.MyGdxGame;
import sun.rmi.runtime.Log;

/**
 * Created by michael on 17.06.2017.
 */

public class Jumper {

    private static int MOVEMENT = 350; // movement jumper

    private Vector3 position; //position of jumper
    private Vector3 velosity; //speed of jumper
    private Vector3 gravity; //speed of gravity
    private Vector3 temp;

    private Rectangle bounds; //rectangle arround jumper
    private Texture texture;


    private float padding;
    private boolean direction;
    private boolean nowJump;
    private boolean climb;
    private float yBeforeJump;
/*    private Animation birdAnimation;
    private Sound flap;*/

    public Jumper(int x, int y){
        yBeforeJump = y;
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        temp = new Vector3(0, 0, 0);
        gravity = new Vector3(0, -10, 0);
        texture = new Texture("Squirrel.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        direction = true;
        nowJump = false;
        climb = false;
        padding = 40;

    }

    public Texture getJumperTexture() {
        return texture;
        /*return birdAnimation.getFrame();*/
    }

    public Vector3 getPosition(){
        return position;
    }

    public void jump(){
        if(nowJump){
            return;
        } else nowJump = true;
        yBeforeJump = position.y;
        velosity.y=800; //присваиваем значение 250 координате y вектора скорости
        //position.add(0,50,0);
        //velosity.add(0, -30, 0);//добавляет значение к вектору y = y + GRAVITY
    }

    public void climb(){
        climb = true;
    }

    public void update(float dt){
        if (nowJump){
            System.out.println("1) velosity.y = " + velosity.y);
            gravity.set(0, -40, 0);
           // gravity.scl(dt);
            velosity.add(gravity);//устанавливаем гравитацию равную -10
            temp.set(velosity);
            velosity.scl(dt);

            System.out.println("2) velosity.y = " + velosity.y);

            //velosity.scl(dt);//умножаем вектор скорости на скаляр промежутка времени

            position.add(0, velosity.y, 0); //устанавливаем новые координаты
            velosity.set(temp);
            System.out.println("3) velosity.y = " + velosity.y);
            System.out.println("3) position.y = " + position.y);
            System.out.println("###########");


           if (position.y <= yBeforeJump  ){
               position.y = yBeforeJump;
               velosity.y = 0;
               yBeforeJump = 0;

               nowJump = false;
            }
            MOVEMENT = 300;
        } else {
            MOVEMENT = 350;
        }

        if (climb){
            position.add(0,MOVEMENT * dt,0);
        }


        if (direction){
            position.add(MOVEMENT * dt, 0, 0 ); //устанавливаем новые координаты
        } else {
            position.sub(MOVEMENT * dt, 0, 0 ); //устанавливаем новые координаты
        }
        if (position.x >= MyGdxGame.WIDTH - texture.getWidth() - padding){
            direction = false; //direction of movement to left
        } else if (position.x <= padding){
            direction = true; //direction of movement to right
        }


        //velosity.scl(1 / dt);//изменение скорости птицы при падении


        bounds.setPosition(position.x, position.y); //set position of Rectangle arround jumper
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }

}
