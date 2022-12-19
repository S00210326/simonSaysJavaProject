package com.example.newprotrannumintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class StatsPage extends AppCompatActivity {
    TextView scoreAsText;
    EditText e1  ;
    int score;


    List<String> items = new ArrayList<>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        DatabaseHandler db = new DatabaseHandler(this);
        Intent i2 = getIntent();
        score = i2.getIntExtra("MY_SCORE",5);
        scoreAsText = findViewById(R.id.etScore);
        e1 = findViewById(R.id.etName);
lv = findViewById(R.id.listViewScores);
        scoreAsText.setText(String.valueOf(score));


    }

    public void restartGame(View view) {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
    public void addHighScore(View view) {
        String name;

        String highscore;

        name = String.valueOf(e1.getText());
        highscore = String.valueOf(score);
        DatabaseHandler db = new DatabaseHandler(this);
        db.addHighscore(new HighscoreClass(name, Integer.parseInt(highscore)));
        int userCount = db.getHighscoreCount();
        Log.i("User count: ", String.valueOf(userCount));

        List<HighscoreClass> highscoreList = db.getAllHighscore();


        for (HighscoreClass cn2 : highscoreList) {


            String log = "Id: " + cn2.getID() + " ,Name: " + cn2.getName() + " ,Highscore: " +
                    cn2.getHighscore();

            items.add(log);
            //if(cn2.getHighscore() >= )


            Log.i("Name: ", log);
        }
        topFiveFilter();
    }
    public void topFiveFilter()
    {
        DatabaseHandler db = new DatabaseHandler(this);
        List<HighscoreClass> highscoreList = db.top5Highscore();

        Log.i("Name: ", "list successful");
        for (HighscoreClass cn2 : highscoreList) {


            String log = "Id: " + cn2.getID() + "  Name: " + cn2.getName() + "  Highscore: " +
                    cn2.getHighscore();

            Log.i("Name: ", log);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(adapter);
        //currently not working but displaying in log

//        HighScoreArrayAdapter adapter = new HighScoreArrayAdapter(this, highscoreList);
//        ArrayAdapter<HighscoreClass> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highscoreList);
//
//        ListView listviewScores = findViewById(R.id.listViewScores);
//        listviewScores.setAdapter(adapter);
    }

}