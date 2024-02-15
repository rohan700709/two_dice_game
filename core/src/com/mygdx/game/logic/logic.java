package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.registry.registry;
import com.mygdx.game.entities.textEffect;

public class logic {

    public registry reg;

    public Vector3 mouse_position = new Vector3(0,0,0);

    public int clickDelayCounter = 0;
    public int clickDelayCounterLength = 15;

    public int savedType = 0;

    public boolean matched = false;
    public boolean aiMadeAMatch = false;

    public int counterTurnStart = 0;

    public void update() {

        //System.out.println("State: " + reg.gameState);

        // Players Turn
        if (reg.gameState == 50) { // Start of Turn Delay
            counterTurnStart++;
            reg.draw.displayText = "";
            if (counterTurnStart > 30/2) {
                reg.draw.displayText = "Your Turn!";
            }
            if (counterTurnStart > 30) {
                reg.gameState = 0;
                counterTurnStart = 0;
            }
        }

        if (reg.gameState == 51) { // End of Turn Delay
            counterTurnStart++;
            if (counterTurnStart > 100/2) {
                reg.draw.displayText = "End of Turn!";
            }
            if (counterTurnStart > 100) {
                reg.gameState = 150;
                counterTurnStart = 0;
            }
        }


        if (reg.gameState == 0) {

            reg.scoreAddingDelayCounter++;
            if (reg.scoreAddingDelayCounter > reg.scoreAddingDelayCounterLength) {
                // Add to Score
                if (reg.addToScore > 0) {
                    reg.addToScore--;
                    reg.score++;
                    reg.sound_Score.play(0.15f);
                }
                reg.scoreAddingDelayCounter = 0;
            }



            mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            reg.camera.unproject(mouse_position);
            mouse_position.x += reg.windowWidth / 2;
            mouse_position.y += reg.windowHeight / 2;

            //System.out.println("Mouse Position: " + mouse_position.x + " " + mouse_position.y);

            clickDelayCounter++;

            // Check for Tile Swap
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                if (clickDelayCounter > clickDelayCounterLength) {

                    // Check tile to left side
                    if (reg.tileIsActive) {
                        if (reg.activeX - 1 >= 0) {
                            if ((reg.tiles[reg.activeX][reg.activeY].type != 100) && (reg.tiles[reg.activeX - 1][reg.activeY].type != 100)) {
                                if ((mouse_position.x > reg.tiles[reg.activeX - 1][reg.activeY].x) && (mouse_position.x < reg.tiles[reg.activeX - 1][reg.activeY].x + 64) && (mouse_position.y > reg.tiles[reg.activeX - 1][reg.activeY].y) && (mouse_position.y < reg.tiles[reg.activeX - 1][reg.activeY].y + 64)) {

                                    // De-activate
                                    reg.tileIsActive = false;
                                    reg.tiles[reg.activeX][reg.activeY].activated = false;

                                    // Swap Types
                                    savedType = reg.tiles[reg.activeX][reg.activeY].type;
                                    reg.tiles[reg.activeX][reg.activeY].type = reg.tiles[reg.activeX - 1][reg.activeY].type;
                                    reg.tiles[reg.activeX - 1][reg.activeY].type = savedType;

                                    // Check for Matches
                                    reg.gameState = 1;

                                    reg.sound_Swap.play();
                                    clickDelayCounter = 0;

                                }
                            }
                        }
                    }
                    // Check tile to right side
                    if (reg.tileIsActive) {
                        if (reg.activeX + 1 <= reg.tiles.length - 1) {
                            if ((reg.tiles[reg.activeX][reg.activeY].type != 100) && (reg.tiles[reg.activeX + 1][reg.activeY].type != 100)) {
                                if ((mouse_position.x > reg.tiles[reg.activeX + 1][reg.activeY].x) && (mouse_position.x < reg.tiles[reg.activeX + 1][reg.activeY].x + 64) && (mouse_position.y > reg.tiles[reg.activeX + 1][reg.activeY].y) && (mouse_position.y < reg.tiles[reg.activeX + 1][reg.activeY].y + 64)) {

                                    // De-activate
                                    reg.tileIsActive = false;
                                    reg.tiles[reg.activeX][reg.activeY].activated = false;

                                    // Swap Types
                                    savedType = reg.tiles[reg.activeX][reg.activeY].type;
                                    reg.tiles[reg.activeX][reg.activeY].type = reg.tiles[reg.activeX + 1][reg.activeY].type;
                                    reg.tiles[reg.activeX + 1][reg.activeY].type = savedType;

                                    // Check for Matches
                                    reg.gameState = 1;

                                    reg.sound_Swap.play();
                                    clickDelayCounter = 0;

                                }
                            }
                        }
                    }
                    // Check tile up
                    if (reg.tileIsActive) {
                        if (reg.activeY + 1 <= reg.tiles.length - 1) {
                            if ((reg.tiles[reg.activeX][reg.activeY].type != 100) && (reg.tiles[reg.activeX][reg.activeY + 1].type != 100)) {
                                if ((mouse_position.x > reg.tiles[reg.activeX][reg.activeY + 1].x) && (mouse_position.x < reg.tiles[reg.activeX][reg.activeY + 1].x + 64) && (mouse_position.y > reg.tiles[reg.activeX][reg.activeY + 1].y) && (mouse_position.y < reg.tiles[reg.activeX][reg.activeY + 1].y + 64)) {

                                    // De-activate
                                    reg.tileIsActive = false;
                                    reg.tiles[reg.activeX][reg.activeY].activated = false;

                                    // Swap Types
                                    savedType = reg.tiles[reg.activeX][reg.activeY].type;
                                    reg.tiles[reg.activeX][reg.activeY].type = reg.tiles[reg.activeX][reg.activeY + 1].type;
                                    reg.tiles[reg.activeX][reg.activeY + 1].type = savedType;

                                    // Check for Matches
                                    reg.gameState = 1;

                                    reg.sound_Swap.play();
                                    clickDelayCounter = 0;

                                }
                            }
                        }
                    }
                    // Check tile down
                    if (reg.tileIsActive) {
                        if (reg.activeY - 1 >= 0) {
                            if ((reg.tiles[reg.activeX][reg.activeY].type != 100) && (reg.tiles[reg.activeX][reg.activeY - 1].type != 100)) {
                                if ((mouse_position.x > reg.tiles[reg.activeX][reg.activeY - 1].x) && (mouse_position.x < reg.tiles[reg.activeX][reg.activeY - 1].x + 64) && (mouse_position.y > reg.tiles[reg.activeX][reg.activeY - 1].y) && (mouse_position.y < reg.tiles[reg.activeX][reg.activeY - 1].y + 64)) {

                                    // De-activate
                                    reg.tileIsActive = false;
                                    reg.tiles[reg.activeX][reg.activeY].activated = false;

                                    // Swap Types
                                    savedType = reg.tiles[reg.activeX][reg.activeY].type;
                                    reg.tiles[reg.activeX][reg.activeY].type = reg.tiles[reg.activeX][reg.activeY - 1].type;
                                    reg.tiles[reg.activeX][reg.activeY - 1].type = savedType;

                                    // Check for Matches
                                    reg.gameState = 1;

                                    reg.sound_Swap.play();
                                    clickDelayCounter = 0;

                                }
                            }
                        }
                    }

                    // Check for Matches
                    //reg.gameState = 1;

                }
            }


            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                if (clickDelayCounter > clickDelayCounterLength) {
                    for (int i = 0; i < reg.tiles.length; i++) {
                        for (int j = 0; j < reg.tiles.length; j++) {

                            if ((mouse_position.x > reg.tiles[i][j].x) && (mouse_position.x < reg.tiles[i][j].x + 64) && (mouse_position.y > reg.tiles[i][j].y) && (mouse_position.y < reg.tiles[i][j].y + 64)) {

                                if (reg.tiles[i][j].type != 100) {

                                    // De-activate previous tile
                                    reg.tiles[reg.activeX][reg.activeY].activated = false;

                                    // Active new tile
                                    reg.tiles[i][j].activated = true;
                                    reg.tileIsActive = true;
                                    reg.activeX = i;
                                    reg.activeY = j;

                                    reg.sound_Activate.play();

                                    clickDelayCounter = 0;

                                    System.out.println("Activated: " + i + " " + j);

                                }

                            }

                        }
                    }
                }


            }
        }

        // Detect Matches
        if (reg.gameState == 1) {
            detectMatches();
        }

        // Tiles Falling
        if (reg.gameState == 2) {
            tilesFalling();
        }








        // AI Turn
        if (reg.gameState == 150) { // Start of Turn Delay
            counterTurnStart++;
            reg.draw.displayText = "";
            if (counterTurnStart > 150/2) {
                reg.draw.displayText = "AI Turn!";
            }
            if (counterTurnStart > 150) {
                reg.gameState = 100;
                counterTurnStart = 0;
            }
        }

        if (reg.gameState == 151) { // End of Turn Delay
            counterTurnStart++;
            if (counterTurnStart > 130) {
                reg.gameState = 50;
                counterTurnStart = 0;
            }
        }

        if (reg.gameState == 100) {
            // AI Finds Match & Moves Match
            AIFindMatches();
        }

        if (reg.gameState == 101) {
            // AI Finds Match & Moves Match
            AIDetectMatches();
        }

        // AI Tiles Falling
        if (reg.gameState == 102) {
            tilesFalling();
        }




    }





    public void detectMatches() {

        matched = false;

        detectMatch5Hori();
        detectMatch5Vert();
        detectMatch4Hori();
        detectMatch4Vert();
        detectMatch3Hori();
        detectMatch3Vert();

        if (matched) {
            reg.gameState = 2;
        }
        else {
            // End of Player Turn
            reg.gameState = 51;
        }

    }

    public void AIFindMatches() {

        aiMadeAMatch = false;

        // AI
        //AIdetectMatch5Hori();
        //AIdetectMatch5Vert();
        //AIdetectMatch4Hori();
        //AIdetectMatch4Vert();
        AIdetectMatch3Hori();
        AIdetectMatch3Vert();

        if (aiMadeAMatch) {
            reg.gameState = 101;
        }
        else { // Couldn't Find AI MATCH
            reg.gameState = 151; // -> End of AI Turn - Add Map Reset Later - No Possible Matches
        }


    }

    public void AIDetectMatches() {

        matched = false;

        detectMatch5Hori();
        detectMatch5Vert();
        detectMatch4Hori();
        detectMatch4Vert();
        detectMatch3Hori();
        detectMatch3Vert();


        if (matched) {
            reg.gameState = 102;
            System.out.println("AI Made a MATCH!");
        }
        else {
            // End of Enemy Turn
            reg.gameState = 151;
            System.out.println("AI couldn't find a match.");
        }

    }


    public void addTextEffect(int type, String text, float x, float y, float moveSpeedY) {

        textEffect temp = new textEffect(type, x, y, moveSpeedY);
        temp.text = text;
        reg.textEffectArray.add(temp);

    }

    public void deleteTextEffects() {

        boolean repeat = false;

        for (textEffect t : reg.textEffectArray) {
            if (t.destroy) {
                reg.textEffectArray.remove(t);
                repeat = true;
                break;
            }
        }

        if (repeat) {
            deleteTextEffects();
        }

    }

    public void determineManaAdded(int manaType, int amtOfMana) {

        if (manaType == 0) { // Red Mana
            reg.mana_red += amtOfMana;
        }
        else if (manaType == 1) { // Green Mana
            reg.mana_green += amtOfMana;
        }
        else if (manaType == 2) { // Blue Mana
            reg.mana_blue += amtOfMana;
        }
        else if (manaType == 3) { // Yellow Mana
            reg.mana_yellow += amtOfMana;
        }
        else if (manaType == 4) { // Coins
            reg.coins += amtOfMana;
        }
        else if (manaType == 5) { // Exp
            reg.exp += amtOfMana;
        }
        else if (manaType == 6) { // Skulls
            reg.aiHealth -= amtOfMana;
        }

    }

    public void aiDetermineManaAdded(int manaType, int amtOfMana) {

        if (manaType == 0) { // Red Mana
            reg.aiMana_red += amtOfMana;
        }
        else if (manaType == 1) { // Green Mana
            reg.aiMana_green += amtOfMana;
        }
        else if (manaType == 2) { // Blue Mana
            reg.aiMana_blue += amtOfMana;
        }
        else if (manaType == 3) { // Yellow Mana
            reg.aiMana_yellow += amtOfMana;
        }
        else if (manaType == 4) { // Coins
            //reg.coins += amtOfMana;
        }
        else if (manaType == 5) { // Exp
            //reg.exp += amtOfMana;
        }
        else if (manaType == 6) { // Skulls
            reg.health -= amtOfMana;
        }

    }



    public void detectMatch3Hori() {
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (reg.tiles[i][j].type != 100) {
                    if ((i + 2) < reg.tiles.length) {
                        if ((reg.tiles[i][j].type == reg.tiles[i + 1][j].type) && (reg.tiles[i][j].type == reg.tiles[i + 2][j].type)) {

                            if (reg.gameState >= 100) {
                                aiDetermineManaAdded(reg.tiles[i][j].type, 3);
                            }
                            else {
                                determineManaAdded(reg.tiles[i][j].type, 3);
                            }
                            addTextEffect(reg.tiles[i][j].type, "3", reg.tiles[i][j].x + 82, reg.tiles[i][j].y, -0.7f);

                            for (int k = 0; k < 3; k++) {
                                reg.tiles[i + k][j].type = 100;
                            }
                            System.out.println("Match 3 Horizontal at " + i + " " + j);
                            matched = true;
                            reg.sound_Destroy.play();
                            reg.addToScore += 3;

                        }
                    }
                }
            }
        }
    }

    public void detectMatch3Vert() {
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (reg.tiles[i][j].type != 100) {
                    if ((j + 2) < reg.tiles.length) {
                        if ((reg.tiles[i][j].type == reg.tiles[i][j + 1].type) && (reg.tiles[i][j].type == reg.tiles[i][j + 2].type)) {

                            if (reg.gameState >= 100) {
                                aiDetermineManaAdded(reg.tiles[i][j].type, 3);
                            }
                            else {
                                determineManaAdded(reg.tiles[i][j].type, 3);
                            }
                            addTextEffect(reg.tiles[i][j].type, "3", reg.tiles[i][j].x + 82 - 64, reg.tiles[i][j].y, -0.7f);

                            for (int k = 0; k < 3; k++) {
                                reg.tiles[i][j + k].type = 100;
                            }
                            System.out.println("Match 3 Vertical at " + i + " " + j);
                            matched = true;
                            reg.sound_Destroy.play();
                            reg.addToScore += 3;
                        }
                    }
                }
            }
        }
    }

    public void detectMatch4Hori() {
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (reg.tiles[i][j].type != 100) {
                    if ((i + 3) < reg.tiles.length) {
                        if ((reg.tiles[i][j].type == reg.tiles[i + 1][j].type) && (reg.tiles[i][j].type == reg.tiles[i + 2][j].type) && (reg.tiles[i][j].type == reg.tiles[i + 3][j].type)) {

                            if (reg.gameState >= 100) {
                                aiDetermineManaAdded(reg.tiles[i][j].type, 4);
                            }
                            else {
                                determineManaAdded(reg.tiles[i][j].type, 4);
                            }
                            addTextEffect(reg.tiles[i][j].type, "MATCH 4!", reg.tiles[i][j].x + 82, reg.tiles[i][j].y + 64, 0.7f);
                            addTextEffect(reg.tiles[i][j].type, "4", reg.tiles[i][j].x + 82, reg.tiles[i][j].y, -0.7f);

                            for (int k = 0; k < 4; k++) {
                                reg.tiles[i + k][j].type = 100;
                            }
                            System.out.println("Match 4 Horizontal at " + i + " " + j);
                            matched = true;
                            reg.sound_Destroy2.play();
                            reg.addToScore += 6;
                        }
                    }
                }
            }
        }
    }

    public void detectMatch4Vert() {
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (reg.tiles[i][j].type != 100) {
                    if ((j + 3) < reg.tiles.length) {
                        if ((reg.tiles[i][j].type == reg.tiles[i][j + 1].type) && (reg.tiles[i][j].type == reg.tiles[i][j + 2].type) && (reg.tiles[i][j].type == reg.tiles[i][j + 3].type)) {

                            if (reg.gameState >= 100) {
                                aiDetermineManaAdded(reg.tiles[i][j].type, 4);
                            }
                            else {
                                determineManaAdded(reg.tiles[i][j].type, 4);
                            }
                            addTextEffect(reg.tiles[i][j].type, "MATCH 4!", reg.tiles[i][j].x + 82 - 64, reg.tiles[i][j].y + 64, 0.7f);
                            addTextEffect(reg.tiles[i][j].type, "4", reg.tiles[i][j].x + 82 - 64, reg.tiles[i][j].y, -0.7f);

                            for (int k = 0; k < 4; k++) {
                                reg.tiles[i][j + k].type = 100;
                            }
                            System.out.println("Match 4 Vertical at " + i + " " + j);
                            matched = true;
                            reg.sound_Destroy2.play();
                            reg.addToScore += 6;
                        }
                    }
                }
            }
        }
    }

    public void detectMatch5Hori() {
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (reg.tiles[i][j].type != 100) {
                    if ((i + 4) < reg.tiles.length) {
                        if ((reg.tiles[i][j].type == reg.tiles[i + 1][j].type) && (reg.tiles[i][j].type == reg.tiles[i + 2][j].type) && (reg.tiles[i][j].type == reg.tiles[i + 3][j].type) && (reg.tiles[i][j].type == reg.tiles[i + 4][j].type)) {

                            if (reg.gameState >= 100) {
                                aiDetermineManaAdded(reg.tiles[i][j].type, 5);
                            }
                            else {
                                determineManaAdded(reg.tiles[i][j].type, 5);
                            }
                            addTextEffect(reg.tiles[i][j].type, "MATCH 5!", reg.tiles[i][j].x + 82, reg.tiles[i][j].y + 64, 0.7f);
                            addTextEffect(reg.tiles[i][j].type, "5", reg.tiles[i][j].x + 82, reg.tiles[i][j].y, -0.7f);

                            for (int k = 0; k < 5; k++) {
                                reg.tiles[i + k][j].type = 100;
                            }
                            System.out.println("Match 5 Horizontal at " + i + " " + j);
                            matched = true;
                            reg.sound_Destroy3.play();
                            reg.addToScore += 15;
                        }
                    }
                }
            }
        }
    }

    public void detectMatch5Vert() {
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (reg.tiles[i][j].type != 100) {
                    if ((j + 4) < reg.tiles.length) {
                        if ((reg.tiles[i][j].type == reg.tiles[i][j + 1].type) && (reg.tiles[i][j].type == reg.tiles[i][j + 2].type) && (reg.tiles[i][j].type == reg.tiles[i][j + 3].type) && (reg.tiles[i][j].type == reg.tiles[i][j + 4].type)) {

                            if (reg.gameState >= 100) {
                                aiDetermineManaAdded(reg.tiles[i][j].type, 5);
                            }
                            else {
                                determineManaAdded(reg.tiles[i][j].type, 5);
                            }
                            addTextEffect(reg.tiles[i][j].type, "MATCH 5!", reg.tiles[i][j].x + 82 - 64, reg.tiles[i][j].y + 64, 0.7f);
                            addTextEffect(reg.tiles[i][j].type, "5", reg.tiles[i][j].x + 82 - 64, reg.tiles[i][j].y, -0.7f);

                            for (int k = 0; k < 5; k++) {
                                reg.tiles[i][j + k].type = 100;
                            }
                            System.out.println("Match 5 Vertical at " + i + " " + j);
                            matched = true;
                            reg.sound_Destroy3.play();
                            reg.addToScore += 15;
                        }
                    }
                }
            }
        }
    }




    public void tilesFalling() {

        boolean repeat = false;
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (reg.tiles[i][j].type != 100) {
                    if ((j - 1) >= 0) {
                        if (reg.tiles[i][j - 1].type == 100) {

                            // Swap Tiles
                            savedType = reg.tiles[i][j].type;
                            reg.tiles[i][j].type = reg.tiles[i][j - 1].type;
                            reg.tiles[i][j - 1].type = savedType;

                            repeat = true;

                        }
                    }
                }
            }
        }

        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (reg.tiles[i][j].type == 100) {
                    if (j == reg.tiles.length-1) {
                        reg.tiles[i][j].type = MathUtils.random(0,6);
                        System.out.println("Added a Tile in Row " + i);

                        repeat = true;
                    }
                }
            }
        }

        if (repeat) {
            tilesFalling();
        }

        if (reg.gameState >= 100) {
            // Check for Matches
            reg.gameState = 101;
        }
        else {
            // Check for Matches
            reg.gameState = 1;
        }

        System.out.println("Completed Tiles Falling!");

    }






    public void AIdetectMatch3Hori() {
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (aiMadeAMatch == false) {
                    if (reg.tiles[i][j].type != 100) {
                        if ((i + 3) < reg.tiles.length) {
                            if ((reg.tiles[i][j].type == reg.tiles[i + 1][j].type) && (reg.tiles[i][j].type == reg.tiles[i + 3][j].type)) {

                                System.out.println("Detected AI Match at " + i + " " + j + " of type " + reg.tiles[i][j].type);

                                // Swap Tiles
                                int saved = reg.tiles[i + 2][j].type;
                                reg.tiles[i + 2][j].type = reg.tiles[i + 3][j].type;
                                reg.tiles[i + 3][j].type = saved;

                                aiMadeAMatch = true;

                                break;

                            } else if ((reg.tiles[i][j].type == reg.tiles[i + 2][j].type) && (reg.tiles[i][j].type == reg.tiles[i + 3][j].type)) {

                                System.out.println("Detected Horizontal AI Match at " + i + " " + j + " of type " + reg.tiles[i][j].type);

                                // Swap Tiles
                                int saved = reg.tiles[i][j].type;
                                reg.tiles[i][j].type = reg.tiles[i + 1][j].type;
                                reg.tiles[i + 1][j].type = saved;

                                aiMadeAMatch = true;

                                break;

                            }
                        }
                    }
                }
            }
        }
    }

    public void AIdetectMatch3Vert() {
        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                if (aiMadeAMatch == false) {
                    if (reg.tiles[i][j].type != 100) {
                        if ((j + 3) < reg.tiles.length) {
                            if ((reg.tiles[i][j].type == reg.tiles[i][j + 1].type) && (reg.tiles[i][j].type == reg.tiles[i][j + 3].type)) {

                                System.out.println("Detected Vertical AI Match at " + i + " " + j + " of type " + reg.tiles[i][j].type);

                                // Swap Tiles
                                int saved = reg.tiles[i][j + 2].type;
                                reg.tiles[i][j + 2].type = reg.tiles[i][j + 3].type;
                                reg.tiles[i][j + 3].type = saved;

                                aiMadeAMatch = true;

                                break;

                            }
                            if ((reg.tiles[i][j].type == reg.tiles[i][j + 2].type) && (reg.tiles[i][j].type == reg.tiles[i][j + 3].type)) {

                                System.out.println("Detected Vertical AI Match at " + i + " " + j + " of type " + reg.tiles[i][j].type);

                                // Swap Tiles
                                int saved = reg.tiles[i][j].type;
                                reg.tiles[i][j].type = reg.tiles[i][j + 1].type;
                                reg.tiles[i][j + 1].type = saved;

                                aiMadeAMatch = true;

                                break;

                            }
                        }
                    }
                }
            }
        }
    }







}
