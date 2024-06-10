package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_juegoMichi extends AppCompatActivity {
    public TextView tv1;
    boolean jugador1= true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_juego_michi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv1 = findViewById(R.id.txtRecepcion);
        String R_Usuario = getIntent().getStringExtra("usuario");
        String R_Contrasenha = getIntent().getStringExtra("contrasenha");
        tv1.setText("hola: "+ R_Usuario + " sea bienvenido al juego de michi");
    }
    public void regresar(View view){
        Intent i = new Intent(this, MainActivityLoguin.class);

        startActivity(i);
        finish();
    }

    public void clickBoton(View view) {
        Button button = (Button) view;
        String buttonID = getResources().getResourceEntryName(button.getId());

//        if (!button.getText().toString().trim().equals("")) {
//            return; // Si el botón ya tiene texto, no hacer nada
//        }

        if(jugador1){
            button.setText( "X" );//: "X"
        }else{
            button.setText( "0" );//: "X"
        }
        jugador1 = !jugador1;
        button.setEnabled(false);
    }

    public void refrescar(View view){
        Intent i = new Intent(this, Activity_juegoMichi.class);

        startActivity(i);
        finish();
    }

}