package com.daniel.flappycorgi;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.daniel.flappycorgi.sprites.CharacterSprite;
import com.daniel.flappycorgi.sprites.GameOverSprite;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private CharacterSprite characterSprite;
    private GameOverSprite gameOverSprite;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        characterSprite = new CharacterSprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.corgi),
                400);
        gameOverSprite = new GameOverSprite(this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        characterSprite.manageTouch();
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            characterSprite.draw(canvas);
            gameOverSprite.draw(canvas);
        }
    }

    public void update() {
        boolean gameOver = characterSprite.update();
        if (gameOver) {
            gameOverSprite.setOpacity(255);
        } else {
            gameOverSprite.setOpacity(0);
        }
    }
}
