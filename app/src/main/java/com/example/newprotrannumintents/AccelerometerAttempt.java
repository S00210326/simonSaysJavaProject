package com.example.newprotrannumintents;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccelerometerAttempt extends AppCompatActivity implements SensorEventListener {
    List<Integer> numSeq2 = new ArrayList<>();
    List<Integer> numSeq = new ArrayList<>();
    Integer score;
    int IntCheckNum;
    int intCheckNumUP2;

    TextView tvx, tvy, tvz, tvDirection;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    Button btnUp, btnDown, btnLeft, btnRight;

    Boolean posDown,posUp,posLeft,posRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_attempt);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        tvDirection = findViewById(R.id.tvDirection);
        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        btnRight = findViewById(R.id.btnRight);
        btnLeft = findViewById(R.id.btnLeft);



        posLeft = false;
        posDown = false;
        posUp = false;
        posRight = false;
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Intent i2 = getIntent();
        score = i2.getIntExtra("MY_SCORE",5);


        IntCheckNum = i2.getIntExtra("ARRAY_CHECK",5);

        Log.d("Nope", "CheckNum from first page : " + String.valueOf(IntCheckNum));

        numSeq = i2.getIntegerArrayListExtra("myList");

        System.out.println(numSeq);

        intCheckNumUP2 = numSeq.size();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numSeq);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void onSensorChanged(SensorEvent event) {

        float x, y, z;
        x = Math.abs(event.values[0]);
        y = event.values[1];
        z = Math.abs(event.values[2]);

        if (y < -2 ) {
            if (!posLeft){
                btnLeft.performClick();
                btnLeft.setPressed(true);
                btnLeft.invalidate();
                btnLeft.setPressed(false);
                btnLeft.invalidate();
                tvDirection.setText("Left");
                checkAgainstList1();

            }
            posLeft = true;

        } else if (y > 2 ) {
            if (!posRight){
                btnRight.performClick();
                btnRight.setPressed(true);
                btnRight.invalidate();
                btnRight.setPressed(false);
                btnRight.invalidate();
                tvDirection.setText("Right");
                checkAgainstList2();
            }

            posRight = true;

        } else if (x > 9 ) {

            if (!posDown){
                tvDirection.setText("Bottom");
                btnDown.performClick();
                btnDown.setPressed(true);
                btnDown.invalidate();
                btnDown.setPressed(false);
                btnDown.invalidate();

                checkAgainstList3();
            }

            posDown = true;


        } else if (x < 3 ) {
            if (!posUp){
                tvDirection.setText("Top");
                btnUp.performClick();
                btnUp.setPressed(true);
                btnUp.invalidate();
                btnUp.setPressed(false);
                btnUp.invalidate();
                checkAgainstList4();
            }

            posUp = true;




        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not using
    }
    public void checkAgainstList1() {
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
        Log.d("Nope", "Attempt Sequence ==  " + numSeq2);



    }
    public void checkAgainstList2() {
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
    public void checkAgainstList3() {
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
    public void checkAgainstList4() {
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







