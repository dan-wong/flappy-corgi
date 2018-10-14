package com.daniel.flappycorgi.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.daniel.flappycorgi.util.BitmapUtil;
import com.daniel.flappycorgi.util.PhysicsEngineUtil;

import java.util.concurrent.ThreadLocalRandom;

public class EnemySprite extends Sprite {
    private static final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private static final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public EnemySprite(Bitmap bmp, int desiredWidth) {
        super();
        this.image = BitmapUtil.resizeBitmap(bmp, desiredWidth);

        spriteData.xVelocity = -10;
        reset();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, spriteData.x, spriteData.y, null);
    }

    @Override
    public void update() {
        PhysicsEngineUtil.calculateNewPosition(this.spriteData, 0, 0);
        if (spriteData.x < (0 - this.image.getWidth())) {
            reset();
        }
    }

    @Override
    public void manageTouch() {
    }

    @Override
    public void reset() {
        spriteData.x = screenWidth;
        spriteData.y = ThreadLocalRandom.current().nextInt(0, screenHeight - this.image.getHeight());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
