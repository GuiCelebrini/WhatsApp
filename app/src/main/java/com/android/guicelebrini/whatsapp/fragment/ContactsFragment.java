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
import com.android.guicelebrini.whatsapp.adapter.AdapterRecyclerContacts;
import com.android.guicelebrini.whatsapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    private RecyclerView recyclerContacts;
    private List<Contact> contactsList = new ArrayList<>();
    private View view;


    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contacts, container, false);

        findViewsById();
        createContactsList();

        AdapterRecyclerContacts adapter = new AdapterRecyclerContacts(contactsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerContacts.setLayoutManager(layoutManager);
        recyclerContacts.setHasFixedSize(true);
        recyclerContacts.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));
        recyclerContacts.setAdapter(adapter);


        return view;
    }

    public void findViewsById(){
        recyclerContacts = view.findViewById(R.id.recyclerContacts);
    }

    public void createContactsList(){
        contactsList.add(new Contact("", "albert@gmail.com", "Albert Johnson"));
    }

}