package com.app.tappydefender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.Random;

public class EnemyShip {
    private Bitmap bitmap;
    private int x, y;
    private int speed = 1;
    // Detect enemies leaving the screen
    private int maxX;
    private int minX;
    // Spawn enemies within screen bounds
    private int maxY;
    private int minY;
    private static final String TAG = "EnemyShip";

    public Bitmap getBitmap(){
        return bitmap;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public EnemyShip(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource
                (context.getResources(), R.drawable.enemy);
        Log.e(TAG, "EnemyShip x: "+screenX );
        Log.e(TAG, "EnemyShip y: "+screenY );
        maxX = screenX;
        maxY = screenY - bitmap.getHeight();
        minX = 0;
        minY = 0;
        Random generator = new Random();
        speed = generator.nextInt(6)+10;
        x = screenX;
        y = generator.nextInt(maxY) - bitmap.getHeight();
        Log.e(TAG, "EnemyShip: maxY "+maxY );
    }

    public void update(int playerSpeed){

        // Move to the left
//        x -= playerSpeed;
        x -= speed;
        //respawn when off screen
        if(x < minX-bitmap.getWidth()){
            Random generator = new Random();
            speed = generator.nextInt(10)+10;
            x = maxX;
            y = generator.nextInt(maxY) - bitmap.getHeight()/2;
            if (y<0){
                y = minY;
            }
            Log.e(TAG, "update: "+y );
        }

    }

}
