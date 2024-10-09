package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirmacion extends AppCompatActivity {

    TextView tvNombre, tvFecha, tvTelefono, tvEmail, tvDescripcion;
    Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        tvNombre = findViewById(R.id.tvNombre);
        tvFecha = findViewById(R.id.tvFecha);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        btnEditar = findViewById(R.id.btnEditar);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String fecha = intent.getStringExtra("fecha");
        String telefono = intent.getStringExtra("telefono");
        String email = intent.getStringExtra("email");
        String descripcion = intent.getStringExtra("descripcion");

        tvNombre.setText(nombre);
        tvFecha.setText(fecha);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);


        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editar = new Intent(Confirmacion.this, MainActivity.class);
                editar.putExtra("nombre", nombre);
                editar.putExtra("fecha", fecha);
                editar.putExtra("telefono", telefono);
                editar.putExtra("email", email);
                editar.putExtra("descripcion", descripcion);
                startActivity(editar);
                finish();
            }
        });
    }
}