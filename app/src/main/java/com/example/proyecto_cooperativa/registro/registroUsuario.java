package com.example.proyecto_cooperativa.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.bd.Controlador;

public class registroUsuario extends AppCompatActivity {

    EditText txtusuario, txtpassword, txtrepassword;
    Button btn_registro, btn_logar;
    TextView textviewolvidocontra;
    Controlador db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        txtusuario = findViewById(R.id.txtusuario);
        txtpassword = findViewById(R.id.txtpassword);
        txtrepassword = findViewById(R.id.txtrepassword);
        btn_registro = findViewById(R.id.btn_registro);
        btn_logar = findViewById(R.id.btn_logar);
        textviewolvidocontra = findViewById(R.id.textviewolvidocontra);

        textviewolvidocontra.setText(Html.fromHtml("<u>Reestablecer la contraseña</u>"));

        db = new Controlador(registroUsuario.this);

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtusuario.getText().toString();
                String password = txtpassword.getText().toString();
                String repassword = txtrepassword.getText().toString();

                if(usuario.equals("") || password.equals("") || repassword.equals("")){
                    Toast.makeText(registroUsuario.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }else{
                    if (password.equals(repassword)){
                        Boolean usuarioValido = db.checkUsuario(usuario);
                        if(usuarioValido == false){
                           Boolean registroUsuario =  db.insertaUsuario(usuario,password);
                           if(registroUsuario == true){
                               Toast.makeText(registroUsuario.this, "REGISTRO GUARDADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                               txtusuario.setText("");
                               txtpassword.setText("");
                               txtrepassword.setText("");
                               Intent i = new Intent(registroUsuario.this, loginUsuario.class );
                               startActivity(i);
                               /*
                               txtusuario.setText("");
                               txtpassword.setText("");
                               txtrepassword.setText("");

                                */
                           }else{
                               Toast.makeText(registroUsuario.this, "NO SE HA PODIDO GUARDAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                           }

                        }else{
                            Toast.makeText(registroUsuario.this, "EL USUARIO YA EXISTE. \n POR FAVOR LOGUESE", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(registroUsuario.this, "NO COINCIDE LAS CONTRASEÑAS", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registroUsuario.this, loginUsuario.class);
                startActivity(i);
            }
        });

        textviewolvidocontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registroUsuario.this, reseteoUsuario.class);
                startActivity(i);
            }
        });
    }
}