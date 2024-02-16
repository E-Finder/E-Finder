package com.example.efinder;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

public class SiguenosActivity extends ToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siguenos_en_redes_layout);

        setToolbarOnClicks();

        Button button2 = findViewById(R.id.button2);

        Button openTwitterBtn = findViewById(R.id.buttonSeguir);

        openTwitterBtn.setOnClickListener(view -> {
            String url = "https://twitter.com/EFindere";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para ir a la nueva actividad
                Intent intent = new Intent(SiguenosActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}