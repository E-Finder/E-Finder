package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcercaDeActivity extends ToolbarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerda_de_layout);

        setToolbarOnClicks();

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para ir a la nueva actividad
                Intent intent = new Intent(AcercaDeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}