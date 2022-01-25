package com.example.proyecto_cooperativa.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.bd.Controlador;

public class modificacionUsuario extends AppCompatActivity {
TextView tusuario;
EditText txtpassword, txtrepassword;
Button btn_confirmacion;
Controlador db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion_usuario);

        tusuario = findViewById(R.id.usuario);
        txtpassword = findViewById(R.id.txtpassword);
        txtrepassword = findViewById(R.id.txtrepassword);
        btn_confirmacion = findViewById(R.id.btn_confirmacion);

        db = new Controlador(modificacionUsuario.this);

        Intent i = getIntent();
        tusuario.setText(i.getStringExtra("txtusuario"));

        btn_confirmacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = tusuario.getText().toString();
                String password = txtpassword.getText().toString();
                String repassword = txtrepassword.getText().toString();

                if (password.equals(repassword)) {


                    Boolean checkpasswordupdate = db.updateUsuario(usuario, password);
                    if (checkpasswordupdate == true) {
                        Intent i = new Intent(modificacionUsuario.this, loginUsuario.class);
                        startActivity(i);
                        Toast.makeText(modificacionUsuario.this, "Password modificado", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(modificacionUsuario.this, "No se ha podido modificar el password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(modificacionUsuario.this, "Los password no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}