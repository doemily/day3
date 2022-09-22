package com.example.day3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView progressText = findViewById(R.id.progressText);
        TextView helloText = findViewById(R.id.helloText);
        helloText.setText("Hello Worldz!!!!!");

        // 1. find the view
        Button goodbyeButton = findViewById(R.id.goodbyeButton);
        Button helloAgain = findViewById(R.id.helloAgain);
        MySurfaceView surfaceView = findViewById(R.id.surfaceView);

        //Button drawButton = findViewById(R.id.drawButton);
        //Button eraseButton = findViewById(R.id.eraseButton);

        surfaceView.setCircleSizeTextView(progressText); // told MySurfaceView, here is the progress text i want it to update
        surfaceView.setOnTouchListener(surfaceView);

        // 2. link the view to an action (listener)
        ViewController viewController = new ViewController(helloText);

        goodbyeButton.setOnClickListener(viewController);
        helloAgain.setOnClickListener(viewController);


        // 1. find the seekBar
        SeekBar seekBar = findViewById(R.id.seekBar);
        // 2. link to the listener (which we found by searching API reference for seekBar
        //seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        seekBar.setOnSeekBarChangeListener(surfaceView);

        //drawButton.setOnClickListener(surfaceView);
        //eraseButton.setOnClickListener(surfaceView);


    }
}