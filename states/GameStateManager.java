package ru.michael.game.jumper.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by michael on 17.06.2017.
 */

public class GameStateManager {

    private Stack<State> states; //array states

    public GameStateManager(){
        states = new Stack<State>();
    }


    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }


    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    //this method updating game (only last element)
    public void update(float dt){
        states.peek().update(dt); //return last element, but don't delete it
    }

    //drawing last state
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}