package com.example.metachef.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
//This class represents the data which the different food items would contain in them
public class Items{

    public int id;
    public String  title;
    public String image;
    public String summary;
    public double pricePerServing;
    public int numberInCart;

    public Items(int numberInCart){
        this.numberInCart = numberInCart;
    }

    public Items(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        image = jsonObject.getString("image");
        title = jsonObject.getString("title");
        summary = jsonObject.getString("summary");
        pricePerServing = jsonObject.getInt("pricePerServing");
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public Double getPricePerServing() {
        return pricePerServing;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setPricePerServing(double pricePerServing) {
        this.pricePerServing = pricePerServing;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
