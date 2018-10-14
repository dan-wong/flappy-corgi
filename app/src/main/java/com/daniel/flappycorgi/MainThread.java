package com.daniel.flappycorgi;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private static final int TARGET_FPS = 60;
    private static Canvas canvas;

    private final SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    public void setRunning(boolean isRunning) {
        this.running = isRunning;
    }

    @Override
    public void run() {
        long startTime, timeMillis, waitTime;
        long targetTime = 1000 / TARGET_FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception ignored) {
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                sleep(waitTime);
            } catch (Exception ignored) {
            }
        }
    }
}
