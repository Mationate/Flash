package com.mationate.flash.presenter;

import com.mationate.flash.callback.LoginCallback;
import com.mationate.flash.data.CurrentUser;

public class AlreadyLogged {


    private LoginCallback callback;

    public AlreadyLogged(LoginCallback callback) {
        this.callback = callback;
    }

    public void alreadyLogged() {
        if (new CurrentUser().isLogged()) {
            callback.logged();
        } else {
            callback.signUp();
        }

    }

}
