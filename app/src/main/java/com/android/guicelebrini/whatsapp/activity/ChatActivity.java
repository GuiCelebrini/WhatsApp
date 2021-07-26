package com.android.guicelebrini.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;

import com.android.guicelebrini.whatsapp.R;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private String contactName, contactEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewsById();
        setExtras();
        configureToolbar();

    }

    private void setExtras(){
        Bundle extra = getIntent().getExtras();

        if (extra != null){
            contactName = extra.getString("contactName");
        }
    }

    private void configureToolbar() {

        toolbar.setTitle(contactName);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        setSupportActionBar(toolbar);

    }

    public void findViewsById(){
        toolbar = findViewById(R.id.toolbar_chat);
    }
}