package com.android.guicelebrini.whatsapp.helper;

import android.content.Context;

public class Helper {

    public static void saveIdInPreferences(Context context, String email){
        Preferences preferences = new Preferences(context);
        String idLoggedUser = Base64Custom.encode(email);
        preferences.saveData(idLoggedUser);
    }
}
