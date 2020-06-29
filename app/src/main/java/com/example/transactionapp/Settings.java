package com.example.transactionapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    private static String preferenceName = "com.example.transactionapp.SETTINGS";

    public static void initializePreferences(Context ctx) {
        SharedPreferences settings = ctx.getSharedPreferences(preferenceName, ctx.MODE_PRIVATE);
        if (!settings.contains("URL")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("URL","http://192.168.1.82");
            editor.commit();
        }
        if (!settings.contains("PORT")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("PORT",8080);
            editor.commit();
        }
    }

    public static String getURL(Context ctx) {
        SharedPreferences settings = ctx.getSharedPreferences(preferenceName, ctx.MODE_PRIVATE);
        return settings.getString("URL","");
    }

    public static int getPort(Context ctx) {
        SharedPreferences settings = ctx.getSharedPreferences(preferenceName, ctx.MODE_PRIVATE);
        return settings.getInt("PORT",0);
    }

    public static void setURL(Context ctx, String url) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(preferenceName,ctx.MODE_PRIVATE).edit();
        editor.putString("URL", url);
        editor.commit();
    }

    public static void setPort(Context ctx, int port) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(preferenceName,ctx.MODE_PRIVATE).edit();
        editor.putInt("PORT", port);
        editor.commit();
    }

}
