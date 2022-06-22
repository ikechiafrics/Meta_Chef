package com.example.metachef;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Items {
    String  title;
    String image;

    public Items(JSONObject jsonObject) throws JSONException {
        image = jsonObject.getString("image");
        title = jsonObject.getString("title");
    }
    public static List<Items> fromJsonArray(JSONArray itemsJsonArray) throws JSONException {
        List<Items> items = new ArrayList<>();
        for (int i = 0; i < itemsJsonArray.length(); i++) {
            items.add(new Items(itemsJsonArray.getJSONObject(i)));
        }
        return items;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
