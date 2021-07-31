package com.android.guicelebrini.whatsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.model.Chat;

import java.util.List;
import java.util.zip.Inflater;

public class AdapterRecyclerChats extends RecyclerView.Adapter<AdapterRecyclerChats.MyViewHolder> {

    private List<Chat> chatsList;

    public AdapterRecyclerChats(List<Chat> chatsList){
        this.chatsList = chatsList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chats_list, parent, false);

        return new AdapterRecyclerChats.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerChats.MyViewHolder holder, int position) {
        Chat chat = chatsList.get(position);
        holder.set(chat);
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textName;
        private TextView textLastMessage;

        public MyViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textChatName);
            textLastMessage = itemView.findViewById(R.id.textChatLastMessage);
        }

        public void set(Chat chat){
            this.textName.setText(chat.getContactName());
            this.textLastMessage.setText(chat.getLastMessage());
        }
    }
}
