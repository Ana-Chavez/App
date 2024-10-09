package com.example.app;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etNombre, etTelefono, etEmail, etDescripcion;
    Button btnSiguiente;
    Calendar calendario;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        mDisplayDate = findViewById(R.id.tv_fecha);//

        //////
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendario = Calendar.getInstance();
                int dia = calendario.get(Calendar.DAY_OF_MONTH);
                int mes = calendario.get(Calendar.MONTH);
                int anio = calendario.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        anio, mes, dia);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this, android.R.color.transparent)));
                datePickerDialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = String.format("%02d/%02d/%04d", dayOfMonth, month, year); // Formato: dd/MM/yyyy
                mDisplayDate.setText(date);
            }
        };

        Intent intent = getIntent();
        if (intent.hasExtra("nombre")){
            etNombre.setText(intent.getStringExtra("nombre"));
            mDisplayDate.setText(intent.getStringExtra("fecha"));
            etTelefono.setText(intent.getStringExtra("telefono"));
            etEmail.setText(intent.getStringExtra("email"));
            etDescripcion.setText(intent.getStringExtra("descripcion"));
        }

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String fecha = mDisplayDate.getText().toString();
                String telefono = etTelefono.getText().toString();
                String email = etEmail.getText().toString();
                String descripcion = etDescripcion.getText().toString();

                if(nombre.isEmpty() || fecha.isEmpty() || telefono.isEmpty() || email.isEmpty() || descripcion.isEmpty()){
                    Toast.makeText(MainActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    Intent confirmar = new Intent(MainActivity.this, Confirmacion.class);
                    confirmar.putExtra("nombre", nombre);
                    confirmar.putExtra("fecha", fecha);
                    confirmar.putExtra("telefono", telefono);
                    confirmar.putExtra("email", email);
                    confirmar.putExtra("descripcion", descripcion);
                    startActivity(confirmar);

                }
            }
        });
    }
}