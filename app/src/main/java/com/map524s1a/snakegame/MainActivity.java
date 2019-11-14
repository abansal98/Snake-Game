package com.map524s1a.snakegame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btnUp;
    ImageButton btnLeft;
    ImageButton btnRight;
    ImageButton btnDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUp = findViewById(R.id.UP);
        btnDown = findViewById(R.id.DOWN);
        btnLeft = findViewById(R.id.LEFT);
        btnRight = findViewById(R.id.RIGHT);

        View.OnTouchListener directionListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    Snake.updateDirection(v.getContentDescription().toString().toUpperCase());
                    System.out.println(v.getContentDescription());
                    return true;
                }

                return false;
            }
        };

        btnUp.setOnTouchListener(directionListener);
        btnDown.setOnTouchListener(directionListener);
        btnLeft.setOnTouchListener(directionListener);
        btnRight.setOnTouchListener(directionListener);
    }
}
