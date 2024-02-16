package com.example.efinder;

import android.os.Bundle;

public class AgendaActivity extends ToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_layout);

        setToolbarOnClicks();
    }
}
