package com.ryancodedev.aplicativodetransporte;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagoUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pago_usuario);

        Spinner spinnerParaderos = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapterParaderos = ArrayAdapter.createFromResource(this, R.array.paraderos, android.R.layout.simple_spinner_item);
        adapterParaderos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  // Usa android.R.layout.simple_spinner_dropdown_item
        spinnerParaderos.setAdapter(adapterParaderos);

        Spinner spinnerDestinos = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapterDestinos = ArrayAdapter.createFromResource(this, R.array.destinos, android.R.layout.simple_spinner_item);
        adapterDestinos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  // Usa android.R.layout.simple_spinner_dropdown_item
        spinnerDestinos.setAdapter(adapterDestinos);

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String selectedParadero = spinnerParaderos.getSelectedItem().toString();
                String selectedDestino = spinnerDestinos.getSelectedItem().toString();
                Intent intent = new Intent(PagoUsuarioActivity.this, PagoQRUsuarioActivity.class);
                intent.putExtra("selectedParadero", selectedParadero);
                intent.putExtra("selectedDestino",selectedDestino);
                startActivity(intent);
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
    public void irPagosQRUsuario(View view){
        Intent i = new Intent (this, PagoQRUsuarioActivity.class);
        startActivity(i);
    }
    public void irMenu(View view){
        Intent i = new Intent (this, MenuUsuarioActivity.class);
        startActivity(i);
    }
}