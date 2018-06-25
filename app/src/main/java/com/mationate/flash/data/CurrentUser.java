package com.mationate.flash.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CurrentUser {

    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public String email()
    {
        return getCurrentUser().getEmail();
    }

    public boolean isLogged() {

        if (currentUser != null) {
            return true;
        } else {
            return false;
        }
        /*return currentUser != null;*/
        /*if (currentUser != null) {
            return true;
        }
        return false;*/
        /*boolean status = (currentUser != null) ? true : false;
        String situation = (currentUser != null) ? "logged" : "not logged";
        return status;*/
        /*boolean status = currentUser != null;
        return status;*/
    }
    public  String sanitizedEmail (String email){
        return email.replace("@","AT").replace(".","DOT");
    }

    public String uid() {
        return currentUser.getUid();
    }
}
