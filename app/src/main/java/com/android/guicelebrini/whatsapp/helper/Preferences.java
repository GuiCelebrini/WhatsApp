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

    private final String KEY_NAME = "username";
    private final String KEY_PHONE_NUMBER = "phoneNumber";
    private final String KEY_TOKEN = "token";

    public Preferences(Context parameterContext){
        this.context = parameterContext;
        preferences = context.getSharedPreferences(FILE_NAME, MODE);
        editor = preferences.edit();
    }

    public void saveUserPreferences(String username, String phoneNumber, String token){
        editor.putString(KEY_NAME, username);
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public HashMap<String, String> getUserData(){
        HashMap<String, String> userData = new HashMap<>();

        userData.put(KEY_NAME, preferences.getString(KEY_NAME, null));
        userData.put(KEY_PHONE_NUMBER, preferences.getString(KEY_PHONE_NUMBER, null));
        userData.put(KEY_TOKEN, preferences.getString(KEY_TOKEN, null));

        return userData;
    }






}
