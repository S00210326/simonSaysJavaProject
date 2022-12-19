package com.example.newprotrannumintents;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ButtonAttempt extends AppCompatActivity {
    List<Integer> numSeq2= new ArrayList<>();
    Button btnUp,btnRight,btnDown,btnLeft;
    List<Integer> numSeq= new ArrayList<>();
    Integer score;
    int IntCheckNum;
    int intCheckNumUP2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //adding handler
        DatabaseHandler db = new DatabaseHandler(this);
        List<HighscoreClass> highscore = db.getAllHighscore();
//        db.emptyHighscore();
        Intent i2 = getIntent();
        score = i2.getIntExtra("MY_SCORE",5);


        IntCheckNum = i2.getIntExtra("ARRAY_CHECK",5);


        Log.d("Nope", "CheckNum from first page : " + String.valueOf(IntCheckNum));

        numSeq = i2.getIntegerArrayListExtra("myList");

        System.out.println(numSeq);

        intCheckNumUP2 = numSeq.size();

        btnUp = findViewById(R.id.buttonUp);
        btnRight = findViewById(R.id.buttonRight);
        btnDown = findViewById(R.id.buttonDown);
        btnLeft = findViewById(R.id.buttonLeft);

        //this will always be the nums in the array + 2 , to be used against other intCheckUp

        Log.d("Nope", "Int Check current + 2 now equals " + intCheckNumUP2);


        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numSeq);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }


    public void checkAgainstList1(View view) {
        Intent i = new Intent(this, DisplaySequence.class);
        Intent i3 = new Intent(this, StatsPage.class);
        //numSeq2 is the players guess . Will be reset after every intent
        numSeq2.add(1);
        IntCheckNum+=1;
        Log.d("Nope", "Int Check current now equals " + IntCheckNum);

        if (IntCheckNum == intCheckNumUP2){
            Log.d("Nope", "Has Entered the IF CHECK");
            if ( numSeq2.containsAll(numSeq)  ) {
//
                Log.i("Fact", "True");
                Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));
                // Create an instance of AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

// Set the title and message for the alert dialog
                builder.setTitle("You Passed the Level");
                builder.setMessage("Click CONTINUE to proceed");


// Set the positive and negative buttons for the alert dialog
                builder.setPositiveButton("CONTINUE NEXT LEVEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when the user clicks the OK button
                        score += 1;
                        Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));
                        i.putExtra("MY_SCORE", score);
                        i.putIntegerArrayListExtra("numSeq",(ArrayList<Integer>) numSeq);
                        startActivity(i);
                    }
                });
//            builder.setNegativeButton("ADD SCORE TO DATABASE AND CHECK SCORE", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Do something when the user clicks the Cancel button
//
//                }
//            });

// Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }else {//THIS DIALOG BOX WILL OPEN IF ALL FAILS

                Log.i("Nope", "False");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

// Set the title and message for the alert dialog
                builder.setTitle("You FAILED the LEVEL ");
                builder.setMessage("Score =  " +  score );
//                builder.setMessage("Click CONTINUE to proceed" );

// Set the positive and negative buttons for the alert dialog
                builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when the user clicks the OK button

                        Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));

                        i3.putExtra("MY_SCORE", score);
                        startActivity(i3);
                    }
                });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Do something when the user clicks the Cancel button
//                }
//            });

// Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }



    }
    public void checkAgainstList2(View view) {
        Intent i = new Intent(this, DisplaySequence.class);
        Intent i3 = new Intent(this, StatsPage.class);
        numSeq2.add(2);
        IntCheckNum+=1;
        Log.d("Nope", "Int Check 1 now equals " + IntCheckNum);
        if (IntCheckNum == intCheckNumUP2){
            Log.d("Nope", "Has Entered the IF CHECK");
            if (numSeq2.containsAll(numSeq)) {

                Log.i("Fact", "True");

                Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));
                // Create an instance of AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

// Set the title and message for the alert dialog
                builder.setTitle("You Passed the Level");
                builder.setMessage("Click CONTINUE to proceed");

// Set the positive and negative buttons for the alert dialog
                builder.setPositiveButton("CONTINUE NEXT LEVEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        score += 1;
                        Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));

                        i.putExtra("MY_SCORE", score);
                        i.putIntegerArrayListExtra("numSeq",(ArrayList<Integer>) numSeq);
                        startActivity(i);
                    }
                });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Do something when the user clicks the Cancel button
