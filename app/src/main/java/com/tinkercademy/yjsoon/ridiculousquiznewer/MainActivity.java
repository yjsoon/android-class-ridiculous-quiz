package com.tinkercademy.yjsoon.ridiculousquiznewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Toast mToast;

    private TextView mQuestionText;
    private int mCurrentQuestion = 0;
    private Question[] mQuestions = new Question[] {
        new Question(R.string.question1, false),
        new Question(R.string.question2, false),
        new Question(R.string.question3, false),
        new Question(R.string.question4, false),
        new Question(R.string.question5, false),
        new Question(R.string.question6, false)
    };

    private Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                intent.putExtra(ScoreActivity.EXTRA_SCORE, "potatoes");
                startActivity(intent);

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("WRONG");
            }
        });

        // Get the TextView for the question
        mQuestionText = (TextView) findViewById(R.id.question_text);
        // Get the current question from the array
        Question currentQuestion = mQuestions[mCurrentQuestion];
        // Get the statement to be shown
        int questionReference = currentQuestion.getStatement();
        // Show it
        mQuestionText.setText(questionReference);

        // Get the Button for next
        mNextButton = (Button) findViewById(R.id.next_button);
        // Set the appropriate listener
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestion++; // Just increment lah! Don't care about checking!!
                Log.d("Potatoes", "My current question is " + mCurrentQuestion);
                Question currentQuestion = mQuestions[mCurrentQuestion]; // hey repeated code
                mQuestionText.setText(currentQuestion.getStatement()); // Set to new qn
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    // This is a private function, accessible only to our class
    private void showToast(String textToShow) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(MainActivity.this, textToShow, Toast.LENGTH_SHORT);
        mToast.show();
    }

}
