package com.daniel.flappycorgi.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.daniel.flappycorgi.GameView;
import com.daniel.flappycorgi.R;

public class GameOverSprite {
    private static final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private static final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private Bitmap image;
    private int opacity = 0;

    public GameOverSprite(GameView gameView) {
        Bitmap bmp = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.gameover);
        int desiredWidth = (int) (screenWidth * 0.75);
        int newHeight = (int) (bmp.getHeight() / (double) bmp.getWidth() * desiredWidth);
        this.image = Bitmap.createScaledBitmap(bmp, desiredWidth, newHeight, false);
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAlpha(this.opacity);

        canvas.drawBitmap(image,
                (screenWidth / 2) - (image.getWidth() / 2),
                (screenHeight / 2) - (image.getHeight() / 2),
                paint);
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }
}
