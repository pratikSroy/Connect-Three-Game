package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //yellow=0 red=1
    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int taggedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[taggedCounter] == 2 && gameActive)
        {
            gameState[taggedCounter]= activePlayer;
            counter.setTranslationY(-1500);

            if(activePlayer==0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }else{
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for(int[] winningPosition : winningPositions)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]] &&gameState[winningPosition[0]]!=2)
                {
                    gameActive = false;
                    String winner = "";
                    if(activePlayer == 0){
                        winner = "Red";
                    }else{
                        winner = "Yellow";
                    }

                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    TextView winnerTextview = findViewById(R.id.winnerTextView);

                    winnerTextview.setText(winner + " has won!");

                    winnerTextview.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void playAgain(View view) {
        Button playAgainButton = findViewById(R.id.playAgainButton);
        TextView winnerTextview = findViewById(R.id.winnerTextView);

        winnerTextview.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout myGridView = findViewById(R.id.gridLayout);


        for (int i = 0; i < myGridView.getChildCount(); i++) {
            ImageView counter = (ImageView) myGridView.getChildAt(i);

            counter.setImageDrawable(null);

        }

        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i]=2;
        }
        gameActive = true;

    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}