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
import com.android.guicelebrini.whatsapp.helper.Base64Custom;
import com.android.guicelebrini.whatsapp.helper.Helper;
import com.android.guicelebrini.whatsapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText editName, editEmail, editPassword;
    private Button buttonRegister;

    private User user;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewsById();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = new User(editName.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                registerUser();

            }
        });
    }

    public void findViewsById(){
        editName = findViewById(R.id.editRegisterName);
        editEmail = findViewById(R.id.editRegisterEmail);
        editPassword = findViewById(R.id.editRegisterPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
    }

    public void registerUser(){
        auth = FirebaseConfig.getFirebaseAuthentication();
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                    String encodedEmail = Base64Custom.encode(user.getEmail());
                    user.setId(encodedEmail);
                    user.saveInFirebase();

                    Helper.saveIdInPreferences(RegisterActivity.this, user.getEmail());
                    returnToLoginActivity();
                } else {

                    String exceptionMessage;

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        exceptionMessage = "Digite uma senha mais forte, com mais caracteres e números";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exceptionMessage = "O endereço de email digitado é inválido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        exceptionMessage = "Já existe um usuário usando esse email";
                    } catch (Exception e) {
                        exceptionMessage = "Erro ao cadastrar usuário";
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(), exceptionMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void returnToLoginActivity(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}