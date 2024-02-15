package com.mygdx.game.registry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.draw.draw;
import com.mygdx.game.entities.tile;
import com.mygdx.game.logic.logic;
import com.mygdx.game.entities.textEffect;
import java.util.ArrayList;

public class registry {

    // Window Information
    public String windowName = "Match 3 Game";
    public int windowWidth = 1000;
    public int windowHeight = 1000;
    public OrthographicCamera camera;
    public StretchViewport viewport;

    // Sound Effects
    public Sound sound_Activate,sound_Swap,sound_Destroy,sound_Destroy2,sound_Destroy3,sound_Score;

    // Font
    public BitmapFont myFont,myFontEffect0, myFontEffect1, myFontEffect2, myFontEffect3, myFontEffect4, myFontEffect5, myFontEffect6;
    public BitmapFont myFontDisplayMana;

    // Tiles
    public tile[][] tiles;
    public float tilesXOffset = 250;
    public float tilesYOffset = 250;

    // Classes
    public draw draw;
    public logic logic;

    // Active Tile
    public boolean tileIsActive = false;
    public int activeX = 0;
    public int activeY = 0;

    public int gameState = 1;

    public int score = 0;
    public int addToScore = 0;
    public float scoreXPos = 20;
    public float scoreYPos = 950;
    public int scoreAddingDelayCounter = 0;
    public int scoreAddingDelayCounterLength = 4;

    public ArrayList<textEffect> textEffectArray;

    public int mana_red, mana_green, mana_blue, mana_yellow = 0;
    public int health = 50;
    public int maxHealth = 50;
    public int coins = 0;
    public int exp = 0;

    public String aiName = "Zombie";
    public int aiHealth = 30;
    public int aiMaxHealth = 30;
    public int aiMana_red, aiMana_green, aiMana_blue, aiMana_yellow = 0;


    public registry() {

        // Window Information
        Gdx.graphics.setTitle(windowName);
        Gdx.graphics.setWindowedMode(windowWidth, windowHeight);
        camera = new OrthographicCamera(windowWidth, windowHeight);
        viewport = new StretchViewport(windowWidth, windowHeight, camera);
        viewport.apply();

        // Initialize Sounds
        sound_Activate = Gdx.audio.newSound(Gdx.files.internal("activate.wav"));
        sound_Swap = Gdx.audio.newSound(Gdx.files.internal("swap.wav"));
        sound_Destroy = Gdx.audio.newSound(Gdx.files.internal("destroy.wav"));
        sound_Destroy2 = Gdx.audio.newSound(Gdx.files.internal("destroy2.wav"));
        sound_Destroy3 = Gdx.audio.newSound(Gdx.files.internal("destroy3.wav"));
        sound_Score = Gdx.audio.newSound(Gdx.files.internal("score.wav"));

        // Initialize Font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("zig.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.color = Color.WHITE;
        parameter.size = 50;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 8;
        myFont = generator.generateFont(parameter);

        parameter.color = Color.RED;
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 4;
        myFontEffect0 = generator.generateFont(parameter);

        parameter.color = Color.GREEN;
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 4;
        myFontEffect1 = generator.generateFont(parameter);

        parameter.color = Color.BLUE;
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 4;
        myFontEffect2 = generator.generateFont(parameter);

        parameter.color = Color.YELLOW;
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 4;
        myFontEffect3 = generator.generateFont(parameter);

        parameter.color = Color.CYAN;
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 4;
        myFontEffect4 = generator.generateFont(parameter);

        parameter.color = Color.VIOLET;
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 4;
        myFontEffect5 = generator.generateFont(parameter);

        parameter.color = Color.GRAY;
        parameter.size = 40;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 4;
        myFontEffect6 = generator.generateFont(parameter);

        parameter.color = Color.WHITE;
        parameter.size = 20;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        myFontDisplayMana = generator.generateFont(parameter);

        // Initialize Classes
        draw = new draw();
        draw.reg = this;
        logic = new logic();
        logic.reg = this;

        // Setup Tiles
        tiles = new tile[8][8];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tile newTile = new tile(MathUtils.random(0, 6), (i * 64) + tilesXOffset, (j * 64) + tilesYOffset);
                tiles[i][j] = newTile;
                System.out.println("Tiles: " + i + " " + j);
            }
        }

        // Setup Arrays
        textEffectArray = new ArrayList<textEffect>();

    }

}
