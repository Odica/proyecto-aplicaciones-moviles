package com.example.tiendavirtual;

import android.content.Intent;
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

public class Register extends AppCompatActivity {
    private EditText username_register, email_register, password_register, password_confirm_register;
    private Button registro_button;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuarioDao = new UsuarioDao(this); // Inicializa el DAO

        username_register = findViewById(R.id.username_register);
        email_register = findViewById(R.id.email_register);
        password_register = findViewById(R.id.password_register);
        password_confirm_register = findViewById(R.id.password_confirm_register);
        registro_button = findViewById(R.id.registro_button);

        registro_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = username_register.getText().toString().trim();
        String email = email_register.getText().toString().trim();
        String password = password_register.getText().toString().trim();
        String confirmPassword = password_confirm_register.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario newUsuario = new Usuario(name, email, password);
        UsuarioDao usuarioDAO = new UsuarioDao(this); // Inicializa el DAO

        if (usuarioDAO.registrarUsuario(newUsuario)) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Error al registrar (¿email ya existe?)", Toast.LENGTH_SHORT).show();
        }
    }


}