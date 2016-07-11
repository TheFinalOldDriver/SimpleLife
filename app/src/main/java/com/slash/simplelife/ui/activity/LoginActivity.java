package com.slash.simplelife.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.slash.simplelife.Model.UserModel;
import com.slash.simplelife.R;
import com.slash.simplelife.base.BaseActivity;
import com.slash.simplelife.util.PreferencesUtils;
import com.slash.simplelife.view.CustomVideoView;
import com.slash.simplelife.view.login.MaterialLoginView;
import com.slash.simplelife.view.login.MaterialLoginViewListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.videoview)
    CustomVideoView videoview;
    @Bind(R.id.login)
    MaterialLoginView login;
    private List<UserModel> list;
    private String login_user;
    private String login_pass;
    private String passRep;
    private String register_user;
    private String register_pass;

    @OnClick(R.id.back)
    public void Back() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        loadBackText();
        loadVideo();
        loadLoginAndRegister();
    }

    private void loadBackText() {
        YoYo.with(Techniques.BounceInLeft)
                .duration(3000)
                .playOn(findViewById(R.id.back));
    }

    private void loadLoginAndRegister() {
        login.setListener(new MaterialLoginViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                register_user = registerUser.getEditText().getText().toString().trim();
                if (register_user.isEmpty()) {
                    registerUser.setError("请输入账户");
                    return;
                }
                registerUser.setError("");

                register_pass = registerPass.getEditText().getText().toString().trim();
                if (register_pass.isEmpty()) {
                    registerPass.setError("请输入密码");
                    return;
                }
                registerPass.setError("");

                passRep = registerPassRep.getEditText().getText().toString().trim();
                if (!register_pass.equals(passRep)) {
                    registerPassRep.setError("两次密码不相同");
                    return;
                }
                registerPassRep.setError("");
                loadHttpRegister(register_user, register_pass);
            }

            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                login_user = loginUser.getEditText().getText().toString().trim();
                if (login_user.isEmpty()) {
                    loginUser.setError("请输入账户");
                    return;
                }
                loginUser.setError("");
                login_pass = loginPass.getEditText().getText().toString().trim();
                if (login_pass.isEmpty()) {
                    loginPass.setError("请输入密码");
                    return;
                }
                loginPass.setError("");
                loadHttpLogin(login_user, login_pass);
            }
        });
    }

    private void loadVideo() {
        //设置播放加载路径
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        //播放
        videoview.start();
        //循环播放
        videoview.setOnCompletionListener(mediaPlayer -> videoview.start());
    }

    private void loadHttpRegister(String username, String password) {
        UserModel item = new UserModel();
        item.setUsername(username);
        item.setPassword(password);
        item.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                PreferencesUtils.putString(LoginActivity.this, USER_NAME, register_user);
//                ToastUtils.show(LoginActivity.this, "注册成功");
                showToast("注册成功");
                loadHttpLogin(username, password);
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
//                ToastUtils.show(LoginActivity.this, "注册失败");
                showToast("注册失败");
            }
        });
    }

    public void loadHttpLogin(String username, String password) {
        BmobUser.loginByAccount(this, username, password, new LogInListener<UserModel>() {

            @Override
            public void done(UserModel user, BmobException e) {
                if (user != null) {
                    PreferencesUtils.putString(LoginActivity.this, USER_NAME, login_user);
                    PreferencesUtils.putBoolean(LoginActivity.this, IS_LOGIN, true);
//                    ToastUtils.show(LoginActivity.this, "登录成功");
                    showToast("登录成功");
                    finish();
                }else {
                    showToast("登录失败");
                }
            }
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
