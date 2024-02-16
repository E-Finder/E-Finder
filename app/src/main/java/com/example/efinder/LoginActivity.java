package com.example.efinder;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class LoginActivity extends AppCompatActivity {
private EditText emailEditText, passwordEditText;
    private Button btnAcceder;
    private ImageButton btnAtras;
GestionarUsuario db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        emailEditText =  findViewById(R.id.etEmail);
        passwordEditText =  findViewById(R.id.etContrasenea);
        btnAcceder =  findViewById(R.id.btnAcceder);
        btnAtras =    findViewById(R.id.btnLoginBack);
    }
public void onBackButtonClick(View view){
//Intent x = new Intent(this,InicioActivity.class);
//startActivity(x);

}
public void accederButtonClick( View view){
    String correo =  emailEditText.getText().toString();
    String contraseña = passwordEditText.getText().toString();
        Usuario usuario1 = new Usuario(correo,contraseña);
    Usuario usuario2 = new Usuario();
        DbUsuario dbUsuario = new DbUsuario(LoginActivity.this);
       usuario2  =dbUsuario.mostrarUsuario(emailEditText.toString());
if(usuario1.getEmail().equalsIgnoreCase(usuario2.getEmail()) && usuario1.getPassword().equals(usuario2.getPassword())){
    Toast.makeText(this, "Bienvenido ", Toast.LENGTH_SHORT).show();
    //Intent x = new Intent(this,HomeActivity.class);
    //startActivity(x);
}else Toast.makeText(this, "No se puede entrar,el correo o la contraseña es incorrecto ", Toast.LENGTH_SHORT).show();
}
    public void registrarButtonClick(View view) {
        Intent x = new Intent(this, RegistrarActivity.class);
        startActivity(x);
    }
}
