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

    // Obtener el primer usuario de la tabla
    public Usuario obtenerPrimerUsuario() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios LIMIT 1", null);

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            cursor.close();
            db.close();
            return new Usuario(name, email, password);
        } else {
            cursor.close();
            db.close();
            return null; // No hay usuarios
        }
    }

    // Actualizar un usuario
    public boolean actualizarUsuario(Usuario usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", usuario.getName());
        values.put("email", usuario.getEmail());
        values.put("password", usuario.getPassword());

        int rows = db.update("usuarios", values, "email = ?", new String[]{usuario.getEmail()});
        db.close();
        return rows > 0;
    }

    // Eliminar un usuario
    public boolean eliminarUsuario(String email) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("usuarios", "email = ?", new String[]{email});
        db.close();
        return rows > 0;
    }
}
