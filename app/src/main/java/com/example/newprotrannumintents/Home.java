package com.example.newprotrannumintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }

    public void playGame(View view) {
        Intent i = new Intent(this, DisplaySequence.class);
        startActivity(i);
    }

    public void GoStatsPage(View view) {
        Intent i = new Intent(this, StatsPage.class);

        startActivity(i);
    }
}