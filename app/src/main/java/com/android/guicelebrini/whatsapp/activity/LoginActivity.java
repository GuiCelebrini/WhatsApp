package com.android.guicelebrini.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.guicelebrini.whatsapp.R;

public class LoginActivity extends AppCompatActivity {
    // ah é
    private Button buttonCadastrar;

    private EditText editTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        editTelefone = findViewById(R.id.editTelefone);


    }

    public void trocarTela(View view){
        Intent destino = new Intent(getApplicationContext(), ValidacaoActivity.class);
        startActivity(destino);
    }
}