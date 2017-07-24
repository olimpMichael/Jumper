package ru.michael.game.jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import ru.michael.game.jumper.MyGdxGame;

/**
 * Created by michael on 17.07.2017.
 */

public class SplashState extends State{
    private Texture background;
    private FreeTypeFontGenerator generator;
    private BitmapFont scoreFont;
    private Texture logo;

    public SplashState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("bg-land.png");
        logo = new Texture("Logo.png");

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ITCKRIST.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;

        scoreFont = generator.generateFont(parameter); // font size 30 pixels
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
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(logo,  (MyGdxGame.WIDTH - logo.getWidth())/2, 150 );

        scoreFont.draw(sb, "Tap to start Game",
                camera.position.x  - 200,
                MyGdxGame.HEIGHT -20);

        scoreFont.draw(sb, "Lukky Squirrel",
                MyGdxGame.WIDTH /2 - 150,
                120);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        logo.dispose();
        generator.dispose();
        scoreFont.dispose();
        System.out.println("SplashState Disposed");
    }
}
