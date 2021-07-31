package com.android.guicelebrini.whatsapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.activity.ChatActivity;
import com.android.guicelebrini.whatsapp.adapter.AdapterRecyclerChats;
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.android.guicelebrini.whatsapp.helper.Base64Custom;
import com.android.guicelebrini.whatsapp.helper.Preferences;
import com.android.guicelebrini.whatsapp.helper.RecyclerItemClickListener;
import com.android.guicelebrini.whatsapp.model.Chat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {

    private RecyclerView recyclerChats;
    private List<Chat> chatsList = new ArrayList<>();
    private AdapterRecyclerChats adapter;

    private View view;

    private DatabaseReference reference;
    private ValueEventListener eventListener;

    public ChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chats, container, false);
        findViewsById();

        createChatsList();

        configureRecyclerChats();


        return view;
    }

    private void findViewsById(){
        recyclerChats = view.findViewById(R.id.recyclerChats);
    }

    private void createChatsList(){

        Preferences preferences = new Preferences(view.getContext());
        String loggedUserId = preferences.getUserId();

        reference = FirebaseConfig.getFirebaseReference();
        reference = reference.child("chats").child(loggedUserId);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                chatsList.clear();

                for ( DataSnapshot data : snapshot.getChildren()) {
                    Chat chat = data.getValue(Chat.class);
                    chatsList.add(chat);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        };
    }

    private void configureRecyclerChats(){
        adapter = new AdapterRecyclerChats(chatsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerChats.setLayoutManager(layoutManager);
        recyclerChats.setHasFixedSize(true);
        recyclerChats.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        recyclerChats.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerChats, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onItemClick(View view, int position) {
                String contactName = chatsList.get(position).getContactName();
                String contactEmail = Base64Custom.decode(chatsList.get(position).getContactId());

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("contactName", contactName);
                intent.putExtra("contactEmail", contactEmail);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        recyclerChats.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        reference.addValueEventListener(eventListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        reference.removeEventListener(eventListener);
    }
}