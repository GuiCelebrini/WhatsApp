package com.android.guicelebrini.whatsapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.android.guicelebrini.whatsapp.helper.Preferences;
import com.android.guicelebrini.whatsapp.model.Contact;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    private RecyclerView recyclerContacts;
    private List<Contact> contactsList = new ArrayList<>();
    private View view;
    private AdapterRecyclerContacts adapter;

    private DatabaseReference reference;
    private ValueEventListener valueEventListenerContacts;


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

        adapter = new AdapterRecyclerContacts(contactsList);
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
        Preferences preferences = new Preferences(getContext());
        String idLoggedUser = preferences.getUserId();

        reference = FirebaseConfig.getFirebaseReference();
        reference = reference.child("users").child(idLoggedUser).child("addedContacts");

        valueEventListenerContacts = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                contactsList.clear();

                for (DataSnapshot data : snapshot.getChildren()) {

                    Contact contact = data.getValue(Contact.class);
                    contactsList.add(contact);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        reference.addValueEventListener(valueEventListenerContacts);
    }

    @Override
    public void onStop() {
        super.onStop();
        reference.removeEventListener(valueEventListenerContacts);
    }
}