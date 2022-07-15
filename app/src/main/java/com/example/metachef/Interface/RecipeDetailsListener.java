package com.example.metachef.Interface;

import com.example.metachef.SearchRecipesResponse;

public interface RecipeDetailsListener {
    void didfetch(SearchRecipesResponse response, String message);

    void diderror(String message);
}
