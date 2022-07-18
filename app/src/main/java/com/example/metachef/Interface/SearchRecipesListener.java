package com.example.metachef.Interface;

import com.example.metachef.SearchRecipesResponse;

public interface SearchRecipesListener {
    void didfetch(SearchRecipesResponse response, String message);

    void diderror(String message);
}
