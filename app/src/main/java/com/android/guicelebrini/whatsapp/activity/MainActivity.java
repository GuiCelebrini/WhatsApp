package com.android.guicelebrini.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.android.guicelebrini.whatsapp.fragment.ChatsFragment;
import com.android.guicelebrini.whatsapp.fragment.ContactsFragment;
import com.android.guicelebrini.whatsapp.helper.Base64Custom;
import com.android.guicelebrini.whatsapp.helper.Preferences;
import com.android.guicelebrini.whatsapp.model.Contact;
import com.android.guicelebrini.whatsapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SmartTabLayout tabLayoutMain;
    private ViewPager viewPager;

    private FirebaseAuth auth;
    private DatabaseReference database;

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
        editEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        alertDialog.setView(editEmail);

        alertDialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveContact(editEmail);
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

    public void saveContact(EditText editEmail){
        String email = editEmail.getText().toString();

        if (email.equals(null)){
            Toast.makeText(getApplicationContext(), "O campo não pode estar vazio", Toast.LENGTH_SHORT).show();
        } else {
            String idAddedUser = Base64Custom.encode(email);

            database = FirebaseConfig.getFirebaseReference().child("users").child(idAddedUser);

            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()){

                        User userContact = snapshot.getValue(User.class);

                        Preferences preferences = new Preferences(MainActivity.this);
                        String loggedUserId = preferences.getUserId();

                        database = FirebaseConfig.getFirebaseReference();
                        database = database.child("contacts").child(loggedUserId).child(idAddedUser);

                        Contact contact = new Contact(idAddedUser, userContact.getEmail(), userContact.getName());
                        database.setValue(contact);

                    } else {
                        Toast.makeText(getApplicationContext(), "O email inserido não existe", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

        }
    }

    public void configureTabLayout(){

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