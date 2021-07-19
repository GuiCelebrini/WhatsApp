package com.android.guicelebrini.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.android.guicelebrini.whatsapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
    private Button buttonLogin;
    private EditText editEmail, editPassword;

    private User user;

    private FirebaseAuth auth;

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
                    Toast.makeText(getApplicationContext(), "Usuário logado com sucesso", Toast.LENGTH_SHORT).show();
                    goToMainActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao logar usuário", Toast.LENGTH_SHORT).show();
                }
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