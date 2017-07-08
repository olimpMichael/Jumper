package ru.michael.game.jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private int BRANCHAE_SPACING = 260;
    private int PADDING_BOTTOM = 60;

    private Jumper jumper;
    //private Bird bird;
    //private Nuts nuts;
    //private Branch branch;
    //private Ladder ladder;
    private Texture bg;
    private int score = 0;
    private BitmapFont scoreFont;

    private Array<Branch> branches;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("bg-land.png");
        jumper = new Jumper(0, PADDING_BOTTOM);
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT); //задаем область обзора
        //ортографической камеры
        scoreFont = new BitmapFont();


        //bird = new Bird(60);
        //nuts = new Nuts();
        //branch = new Branch(20);
        branches = new Array<Branch>();
        for (int i=0; i < BRANCHES_COUNT; i++){
            branches.add(new Branch(20 + i * (BRANCHAE_SPACING + Branch.BRANCH_HEIGHT)));
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
        jumper.update(dt);
        for (Branch branch : branches) {
            //Determines collides between jumper and bird
            if (branch.getBird().collides(jumper.getBounds())) {
                gsm.set(new PlayState(gsm));
                return;
            }

            //Determines collides between jumper and nuts
            if (branch.getNuts().collides(jumper.getBounds())) {
                score += 10;
            }

            //Determines collides between jumper and ladders
            if (branch.getLadder().collides(jumper.getBounds())) {
                jumper.climb();
                //camera.position.y = bird.getPosition().y + 80; // привязка позиции камеры к позиции птицы
                //Камеру привязываем к белке
                //Заставляем белку двигаться только вверх.
                //Рисуем репозицию веток
                //открепляем камеру от белки
                //белка двигается как и раньше
            }
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.setProjectionMatrix(camera.combined); //установка матрицы проекций для нашей камеры
        scoreFont.draw(sb, "Score: " + score, 10 ,MyGdxGame.HEIGHT-10);

        //drawing branches
        for (Branch branch : branches) {
            sb.draw(branch.getbranch(), branch.getPosition().x, branch.getPosition().y);
            //sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
            sb.draw(branch.getBird().getBirdTexture(),
                    branch.getBird().getPosition().x,
                    branch.getBird().getPosition().y); //draw bird
            sb.draw(branch.getNuts().getNutsTexture(),
                    branch.getNuts().getPosition().x,
                    branch.getNuts().getPosition().y); //draw nuts
            sb.draw(branch.getLadder().getLadderTexture(),
                    branch.getLadder().getPosition().x,
                    branch.getLadder().getPosition().y); //draw ladder
        }

        //sb.draw(branch.getbranch(), branch.getPosition().x, branch.getPosition().y); // draw branch;
        sb.draw(jumper.getJumperTexture(), jumper.getPosition().x, jumper.getPosition().y); //draw jumper


        sb.end();

    }

    @Override
    public void dispose() {
        jumper.dispose();
        //bird.dispose();
        //nuts.dispose();
        for (Branch branch : branches) {
            branch.dispose();
        }
        //ladder.dispose();
        scoreFont.dispose();
        bg.dispose();


        System.out.println("PlayState disposed");

    }
}
