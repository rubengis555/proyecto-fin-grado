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
import com.example.proyecto_cooperativa.pantallaCargando;

public class loginUsuario extends AppCompatActivity {
EditText txtusuarioLogin, txtpasswordLogin;
Button btn_Login;
Controlador db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        txtusuarioLogin = findViewById(R.id.txtusuarioLogin);
        txtpasswordLogin = findViewById(R.id.txtpasswordLogin);
        btn_Login = findViewById(R.id.btn_Login);

        db = new Controlador(loginUsuario.this);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtusuarioLogin.getText().toString();
                String password = txtpasswordLogin.getText().toString();
                if(usuario.equals("") || password.equals("")){
                    Toast.makeText(loginUsuario.this, "RELLENE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }else{
                     Boolean resultado = db.checkUsuarioContra(usuario, password);
                     if (resultado == true){
                         txtusuarioLogin.setText("");
                         txtpasswordLogin.setText("");
                         Intent i = new Intent(loginUsuario.this, pantallaCargando.class);
                         startActivity(i);
                         
                     }else{
                         Toast.makeText(loginUsuario.this, "CREDENCIALES ERRÓNEAS", Toast.LENGTH_SHORT).show();
                     }
                }
            }
        });

    }
}