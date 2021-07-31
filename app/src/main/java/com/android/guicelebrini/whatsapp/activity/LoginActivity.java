package com.android.guicelebrini.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.android.guicelebrini.whatsapp.helper.Base64Custom;
import com.android.guicelebrini.whatsapp.helper.Helper;
import com.android.guicelebrini.whatsapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private Button buttonLogin;
    private EditText editEmail, editPassword;

    private User user;

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewsById();

        isLoggedIn();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setEmail(editEmail.getText().toString());
                user.setPassword(editPassword.getText().toString());
                validateUser();
            }
        });

    }

    private void validateUser() {
        auth = FirebaseConfig.getFirebaseAuthentication();

        auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()){
                    setInformationsOnSharedPreferences();
                    Toast.makeText(getApplicationContext(), "Usuário logado com sucesso", Toast.LENGTH_SHORT).show();
                    goToMainActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao logar usuário", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setInformationsOnSharedPreferences(){
        String loggedUserId = Base64Custom.encode(user.getEmail());
        reference = FirebaseConfig.getFirebaseReference();
        reference = reference.child("users").child(loggedUserId);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User firebaseUser = snapshot.getValue(User.class);
                user.setName(firebaseUser.getName());
                Helper.saveDataInPreferences(LoginActivity.this, user.getEmail(), user.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void isLoggedIn(){
        auth = FirebaseConfig.getFirebaseAuthentication();

        if (auth.getCurrentUser() != null){
            goToMainActivity();
        }
    }

    public void findViewsById(){
        buttonLogin = findViewById(R.id.buttonLogin);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
    }

    public void goToMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToRegisterActivity(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}