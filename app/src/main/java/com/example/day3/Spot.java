package com.example.day3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

public class Spot {
    private float radius;
    private float cx;
    private float cy;
    private Paint color;

    public Spot() {
        cx = 50;
        cy = 50;
        radius = 50;
        color = new Paint();
        color.setARGB(255, 255, 0, 0);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, color);
        Log.d("Spot.draw", "drewAt "+cx+ " , "+cy);
    }

    public void setRadius(float r) {
        radius = r;
    }

    public void setCenters(float x, float y) {
        cx = x;
        cy = y;
    }

    public void setRandomColor() {

        Random random = new Random();
        int randR = (int)(Math.random() * 255);
        int randG = (int)(Math.random() * 255);
        int randB = (int)(Math.random() * 255);

        color.setARGB(255, randR, randG, randB);
    }
}
