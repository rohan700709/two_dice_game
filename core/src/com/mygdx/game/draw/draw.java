package com.mygdx.game.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.registry.registry;
import com.mygdx.game.entities.textEffect;

public class draw {

    public registry reg;

    SpriteBatch batch;
    public TextureRegion[] textureRegions;
    public String displayText = "";

    public draw() {

        batch = new SpriteBatch();

        textureRegions = new TextureRegion[2000];
        textureRegions[0] = new TextureRegion(new Texture("sprite.png"), 0, 0, 64, 64);
        //for (int i = 0; i < 8; i++) {
        //    textureRegions[i] = new TextureRegion(new Texture("puzzlequestsprites.png"), i * 64, 0, 64, 64);
        //}

    }

    public void update() {

        Gdx.gl.glClearColor(0.6f, 0.6f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();


        // Draw Tiles
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {

                if (reg.tiles[i][j].type == 0) { // Red Mana
                    batch.setColor(1, 0, 0, 1);
                    if (reg.tiles[i][j].activated) {
                        batch.setColor(1, 0, 0, 0.5f);
                    }
                }
                else if (reg.tiles[i][j].type == 1) { // Green Mana
                    batch.setColor(0, 1, 0, 1);
                    if (reg.tiles[i][j].activated) {
                        batch.setColor(0, 1, 0, 0.5f);
                    }
                }
                else if (reg.tiles[i][j].type == 2) { // Blue Mana
                    batch.setColor(0, 0, 1, 1);
                    if (reg.tiles[i][j].activated) {
                        batch.setColor(0, 0, 1, 0.5f);
                    }
                }
                else if (reg.tiles[i][j].type == 3) { // Yellow Mana
                    batch.setColor(1, 1, 0, 1);
                    if (reg.tiles[i][j].activated) {
                        batch.setColor(1, 1, 0, 0.5f);
                    }
                }
                else if (reg.tiles[i][j].type == 4) { // Cyan - Coin
                    batch.setColor(0, 1, 1, 1);
                    if (reg.tiles[i][j].activated) {
                        batch.setColor(0, 1, 1, 0.5f);
                    }
                }
                else if (reg.tiles[i][j].type == 5) { // Violet - Exp
                    batch.setColor(1, 0, 1, 1);
                    if (reg.tiles[i][j].activated) {
                        batch.setColor(1, 0, 1, 0.5f);
                    }
                }
                else if (reg.tiles[i][j].type == 6) { // Gray - Skull
                    batch.setColor(0.5f, 0.5f, 0.5f, 1);
                    if (reg.tiles[i][j].activated) {
                        batch.setColor(0.5f, 0.5f, 0.5f, 0.5f);
                    }
                }
                else if (reg.tiles[i][j].type == 100) {
                    batch.setColor(1, 1, 1, 0);
                }

                //batch.draw(textureRegions[reg.tiles[i][j].type], reg.tiles[i][j].x, reg.tiles[i][j].y);

                batch.draw(textureRegions[0], reg.tiles[i][j].x, reg.tiles[i][j].y);

                batch.setColor(1,1,1,1);
            }
        }

        // Draw Text Effect
        for (textEffect t : reg.textEffectArray) {

            t.update();

            if (t.type == 0) {
                reg.myFontEffect0.draw(reg.draw.batch, t.text, t.x, t.y);
            }
            else if (t.type == 1) {
                reg.myFontEffect1.draw(reg.draw.batch, t.text, t.x, t.y);
            }
            else if (t.type == 2) {
                reg.myFontEffect2.draw(reg.draw.batch, t.text, t.x, t.y);
            }
            else if (t.type == 3) {
                reg.myFontEffect3.draw(reg.draw.batch, t.text, t.x, t.y);
            }
            else if (t.type == 4) {
                reg.myFontEffect4.draw(reg.draw.batch, t.text, t.x, t.y);
            }
            else if (t.type == 5) {
                reg.myFontEffect5.draw(reg.draw.batch, t.text, t.x, t.y);
            }
            else if (t.type == 6) {
                reg.myFontEffect6.draw(reg.draw.batch, t.text, t.x, t.y);
            }
            else {
                reg.myFontEffect0.draw(reg.draw.batch, t.text, t.x, t.y);
            }

        }

        // Turn Display
        reg.myFont.draw(reg.draw.batch, displayText, reg.scoreXPos + 150, reg.scoreYPos);

        // Draw Score
        //reg.myFont.draw(reg.draw.batch, "Score: " + reg.score, reg.scoreXPos, reg.scoreYPos);

        // Draw Mana and Player Related Stats
        reg.myFontDisplayMana.draw(reg.draw.batch, "HP: " + reg.health + ":" + reg.maxHealth, reg.scoreXPos, reg.scoreYPos - 128 + 64);
        reg.myFontDisplayMana.draw(reg.draw.batch, "Coins: " + reg.coins, reg.scoreXPos, reg.scoreYPos - 128 + 32);
        reg.myFontDisplayMana.draw(reg.draw.batch, "Experience: " + reg.exp, reg.scoreXPos, reg.scoreYPos - 128);

        reg.myFontDisplayMana.draw(reg.draw.batch, "Red Mana: " + reg.mana_red, reg.scoreXPos, reg.scoreYPos - 128 - 32);
        reg.myFontDisplayMana.draw(reg.draw.batch, "Green Mana: " + reg.mana_green, reg.scoreXPos, reg.scoreYPos - 128 - 64);
        reg.myFontDisplayMana.draw(reg.draw.batch, "Blue Mana: " + reg.mana_blue, reg.scoreXPos, reg.scoreYPos - 128 - 96);
        reg.myFontDisplayMana.draw(reg.draw.batch, "Yellow Mana: " + reg.mana_yellow, reg.scoreXPos, reg.scoreYPos - 128 - 128);

        // Draw Enemy Information
        reg.myFont.draw(reg.draw.batch, reg.aiName, reg.scoreXPos + 650, reg.scoreYPos);
        reg.myFontDisplayMana.draw(reg.draw.batch, "HP: " + reg.aiHealth + ":" + reg.aiMaxHealth, reg.scoreXPos + 750, reg.scoreYPos - 128 + 64);
        reg.myFontDisplayMana.draw(reg.draw.batch, "Red Mana: " + reg.aiMana_red, reg.scoreXPos + 750, reg.scoreYPos - 128 - 32);
        reg.myFontDisplayMana.draw(reg.draw.batch, "Green Mana: " + reg.aiMana_green, reg.scoreXPos + 750, reg.scoreYPos - 128 - 64);
        reg.myFontDisplayMana.draw(reg.draw.batch, "Blue Mana: " + reg.aiMana_blue, reg.scoreXPos + 750, reg.scoreYPos - 128 - 96);
        reg.myFontDisplayMana.draw(reg.draw.batch, "Yellow Mana: " + reg.aiMana_yellow, reg.scoreXPos + 750, reg.scoreYPos - 128 - 128);



        batch.end();

    }



}
