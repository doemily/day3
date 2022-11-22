package com.example.day3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

public class Spot {
    // private float cx;
    // private float cy;
    private float radius;
    private float centers[];
    private Paint color;

    public Spot() {
        centers = new float[2];
        centers[0] = 50;
        // cx = 50;
        // cy = 50;
        radius = 50;
        color = new Paint();
        color.setARGB(255, 255, 0, 0);
    }

    /*
        // a normal constructor looks like this:
        Spot aSpot = new Spot();
        // duplicate requires a COPY constructor
        Spot duplicateSpot = new Spot(aSpot);
        duplicateSpot.setRandomColor(); // only changes the color of dupSpot
        Spot bSpot = aSpot; // this gives a second reference to the original aSpot, meanwhile COPY constructor makes a brand new Spot object
        Spot.setRandomColor(); // change the color of aSpot (aka bSpot)
        // a reference is like having the address
    */

    // copy constructor
    public Spot(Spot other) {
        // goal of a copy constructor is to copy all of the data from the other object into THIS object (this object is a NEW one)
        // cx = other.cx;
        // this.cy = other.cy; // could use this.cy or cy
        // first create new container (array, arrayList, etc.)
        // copy each item into new container
        // !!!!!! count the NEWs when creating a copy constructor (MIDTERM) !!!!!!
        centers = new float[2]; // exact line from default constructor
        for (int i = 0; i < centers.length; i++) {
            // if og item is an object that was allocated anywhere with a NEW, this must also create a new object
            centers[i] = other.centers[i]; // okay because this is a primitive type (not made with new)
        }
        radius = other.radius;
        // color = other.color; WRONG not primitive type, shallow copy, there for will match the color of the original spot.. if og spot changes to yellow, copySpot will also be yellow (WRONG)
        color = new Paint(); // deep copy requires allocating NEW memory if the constructor allocated NEW memory
        // OR color.setColor(other.color.getColor());

    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(centers[0], centers[1], radius, color);
        // Log.d("Spot.draw", "drewAt "+cx+ " , "+cy);
    }

    public void setRadius(float r) {
        radius = r;
    }

    public void setCenters(float x, float y) {
        centers[0] = x;
        centers[1] = y;
    }

    public void setRandomColor() {

        Random random = new Random();
        int randR = (int)(Math.random() * 255);
        int randG = (int)(Math.random() * 255);
        int randB = (int)(Math.random() * 255);

        color.setARGB(255, randR, randG, randB);
    }
}
