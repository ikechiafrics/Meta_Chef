package com.example.metachef.model;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

//This class contains data which calls the use's information from the database
@ParseClassName("Cart")
public class Cart extends ParseObject {
    public static final String KEY_ID = "item";
    public static final String KEY_TITLE = "title";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_SIZE = "size";
    public static final String KEY_PRICE = "price";

    public int getItem() {return getInt(KEY_ID);}

    public void setItem(int item){
        put(KEY_ID, item);
    }

    public String getTitle() {return getString(KEY_TITLE);}

    public void setTitle(String title){
        put(KEY_TITLE, title);
    }

    public Double getPrice() { return getDouble(KEY_PRICE);}

    public void setPrice(Double price){
        put(KEY_PRICE, price);
    }

    public ParseFile getImage() {return getParseFile(KEY_IMAGE);
    }

    public int getSize() {return getInt(KEY_SIZE);}

    public void setSize(int size){
        put(KEY_SIZE, size);
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
