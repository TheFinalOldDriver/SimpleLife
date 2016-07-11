package com.slash.simplelife.base;

import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.slash.simplelife.base.Constant;

/**
 * Created by slash on 2016/5/3.
 */
public class BaseFragment extends Fragment implements Constant{
    public int mScreenHeight = 0;// 屏幕高度
    public int mScreenWidth = 0;// 屏幕宽度
    public int mitemHeight = 0;// view的高度

    public void doMeasure(){
        WindowManager windowManager = (WindowManager) getActivity()
                .getSystemService(getActivity().WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        mScreenHeight = metrics.heightPixels;
        mScreenWidth = metrics.widthPixels;
        mitemHeight = mScreenWidth * 2 / 3;
    }
}
