package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button[][] buttons = new Button[3][3];
    private Boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView txtplayer1;
    private TextView txtplayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtplayer1 = findViewById(R.id.txtplayer1);
        txtplayer2 = findViewById(R.id.txtplayer2);
        for(int i=0 ; i<3 ; i++)
        {
            for(int j=0 ; j<3 ; j++)
            {
                String btnID = "btn"+ i + j;
                int resID = getResources().getIdentifier(btnID,"id",getPackageName());
                buttons[i][j]= findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button btnReset = findViewById(R.id.btnreset);
        btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

    }

    @Override
    public void onClick(View view)
    {
        if(!((Button) view).getText().toString().equals(""))
        {
            return;
        }
        if(player1Turn)
        {
            ((Button) view).setText("X");
           // txtplayer1.setTextColor(Color.parseColor("##D80000"));

           // ((Button) view).setTextColor(23445);
        }else
            {
                ((Button) view).setText("O");
               // txtplayer2.setTextColor(Color.parseColor("##0420BC"));

                //  ((Button) view).setTextColor(23985);

            }
        roundCount++;

        if(checkForWin())
        {
            if(player1Turn)
            {
                player1Wins();
            }else
            {
                player2Wins();
            }
        }else if(roundCount==9)
        {
            Draw();
        }
        else
        {
            player1Turn = !player1Turn;
        }

    }
    private boolean checkForWin()
    {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++)
        {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals(""))
            {
                return true;
            }
        }
        for (int i = 0; i < 3; i++)
        {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals(""))
            {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals(""))
        {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals(""))
        {
            return true;
        }

        return  false;
    }
    private void player1Wins()
    {
        player1Points++;
        Toast.makeText(this,"player 1 wins!!", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void resetBoard()
    {
        for(int i = 0; i <3;i++)
        {
            for(int j=0 ; j<3; j++)
            {
                buttons[i][j].setText("");
            }

        }
        roundCount = 0;
        player1Turn = true;
    }

    private void player2Wins()
    {
        player2Points++;
        Toast.makeText(this,"player 2 wins!!", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();

    }

    private void updatePointsText()
    {
        txtplayer1.setText("player1 : " +player1Points);
        txtplayer2.setText("player2 : " +player2Points);

    }

    private void Draw()
    {
        Toast.makeText(this,"Draw!!", Toast.LENGTH_LONG).show();
        resetBoard();
    }
}