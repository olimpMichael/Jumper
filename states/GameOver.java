package ru.michael.game.jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import ru.michael.game.jumper.MyGdxGame;

/**
 * Created by michael on 16.07.2017.
 */

public class GameOver extends State {
    private Texture background;
    //private Texture gameover;
    private FreeTypeFontGenerator generator;
    private BitmapFont scoreFont, scoreFontGameOver;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("bg-land.png");
        //gameover = new Texture("gameover.png");

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ITCKRIST.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;

        FreeTypeFontGenerator.FreeTypeFontParameter parameterGameOver = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterGameOver.size = 60;
        parameterGameOver.borderColor = Color.BLACK;
        parameterGameOver.borderWidth = 1;

        scoreFont = generator.generateFont(parameter); // font size 40 pixels
        scoreFontGameOver = generator.generateFont(parameterGameOver); // font size 30 pixels
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0.5686f, 0.8313f, 0.9607f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
       /* sb.draw(gameover,
                camera.position.x - gameover.getWidth() / 2,
                camera.position.y + (camera.position.y / 2));*/

        scoreFontGameOver.draw(sb, "Game Over",
                camera.position.x  - 200,
                camera.position.y + (MyGdxGame.HEIGHT / 2) - 50);

        scoreFont.draw(sb, "Score: " + PlayState.score,
                camera.position.x  - 70,
                camera.position.y + (MyGdxGame.HEIGHT / 2) - 200);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        //gameover.dispose();
        generator.dispose();
        scoreFont.dispose();
        scoreFontGameOver.dispose();
        PlayState.score = 0;
        PlayState.level =1;
        PlayState.levelBranch =0;
        System.out.println("GameOver Disposed");
    }
}
