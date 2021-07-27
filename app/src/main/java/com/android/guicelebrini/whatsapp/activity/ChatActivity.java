package com.android.guicelebrini.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.android.guicelebrini.whatsapp.helper.Base64Custom;
import com.android.guicelebrini.whatsapp.helper.Preferences;
import com.android.guicelebrini.whatsapp.model.Message;
import com.google.firebase.database.DatabaseReference;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMessage;
    private ImageButton buttonSend;

    private Preferences preferences;

    private DatabaseReference reference;

    private String contactName, contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewsById();
        setExtras();
        configureToolbar();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preferences = new Preferences(getApplicationContext());
                String loggedUserId = preferences.getUserId();
                Message message = new Message(loggedUserId, editMessage.getText().toString());
                saveMessage(loggedUserId, contactId, message);


            }
        });

    }

    public boolean saveMessage(String loggedUserId, String contactId, Message message){
        try {
            reference = FirebaseConfig.getFirebaseReference();
            reference.child("messages")
                    .child(loggedUserId)
                    .child(contactId)
                    .push()
                    .setValue(message);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void setExtras(){
        Bundle extra = getIntent().getExtras();

        if (extra != null){
            contactName = extra.getString("contactName");
            String contactEmail = extra.getString("contactEmail");
            contactId = Base64Custom.encode(contactEmail);
        }
    }

    private void configureToolbar() {

        toolbar.setTitle(contactName);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        setSupportActionBar(toolbar);

    }

    public void findViewsById(){
        toolbar = findViewById(R.id.toolbar_chat);
        editMessage = findViewById(R.id.editMessage);
        buttonSend = findViewById(R.id.buttonSendMessage);
    }
}