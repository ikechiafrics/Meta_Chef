package com.example.metachef.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("Food")
public class Food extends ParseObject{
    public static final String KEY_USER = "user";
    public static final String KEY_LIKED_BY = "liked_post";

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }

    public List<String> getLikedBy() {
        List<String> likeBy = getList(KEY_LIKED_BY);
        if(likeBy == null) {
            likeBy = new ArrayList<>();
        }
        return likeBy;
    }

    public void setLikedBy(List<String> likedBy)
    {
        put(KEY_LIKED_BY, likedBy);
    }
}
