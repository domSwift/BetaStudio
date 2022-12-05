package com.example.betastudio.general;

import android.content.Context;
import android.content.SharedPreferences;

public class Persistent {

    private SharedPreferences sharedPreferences;
    private static String PREF_NAME = "prefs";

    public Persistent() {
        // Blank
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getUsername(Context context) {
        return getPrefs(context).getString("username_key", "default_username");
    }

    public static void setUsername(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("username_key", input);
        editor.commit();
    }

    public static String getMedID(Context context) {
        return getPrefs(context).getString("medid_key", "default_medid");
    }

    public static void setMedId(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("medid_key", input);
        editor.commit();
    }

    public static String getMemPatient(Context context) {
        return getPrefs(context).getString("memPatient_key", "default_memPatient");
    }

    public static void setMemPatient(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("memPatient_key", input);
        editor.commit();
    }

    public static String getMemRequest(Context context) {
        return getPrefs(context).getString("memRequest_key", "default_memRequest");
    }

    public static void setMemRequest(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("memRequest_key", input);
        editor.commit();
    }

}
