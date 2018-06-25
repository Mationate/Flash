package com.mationate.flash.views.main.drawer;

import android.content.Context;
import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mationate.flash.data.CurrentUser;
import com.mationate.flash.data.Nodes;
import com.mationate.flash.models.LocalUser;

public class UploadPhoto {

    private Context context;

    public UploadPhoto(Context context) {
        this.context = context;
    }

    public void toFirebase(String path){

        final CurrentUser currentUser = new CurrentUser();
        String folder = currentUser.sanitizedEmail(currentUser.email() + "/");
        String photoName = "avatar.jpeg";
        String baseUrl = "gs://flash-61a26.appspot.com/avatars/";
        String refUrl = baseUrl + folder + photoName;
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(refUrl);
        storageReference.putFile(Uri.parse(path)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
           String[] fullUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString().split("&token");
            String url = fullUrl[0];
                LocalUser user = new LocalUser();
                user.setEmail(currentUser.email());
                user.setName(currentUser.getCurrentUser().getDisplayName());
                user.setPhoto(url);
                user.setUid(currentUser.uid());
                String key = currentUser.sanitizedEmail(currentUser.email());
                //new Nodes().users().child(key).setValue(user);
                new Nodes().user(key).setValue(user);
            }
        });

    }
}
