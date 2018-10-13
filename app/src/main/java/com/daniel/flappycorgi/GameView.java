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

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private List<Sprite> sprites;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

        thread = new MainThread(getHolder(), this);
        sprites = new ArrayList<>();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        CharacterSprite characterSprite = new CharacterSprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.corgi),
                350);
        EnemySprite enemySprite = new EnemySprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.cat),
                300);

        sprites.add(characterSprite);
        sprites.add(enemySprite);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sprites.forEach(Sprite::manageTouch);
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
            sprites.forEach(sprite -> sprite.draw(canvas));
        }
    }

    public void update() {
        sprites.forEach(Sprite::update);
    }
}
