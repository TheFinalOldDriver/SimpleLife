package com.slash.simplelife.data;

import com.slash.simplelife.Model.UserModel;

public class DataExchange {
    public interface GetUserInfo{
        void onGetUserInfo(UserModel info);
    }
}
