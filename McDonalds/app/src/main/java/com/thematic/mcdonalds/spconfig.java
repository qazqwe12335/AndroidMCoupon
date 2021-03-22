package com.thematic.mcdonalds;

import android.content.Context;
import android.content.SharedPreferences;

public class spconfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public spconfig(Context applicationContext) {
        this.context = applicationContext;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.mc_donalds_app), Context.MODE_PRIVATE);
    }

    public void notice_apply(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.notice_apply), status);
        editor.commit();
    }

    public boolean apply_check() {
        boolean status = false;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.notice_apply), status);
        return status;
    }


}
