package com.android.guicelebrini.whatsapp.config;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FirebaseConfig {

    private static DatabaseReference reference;

    public static DatabaseReference getFirebaseReference(){
        if (reference == null) {
            reference = FirebaseDatabase.getInstance().getReference();
        }

        return reference;
    }



}
