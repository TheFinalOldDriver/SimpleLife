package com.slash.simplelife.util;

import android.content.Context;

import com.slash.simplelife.base.Constant;

public class MemberUtil implements Constant{
    public static void saveMember(Context context, String nickname,String content) {
        PreferencesUtils.putString(context, NICKNAME, nickname);
        PreferencesUtils.putString(context, CONTENT, content);
//        PreferencesUtils.putString(context, HEADER_URL, header);
    }

    public static void clearMember(Context context) {
        PreferencesUtils.putString(context, NICKNAME, null);
        PreferencesUtils.putString(context, CONTENT, null);
        PreferencesUtils.putString(context, USER_NAME, null);
//        PreferencesUtils.putString(context, HEADER_URL, null);
    }

}
