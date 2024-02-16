package com.example.efinder;
import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {
private Button confirmar;
private EditText correo, contraseña, contraseñaRepetida;
private GestionarUsuario db;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        //Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        confirmar =  findViewById(R.id.btnConfirmar);
        correo =  findViewById(R.id.editTextCorreoReg);
        contraseña =  findViewById(R.id.editTextContraseñaReg);
        contraseñaRepetida = findViewById(R.id.editTextRepetirContraseñaReg);
    }

    public void onClickRegistrar(View view) {
        Usuario usuario3 = new Usuario();
        String email = correo.getText().toString();
        String pass = contraseña.getText().toString();
        String passR = contraseñaRepetida.getText().toString();
        if (!pass.equals(passR)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }
        usuario3.setEmail(email);
        usuario3.setPassword(pass);
        DbUsuario dbUsuario = new DbUsuario(RegistrarActivity.this);
        long id = dbUsuario.insertarUsuario(usuario3);
        if(id>0){
            Toast.makeText(RegistrarActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } else {
            Toast.makeText(RegistrarActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }
public void limpiarCampos(){
        correo.setText("");
        contraseña.setText("");
        contraseñaRepetida.setText("");
}
}