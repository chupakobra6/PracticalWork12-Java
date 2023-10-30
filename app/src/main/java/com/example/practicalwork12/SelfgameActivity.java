package com.example.practicalwork12;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelfgameActivity extends AppCompatActivity {

    boolean cross = true;
    WinLogic winLogic;
    GameStatsManager gameStatsManager;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfgame);

        winLogic = new WinLogic();
        gameStatsManager = new GameStatsManager(getApplicationContext());

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
    }

    @SuppressLint("NonConstantResourceId")
    public void onCellClicked(View view) {
        Button button = (Button) view;
        String currentText = button.getText().toString();

        if (TextUtils.isEmpty(currentText)) {
            if (cross) {
                button.setText("X");
            } else {
                button.setText("O");
            }

            int row = -1;
            int col = -1;
            int viewId = view.getId();
            switch (viewId) {
                case R.id.button1:
                    row = 0;
                    col = 0;
                    break;
                case R.id.button2:
                    row = 0;
                    col = 1;
                    break;
                case R.id.button3:
                    row = 0;
                    col = 2;
                    break;
                case R.id.button4:
                    row = 1;
                    col = 0;
                    break;
                case R.id.button5:
                    row = 1;
                    col = 1;
                    break;
                case R.id.button6:
                    row = 1;
                    col = 2;
                    break;
                case R.id.button7:
                    row = 2;
                    col = 0;
                    break;
                case R.id.button8:
                    row = 2;
                    col = 1;
                    break;
                case R.id.button9:
                    row = 2;
                    col = 2;
                    break;
            }

            boolean moveSuccessful = winLogic.makeMove(row, col);
            if (moveSuccessful) {
                if (winLogic.checkWin()) {
                    if (cross) {
                        gameStatsManager.incrementWins();
                        showToast("Победа крестоносцев!");
                    } else {
                        gameStatsManager.incrementLosses();
                        showToast("Победа дырявых!");
                    }

                    newGame();
                } else if (winLogic.checkDraw()) {
                    gameStatsManager.incrementDraws();
                    showToast("Умерли все!");
                    newGame();
                }
                cross = !cross;
            }

        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void newGame() {
        winLogic = new WinLogic();
        cross = !cross;

        disableButtons();
        new Handler().postDelayed(this::resetGame, 4000);

        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText(gameStatsManager.getStats());
    }

    private void disableButtons() {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
    }

    private void resetGame() {
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");

        enableButtons();
    }

    private void enableButtons() {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
    }
}