package com.daniel.flappycorgi.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.daniel.flappycorgi.util.BitmapUtil;
import com.daniel.flappycorgi.util.PhysicsEngineUtil;

public class EnemySprite extends Sprite {
    private static final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private static final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private final Bitmap image;

    public EnemySprite(Bitmap bmp, int desiredWidth) {
        super();
        this.image = BitmapUtil.resizeBitmap(bmp, desiredWidth);

        spriteData.y = screenHeight / 2 - this.image.getHeight() / 2;
        spriteData.xVelocity = -10;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, spriteData.x, spriteData.y, null);
    }

    @Override
    public void update() {
        PhysicsEngineUtil.calculateNewPosition(this.spriteData, 0, 0);
        if (spriteData.x < (0 - this.image.getWidth())) {
            spriteData.x = screenWidth;
        }
    }

    @Override
    public void manageTouch() {
    }
}
