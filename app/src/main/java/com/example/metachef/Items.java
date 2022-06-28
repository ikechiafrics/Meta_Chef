package com.example.metachef;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;
import org.parceler.ParcelClass;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Items{

    public String  title;
    public String image;
    public double price;
//    public String price;

    public Items(){}


    public Items(JSONObject jsonObject) throws JSONException {
        image = jsonObject.getString("image");
        title = jsonObject.getString("title");
        price = jsonObject.getDouble("pricePerServing");
    }
    public static List<Items> fromJsonArray(JSONArray itemsJsonArray) throws JSONException {
        List<Items> allItems = new ArrayList<>();
        for (int i = 0; i < itemsJsonArray.length(); i++) {
            if(new Items(itemsJsonArray.getJSONObject(i)).getImage() != null){
                allItems.add(new Items(itemsJsonArray.getJSONObject(i)));
            }
        }
        return allItems;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }
}
