package com.ryancodedev.aplicativodetransporte;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
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
import com.google.firebase.auth.FirebaseUser;

public class RegistrarseActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText usuario;
    private EditText email;
    private EditText contrasena;
    private EditText contrasenaConfirmada;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();
        usuario = findViewById(R.id.usuarior);
        email = findViewById(R.id.correor);
        contrasena = findViewById(R.id.contrasenar);
        contrasenaConfirmada = findViewById(R.id.contrasenarConfirmada);

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

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
   public void registrarUsuario(View view){

        if(contrasena.getText().toString().equals(contrasenaConfirmada.getText().toString())){

            mAuth.createUserWithEmailAndPassword(email.getText().toString(), contrasena.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(), ConfirmarRegistroActivity.class);
                                startActivity(i);
                            } else {
                                // Si el registro falla, muestra un mensaje al usuario
                                Toast.makeText(getApplicationContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }

   }

    public void confirmacion(View view){
        Intent i = new Intent(this, ConfirmarRegistroActivity.class);
        startActivity(i);
    }
    public void irRegresar3(View view){
        Intent i = new Intent(this, LoginUserActivity.class);
        startActivity(i);
    }

}