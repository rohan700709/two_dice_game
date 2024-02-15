package com.mygdx.game.entities;

public class textEffect {

    public int type = 0; // 0 - Text
    public float moveSpeedY = -0.7f;
    public float x = 0;
    public float y = 0;
    public boolean destroy = false;
    public String text = "not set!";
    public int counter = 0;
    public int counterDeleteLength = 40;

    public textEffect(int type, float x, float y, float moveSpeedY) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.moveSpeedY = moveSpeedY;
    }

    public void update() {

        y += moveSpeedY;

        counter++;
        if (counter > counterDeleteLength) {
            destroy = true;
        }

    }


}
