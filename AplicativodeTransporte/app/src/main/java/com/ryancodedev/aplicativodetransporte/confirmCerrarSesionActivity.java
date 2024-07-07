package com.ryancodedev.aplicativodetransporte;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class confirmCerrarSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_cerrar_sesion);

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
    public void irMain(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}