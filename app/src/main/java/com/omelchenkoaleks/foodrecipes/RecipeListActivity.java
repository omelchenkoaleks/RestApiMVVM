package com.omelchenkoaleks.foodrecipes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.DialogTitle;

import com.omelchenkoaleks.foodrecipes.models.Recipe;
import com.omelchenkoaleks.foodrecipes.requests.RecipeApi;
import com.omelchenkoaleks.foodrecipes.requests.ServiceGenerator;
import com.omelchenkoaleks.foodrecipes.requests.responses.RecipeResponse;
import com.omelchenkoaleks.foodrecipes.requests.responses.RecipeSearchResponse;
import com.omelchenkoaleks.foodrecipes.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {
    private static final String TAG = "RecipeListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        // TESTS
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // работы Retrofit
                testRetrofitRequest();

                // метода showProgressBar
//                if (mProgressBar.getVisibility() == View.VISIBLE) {
//                    showProgressBar(false);
//                } else {
//                    showProgressBar(true);
//                }
            }
        });
    }

//     Test ответа RecipeSearchResponse
//    private void testRetrofitRequest() {
//        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
//
//        Call<RecipeSearchResponse> responseCall = recipeApi
//                .searchRecipe(
//                        Constants.API_KEY,
//                        "chicken breast",
//                        "1"
//                );
//
//        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
//            @Override
//            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
//                Log.d(TAG, "onResponse: server response: " + response.toString());
//                if (response.code() == 200) {
//                    Log.d(TAG, "onResponse: " + response.toString());
//                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
//                    for (Recipe recipe : recipes) {
//                        Log.e(TAG, "onResponse: " + recipe.getTitle());
//                    }
//                } else {
//                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
//
//            }
//        });
//    }

    //     Test ответа RecipeResponse
    private void testRetrofitRequest() {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();

        Call<RecipeResponse> responseCall = recipeApi
                .getRecipe(
                        Constants.API_KEY,
                        "35382"
                );

        responseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.d(TAG, "onResponse: server response: " + response.toString());
                if (response.code() == 200) {
                    Recipe recipe = response.body().getRecipe();
                    Log.d(TAG, "onResponse: " + recipe.toString());
                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });

    }

}
