package com.example.efinder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toolbar;

public class MainActivity2 extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Toolbar toolbarActivity=(Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbarActivity);

        ImageButton imageButtonPlan = findViewById(R.id.imageButtonPlan);
        imageButtonPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo();
            }
        });
    }
}
