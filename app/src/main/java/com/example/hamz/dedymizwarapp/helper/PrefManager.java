package com.example.hamz.dedymizwarapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    private static final String PREF_NAME = "MATEMATRIKS";
    private static final String IS_LOGGED = "IsFirstTimeLaunch";
    private static final String Token = "token";
    private static final String Id = "id";
    private static final String Name = "name";
    private static final String Email = "email";

    int PRIVATE_MODE = 0;

    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setIsLogged(boolean isLogged)
    {
        editor.putBoolean(IS_LOGGED, isLogged);
        editor.commit();
    }

    public boolean getIsLogged()
    {
        return pref.getBoolean(IS_LOGGED, false);
    }

    public void setToken(String token)
    {
        editor.putString(Token, token);
        editor.commit();
    }

    public String getToken()
    {
        return pref.getString(Token, "");
    }

    public void setId(int id)
    {
        editor.putInt(Id, id);
        editor.commit();
    }

    public int getId()
    {
        return pref.getInt(Id, 0);
    }

    public String getName() {
        return pref.getString(Name, "");
    }

    public void setName(String name)
    {
        editor.putString(Name, name);
        editor.commit();
    }

    public String getEmail() {
        return pref.getString(Email, "");
    }

    public void setEmail(String email)
    {
        editor.putString(Email, email);
        editor.commit();
    }
}
