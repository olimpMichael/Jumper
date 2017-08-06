package ru.michael.game.jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ru.michael.game.jumper.MyGdxGame;

/**
 * Created by michael on 17.07.2017.
 */

public class SplashState extends State{
    private Texture background;
    private FreeTypeFontGenerator generator;
    private BitmapFont scoreFont;
    private Texture logo;
    private Stage stage;
    private CheckBox chbMusic, chbSoundEffects;
    Texture textureChecked, textureNotCheked;

    public SplashState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);

        // creating Font
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ITCKRIST.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        scoreFont = generator.generateFont(parameter); // font size 30 pixels

        //** window is stage **//
        stage = new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//


        background = new Texture("bg-land.png");
        logo = new Texture("Logo.png");

        textureChecked = new Texture("check.png");
        textureNotCheked = new Texture("not_check.png");
        Drawable checked = new TextureRegionDrawable(new TextureRegion(textureChecked));
        Drawable not_checked = new TextureRegionDrawable(new TextureRegion(textureNotCheked));

        CheckBox.CheckBoxStyle chbStyle = new
                CheckBox.CheckBoxStyle(not_checked, checked, scoreFont, Color.WHITE);

        chbMusic = new CheckBox("Music", chbStyle);
        chbMusic.setWidth(200);
        chbMusic.setHeight(20);
        chbMusic.setPosition(100, 50);
        chbMusic.setChecked(false);
        stage.addActor(chbMusic);

        chbMusic.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if (chbMusic.isChecked()) {
                    MyGdxGame.music.play();
                } else MyGdxGame.music.pause();
            }
        });

        chbSoundEffects = new CheckBox("Sounds", chbStyle);
        chbSoundEffects.setWidth(200);
        chbSoundEffects.setHeight(20);
        chbSoundEffects.setPosition(520, 50);
        chbSoundEffects.setChecked(true);
        stage.addActor(chbSoundEffects);

        chbSoundEffects.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if (chbSoundEffects.isChecked()) {
                    MyGdxGame.soundEffectsVolume = 0.4f;
                } else MyGdxGame.soundEffectsVolume = 0.0f;
            }
        });
    }


    @Override
    public void handleInput() {
        if(Gdx.input.justTouched() && (Gdx.input.getY() < MyGdxGame.HEIGHT - (MyGdxGame.HEIGHT /4))){
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            System.out.println("justTouched");
            System.out.println("x = " + x);
            System.out.println("y = " + y);
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
        sb.draw(logo,  (MyGdxGame.WIDTH - logo.getWidth())/2, 150 );
        scoreFont.draw(sb, "Tap to start Game",
                camera.position.x  - 200,
                MyGdxGame.HEIGHT -20);

        scoreFont.draw(sb, "Lukky Squirrel",
                MyGdxGame.WIDTH /2 - 150,
                140);
        sb.end();

        stage.draw();
        stage.act();

    }

    @Override
    public void dispose() {
        background.dispose();
        logo.dispose();
        textureChecked.dispose();
        textureNotCheked.dispose();

        stage.dispose();
        generator.dispose();
        scoreFont.dispose();
        System.out.println("SplashState Disposed");
    }
}
