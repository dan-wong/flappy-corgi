package com.daniel.flappycorgi.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {
    private static final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private static final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private static final double GRAVITY = 3;
    private static final double UPWARD_FORCE = 5;

    private Bitmap image;
    private boolean gameOver = false;
    private boolean upwardForce = false;

    private int upwardForceCounter = 0;
    private int upwardForceLimit = 15;

    private int x;
    private int y;
    private double yVelocity;

    public CharacterSprite(Bitmap bmp, int desiredWidth) {
        int newHeight = (int) (bmp.getHeight() / (double) bmp.getWidth() * desiredWidth);
        this.image = Bitmap.createScaledBitmap(bmp, desiredWidth, newHeight, false);

        x = screenWidth / 2 - this.image.getWidth() / 2;
        y = 0;
        yVelocity = 0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public boolean update() {
        if (!gameOver) {
            if (upwardForce) {
                if (upwardForceCounter == upwardForceLimit) {
                    upwardForce = false;
                    upwardForceCounter = 0;
                    yVelocity = 0;
                } else {
                    yVelocity = 75 - (UPWARD_FORCE * upwardForceCounter);
                    y -= Math.ceil(yVelocity);
                    upwardForceCounter++;
                }
            } else {
                yVelocity += GRAVITY;
                y += Math.ceil(yVelocity);
            }
        }

        if (y > screenHeight - image.getHeight()) {
            gameOver = true;
            y = screenHeight - image.getHeight();
        }

        return gameOver;
    }

    public void manageTouch() {
        if (gameOver) {
            y = 0;
            yVelocity = 0;
            gameOver = false;
        } else { //Otherwise it is an upward touch
            upwardForce = true;
        }
    }
}
