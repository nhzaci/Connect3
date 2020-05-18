package com.zack.connect3attempt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //red is 0, yellow is 1
    private int player = 0;
    private int[] gameState = {2,2,2,2,2,2,2,2,2};
    private int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}};
    private boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = player;
            if (player == 0) {
                counter.setImageResource(R.drawable.red);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                player = 0;
            }
            counter.setTranslationY(-1500);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
        }

        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2
                    && gameState[winningPosition[1]] != 2
                    && gameState[winningPosition[2]] != 2) {
                String winner = "";
                if (player == 0) {
                    winner = "Yellow";
                } else {
                    winner = "Red";
                }

                Button restartGame = findViewById(R.id.restartGame);
                TextView winnerText = findViewById(R.id.winnerText);

                winnerText.setText(String.format(Locale.ENGLISH, "Congratulations to %s, you won!", winner));
                winnerText.setAlpha(1.0f);
                restartGame.setAlpha(1.0f);
            }
        }
    }

    public void playAgain(View view) {
        Button restartGame = findViewById(R.id.restartGame);
        TextView winnerText = findViewById(R.id.winnerText);
        winnerText.setAlpha(0.0f);
        restartGame.setAlpha(0.0f);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageResource(android.R.color.transparent);
        }
        gameState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
