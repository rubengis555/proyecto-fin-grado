package com.example.proyecto_cooperativa.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.bd.Controlador;

public class reseteoUsuario extends AppCompatActivity {
EditText txtusuario;
Button btn_registro;
Controlador db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reseteo_usuario);

        txtusuario = findViewById(R.id.txtusuario);
        btn_registro = findViewById(R.id.btn_registro);

        db = new Controlador(reseteoUsuario.this);

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtusuario.getText().toString();
                Boolean checkUsuariov = db.checkUsuario(usuario);
                if(checkUsuariov == true){
                    Intent i = new Intent(reseteoUsuario.this, modificacionUsuario.class);
                    i.putExtra("txtusuario", usuario);
                    startActivity(i);
                }else{
                    Toast.makeText(reseteoUsuario.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}