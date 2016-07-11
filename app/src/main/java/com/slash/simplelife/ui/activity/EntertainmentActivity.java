package com.slash.simplelife.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.slash.simplelife.Model.MovieTop250Model;
import com.slash.simplelife.R;
import com.slash.simplelife.base.BaseActivity;
import com.slash.simplelife.ui.fragment.MovieFragment;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;

public class EntertainmentActivity extends BaseActivity {

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 111:
                    Bundle b = msg.getData();
                    Toast.makeText(EntertainmentActivity.this, b.getString("name") + "", Toast.LENGTH_LONG).show();
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        replaceFragment(R.id.frame_container, new MovieFragment());
    }

    @Override
    protected void initData() {

    }

    private void getInfo() {
        OkHttpUtils
                .get()
                .url(MOVIE_TOP250)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("aaaa", response + "");
                        String jsonString = response;
                        MovieTop250Model movieTop250Model = gson.fromJson(jsonString, MovieTop250Model.class);
//                        Log.d("ddd", movieTop250Model.getTitle() + "");
//                        Message msg = handler.obtainMessage();
//                        Bundle b = new Bundle();
//                        b.putString("name", movieTop250Model.getTitle() + "");
//                        msg.setData(b);
//                        msg.what = 111;
//                        msg.sendToTarget();
                    }
                });
    }
}
