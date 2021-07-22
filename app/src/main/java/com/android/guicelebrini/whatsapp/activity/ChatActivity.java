package com.android.guicelebrini.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.android.guicelebrini.whatsapp.R;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewsById();
        configureToolbar();



    }

    private void configureToolbar() {

        toolbar.setTitle("Saint John");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        setSupportActionBar(toolbar);

    }

    public void findViewsById(){
        toolbar = findViewById(R.id.toolbar_chat);
    }
}