package ru.michael.game.jumper.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by michael on 17.06.2017.
 */

public abstract class State {
    protected OrthographicCamera camera; //this is a window to our game's world
    protected Vector3 mouse;
    protected GameStateManager gsm; //manage states of game

    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput(); //examine user's input
    public abstract void update(float dt); //update screen across interval
    public abstract void render(SpriteBatch sb); //draw screen
    public abstract void dispose(); //delete resources from RAM
}
