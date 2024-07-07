package com.ryancodedev.aplicativodetransporte;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);

        //pasajero
        //guardar sesion
        // Verifica si la sesión está activa
        SharedPreferences sharedPreferences = getSharedPreferences("sesiones", MODE_PRIVATE);
        boolean sesion = sharedPreferences.getBoolean("sesion", false);

        if (sesion) {
            // Redirige al menú si la sesión está activa
            Intent intent = new Intent(InicioActivity.this, MenuUsuarioActivity.class);
            startActivity(intent);
            finish();  // Cierra la actividad de bienvenida
        } else {
            // Configura la vista de bienvenida
            setContentView(R.layout.activity_inicio);

            Button empezarButton = findViewById(R.id.buttonEmpezar);
            empezarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InicioActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }


        //conductor
        //guardar sesion
        // Verifica si la sesión está activa
        SharedPreferences sharedPreferencesc = getSharedPreferences("sesionesc", MODE_PRIVATE);
        boolean sesionc = sharedPreferences.getBoolean("sesionc", false);

        if (sesionc) {
            // Redirige al menú si la sesión está activa
            Intent intent = new Intent(InicioActivity.this, MenuConductorActivity.class);
            startActivity(intent);
            finish();  // Cierra la actividad de bienvenida
        } else {
            // Configura la vista de bienvenida
            setContentView(R.layout.activity_inicio);

            Button empezarButton = findViewById(R.id.buttonEmpezar);
            empezarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InicioActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

        // Carga la imagen que quieres usar como fondo de pantalla
        Drawable backgroundImage = ContextCompat.getDrawable(this, R.drawable.fondo1);

        // Establece la imagen como fondo de pantalla de la actividad
        getWindow().setBackgroundDrawable(backgroundImage);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void irPrincipal(View view){
   Intent i = new Intent(this,MainActivity.class);
   startActivity(i);
    }





}