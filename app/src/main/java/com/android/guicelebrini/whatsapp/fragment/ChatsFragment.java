package com.android.guicelebrini.whatsapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.adapter.AdapterRecyclerChats;
import com.android.guicelebrini.whatsapp.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {

    private RecyclerView recyclerChats;
    private List<Chat> chatsList = new ArrayList<>();
    private AdapterRecyclerChats adapter;

    private View view;

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
        
    }

    private void configureRecyclerChats(){
        adapter = new AdapterRecyclerChats(chatsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerChats.setLayoutManager(layoutManager);
        recyclerChats.setHasFixedSize(true);
        recyclerChats.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));
        recyclerChats.setAdapter(adapter);
    }
}