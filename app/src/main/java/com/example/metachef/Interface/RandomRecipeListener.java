package com.example.metachef.Interface;

import com.example.metachef.RandomRecipesResponse;

public interface RandomRecipeListener {
    void didfetch(RandomRecipesResponse response, String message);

    void diderror(String message);
}
