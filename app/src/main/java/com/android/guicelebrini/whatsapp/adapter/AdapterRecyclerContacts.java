package com.android.guicelebrini.whatsapp.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.model.Contact;

import java.util.List;

public class AdapterRecyclerContacts extends RecyclerView.Adapter<AdapterRecyclerContacts.MyViewHolder> {

    private List<Contact> contactsList;

    public AdapterRecyclerContacts(List<Contact> contactsList){
        this.contactsList = contactsList;
    }

    @Override
    public AdapterRecyclerContacts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts_list, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerContacts.MyViewHolder holder, int position) {
         Contact contact = contactsList.get(position);
         holder.set(contact);
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textEmail;

        public MyViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textEmail = itemView.findViewById(R.id.textEmail);

        }

        public void set(Contact contact){
            this.textName.setText(contact.getName());
            this.textEmail.setText(contact.getEmail());
        }
    }
}
