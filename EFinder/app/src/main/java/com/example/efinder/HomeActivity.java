package com.example.efinder;

import android.os.Bundle;

public class HomeActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        setToolbarOnClicks();



    }
}