//                }
//            });

// Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {//THIS DIALOG BOX WILL OPEN IF ALL FAILS

                Log.i("Nope", "False");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

// Set the title and message for the alert dialog
                builder.setTitle("You FAILED the LEVEL ");
                builder.setMessage("Score =  " +  score );


// Set the positive and negative buttons for the alert dialog
                builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when the user clicks the OK button

                        Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));
                        i3.putExtra("MY_SCORE", score);

                        startActivity(i3);
                    }
                });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Do something when the user clicks the Cancel button
//                }
//            });

// Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }


    }
    public void checkAgainstList3(View view) {
        Intent i = new Intent(this, DisplaySequence.class);
        Intent i3 = new Intent(this, StatsPage.class);
        numSeq2.add(3);
        IntCheckNum+=1;
        Log.d("Nope", "Int Check 1 now equals " + IntCheckNum);
        if (IntCheckNum == intCheckNumUP2){
            Log.d("Nope", "Has Entered the IF CHECK");
            if (numSeq2.containsAll(numSeq)) {
//
                Log.i("Fact", "True");

                Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));

                // Create an instance of AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

// Set the title and message for the alert dialog
                builder.setTitle("You Passed the Level");
                builder.setMessage("Click CONTINUE to proceed");

// Set the positive and negative buttons for the alert dialog
                builder.setPositiveButton("CONTINUE NEXT LEVEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when the user clicks the OK button
                        score += 1;
                        Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));
                        i.putExtra("MY_SCORE", score);
                        i.putIntegerArrayListExtra("numSeq",(ArrayList<Integer>) numSeq);
                        startActivity(i);
                    }
                });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Do something when the user clicks the Cancel button
//                }
//            });

// Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {//THIS DIALOG BOX WILL OPEN IF ALL FAILS

                Log.i("Nope", "False");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

// Set the title and message for the alert dialog
                builder.setTitle("You FAILED the LEVEL ");
                builder.setMessage("Score =  " +  score );
//                builder.setMessage("Click CONTINUE to proceed" );

// Set the positive and negative buttons for the alert dialog
                builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when the user clicks the OK button

                        Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));

                        i3.putExtra("MY_SCORE", score);
                        startActivity(i3);
                    }
                });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Do something when the user clicks the Cancel button
//                }
//            });

// Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }



    }
    public void checkAgainstList4(View view) {
        Intent i = new Intent(this, DisplaySequence.class);
        Intent i3 = new Intent(this, StatsPage.class);
        numSeq2.add(4);
        IntCheckNum+=1;
        Log.d("Nope", "Int Check 1 now equals " + IntCheckNum);
        if (IntCheckNum == intCheckNumUP2){
            Log.d("Nope", "Has Entered the IF CHECK");
            if (numSeq2.containsAll(numSeq) ) {

                Log.i("Fact", "True");

                Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));
                // Create an instance of AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

// Set the title and message for the alert dialog
                builder.setTitle("You Passed the Level");
                builder.setMessage("Click CONTINUE to proceed");

// Set the positive and negative buttons for the alert dialog
                builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when the user clicks the OK button
                        score += 1;
                        Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));
                        i.putExtra("MY_SCORE", score);
                        i.putIntegerArrayListExtra("numSeq",(ArrayList<Integer>) numSeq);
                        startActivity(i);
                    }
                });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Do something when the user clicks the Cancel button
//                }
//            });

// Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
            else {//THIS DIALOG BOX WILL OPEN IF ALL FAILS

                Log.i("Nope", "False");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

// Set the title and message for the alert dialog
                builder.setTitle("You FAILED the LEVEL ");
                builder.setMessage("Score =  " +  score );
//                builder.setMessage("Click CONTINUE to proceed" );

// Set the positive and negative buttons for the alert dialog
                builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when the user clicks the OK button

                        Log.d("Tag", "YOUR SCORE IS " + String.valueOf(score));

                        i3.putExtra("MY_SCORE", score);
                        startActivity(i3);
                    }
                });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Do something when the user clicks the Cancel button
//                }
//            });

// Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }


    }

}