package com.pgmacdesign.randomuserapitests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariables();
        initFragments();
    }

    private void initVariables(){
        container = R.id.main_layout;

    }

    private void initFragments(){

    }
}
