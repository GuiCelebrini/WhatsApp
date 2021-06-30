package com.android.guicelebrini.whatsapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.helper.Permission;
import com.android.guicelebrini.whatsapp.helper.Preferences;

import java.util.HashMap;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    private Button buttonRegister;
    private EditText editPersonName;
    private EditText editNumberCountry ,editNumberDDD ,editNumberPhone;

    private String messageSMS;
    private String[] necessaryPermissions = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET
    };

    private static final int PERMISSIONS_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewsById();
        Permission.validatePermission(PERMISSIONS_REQUEST_CODE ,this, necessaryPermissions);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editPersonName.getText().toString();
                String phoneNumber =
                        editNumberCountry.getText().toString() +
                                editNumberDDD.getText().toString() +
                                editNumberPhone.getText().toString();

                //Saving data for validation
                Preferences preferences = new Preferences(LoginActivity.this);
                preferences.saveUserPreferences(username, phoneNumber, generateToken());

                //sending the SMS
                Boolean wasSent = sendSMS("+" + phoneNumber, messageSMS);

                /*HashMap<String, String> user = preferences.getUserData();
                Log.i("Result",  "Name: " + user.get("username") + " Phone: " + user.get("phoneNumber") + " Token: " + user.get("token"));*/

            }
        });

    }

    public String generateToken(){
        Random random = new Random();
        int randomNumber = random.nextInt((9999-1000) + 1000);
        String token = String.valueOf(randomNumber);

        messageSMS = "Your validation token is " + token;

        return token;
    }

    public Boolean sendSMS(String phoneNumber, String message){
        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
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

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {

            if (result == PackageManager.PERMISSION_DENIED){
                alertPermissionDenied();
            }

        }
    }

    private void alertPermissionDenied() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Permissions Denied");
        builder.setMessage("To use this app is necessary to allow all the permissions");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}