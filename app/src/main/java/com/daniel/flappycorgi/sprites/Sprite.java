package com.daniel.flappycorgi.sprites;

import android.graphics.Canvas;

import com.daniel.flappycorgi.dataclass.SpriteData;

public abstract class Sprite {
    protected SpriteData spriteData;

    protected Sprite() {
        this.spriteData = new SpriteData(0, 0, 0, 0);
    }

    protected Sprite(int x, int y, double xSpeed, double ySpeed) {
        this.spriteData = new SpriteData(x, y, xSpeed, ySpeed);
    }

    public SpriteData getSpriteData() {
        return spriteData;
    }

    public abstract void draw(Canvas canvas);

    public abstract void update();

    public abstract void manageTouch();
}
