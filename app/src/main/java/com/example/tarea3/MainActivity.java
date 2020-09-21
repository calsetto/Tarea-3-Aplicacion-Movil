package com.example.tarea3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, View.OnTouchListener {

    EditText Monto;
    TextView coin_100, coin_50, coin_20, coin_10, coin_5;
    Button blanco, gris, gris_oscuro, negro_claro, negro;
    ConstraintLayout backg;
    LinearLayout left, right;
    private static final  String TAG = "Swipe Position";
    float x1, x2, y1,y2;
    int MIN_DISTANCE = 150;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Monto = (EditText) findViewById(R.id.editText2);
        coin_100 = (TextView)findViewById(R.id.coin100);
        coin_50 = (TextView)findViewById(R.id.coin50);
        coin_20 = (TextView)findViewById(R.id.coin20);
        coin_10 = (TextView)findViewById(R.id.coin10);
        coin_5 = (TextView)findViewById(R.id.coin5);

        blanco = (Button) findViewById(R.id.blanco);
        blanco.setOnClickListener(this);
        gris = (Button) findViewById(R.id.gris);
        gris.setOnClickListener(this);
        gris_oscuro = (Button) findViewById(R.id.gris_oscuro);
        gris_oscuro.setOnClickListener(this);
        negro_claro = (Button) findViewById(R.id.negro_claro);
        negro_claro.setOnClickListener(this);
        negro = (Button) findViewById(R.id.negro);
        negro.setOnClickListener(this);

        backg = (ConstraintLayout) findViewById(R.id.backg);

        left = (LinearLayout) findViewById(R.id.linearLayout4);
        left.setOnTouchListener(this);
        right = (LinearLayout) findViewById(R.id.linearLayout5);
        right.setOnTouchListener(this);

        this.gestureDetector = new GestureDetector(MainActivity.this, this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action, keycode;
        action = event.getAction();
        keycode = event.getKeyCode();

        switch (keycode){

            case KeyEvent.KEYCODE_VOLUME_UP:
                if (KeyEvent.ACTION_UP == action){
                    String coin = Monto.getText().toString();
                    Calcular(coin);
                }
                break;
        }

        return super.dispatchKeyEvent(event);
    }

    public void Calcular(String s){

        try {

            int coin = Integer.parseInt(s);
            coin_100.setText(String.valueOf(coin / 100));
            coin_50.setText(String.valueOf(coin / 50));
            coin_20.setText(String.valueOf(coin / 20));
            coin_10.setText(String.valueOf(coin / 10));
            coin_5.setText(String.valueOf(coin / 5));

        } catch(NumberFormatException nfe) {
            // Handle parse error.
            Toast.makeText(this,"Debe ingresar una cantidad", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.blanco:
                backg.setBackgroundColor(Color.WHITE);
                break;

            case R.id.gris:
                backg.setBackgroundColor(Color.parseColor("#BBB9B9"));
                break;

            case R.id.gris_oscuro:
                backg.setBackgroundColor(Color.parseColor("#747373"));
                break;

            case R.id.negro_claro:
                backg.setBackgroundColor(Color.parseColor("#333333"));
                break;

            case R.id.negro:
                backg.setBackgroundColor(Color.parseColor("#000000"));
                break;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }





    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;

            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float valueX = x2 - x1;

                if(Math.abs(valueX) > MIN_DISTANCE){
                    //left to right

                    if(x2 > x1 && v.getId() == R.id.linearLayout4){
                        finish();
                    }
                    else if(x2 < x1 && v.getId() == R.id.linearLayout5){
                        Monto.setText("");
                    }
                }

        }

        return true;
    }
}
