package com.example.metachef.Interface;

import com.example.metachef.RecipeDetailsResponse;
import com.example.metachef.ShowDetailActivity;

public interface RecipeDetailsListener {
    void didfetch(RecipeDetailsResponse response, String message);

    void diderror(String message);
}
