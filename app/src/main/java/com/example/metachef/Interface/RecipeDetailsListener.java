package com.example.metachef.Interface;

import com.example.metachef.ShowDetailActivity;

public interface RecipeDetailsListener {
    void didfetch(ShowDetailActivity response, String message);

    void diderror(String message);
}
