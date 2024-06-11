package com.example.examenparcial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityLoguin extends AppCompatActivity {

    private EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loguin);

        // Ocultar los botones de navegación
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_loguin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });



        userName = findViewById(R.id.txtusername);
        password = findViewById(R.id.txtpassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        btnLogin.setOnClickListener(view -> loginUser());
        btnRegistrar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivityLoguin.this, MainActivityRegistro.class);
            startActivity(intent);
            finish();


        });
    }

    private void loginUser() {
        String inputUsername = userName.getText().toString().trim();
        String inputPassword = password.getText().toString().trim();

        if (inputUsername.length() > 10) {
            Toast.makeText(this, "El nombre de usuario no debe exceder los 10 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", null);
        String savedPassword = sharedPreferences.getString("password", null);

        if (inputUsername.equals(savedUsername) && inputPassword.equals(savedPassword)) {
            Intent intent = new Intent(MainActivityLoguin.this, Activity_juegoMichi.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
