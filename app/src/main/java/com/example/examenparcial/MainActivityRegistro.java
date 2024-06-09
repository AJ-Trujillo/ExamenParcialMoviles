package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityRegistro extends AppCompatActivity {

    private EditText txtUsuario, txtPassword;
    private RadioGroup genderRadioGroup;
    private Spinner countrySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        countrySpinner = findViewById(R.id.countrySpinner);
        Button btnRegistro = findViewById(R.id.btnRegistro);
        Button btnVolver = findViewById(R.id.btnVolver);

        // Set up the spinner with country names
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);

        // Set up button listeners
        btnRegistro.setOnClickListener(view -> registerUser());
        btnVolver.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivityRegistro.this, MainActivityLoguin.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent back navigation to it
        });
    }

    private void registerUser() {
        // Retrieve input values
        String username = txtUsuario.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        String selectedCountry = countrySpinner.getSelectedItem().toString();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty() || selectedGenderId == -1 || selectedCountry.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Perform registration logic (e.g., save user data to database or shared preferences)
        // For simplicity, we'll just show a success message
        Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

        // Redirect to login activity
        Intent intent = new Intent(MainActivityRegistro.this, MainActivityLoguin.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent back navigation to it
    }
}
