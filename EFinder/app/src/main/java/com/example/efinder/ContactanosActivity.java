package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactanosActivity extends ToolbarActivity {
    EditText inputTextNombre;
    EditText inputTextTelefono;
    EditText inputTextEmail;
    EditText inputTextAsunto;
    EditText inputTextMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactanos_layout);

        setToolbarOnClicks();

        Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para ir a la nueva actividad
                Intent intent = new Intent(ContactanosActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }


}
