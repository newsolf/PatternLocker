package com.newolf.patternlocker.utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * ================================================
 * @author : NeWolf
 * @version : 1.0
 * date :  2018/6/29
 * desc:SharedPreferencesUtil
 * history:
 * ================================================
 */

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil instance;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefer;

    public SharedPreferencesUtil(Application application) {
        this.prefer = PreferenceManager.getDefaultSharedPreferences(application);
        this.editor = this.prefer.edit();
    }

    public static SharedPreferencesUtil getInstance(Application application) {
        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtil(application);
                }
            }
        }

        return instance;
    }

    public void saveString(String name, String data) {
        this.editor.putString(name, data);
        this.editor.commit();
    }

    public String getString(String name) {
        return this.prefer.getString(name, null);
    }
}
