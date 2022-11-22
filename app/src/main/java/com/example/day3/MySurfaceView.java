package com.example.day3;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

// 1. create subclass of SurfaceView in order to draw my own things

// *** 5. need to link this class to SurfaceView layout element
// *** in the xml file so that it knows to use this more specific
// *** version of a SurfaceView
public class MySurfaceView extends SurfaceView
        implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, View.OnTouchListener, Runnable{

    //View.OnClickListener{

    private MonopolyState.Token token;
    private Spot theBigRedSpot;
    private TextView circleSizeTextView; // originally a null references, to fix this, add a setter function
    private Paint imgPaint;
    private ArrayList<Spot> spots;


    public MySurfaceView(Context context, AttributeSet attrs) {

        super(context, attrs); // call parent constructor
        // 2. enable drawing
        setWillNotDraw(false);
        // 3. setup any required member variables
        theBigRedSpot = new Spot(); // this is at (50,50) and red
        theBigRedSpot.setCenters(100, 100);
        theBigRedSpot.setRandomColor();
        imgPaint = new Paint();
        imgPaint.setColor(Color.BLACK);
        spots = new ArrayList<>();
    }

    // 4. tell the view what to draw/how to draw
    protected void onDraw(Canvas canvas) {
        // DO NOT, if at all possible, ALLOCATE ANYTHING IN THE DRAW METHOD (memory use optimization)
        // This method could run 100+ times per second
        theBigRedSpot.draw(canvas);

        //Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.myimage); // myimage.png dragged to "drawable" folder
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.shrek);
        canvas.drawBitmap(image, 550, 50, imgPaint);

        synchronized(spots) {
            for (Spot s : spots) {
                s.draw(canvas);
            }
        }
        Log.d("onDraw","drew "+spots.size()+" spots");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        theBigRedSpot.setRadius(progress);
        invalidate(); //TELL the surface view (this), that the old view is no longer valid so it should redraw at it s earliest convenience
        circleSizeTextView.setText(""+progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void setCircleSizeTextView(TextView tv) {
        circleSizeTextView = tv;
    }


    @Override
    public void onClick(View view) {
        /*
        if (view.getId() == R.id.drawButton) {
            // theBigRedSpot.onDraw(canvas)
        }

        invalidate();
*/
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // check that it was the first tap
        if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) { // action_down is when they click down (press on screen)
            // ask for coordinates, finger location
            float x = motionEvent.getX();
            float y = motionEvent.getY();

            Log.d("onTouch", "x: "+x+" y: " +y);
            // tell surfaceview this is where spot should be
            // 1. create new spot
            Spot s = new Spot();
            // 2. make sure it knows where to draw
            s.setCenters(x, y);
            s.setRandomColor();
            // 3. add this new spot to my list of spots
            synchronized(spots) {
                spots.add(s);
            }
            // 4. tell myself to update/draw again soon
            invalidate();

            return true; // "consumed" the event
        }
        return false; // in this case we didn't do anything
    }

    // this method is runnable but is never used because we didn't create a thread for it yet, do so in main activity
    // when run, will give error because when changing color, we are modifying two things at a a time, the color, and the number of spots in the spots array
    // there you could be adding or removing dots in the array while also changing the color of those spots.
    // have to syncrhonize - only use for methods that you think is changing the spots array simultaneously with smth else
    // DONT DO WHOLE METHOD, DO AS LITTLE AS POSSIBLE (MAYBE A FEW LINES OF THE METHOD ONLY)
    @Override
    public void run() {
        // Goal: animate the spots by randomly changing colors
        // We will do this every so often
        while(true) {
            // sleep for a bit...
            try { // try to run this code
            sleep((int)(Math.random() % 2000));
            }
            catch(InterruptedException e) {
                // ...and if i get a specific error, then i dont care that it was interrupted, so do nothing
            }
            // update colors
            synchronized(spots) {
                for (Spot s : spots) {
                    s.setRandomColor();
                }
            }
            this.postInvalidate();
            }
        }
    }

