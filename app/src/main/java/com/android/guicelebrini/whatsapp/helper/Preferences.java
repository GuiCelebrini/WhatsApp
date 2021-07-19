package com.android.guicelebrini.whatsapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferences {

    private Context context;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "whatsapp.preferences";
    private final int MODE = 0;

    private final String KEY_ID = "idLoggedUser";

    public Preferences(Context parameterContext){
        this.context = parameterContext;
        preferences = context.getSharedPreferences(FILE_NAME, MODE);
        editor = preferences.edit();
    }

    public void saveData(String idUser){
        editor.putString(KEY_ID, idUser);
        editor.commit();
    }

    public String getUserId(){
        return preferences.getString(KEY_ID, null);
    }
}
