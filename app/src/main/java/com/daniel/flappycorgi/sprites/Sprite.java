package com.daniel.flappycorgi.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.daniel.flappycorgi.dataclass.SpriteData;

public abstract class Sprite {
    protected final SpriteData spriteData;
    protected Bitmap image;

    protected Sprite() {
        this.spriteData = new SpriteData(0, 0, 0, 0);
    }

    public SpriteData getSpriteData() {
        return spriteData;
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getWidth() {
        return image.getWidth();
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();
    public abstract void manageTouch();

    public abstract void reset();

    public abstract boolean isGameOver();
}
