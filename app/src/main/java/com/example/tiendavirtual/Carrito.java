package com.example.tiendavirtual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Carrito extends AppCompatActivity {
    private Button ListadoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        ListadoButton = findViewById(R.id.btnContinueShopping);

        ListadoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrito.this, ListadoProductosActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout productosLayout = findViewById(R.id.productosLayout);


        String[] nombresProductos = {"IPhone 15", "Asus Vivobook", "Monitor Samsung", "Audífonos gamer"};
        double[] preciosProductos = {1800000, 1500000, 1200000, 350000};

        for (int i = 0; i < nombresProductos.length; i++) {
            int precioEntero = (int) preciosProductos[i];

            TextView textViewProducto = new TextView(this);
            textViewProducto.setText(nombresProductos[i] + " - $" + precioEntero);
            textViewProducto.setTextSize(18);
            textViewProducto.setPadding(8, 16, 8, 16);

            productosLayout.addView(textViewProducto);
        }
    }
}
