package com.example.examenparcial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.Queue;

public class Activity_juegoMichi extends AppCompatActivity {
    private TextView tv1;
    private boolean jugador1 = true;
    private String[][] board = new String[3][3];
    private Queue<int[]> moveQueueX = new LinkedList<>();
    private Queue<int[]> moveQueueO = new LinkedList<>();
    private final int MAX_MOVES = 3;

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_michi);

        tv1 = findViewById(R.id.txtRecepcion);
        String R_Usuario = getIntent().getStringExtra("usuario");
        tv1.setText(String.format(getString(R.string.bienvenido_mensaje), R_Usuario));
    }

    public void regresar(View view){
        Intent i = new Intent(this, MainActivityLoguin.class);
        startActivity(i);
        finish();
    }

    public void clickBoton(View view) {
        Button button = (Button) view;
        int row = Character.getNumericValue(button.getResources().getResourceEntryName(button.getId()).charAt(6));
        int col = Character.getNumericValue(button.getResources().getResourceEntryName(button.getId()).charAt(7));

        if (board[row][col] != null) {
            return;
        }

        if (jugador1) {
            button.setText("X");
            board[row][col] = "X";
            moveQueueX.add(new int[]{row, col});
            if (moveQueueX.size() > MAX_MOVES) {
                int[] oldMove = moveQueueX.poll();
                board[oldMove[0]][oldMove[1]] = null;
                updateButton(oldMove[0], oldMove[1]);
            }
        } else {
            button.setText("O");
            board[row][col] = "O";
            moveQueueO.add(new int[]{row, col});
            if (moveQueueO.size() > MAX_MOVES) {
                int[] oldMove = moveQueueO.poll();
                board[oldMove[0]][oldMove[1]] = null;
                updateButton(oldMove[0], oldMove[1]);
            }
        }

        jugador1 = !jugador1;
        button.setEnabled(false);

        String winner = checkWinner();
        if (winner != null) {
            String message = winner.equals("X") ? getString(R.string.ganaron_las_x) : getString(R.string.ganaron_las_o);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            resetGame();
        }
    }

    private void updateButton(int row, int col) {
        String buttonID = "button" + row + col;
        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
        Button button = findViewById(resID);
        button.setText("");
        button.setEnabled(true);
    }

    private String checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }

        return null;
    }

    private void resetGame() {
        board = new String[3][3];
        moveQueueX.clear();
        moveQueueO.clear();
        jugador1 = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                updateButton(i, j);
            }
        }
    }

    public void refrescar(View view) {
        resetGame();
    }
}
