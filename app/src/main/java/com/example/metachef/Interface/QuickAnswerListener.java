package com.example.metachef.Interface;

import com.example.metachef.QuickAnswerResponse;
import com.example.metachef.RandomRecipesResponse;

public interface QuickAnswerListener {
    void didfetch(QuickAnswerResponse response, String message);

    void diderror(String message);
}
