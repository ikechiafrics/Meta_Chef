package com.example.metachef;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("_User")
public class User extends ParseObject {
    public static final String KEY_IMAGE = "profile_picture";
    public static final String KEY_USER = "username";


    public ParseFile getPicture() {return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parsefile){
        put(KEY_IMAGE, parsefile);
    }
    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }
}
