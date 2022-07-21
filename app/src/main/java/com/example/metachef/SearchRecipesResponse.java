package com.example.metachef;

import com.example.metachef.model.Result;

import java.util.ArrayList;

public class SearchRecipesResponse {
    public ArrayList<Result> results;
    public int offset;
    public int number;
    public int totalResults;
}
