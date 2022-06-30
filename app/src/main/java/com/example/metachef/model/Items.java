package com.example.metachef.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
//This class represents the data which the different food items would contain in them
public class Items{

    public String  title;
    public String image;
    public int num;
    public int numberInCart;

    public Items(int numberInCart){
        this.numberInCart = numberInCart;
    }

    public Items(JSONObject jsonObject) throws JSONException {
        image = jsonObject.getString("image");
        title = jsonObject.getString("title");
        num = jsonObject.getInt("readyInMinutes");

    }

    public Items() {

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

    public int getNum() {
        return num;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
