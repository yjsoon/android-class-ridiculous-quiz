package com.tinkercademy.yjsoon.ridiculousquiznewer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Toast mToast;

    private TextView mQuestionText;
    private int mCurrentQuestion = 0;
    private Question[] mQuestions = new Question[] {
        new Question(R.string.question1, false),
        new Question(R.string.question2, true),
        new Question(R.string.question3, false),
        new Question(R.string.question4, true),
        new Question(R.string.question5, false),
        new Question(R.string.question6, false)
    };

    private Button mNextButton;
    private Button mShowScoreButton;

    private static final String KEY_CURRENT_QUESTION = "current question";
    private static final String KEY_SCORE = "score";

    private int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserAnswer(false);
            }
        });

        // restore data before showing current question
        if (savedInstanceState != null) {
            mCurrentQuestion = savedInstanceState.getInt(KEY_CURRENT_QUESTION);
            mScore = savedInstanceState.getInt(KEY_SCORE);
        }
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
                }

                // however, we also want to disable the next button the moment we reach
                // the last question. hence this isn't an "else if".
                if (mCurrentQuestion == mQuestions.length - 1) {
                    mNextButton.setText(getString(R.string.no_more_questions));
                    mNextButton.setTextColor(Color.GRAY);
                    mNextButton.setClickable(false);
                    // show the "show score" button
                    mShowScoreButton.setVisibility(View.VISIBLE);
                }
                showCurrentQuestion();
            }
        });

        mShowScoreButton = (Button) findViewById(R.id.show_score_button);
        mShowScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
                // calculate our percentage score
                int percentageScore = Math.round((float) mScore / mQuestions.length * 100); // caaasting
                intent.putExtra(ScoreActivity.SCORE_EXTRA_INT, percentageScore);
                startActivity(intent);
            }
        });

        getResources().getString(R.string.abc_action_bar_home_description);

    }

    // Save before we get killed off
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_QUESTION, mCurrentQuestion);
        outState.putInt(KEY_SCORE, mScore);
    }


    // This is a private function, accessible only to our class
    private void showToast(String textToShow) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(QuizActivity.this, textToShow, Toast.LENGTH_SHORT);
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

    // This checks what the user entered against the truthiness of the current question,
    // and displays an appropriate Toast for it
    private void checkUserAnswer(boolean userSelectedTrue) {
        Question currentQuestion = mQuestions[mCurrentQuestion];
        if (userSelectedTrue == currentQuestion.isStatementTruth()) {
            showToast(getString(R.string.correct));
            mScore++;
        } else {
            showToast(getString(R.string.wrong));
        }
    }

}
