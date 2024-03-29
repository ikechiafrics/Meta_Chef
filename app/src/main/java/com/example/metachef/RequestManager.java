package com.example.metachef;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.metachef.Interface.QuickAnswerListener;
import com.example.metachef.Interface.RandomRecipeListener;
import com.example.metachef.Interface.SearchRecipesListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    final Context mContext;
    final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.spoonacular.com/").addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context mContext) {
        this.mContext = mContext;
    }

    public void getRandomRecipes(RandomRecipeListener listener, List<String> tags){
        RandomRecipesCall randomRecipesCall = retrofit.create(RandomRecipesCall.class);
        Call<RandomRecipesResponse> call = randomRecipesCall.callRandomRecipe(mContext.getString(R.string.ApiKey), "30",tags);

        //enqueue to make call asynchronously
        call.enqueue(new Callback<RandomRecipesResponse>() {
            @Override
            public void onResponse(@NonNull Call<RandomRecipesResponse> call, @NonNull Response<RandomRecipesResponse> response) {
                if (!response.isSuccessful()){
                    listener.diderror(response.message());
                    return;
                }
                listener.didfetch(response.body(), response.message());
            }

            @Override
            public void onFailure(@NonNull Call<RandomRecipesResponse> call, @NonNull Throwable t) {
                listener.diderror(t.getMessage());
            }
        });
    }

    public void getSearchRecipes(SearchRecipesListener listener, String query){
        SearchRecipesCall searchRecipesCall = retrofit.create(SearchRecipesCall.class);
        Call<SearchRecipesResponse> call = searchRecipesCall.callSearchRecipes(query, null, null,null, null, mContext.getString(R.string.ApiKey));
        //enqueue to make call asynchronously
        call.enqueue(new Callback<SearchRecipesResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchRecipesResponse> call, @NonNull Response<SearchRecipesResponse> response) {
                if (!response.isSuccessful()){
                    listener.diderror(response.message());
                    return;
                }
                listener.didfetch(response.body(), response.message());
            }

            @Override
            public void onFailure(@NonNull Call<SearchRecipesResponse> call, @NonNull Throwable t) {
                listener.diderror(t.getMessage());
            }
        });
    }

    public void getSearchRecipes(SearchRecipesListener listener, String query, List<String> intolerances, List<String> sort, List<String> sortDirection, String number){
        SearchRecipesCall searchRecipesCall = retrofit.create(SearchRecipesCall.class);
        Call<SearchRecipesResponse> call = searchRecipesCall.callSearchRecipes(query, intolerances,sort, sortDirection, number, mContext.getString(R.string.ApiKey));
        //enqueue to make call asynchronously
        call.enqueue(new Callback<SearchRecipesResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchRecipesResponse> call, @NonNull Response<SearchRecipesResponse> response) {
                if (!response.isSuccessful()){
                    listener.diderror(response.message());
                    return;
                }
                listener.didfetch(response.body(), response.message());
            }

            @Override
            public void onFailure(@NonNull Call<SearchRecipesResponse> call, @NonNull Throwable t) {
                listener.diderror(t.getMessage());
            }
        });
    }

    public void getQuickAnswer(QuickAnswerListener listener, String q){
        QuickAnswerCall quickAnswerCall = retrofit.create(QuickAnswerCall.class);
        Call<QuickAnswerResponse> call = quickAnswerCall.callQuickAnswer(q, mContext.getString(R.string.ApiKey));
        //enqueue to make call asynchronously
        call.enqueue(new Callback<QuickAnswerResponse>() {
            @Override
            public void onResponse(@NonNull Call<QuickAnswerResponse> call, @NonNull Response<QuickAnswerResponse> response) {
                if (!response.isSuccessful()){
                    listener.diderror(response.message());
                    return;
                }
                listener.didfetch(response.body(), response.message());
            }

            @Override
            public void onFailure(@NonNull Call<QuickAnswerResponse> call, @NonNull Throwable t) {
                listener.diderror(t.getMessage());
            }
        });
    }

    private interface RandomRecipesCall{
//        Get Call
        @GET("recipes/random")
        Call<RandomRecipesResponse> callRandomRecipe(@Query("apiKey") String apiKey, @Query("number") String number, @Query("tags")List<String> tags);
    }

    private interface SearchRecipesCall{
//        Get Call
        @GET("recipes/complexSearch")
        Call<SearchRecipesResponse> callSearchRecipes(@Query("query") String query,@Query("intolerances") List<String> intolerances, @Query("sort") List<String> sort, @Query("sortDirection") List<String> sortDirection, @Query("number") String number, @Query("apiKey") String apiKey);
    }

    private interface QuickAnswerCall{
//        Get call
        @GET("recipes/quickAnswer")
        Call<QuickAnswerResponse> callQuickAnswer(@Query("q") String q, @Query("apiKey") String apiKey);
    }
}
