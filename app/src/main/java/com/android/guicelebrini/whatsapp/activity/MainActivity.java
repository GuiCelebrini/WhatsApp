package com.android.guicelebrini.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.adapter.TabAdapter;
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.android.guicelebrini.whatsapp.fragment.ChatsFragment;
import com.android.guicelebrini.whatsapp.fragment.ContactsFragment;
import com.android.guicelebrini.whatsapp.helper.SlidingTabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SmartTabLayout tabLayoutMain;
    private ViewPager viewPager;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setSupportActionBar(toolbar);

        configureTabLayout();


    }

    public void findViewsById(){
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        tabLayoutMain = findViewById(R.id.tabLayoutMain);
    }

    private void logoutUser(){
        auth = FirebaseConfig.getFirebaseAuthentication();
        auth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void openUserRegisterScreen(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        //configuring alertDialog
        alertDialog.setTitle("Adicionar novo contato");
        alertDialog.setMessage("Email do contato");
        alertDialog.setCancelable(false);

        EditText editEmail = new EditText(this);
        alertDialog.setView(editEmail);

        alertDialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.create();
        alertDialog.show();


    }

    public void configureTabLayout(){
        /*//configuring adapter on viewPager
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), 1);
        viewPager.setAdapter(tabAdapter);

        //configuring slidingTabLayout
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));

        //configuring viewPager on slidingTabLayout
        slidingTabLayout.setViewPager(viewPager);*/

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("CONVERSAS", ChatsFragment.class)
                .add("CONTATOS", ContactsFragment.class)
                .create());


        viewPager.setAdapter(adapter);
        tabLayoutMain.setViewPager(viewPager);
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

            case R.id.item_add_person:
                openUserRegisterScreen();
                return true;
            case R.id.item_logout:
                logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}