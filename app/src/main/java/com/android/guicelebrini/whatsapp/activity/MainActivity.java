package com.android.guicelebrini.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.adapter.TabAdapter;
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.android.guicelebrini.whatsapp.helper.SlidingTabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setSupportActionBar(toolbar);

        //configuring adapter on viewPager
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), 1);
        viewPager.setAdapter(tabAdapter);

        //configuring slidingTabLayout
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));

        //configuring viewPager on slidingTabLayout
        slidingTabLayout.setViewPager(viewPager);


    }

    public void findViewsById(){
        toolbar = findViewById(R.id.toolbar);
        slidingTabLayout = findViewById(R.id.stl_tabs);
        viewPager = findViewById(R.id.vpMain);
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