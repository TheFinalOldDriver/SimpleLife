package com.slash.simplelife.base;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slash on 2016/4/21.
 */
public class SimpleApplication extends Application {
    public static List<Activity> listAcitivity = new ArrayList<Activity>();
}
