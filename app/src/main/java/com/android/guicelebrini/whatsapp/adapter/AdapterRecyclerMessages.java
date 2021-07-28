package com.android.guicelebrini.whatsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.guicelebrini.whatsapp.R;
import com.android.guicelebrini.whatsapp.model.Message;

import java.util.List;

public class AdapterRecyclerMessages extends RecyclerView.Adapter<AdapterRecyclerMessages.MyViewHolder> {

    private List<Message> messagesList;

    public AdapterRecyclerMessages(List<Message> messagesList){
        this.messagesList = messagesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messages_list, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerMessages.MyViewHolder holder, int position) {
        Message message = messagesList.get(position);
        holder.set(message);

    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textMessage;

        public MyViewHolder(View itemView) {
            super(itemView);

            textMessage = itemView.findViewById(R.id.textMessage);
        }

        public void set(Message message){
            this.textMessage.setText(message.getMessageText());
        }
    }
}
