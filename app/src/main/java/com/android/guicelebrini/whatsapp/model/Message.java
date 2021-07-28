package com.android.guicelebrini.whatsapp.model;

public class Message {
    private String idUser;
    private String messageText;
    private int isFromLoggedUser; // if yes, the value equals "1", if not, it equals "0"

    public Message(){

    }

    public Message(String idUser, String messageText){
        this.setIdUser(idUser);
        this.setMessageText(messageText);
    }

    public Message(String idUser, String messageText, int isFromLoggedUser){
        this.setIdUser(idUser);
        this.setMessageText(messageText);
        this.setIsFromLoggedUser(isFromLoggedUser);
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

    public int getIsFromLoggedUser() {
        return isFromLoggedUser;
    }

    public void setIsFromLoggedUser(int isFromLoggedUser) {
        this.isFromLoggedUser = isFromLoggedUser;
    }
}
