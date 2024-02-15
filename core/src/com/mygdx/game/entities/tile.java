package com.mygdx.game.entities;

public class tile {

    public int type = 0;
    public float x = 0;
    public float y = 0;
    public boolean activated = false;
    public boolean destroy = false;

    public tile(int type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

}
