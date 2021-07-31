package com.android.guicelebrini.whatsapp.model;

public class Chat {

    private String contactId;
    private String contactName;
    private String lastMessage;

    public Chat() {
    }

    public Chat(String contactId, String contactName, String lastMessage){
        this.setContactId(contactId);
        this.setContactName(contactName);
        this.setLastMessage(lastMessage);

    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
