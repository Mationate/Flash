package com.mationate.flash.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.mationate.flash.R;
import com.mationate.flash.callback.LoginCallback;
import com.mationate.flash.presenter.AlreadyLogged;
import com.mationate.flash.views.main.MainActivity;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements LoginCallback {

    private static final int RC_SIGN_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new AlreadyLogged(this).alreadyLogged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RC_SIGN_IN == requestCode) {
            if (RESULT_OK == resultCode) {
                logged();
            } else {
                signUp();
            }
        }

    }

    @Override
    public void signUp() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.FacebookBuilder().build()/*,
                                new AuthUI.IdpConfig.PhoneBuilder().build(),
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.TwitterBuilder().build()*/))
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.mipmap.logo)
                        .build(),
                RC_SIGN_IN);

    }

    @Override
    public void logged() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
