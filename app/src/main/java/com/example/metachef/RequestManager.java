package com.example.metachef;

import android.content.Context;

import com.example.metachef.Interface.RandomRecipeListener;
import com.example.metachef.Interface.RecipeDetailsListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    final Context mContext;
    final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.spoonacular.com/").addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context mContext) {
        this.mContext = mContext;
    }

    public void getRandomRecipes(RandomRecipeListener listener, List<String> tags){
        RandomRecipesCall randomRecipesCall = retrofit.create(RandomRecipesCall.class);
        Call<RandomRecipesResponse> call = randomRecipesCall.callRandomRecipe("a0b47258ef634097812d0213ca6217ea", "20",tags);

        //enqueue to make call asynchronously
        call.enqueue(new Callback<RandomRecipesResponse>() {
            @Override
            public void onResponse(Call<RandomRecipesResponse> call, Response<RandomRecipesResponse> response) {
                if (!response.isSuccessful()){
                    listener.diderror(response.message());
                    return;
                }
                listener.didfetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipesResponse> call, Throwable t) {
                listener.diderror(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        RecipeDetailsCall RecipeDetailsCall = retrofit.create(RecipeDetailsCall.class);
        Call<ShowDetailActivity> call = RecipeDetailsCall.callRecipeDetails(id, "a0b47258ef634097812d0213ca6217ea");
        //enqueue to make call asynchronously
        call.enqueue(new Callback<ShowDetailActivity>() {
            @Override
            public void onResponse(Call<ShowDetailActivity> call, Response<ShowDetailActivity> response) {
                if (!response.isSuccessful()){
                    listener.diderror(response.message());
                    return;
                }
                listener.didfetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<ShowDetailActivity> call, Throwable t) {
                listener.diderror(t.getMessage());
            }
        });
    }

    private interface RandomRecipesCall{
//        Get Call
        @GET("recipes/random")
        Call<RandomRecipesResponse> callRandomRecipe(@Query("apiKey") String apiKey, @Query("number") String number, @Query("tags")List<String> tags);
    }

    private interface RecipeDetailsCall{
//        Get Call
        @GET("recipes/{id}/information")
        Call<ShowDetailActivity> callRecipeDetails(@Path("id") int id, @Query("apiKey") String apiKey);
    }

}
