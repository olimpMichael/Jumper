package ru.michael.game.jumper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.michael.game.jumper.sprites.Jumper;
import ru.michael.game.jumper.states.GameStateManager;
import ru.michael.game.jumper.states.PlayState;

public class MyGdxGame extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final int HEIGHT_LEVEL = 200;
	public static final String TITLE = "Jumper Demo";

	private SpriteBatch batch;
	private GameStateManager gsm;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		Gdx.gl.glClearColor(1, 0, 0, 1); //clear our screen
		gsm.push(new PlayState(gsm)); //create new screen menu and put it to Stack
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime()); //update last state in Stack
		gsm.render(batch); //draw last stack's still
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
