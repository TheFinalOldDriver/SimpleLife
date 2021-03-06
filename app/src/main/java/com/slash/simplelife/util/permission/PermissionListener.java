package com.slash.simplelife.util.permission;

import java.util.List;

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> permissions);
}
