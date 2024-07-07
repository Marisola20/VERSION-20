package com.ryancodedev.aplicativodetransporte;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginConductorActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, contrasena;
    Button btnIniciarSesionc;
    CheckBox chekGuardarSesion2;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave = "sesionc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_conductor);
        inicializarElementos();
        if(revisarSesion()){
            startActivity(new Intent(this, MenuConductorActivity.class));
        }else{
            String mensaje = "Por favor, inicie sesión";
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
        }

        btnIniciarSesionc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                guardarSesion(chekGuardarSesion2.isChecked());
                iniciarSesionConductor();
                finish();
            }
        });

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Referencias a los campos de entrada
        email = findViewById(R.id.emailConductor);
        contrasena = findViewById(R.id.contrasenaConductor);

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

    private boolean revisarSesion(){
        return this.preferences.getBoolean(llave,false);
    }

    private void guardarSesion(boolean checked){
        editor.putBoolean(llave, checked);
        editor.apply();
    }

    private void inicializarElementos(){
        preferences = this.getSharedPreferences("sesionesc", Context.MODE_PRIVATE);
        editor = preferences.edit();
        btnIniciarSesionc = findViewById(R.id.btnIniciarSesionc);
        chekGuardarSesion2 = findViewById(R.id.chekGuardarSesion2);
    }

    public void iniciarSesionConductor() {
        String emailInput = email.getText().toString().trim();
        String contrasenaInput = contrasena.getText().toString().trim();

        if (emailInput.isEmpty() || contrasenaInput.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailInput, contrasenaInput)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio de sesión exitoso
                            Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MenuConductorActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // Si el inicio de sesión falla, verifica si es por usuario no registrado
                            Toast.makeText(getApplicationContext(), "Usted no tiene una cuenta de conductor", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }
                    }
                });
    }

    public void irRegresar5(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}