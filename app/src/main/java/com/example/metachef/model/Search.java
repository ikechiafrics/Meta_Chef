package com.example.metachef.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//This Class represents the variables which the search view would contain
public class Search {
    String title;
    String image;
    String description;

    public Search() {

    }
    public Search(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("title");
        image = jsonObject.getString("image");
        description = jsonObject.getString("summary");
    }

    public  static List<Search> fromJsonArray(JSONArray searchJsonArray) throws JSONException {
        List<Search> searches = new ArrayList<>();
        for(int i = 0; i < searchJsonArray.length(); i++){
            searches.add(new Search(searchJsonArray.getJSONObject(i)));
        }
        return searches;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
