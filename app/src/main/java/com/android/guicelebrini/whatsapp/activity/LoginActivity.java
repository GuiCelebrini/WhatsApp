package com.android.guicelebrini.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.guicelebrini.whatsapp.R;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    private Button buttonRegister;
    private EditText editPersonName;
    private EditText editNumberCountry ,editNumberDDD ,editNumberPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewsById();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editPersonName.getText().toString();
                String phoneNumber =
                        editNumberCountry.getText().toString() +
                                editNumberDDD.getText().toString() +
                                editNumberPhone.getText().toString();

                //gerar token
                Random random = new Random();
                int randomNumber = random.nextInt((9999-1000) + 1000);
                String token = String.valueOf(randomNumber);

                Log.i("Resultado", token);
            }
        });

    }

    public void findViewsById(){
        buttonRegister = findViewById(R.id.buttonRegister);
        editPersonName = findViewById(R.id.editPersonName);
        editNumberCountry = findViewById(R.id.editNumberCountry);
        editNumberDDD = findViewById(R.id.editNumberDDD);
        editNumberPhone = findViewById(R.id.editNumberPhone);
    }

    public void trocarTela(View view){
        Intent destino = new Intent(getApplicationContext(), ValidacaoActivity.class);
        startActivity(destino);
    }
}