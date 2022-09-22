package com.example.day3;

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
        implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, View.OnTouchListener{

    //View.OnClickListener{

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

        for(Spot s : spots) {
            s.draw(canvas);
        }
        Log.d("onDraw","drwew "+spots.size()+" spots");
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
            spots.add(s);
            // 4. tell myself to update/draw again soon
            invalidate();

            return true; // "consumed" the event
        }
        return false; // in this case we didn't do anything
    }
}
