package com.example.arturbaboskin.touchtest;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    //private GestureDetector detector;

    private GestureLibrary mGestureLib;
    private GestureOverlayView gestures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);

        /*detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                text.setText(String.valueOf(v)+" "+String.valueOf(v1));
                return false;
            }
        });*/


        mGestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!mGestureLib.load())
            finish();

        gestures = findViewById(R.id.gesture_view);
        gestures.setGestureVisible(false);

        gestures.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                ArrayList<Prediction> predictions = mGestureLib.recognize(gesture);
                for (Prediction i : predictions)
                    if (i.score > 2.0)
                        if (i.name.equals("test"))
                            Toast.makeText(getApplicationContext(),
                                    "Это галочка",
                                    Toast.LENGTH_LONG)
                                    .show();
            }
        });

    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }*/

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        String result = text.getText().toString() +
                "x = " + String.valueOf(x) +
                "y = " + String.valueOf(y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                result += " action  = ACTION_DOWN\n";
                break;
            case MotionEvent.ACTION_MOVE:
                result += " action  = ACTION_MOVE\n";
                break;
            case MotionEvent.ACTION_UP:
                result += " action  = ACTION_UP\n";
                break;
            default:
                result += " action  = что-то\n";
        }
        text.setText(result);
        return true;
    }*/
}
