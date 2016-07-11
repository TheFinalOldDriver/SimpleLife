package com.slash.simplelife.view.login;

import android.support.design.widget.TextInputLayout;

public interface MaterialLoginViewListener {
    void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep);

    void onLogin(TextInputLayout loginUser, TextInputLayout loginPass);
}
