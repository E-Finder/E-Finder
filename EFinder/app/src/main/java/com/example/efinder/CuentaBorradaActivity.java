package com.example.efinder;

import android.os.Bundle;

public class CuentaBorradaActivity extends ToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuenta_borrada_layout);

        setToolbarOnClicks();
    }
}