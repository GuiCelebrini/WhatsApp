package com.android.guicelebrini.whatsapp.model;

import com.android.guicelebrini.whatsapp.config.FirebaseConfig;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;

    public User(){}

    public User(String name, String email, String password){
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
    }

    public void saveInFirebase(){
        DatabaseReference userReference = FirebaseConfig.getFirebaseReference();

        userReference.child("users").child(this.getId()).setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
