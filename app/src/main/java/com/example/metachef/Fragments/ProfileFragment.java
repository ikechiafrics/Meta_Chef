package com.example.metachef.Fragments;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.FavouritesActivity;
import com.example.metachef.LoginActivity;
import com.example.metachef.R;
import com.example.metachef.model.User;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.File;

//This class represents the profile page
public class ProfileFragment extends Fragment {
    public static final String KEY_IMAGE = "profile_picture";
    public final User user = (User) User.getCurrentUser();
    protected File photoFile;
    private ImageView ivProfileImg;
    private EditText updatePassword;
    private Dialog dialog;
    String newPassword;
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button btnOkay = dialog.findViewById(R.id.btn_Okay);
        Button btnCancel = dialog.findViewById(R.id.btn_Cancel);
        Button btnFav = view.findViewById(R.id.btnFav);
        Button btnUpdatePassword = view.findViewById(R.id.btnUpdatePassword);
        ImageView btnLogout = view.findViewById(R.id.BtnLogout);
        ivProfileImg = view.findViewById(R.id.ivProfileImg);
        updatePassword = dialog.findViewById(R.id.etUpdatePassword);
        ParseUser user = ParseUser.getCurrentUser();



//        set profile picture
        ivProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });

        user.fetchInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                displayInfo();
            }
        });

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Favourites();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Cancelled", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        String photoFileName = "photo.jpg";
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.example.fileprovider.metachef", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    private File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "myTag");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d("myTag", "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }

    private void displayInfo() {
        int roundingRadius = 200;
        ParseUser user = ParseUser.getCurrentUser();
        ParseFile profilePhoto = user.getParseFile(KEY_IMAGE);
        if (profilePhoto != null) {
            Glide.with(getContext()).load(profilePhoto.getUrl()).transform(new RoundedCorners(roundingRadius)).into(ivProfileImg);
        } else {
            Glide.with(getContext()).load(R.drawable.profile_pic).transform(new RoundedCorners(roundingRadius)).into(ivProfileImg);
            Toast.makeText(getContext(), "profile photo does not exist for " + user.getUsername(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                Glide.with(getContext()).load(takenImage).circleCrop().into(ivProfileImg);
//                profileImage.setImageBitmap(takenImage);
                user.setImage(new ParseFile(photoFile));
                user.saveInBackground();
            } else { // Result was a failure
                Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void Favourites() {
        Intent intent = new Intent(getContext(), FavouritesActivity.class);
        startActivity(intent);
    }

    private void updatePassword(){
        if(user != null){
            newPassword = updatePassword.getText().toString();
            user.setPassword(newPassword);

            user.saveInBackground(e -> {
                if(e == null) {
                    Toast.makeText(getContext(), "Successfully Updated password", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
