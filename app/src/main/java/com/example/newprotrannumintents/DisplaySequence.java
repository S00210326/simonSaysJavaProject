package com.example.newprotrannumintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DisplaySequence extends AppCompatActivity {

    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;
        List<Integer> numSeq = new ArrayList<>();
        Button b1;
        Integer score;
        int arrayCheckNum;
    int sequenceCount = 4, n = 0;
    int[] gameSequence = new int[120];
        Button btnUp, btnRight, btnLeft, btnDown, fb;

    int arrayIndex = 0;
    CountDownTimer ct = new CountDownTimer(6000,  1500) {

        public void onTick(long millisUntilFinished) {
            //mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);
            oneButton();
            //here you can have your logic to set text to edittext
        }

        public void onFinish() {
            //mTextField.setText("done!");
            // we now have the game sequence

            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            // start next activity

            // put the sequence into the next activity
            // stack overglow https://stackoverflow.com/questions/3848148/sending-arrays-with-intent-putextra
            //Intent i = new Intent(A.this, B.class);
            //i.putExtra("numbers", array);
            //startActivity(i);

            // start the next activity
            // int[] arrayB = extras.getIntArray("numbers");
        }
    };
    private void oneButton() {
        n = getRandom(sequenceCount);

        Toast.makeText(this, "Number = " + n, Toast.LENGTH_SHORT).show();

        switch (n) {
            case 1:
                flashButton(btnUp);
                gameSequence[arrayIndex++] = BLUE;
                break;
            case 2:
                flashButton(btnRight);
                gameSequence[arrayIndex++] = RED;
                break;
            case 3:
                flashButton(btnDown);
                gameSequence[arrayIndex++] = YELLOW;
                break;
            case 4:
                flashButton(btnLeft);
                gameSequence[arrayIndex++] = GREEN;
                break;
            default:
                break;
        }   // end switch
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent game = getIntent();
        score = game.getIntExtra("MY_SCORE",0);

//        numSeq = game.getIntegerArrayListExtra("numSeq");
        Log.i("Check", String.valueOf(arrayCheckNum));

        if (score == 0){
            Log.i("Check", "Checking if score is 0 and doing nothing");
        }
        else{

            numSeq = game.getIntegerArrayListExtra("numSeq");
        }

        b1 = findViewById(R.id.button);

        TextView cScore = findViewById(R.id.tvCurrentScore);
        cScore.setText(String.valueOf(score));


    }

    public void getAndGo(List<Integer> numSequence){
        Intent i = new Intent(this, ButtonAttempt.class);
        i.putIntegerArrayListExtra("myList",(ArrayList<Integer>) numSequence);
        i.putExtra("MY_SCORE", score);
        i.putExtra("ARRAY_CHECK", arrayCheckNum);
        startActivity(i);
    }
    private int getRandom(int maxValue) {
        return ((int) ((Math.random() * maxValue) + 1));
    }
    public void getAndGoHard(List<Integer> numSequence){
        Intent i = new Intent(this, AccelerometerAttempt.class);
        i.putIntegerArrayListExtra("myList",(ArrayList<Integer>) numSequence);
        i.putExtra("MY_SCORE", score);
        i.putExtra("ARRAY_CHECK", arrayCheckNum);
        startActivity(i);
    }
    private void flashButton(Button button) {
        fb = button;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                fb.setPressed(true);
                fb.invalidate();
                fb.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        fb.setPressed(false);
                        fb.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);

            } // end runnable
        };
        handler.postDelayed(r, 600);
    }


    public void getSequence(View view) {
        Random random = new Random();
        for (int i =0; i<2; i++){
            int val = random.nextInt(5-1)+1;
            numSeq.add(val);
        }
        getAndGo(numSeq);
    }

    public void getSequenceHard(View view) {

        Random random = new Random();
        for (int i =0; i<2; i++){
            int val = random.nextInt(5-1)+1;
            numSeq.add(val);
        }
        getAndGoHard(numSeq);

    }
}