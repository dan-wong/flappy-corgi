package com.daniel.flappycorgi.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.daniel.flappycorgi.util.BitmapUtil;
import com.daniel.flappycorgi.util.PhysicsEngineUtil;

public class CharacterSprite extends Sprite {
    private static final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private static final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private boolean gameOver = false;
    private boolean upwardForce = false;
    private int upwardForceCounter = 0;

    public CharacterSprite(Bitmap bmp, int desiredWidth) {
        super();
        this.image = BitmapUtil.resizeBitmap(bmp, desiredWidth);

        spriteData.x = screenWidth / 3 - this.image.getWidth() / 2;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, spriteData.x, spriteData.y, null);
    }

    @Override
    public void update() {
        if (!gameOver) {
            if (upwardForce) {
                PhysicsEngineUtil.calculateNewPosition(this.spriteData, 0, -13);
                upwardForceCounter++;

                if (upwardForceCounter == 3) {
                    upwardForce = false;
                    upwardForceCounter = 0;
                }
            } else {
                PhysicsEngineUtil.calculateNewPosition(this.spriteData, 0, PhysicsEngineUtil.GRAVITY);
            }
        }
        if (spriteData.y > screenHeight - image.getHeight()) {
            spriteData.y = screenHeight - image.getHeight();
        } else if (spriteData.y < 0) {
            spriteData.yVelocity = 5;
        }
    }

    @Override
    public void manageTouch() {
        upwardForce = true;
    }

    @Override
    public void reset() {
        spriteData.y = 0;
    }

    @Override
    public boolean isGameOver() {
        return spriteData.y == screenHeight - image.getHeight();
    }
}
