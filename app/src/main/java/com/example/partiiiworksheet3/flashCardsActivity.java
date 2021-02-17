package com.example.partiiiworksheet3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class flashCardsActivity extends AppCompatActivity{
    private Button btnStart;
    private TextView txtDividend;
    private TextView txtDivisor;
    private EditText txtAnswer;
    private Button btnSubmit;
    private int DivranNum;
    private int Divanswer;
    private int Divdivisor;
    private int Divdividend;

    private static final String TAG = "Test";
    private int score = 0;
    private int counter = 0;
    private int[][] problems = new int[10][3];
    private int[] currentProb = new int[3];
    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        btnStart = (Button)findViewById(R.id.btnStart);
        txtAnswer = (EditText)findViewById(R.id.txtAnswer);
        txtDividend = (TextView)findViewById(R.id.txtDividend);
        txtDivisor = (TextView)findViewById(R.id.txtDivisor);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter == 10){
                    counter = 0;
                    score = 0;
                    txtAnswer.setText("");
                }
                if (counter == 0) {
                    createProblems();
                    started = true;

                    int dividend = problems[0][0];
                    int divisor = problems[0][1];
                    txtDividend.setText(Integer.toString(dividend));
                    txtDivisor.setText(Integer.toString(divisor));
                    setCurrentProb(0);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(started) {
                    //cover case where user doesn't enter an answer
                    if (counter < 9) {
                        Log.i(TAG, "Counter: " + counter);
                        String input = txtAnswer.getText().toString();
                        if (input.equals(Integer.toString(problems[counter][2]))) {
                            score++;
                        }
                        txtAnswer.setText("");

                        int dividend = problems[counter + 1][0];
                        int divisor = problems[counter + 1][1];
                        Log.i(TAG, "Dividend @" + (counter + 1) + ": " + dividend);
                        Log.i(TAG, "Dividend @" + (counter + 1) + ": " + divisor);
                        txtDividend.setText(Integer.toString(dividend));
                        txtDivisor.setText(Integer.toString(divisor));
                        setCurrentProb(counter+1);
                        counter++;
                    } else {
                        if (counter < 10) {
                            //calculates the 10th score
                            String input = txtAnswer.getText().toString();
                            if (input.equals(Integer.toString(problems[counter][2]))) {
                                score++;
                            }
                            Log.i(TAG, "Score @" + counter + ": " + score);
                            counter++;
                        }
                    }
                    //Toast display of score, counter==9 means problem 10 is reached
                    if (counter == 10) {
                        txtAnswer.setText("Done");
                        String userScoreStr = "You got "+ Integer.toString(score) + " out of 10 Correct";
                        Toast.makeText(getApplicationContext(), userScoreStr,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("counter", counter);
        outState.putInt("score", score);
        outState.putBoolean("started", started);
        outState.putIntArray("currentProb", currentProb);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        counter = savedInstanceState.getInt("counter");
        score = savedInstanceState.getInt("score");
        started = savedInstanceState.getBoolean("started");
        currentProb = savedInstanceState.getIntArray("currentProb");

        txtDividend.setText(Integer.toString(currentProb[0]));
        txtDivisor.setText(Integer.toString(currentProb[1]));
        createProblems();
        if(counter==10){

        }else {
            for (int i = 0; i < 3; i++) {
                problems[counter][i] = currentProb[i];
            }
        }
    }

    private void createProblems(){
        for (int i = 0; i < 10; i++) {
            int[] problem = division();
            for(int j = 0; j<3; j++) {
                problems[i][j] = problem[j];
            }
        }
    }

    private void setCurrentProb(int counter){
        for(int i = 0; i<3; i++){
            currentProb[i] = problems[counter][i];
        }
    }
    private int[] division(){
        DivranNum = ThreadLocalRandom.current().nextInt(2, 100);
        Divanswer = ThreadLocalRandom.current().nextInt(1, DivranNum/2+1);
        Divdivisor = DivranNum/Divanswer;
        Divdividend = Divdivisor*Divanswer;
        return new int[] {Divdividend, Divdivisor, Divanswer};
    }
}
