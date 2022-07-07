package com.example.metachef.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

//This class contains data which calls the use's information from the database
@ParseClassName("_User")
public class User extends ParseUser {
    public static final String KEY_IMAGE = "profile_picture";
    private static final String KEY_USER = "username";
    public static final String KEY_CART = "cart";


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

    public List<String> getCart() {
        List<String> cart = getList(KEY_CART);
        if (cart != null) {
            return getList(KEY_CART);
        } else {
            return new ArrayList<>();
        }
    }
}
