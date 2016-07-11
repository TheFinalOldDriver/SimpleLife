package com.slash.beatytextlibrary.util;

import android.util.Log;

import com.slash.beatytextlibrary.BuildConfig;


public class HLog {
    public static void i(Object s){
        if(BuildConfig.DEBUG) {
            Log.i("HLog", s.toString());
        }
    }
}
