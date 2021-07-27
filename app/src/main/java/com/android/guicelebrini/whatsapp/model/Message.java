package com.android.guicelebrini.whatsapp.model;

public class Message {
    private String idUser;
    private String messageText;

    public Message(){

    }

    public Message(String idUser, String messageText){
        this.setIdUser(idUser);
        this.setMessageText(messageText);
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
