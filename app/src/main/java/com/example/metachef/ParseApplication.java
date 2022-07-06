package com.example.metachef;

import android.app.Application;

import com.example.metachef.model.Cart;
import com.parse.Parse;
import com.parse.ParseObject;
//this class links the android studio to the parse database

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Cart.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("fC6abkFFT8zsmhxtKdOHMjx2XwCRvI6NxfmbwI5B")
                .clientKey("NSc2n5WgQWSHxDGbHBgzDnrzeJyuOGGFV6dfLuSc")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
