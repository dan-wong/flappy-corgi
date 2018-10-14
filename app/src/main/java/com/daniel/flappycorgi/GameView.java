package com.daniel.flappycorgi;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.daniel.flappycorgi.sprites.CharacterSprite;
import com.daniel.flappycorgi.sprites.EnemySprite;
import com.daniel.flappycorgi.sprites.Sprite;
import com.daniel.flappycorgi.util.CollisionDetectionUtil;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private List<Sprite> sprites;
    private CharacterSprite characterSprite;

    private boolean gameOver = false;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

        thread = new MainThread(getHolder(), this);
        sprites = new ArrayList<>();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.characterSprite = new CharacterSprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.corgi),
                350);
        EnemySprite enemySprite = new EnemySprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.cat),
                300);

        EnemySprite enemySprite2 = new EnemySprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.cat),
                300);

        sprites.add(this.characterSprite);
        sprites.add(enemySprite);
        sprites.add(enemySprite2);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick(); //Something about accessibility requiring the performClick method to be called
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        if (gameOver) {
            gameOver = false;
            sprites.forEach(Sprite::reset);
        }
        sprites.forEach(Sprite::manageTouch);

        return super.performClick();
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
            sprites.forEach(sprite -> sprite.draw(canvas));
        }
    }

    public void update() {
        if (!gameOver) {
            sprites.forEach(Sprite::update);
            gameOver = sprites.parallelStream().anyMatch(sprite -> !sprite.equals(characterSprite)
                    && CollisionDetectionUtil.detectCollision(characterSprite, sprite)) ||
                    sprites.parallelStream().anyMatch(Sprite::isGameOver);
        }
    }
}
