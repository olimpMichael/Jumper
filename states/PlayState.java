package ru.michael.game.jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

import ru.michael.game.jumper.MyGdxGame;
import ru.michael.game.jumper.sprites.Bird;
import ru.michael.game.jumper.sprites.Branch;
import ru.michael.game.jumper.sprites.Jumper;
import ru.michael.game.jumper.sprites.Ladder;
import ru.michael.game.jumper.sprites.Nuts;

/**
 * Created by michael on 17.06.2017.
 */

public class PlayState extends State {
    private int BRANCHES_COUNT = 4;
    public static int BRANCHES_SPACING = 260;
    private int PADDING_BOTTOM = 60;

    public static int score = 0;

    private Jumper jumper;
    //private Bird bird;
    //private Nuts nuts;
    //private Branch branch;
    //private Ladder ladder;
    private Texture bg;
    private FreeTypeFontGenerator generator;
    private BitmapFont scoreFont;

    private Array<Branch> branches;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("bg-land.png");
        jumper = new Jumper(0, PADDING_BOTTOM);
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT); //задаем область обзора
        //ортографической камеры

        //scoreFont = TrueTypeFontFactory.createBitmapFont.
        //FileHandle fontFile = new FileHandle();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ITCKRIST.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 0.8f;
        scoreFont = generator.generateFont(parameter); // font size 12 pixels




        //bird = new Bird(60);
        //nuts = new Nuts();
        //branch = new Branch(20);
        branches = new Array<Branch>();
        for (int i=0; i < BRANCHES_COUNT; i++){
            branches.add(new Branch(20 + i * (BRANCHES_SPACING + Branch.BRANCH_HEIGHT)));
        }
        //ladder = new Ladder(60 + 115);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            jumper.jump();
        }
    }

    @Override
    public void update(float dt) {

        handleInput();

        for (Branch branch : branches) {
            //Determines collides between jumper and bird
            if (branch.getBird().collides(jumper.getBounds())) {
                gsm.set(new GameOver(gsm));
                return;
            }

            //Determines collides between jumper and nuts
            if (branch.getNuts().collides(jumper.getBounds())) {
                branch.getNuts().setPosition(-300); // nut is disappearing
                score += 10;
            }

            if(jumper.climb){
                camera.position.y = jumper.getPosition().y + 220;
            }

            //Determines collides between jumper and ladders
            if (Gdx.input.isTouched() && branch.getLadder().collides(jumper.getBounds()) && !jumper.climb) {
                score += 10;
                jumper.setPositionX(branch.getLadder().getPosition().x); //помещаем белку посередине лестницы
                jumper.climb();
                camera.position.y = jumper.getPosition().y + 220 ; // привязка позиции камеры к позиции птицы

                //Рисуем репозицию веток
                for(int i=0; i<branches.size; i++){
                    Branch brch = branches.get(i);

                    if(camera.position.y - (camera.viewportHeight / 2) > brch.getPosition().y
                            + brch.getbranch().getHeight()){
                        System.out.println(" branch.reposition, i = " + i);
                        //создание ветвей
                        brch.reposition(brch.getPosition().y + ((Branch.BRANCH_HEIGHT + BRANCHES_SPACING) * BRANCHES_COUNT));
                    }
                }
            }
        }
        jumper.update(dt);

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setProjectionMatrix(camera.combined); //установка матрицы проекций для нашей камеры

        sb.begin();
        sb.draw(bg, 0, camera.position.y - (camera.viewportHeight / 2));
        sb.setProjectionMatrix(camera.combined); //установка матрицы проекций для нашей камеры
        //drawing branches
        for (Branch branch : branches) {
            sb.draw(branch.getbranch(), branch.getPosition().x, branch.getPosition().y);
            sb.draw(branch.getLadder().getLadderTexture(),
                    branch.getLadder().getPosition().x,
                    branch.getLadder().getPosition().y); //draw ladder
            sb.draw(branch.getNuts().getNutsTexture(),
                    branch.getNuts().getPosition().x,
                    branch.getNuts().getPosition().y); //draw nuts
            sb.draw(branch.getBird().getBirdTexture(),
                    branch.getBird().getPosition().x,
                    branch.getBird().getPosition().y); //draw bird
        }
        sb.draw(jumper.getJumperTexture(), jumper.getPosition().x, jumper.getPosition().y); //draw jumper
        scoreFont.draw(sb, "Score: " + score, 40 ,camera.position.y + (MyGdxGame.HEIGHT / 2) - 10);
        sb.end();
    }

    @Override
    public void dispose() {
        jumper.dispose();
        for (Branch branch : branches) {
            branch.dispose();
        }
        generator.dispose();
        scoreFont.dispose();
        bg.dispose();
        System.out.println("PlayState disposed");
    }
}
