package com.android.guicelebrini.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText editName, editEmail, editPassword;
    private Button buttonRegister;
    private User user;

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

    }
}