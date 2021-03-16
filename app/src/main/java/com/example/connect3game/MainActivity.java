package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0: yellow, 1:red 2: empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int activePlayer = 0;
    int[][] winningPositions= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean activeGame =true;
    public void DropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

       if(gameState[tappedCounter] == 2 && activeGame == true) {

           gameState[tappedCounter] = activePlayer;

           counter.setTranslationY(-1500);
           if (activePlayer == 0) {
               counter.setImageResource(R.drawable.yellow);
               activePlayer = 1;
           } else {
               counter.setImageResource(R.drawable.red);
               activePlayer = 0;
           }
           counter.animate().translationY(0).rotation(3600).setDuration(500);

           for (int[] winningPosition : winningPositions) {
               if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                   //someone has won
                   activeGame=false;
                   String winner = "";
                   if (activePlayer == 1) {
                       winner = "Yellow";

                   } else {
                       winner = "Red";

                   }
                   Button playAgainbutton = (Button) findViewById(R.id.PlayAgainbutton);
                   TextView winnerLabel = (TextView) findViewById(R.id.textView4);
                   winnerLabel.setText(winner +" has Won");
                   playAgainbutton.setVisibility(View.VISIBLE);
                   winnerLabel.setVisibility(View.VISIBLE);


               }

           }
       }

    }

    public void PlayAgain(View view){
        Button playAgainbutton = (Button) findViewById(R.id.PlayAgainbutton);
        TextView winnerLabel = (TextView) findViewById(R.id.textView4);
        playAgainbutton.setVisibility(View.INVISIBLE);
        winnerLabel.setVisibility(View.INVISIBLE);

        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i< gridlayout.getChildCount(); i++ ){

        ImageView counter = (ImageView) gridlayout.getChildAt(1);
        counter.setImageDrawable(null);

        }

        for(int i=0; i< gameState.length; i++) {
            gameState[i] = 2;
        }

         activePlayer = 0;
         activeGame =true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}