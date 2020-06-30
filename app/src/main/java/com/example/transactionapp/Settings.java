package com.example.transactionapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    /**
     * A string unique to this app used as an identifier for getting and setting saved preferences
     */
    private final static String preferenceName = "com.example.transactionapp.SETTINGS";

    /**
     * If the settings for URL and PORT do not exist yet, create them with the default values
     * @param ctx - the application context
     */
    public static void initializeSettings(Context ctx) {
        SharedPreferences settings = ctx.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        if (!settings.contains("URL")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("URL","http://192.168.1.82");
            editor.apply();
        }
        if (!settings.contains("PORT")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("PORT",8080);
            editor.apply();
        }
    }

    /**
     * Gets the root of the URL used to access the RESTful API from Shared Preferences
     * @param ctx - the application context
     * @return the URL as a string
     */
    public static String getURL(Context ctx) {
        SharedPreferences settings = ctx.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        return settings.getString("URL","");
    }

    /**
     * Gets the port of the URL used to access the RESTful API from Shared Preferences
     * @param ctx - the application context
     * @return the port as an integer
     */
    public static int getPort(Context ctx) {
        SharedPreferences settings = ctx.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        return settings.getInt("PORT",0);
    }

    /**
     * Sets the root of the URL used to access the RESTful API within Shared Preferences
     * @param ctx - the application context
     * @param url - the root url of the RESTful API as a string
     */
    public static void setURL(Context ctx, String url) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(preferenceName, Context.MODE_PRIVATE).edit();
        editor.putString("URL", url);
        editor.apply();
    }

    /**
     * Sets the port of the URL used to access the RESTful API within Shared Preferences
     * @param ctx - the application context
     * @param port - the port of the RESTful API as an integer
     */
    public static void setPort(Context ctx, int port) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(preferenceName, Context.MODE_PRIVATE).edit();
        editor.putInt("PORT", port);
        editor.apply();
    }

}
