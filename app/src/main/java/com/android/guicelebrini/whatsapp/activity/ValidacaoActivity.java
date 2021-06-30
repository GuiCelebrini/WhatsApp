package com.android.guicelebrini.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.helper.Preferences;

import java.util.HashMap;

public class ValidacaoActivity extends AppCompatActivity {

    private EditText editCode;
    private Button buttonValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);
        findViewsById();

        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences preferences = new Preferences(ValidacaoActivity.this);
                HashMap<String, String> user = preferences.getUserData();

                String generatedToken = user.get("token");
                String insertedToken = editCode.getText().toString();

                if (insertedToken.equals(generatedToken)){
                    Toast.makeText(getApplicationContext(), "Your token was validated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Your token couldn't be validated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void findViewsById(){
        editCode = findViewById(R.id.editCode);
        buttonValidate = findViewById(R.id.buttonValidate);
    }
}