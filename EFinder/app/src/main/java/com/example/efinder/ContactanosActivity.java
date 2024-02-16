package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactanosActivity extends ToolbarActivity {
    EditText inputTextNombre, inputTextTelefono, inputTextEmail, inputTextAsunto, inputTextMensaje;
    Button buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactanos_layout);

        setToolbarOnClicks();

        // Inicializar los EditTexts
        inputTextNombre = findViewById(R.id.inputTextNombre);
        inputTextTelefono = findViewById(R.id.inputTextTelefono);
        inputTextEmail = findViewById(R.id.inputTextEmail);
        inputTextAsunto = findViewById(R.id.inputTextAsunto);
        inputTextMensaje = findViewById(R.id.inputTextMensaje);

        // Encuentra el botón Enviar por su ID
        buttonEnviar = findViewById(R.id.buttonEnviar); // Asegúrate de que este es el ID correcto

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEmail();
            }
        });
    }

    private void enviarEmail() {
        String nombre = inputTextNombre.getText().toString();
        String telefono = inputTextTelefono.getText().toString();
        // El email del usuario se recoge pero no se usa como destinatario, se puede incluir en el cuerpo del mensaje.
        String emailUsuario = inputTextEmail.getText().toString();
        String asunto = inputTextAsunto.getText().toString();
        String mensaje = inputTextMensaje.getText().toString();

        // Combina todos los datos en el cuerpo del correo electrónico
        String cuerpoEmail = "Nombre: " + nombre + "\nTeléfono: " + telefono + "\nEmail del usuario: " + emailUsuario + "\nMensaje: " + mensaje;

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");

        // Configura la dirección de correo electrónico a la que deseas enviar todos los mensajes.
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"estebanbernaldam@gmail.com"}); // Reemplaza con tu dirección de correo destino

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, cuerpoEmail);

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactanosActivity.this, "No hay aplicaciones de correo instaladas.", Toast.LENGTH_SHORT).show();
        }
    }
}
