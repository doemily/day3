package com.example.day3;

import android.view.View;
import android.widget.TextView;

public class ViewController implements View.OnClickListener {
    // there are abstract methods we need to implement these methods

    private TextView helloText;
    private int numClicks;

    public ViewController(TextView _helloText) {
        helloText = _helloText;
        numClicks = 0;
    }

    @Override
    public void onClick(View v) {
        numClicks += 1;
        if (v.getId() == R.id.goodbyeButton) {
            helloText.setText("Goodbye! (This is click " + numClicks + ")");
        }
        else {
            helloText.setText("Hello Worldz!!!!! (This is click " + numClicks + ")");
        }
    }
}
