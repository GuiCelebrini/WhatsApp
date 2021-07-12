package com.android.guicelebrini.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setSupportActionBar(toolbar);


    }

    public void findViewsById(){
        toolbar = findViewById(R.id.toolbar);
    }

    public void logoutUser(){
        auth = FirebaseConfig.getFirebaseAuthentication();
        auth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.item_logout:
                logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}