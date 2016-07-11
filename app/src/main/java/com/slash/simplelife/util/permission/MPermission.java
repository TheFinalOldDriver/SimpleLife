package com.slash.simplelife.util.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class MPermission {

    private static MPermission mInstance;
    private AcpManager mAcpManager;

    public static MPermission getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MPermission(context);
        }
        return mInstance;
    }

    private MPermission(Context context) {
        mAcpManager = new AcpManager(context.getApplicationContext());
    }

    public void request(AcpOptions options, AcpListener acpListener) {
        if (options == null) new RuntimeException("AcpOptions is null...");
        if (acpListener == null) new RuntimeException("AcpListener is null...");
        mAcpManager.request(options, acpListener);
    }

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        mAcpManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAcpManager.onActivityResult(requestCode, resultCode, data);
    }

    void requestPermissions(Activity activity) {
        mAcpManager.requestPermissions(activity);
    }
}
