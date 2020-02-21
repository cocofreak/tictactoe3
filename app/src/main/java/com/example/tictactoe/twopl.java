package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class twopl extends AppCompatActivity {
    Button b;
    TextView champ;
    TextView turn;

    private Button[][] buttons = new Button[3][3];
    private boolean player1_turn = true;
    private int round_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onepla);

        b = findViewById(R.id.b);
        champ = findViewById(R.id.champ);
        turn = findViewById(R.id.turn);

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(twopl.this, MainActivity.class);
                startActivity(intent);
            }
        });



        turn.setText("Your turn");

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String buttonID = "button_"+i+j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!((Button) v).getText().toString().equals("")){
                            return;
                        }

                        if(player1_turn){
                            ((Button) v).setText("X");
                            turn.setText("Their turn");
                        }
                        else{
                            ((Button) v).setText("O");
                            turn.setText("Your turn");
                        }

                        round_count++;

                        if(checkWin()){
                            if(player1_turn){
                                champ.setText("You won!");
                                resetBoard();
                            }
                            else{
                                champ.setText("They won!");
                                resetBoard();
                            }
                        }

                        else if(round_count == 9){
                            champ.setText("It's a tie!");
                            resetBoard();
                        }

                        else{
                            player1_turn = !player1_turn;
                        }

                    }
                });
            }
        }


    }

    private boolean checkWin(){
        String[][] field = new String[3][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        //Horizontal check win
        for(int i = 0; i < 3; i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }

        //Vertical check win
        for(int i = 0; i < 3; i++){
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        //Diagonal check win at 0,0
        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }

        //Diagonal check win 0,2
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }

        return false;
    }

    private void resetBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }
        round_count = 0;
        player1_turn = true;
        turn.setText("Your turn");
    }
}