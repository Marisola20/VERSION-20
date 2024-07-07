package com.ryancodedev.aplicativodetransporte;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginUserActivity extends AppCompatActivity {

    Button btnIniciarSesion;
    CheckBox chekGuardarSesion;
    private FirebaseAuth auth;
    private EditText email, password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave = "sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_user);
        inicializarElementos();
        if(revisarSesion()){
            startActivity(new Intent(this, MenuUsuarioActivity.class));
        }else{
            String mensaje = "Por favor, inicie sesión";
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
        }

        btnIniciarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                guardarSesion(chekGuardarSesion.isChecked());
                iniciarSesionUsuario();
                finish();
            }
        });

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance();

        //Referencias de los campos de entrada
        email = findViewById(R.id.emailUsuario);
        password = findViewById(R.id.passwordUsuario);

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
        preferences = this.getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        chekGuardarSesion = findViewById(R.id.chekGuardarSesion);
    }

    public void iniciarSesionUsuario() {
        String emailInput = email.getText().toString().trim();
        String contrasenaInput = password.getText().toString().trim();

        if (emailInput.isEmpty() || contrasenaInput.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(emailInput, contrasenaInput)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio de sesión exitoso
                            Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MenuUsuarioActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuario no registrado. Por favor, registrese.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplication(),RegistrarseActivity.class);
                            startActivity(i);
                        }
                    }
               });
    }

    public void registrarse(View view) {
        Intent i = new Intent(getApplicationContext(), RegistrarseActivity.class);
        startActivity(i);
    }

    public void irRegistro(View view){
        Intent i = new Intent(this,RegistrarseActivity.class);
        startActivity(i);
    }
    public void irRegresar2(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void irMenu(View view){
        Intent i = new Intent (this, MenuUsuarioActivity.class);
        startActivity(i);
    }
}