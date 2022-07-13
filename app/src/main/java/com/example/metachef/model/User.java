package com.example.metachef.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

//This class contains data which calls the use's information from the database
@ParseClassName("_User")
public class User extends ParseUser {
    public static final String KEY_IMAGE = "profile_picture";
    public static final String KEY_USER = "username";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_CART = "cart";
    public static final String KEY_EMAIL = "email";




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

    public ParseUser getLastname() {
        return getParseUser(KEY_LASTNAME);
    }

    public void setLastname(String lastname){
        put(KEY_LASTNAME, lastname);
    }

    public ParseUser getFirstname() {
        return getParseUser(KEY_FIRSTNAME);
    }

    public void setFirstname(String firstname){
        put(KEY_FIRSTNAME, firstname);
    }

    public String getEmail() {
        return getString(KEY_EMAIL);
    }

    public void setEmail(String email){
        put(KEY_EMAIL, email);
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
