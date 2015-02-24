package com.tinkercademy.yjsoon.ridiculousquiznewer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ScoreActivity extends ActionBarActivity {

    public static final String SCORE_EXTRA_INT = "com.tinkercademy.yjsoon.RidiculousQuiz.scoreExtraInt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Finish this code
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        int score = intent.getIntExtra(SCORE_EXTRA_INT, 0);
        TextView scoreView = (TextView) findViewById(R.id.score_display);
        scoreView.setText(score + "%"); // add an int into a String to make it a String
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
