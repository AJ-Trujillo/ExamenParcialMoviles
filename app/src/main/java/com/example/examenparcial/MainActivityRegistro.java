package com.example.examenparcial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityRegistro extends AppCompatActivity {

    private EditText txtUsuario, txtPassword;
    private RadioGroup genderRadioGroup;
    private Spinner countrySpinner;
    private CheckBox checkBoxDNI, checkBoxPasaporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro);

        // Ocultar los botones de navegación
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_main_registro);

        // Initialize views
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        countrySpinner = findViewById(R.id.countrySpinner);
        checkBoxDNI = findViewById(R.id.checkBox);
        checkBoxPasaporte = findViewById(R.id.checkBox2);
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
        boolean isDNISelected = checkBoxDNI.isChecked();
        boolean isPasaporteSelected = checkBoxPasaporte.isChecked();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty() || selectedGenderId == -1 || selectedCountry.isEmpty() ||
                (!isDNISelected && !isPasaporteSelected)) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save user data to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();

        // Show success message
        Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

        // Redirect to login activity
        Intent intent = new Intent(MainActivityRegistro.this, MainActivityLoguin.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent back navigation to it
    }
}
