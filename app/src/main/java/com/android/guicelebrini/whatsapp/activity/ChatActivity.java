package com.android.guicelebrini.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.adapter.AdapterRecyclerMessages;
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.android.guicelebrini.whatsapp.helper.Base64Custom;
import com.android.guicelebrini.whatsapp.helper.Preferences;
import com.android.guicelebrini.whatsapp.model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMessage;
    private ImageButton buttonSend;

    private RecyclerView recyclerMessages;
    private List<Message> messagesList = new ArrayList<>();
    private AdapterRecyclerMessages adapter;

    private Preferences preferences;

    private DatabaseReference reference;
    private ValueEventListener eventListener;

    private String loggedUserId;
    private String contactName, contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewsById();
        setExtras();
        configureToolbar();

        preferences = new Preferences(getApplicationContext());
        loggedUserId = preferences.getUserId();

        createMessagesList();

        configureRecyclerMessages();


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message(loggedUserId, editMessage.getText().toString());
                saveMessage(loggedUserId, contactId, message);
                editMessage.setText("");
            }
        });

    }

    private boolean saveMessage(String loggedUserId, String contactId, Message message){
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

    private void createMessagesList(){

        reference = FirebaseConfig.getFirebaseReference();
        reference = reference.child("messages")
                .child(loggedUserId).child(contactId);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                messagesList.clear();

                for (DataSnapshot data : snapshot.getChildren()) {
                    Message message = data.getValue(Message.class);
                    messagesList.add(message);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        };

        reference.addValueEventListener(eventListener);
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

    private void configureRecyclerMessages(){
        adapter = new AdapterRecyclerMessages(messagesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMessages.setLayoutManager(layoutManager);
        recyclerMessages.setHasFixedSize(true);
        recyclerMessages.setAdapter(adapter);
    }

    public void findViewsById(){
        toolbar = findViewById(R.id.toolbar_chat);
        editMessage = findViewById(R.id.editMessage);
        buttonSend = findViewById(R.id.buttonSendMessage);
        recyclerMessages = findViewById(R.id.recyclerMessages);
    }

    @Override
    protected void onStop() {
        super.onStop();
        reference.removeEventListener(eventListener);
    }
}