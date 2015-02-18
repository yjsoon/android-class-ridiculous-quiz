package com.tinkercademy.yjsoon.ridiculousquiznewer;

import android.graphics.Color;
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
                showToast("Correct");
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("WRONG");
            }
        });

        showCurrentQuestion();

        // Get the Button for next
        mNextButton = (Button) findViewById(R.id.next_button);
        // Set the appropriate listener
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check for 1 less than mQuestions.length, because otherwise we'll
                // increment it to length and try to access array[length] -- out of bounds
                if (mCurrentQuestion < mQuestions.length - 1) {
                    mCurrentQuestion++;
                } else {
                    mNextButton.setText(getString(R.string.no_more_questions));
                    mNextButton.setTextColor(Color.GRAY);
                    mNextButton.setClickable(false);
                }
                showCurrentQuestion();
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

    // To set the current question
    private void showCurrentQuestion() {
        // Get the TextView for the question
        mQuestionText = (TextView) findViewById(R.id.question_text);
        // Get the current question from the array
        Question currentQuestion = mQuestions[mCurrentQuestion];
        // Get the statement to be shown
        int questionReference = currentQuestion.getStatement();
        // Show it
        mQuestionText.setText(questionReference);
    }

}
