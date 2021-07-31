package com.android.guicelebrini.whatsapp.helper;

import android.content.Context;
import android.util.Log;

public class Helper {

    public static void saveDataInPreferences(Context context, String email, String name){
        Preferences preferences = new Preferences(context);
        String idLoggedUser = Base64Custom.encode(email);
        Log.i("Resultado", name + " Helper");
        preferences.saveData(idLoggedUser, name);
    }
}
