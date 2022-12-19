package com.example.newprotrannumintents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HighScoreArrayAdapter extends ArrayAdapter<HighscoreClass> {
    public HighScoreArrayAdapter(Context context, List<HighscoreClass> highScores) {
        super(context, 0, highScores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HighscoreClass highScore = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, parent, false);
        }

        // Lookup views within the inflated layout
        TextView nameTextView = convertView.findViewById(R.id.etName);
        TextView scoreTextView = convertView.findViewById(R.id.etScore);

        // Populate the data into the template view using the data object
        nameTextView.setText(highScore.getName());
        scoreTextView.setText(highScore.getHighscore());

        // Return the completed view to render on screen
        return convertView;
    }
}

