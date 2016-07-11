package com.slash.simplelife.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.slash.simplelife.base.BaseActivity;
import com.slash.simplelife.util.Once;

/**
 * Created by slash on 2016/4/21.
 */
public class WelcomeAct extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void initView() {
        new Once(this).show("guide", () -> {
            Intent intent=new Intent(this,Guide.class);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {

    }
}
