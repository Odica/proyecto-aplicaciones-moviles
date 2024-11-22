package com.example.tiendavirtual;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button saveButton, deleteButton;
    private Usuario currentUser;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usuarioDao = new UsuarioDao(this);

        // Recupera el usuario desde la base de datos
        currentUser = obtenerUsuarioActual();

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        saveButton = findViewById(R.id.save_button);
        deleteButton = findViewById(R.id.delete_button);

        if (currentUser != null) {
            editTextName.setText(currentUser.getName());
            editTextEmail.setText(currentUser.getEmail());
            editTextPassword.setText(currentUser.getPassword());
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteAccount();
            }
        });
    }

    private Usuario obtenerUsuarioActual() {
        return usuarioDao.obtenerPrimerUsuario();
    }

    private void saveChanges() {
        if (currentUser != null) {
            currentUser.setName(editTextName.getText().toString());
            currentUser.setEmail(editTextEmail.getText().toString());
            currentUser.setPassword(editTextPassword.getText().toString());

            boolean actualizado = usuarioDao.actualizarUsuario(currentUser);
            if (actualizado) {
                Toast.makeText(this, "Perfil actualizado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al actualizar el perfil", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void confirmDeleteAccount() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar cuenta")
                .setMessage("¿Estás seguro de que quieres eliminar tu cuenta?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAccount();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteAccount() {
        if (currentUser != null) {
            boolean eliminado = usuarioDao.eliminarUsuario(currentUser.getEmail());
            if (eliminado) {
                Toast.makeText(this, "Cuenta eliminada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
