package com.example.tiendavirtual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDao {

    private DatabaseHelper dbHelper;

    public UsuarioDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Registrar usuario
    public boolean registrarUsuario(Usuario usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", usuario.getName());
        values.put("email", usuario.getEmail());
        values.put("password", usuario.getPassword());

        long resultado = db.insert("usuarios", null, values);
        db.close();
        return resultado != -1;
    }

    // Validar inicio de sesión
    public boolean iniciarSesion(String name, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM usuarios WHERE name = ? AND password = ?",
                new String[]{name, password}
        );

        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return existe;
    }
}
