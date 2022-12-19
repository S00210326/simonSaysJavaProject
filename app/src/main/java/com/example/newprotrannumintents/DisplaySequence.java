package com.example.newprotrannumintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DisplaySequence extends AppCompatActivity {

        List<Integer> numSeq = new ArrayList<>();
        Button b1;
        Integer score;
        int arrayCheckNum;

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
    public void getAndGoHard(List<Integer> numSequence){
        Intent i = new Intent(this, AccelerometerAttempt.class);
        i.putIntegerArrayListExtra("myList",(ArrayList<Integer>) numSequence);
        i.putExtra("MY_SCORE", score);
        i.putExtra("ARRAY_CHECK", arrayCheckNum);
        startActivity(i);
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