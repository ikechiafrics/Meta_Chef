package com.example.metachef;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("fC6abkFFT8zsmhxtKdOHMjx2XwCRvI6NxfmbwI5B")
                .clientKey("NSc2n5WgQWSHxDGbHBgzDnrzeJyuOGGFV6dfLuSc")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
