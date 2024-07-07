package com.ryancodedev.aplicativodetransporte;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class PagoQRUsuarioActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagoqr_usuario);
        EditText txtDatos = findViewById(R.id.txtDatos);
        Button btnGenera = findViewById(R.id.btnGenera);
        ImageView imgQR = findViewById(R.id.qrCode);

        TextView paraderoTextView = findViewById(R.id.paraderoTextView);
        TextView destinoTextView = findViewById(R.id.destinoTextView);

        Intent intent = getIntent();
        String selectedParadero = intent.getStringExtra("selectedParadero");
        String selectedDestino = intent.getStringExtra("selectedDestino");

        paraderoTextView.setText(selectedParadero);
        destinoTextView.setText(selectedDestino);

        btnGenera.setOnClickListener(v -> {
            try{
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.encodeBitmap("tel:"+txtDatos.getText().toString(), BarcodeFormat.QR_CODE, 190,190);
                imgQR.setImageBitmap(bitmap);
            }catch(Exception e){
                e.printStackTrace();
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
    public void irPagosUsuario(View view){
        Intent i = new Intent (this, PagoUsuarioActivity.class);
        startActivity(i);
    }
}
