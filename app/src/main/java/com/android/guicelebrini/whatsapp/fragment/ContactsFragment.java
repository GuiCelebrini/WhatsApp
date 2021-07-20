package com.android.guicelebrini.whatsapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.model.Contact;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {

    private RecyclerView recyclerContacts;
    private List<Contact>  contactsList;
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

        return view;
    }

    public void findViewsById(){
        recyclerContacts = view.findViewById(R.id.recyclerContacts);
    }
}