package com.ryancodedev.aplicativodetransporte;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuConductorActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_conductor);
        Button btnSalirc = findViewById(R.id.btnSalirc);
        preferences = this.getSharedPreferences("sesionesc", Context.MODE_PRIVATE);
        editor = preferences.edit();

        btnSalirc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                editor.putBoolean("sesionc", false);
                editor.apply();
                Toast.makeText(MenuConductorActivity.this,"Se cerro la sesión",Toast.LENGTH_SHORT).show();
                irPrincipal();
            }
        });



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
    public void irRutasConductor(View view){
        Intent i = new Intent (this, RutasConductorActivity.class);
        startActivity(i);
    }
    public void irDatosQRConductor(View view){
        Intent i = new Intent (this, DatosQRConductorActivity.class);
        startActivity(i);
    }
    public void irCerrarSesion(View view){
        Intent i = new Intent (this, CerrarSesionActivity.class);
        startActivity(i);
    }
    public void irPrincipal(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}