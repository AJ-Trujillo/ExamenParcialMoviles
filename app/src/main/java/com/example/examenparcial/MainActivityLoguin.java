package com.example.examenparcial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", null);
        String savedPassword = sharedPreferences.getString("password", null);

        if (inputUsername.equals(savedUsername) && inputPassword.equals(savedPassword)) {
            Intent intent = new Intent(MainActivityLoguin.this, MainActivity_michi.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
