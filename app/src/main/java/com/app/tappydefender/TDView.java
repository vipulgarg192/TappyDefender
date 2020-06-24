package com.app.tappydefender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TDView extends SurfaceView implements Runnable{

    volatile boolean playing;
    Thread gameThread = null;

    private PlayerShip player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;

    public EnemyShip enemy1;
    public EnemyShip enemy2;
    public EnemyShip enemy3;

    int x,y;

    public TDView(Context context, int x, int y) {
        super(context);
        ourHolder = getHolder();
        paint = new Paint();
        player = new PlayerShip(context,x,y);

        enemy1 = new EnemyShip(context, x, y);
//        enemy2 = new EnemyShip(context, x, y);
//        enemy3 = new EnemyShip(context, x, y);
    }


    public TDView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TDView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TDView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update(){
        player.update();
        enemy1.update(player.getSpeed());
        enemy2.update(player.getSpeed());
        enemy3.update(player.getSpeed());
    }

    private void draw(){
        if (ourHolder.getSurface().isValid()) {
            //First we lock the area of memory we will be drawing to
            canvas = ourHolder.lockCanvas();
            // Rub out the last frame
            canvas.drawColor(Color.argb(255, 0, 0, 0));
            // Draw the player
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);

            canvas.drawBitmap
                    (enemy1.getBitmap(),
                            enemy1.getX(),
                            enemy1.getY(), paint);
            canvas.drawBitmap
                    (enemy2.getBitmap(),
                            enemy2.getX(),
                            enemy2.getY(), paint);
            canvas.drawBitmap
                    (enemy3.getBitmap(),
                            enemy3.getX(),
                            enemy3.getY(), paint);

            // Unlock and draw the scene
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control(){
        try
        {
            gameThread.sleep(17);
        }
        catch (InterruptedException e) {    }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        }
        catch (InterruptedException e) {
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // There are many different events in MotionEvent
        // We care about just 2 - for now.
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            // Has the player lifted their finger up?
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                // Do something here
                break;
            // Has the player touched the screen?
            case MotionEvent.ACTION_DOWN:
                // Do something here
                player.setBoosting();
                break;
        }
        return true;
    }
}
