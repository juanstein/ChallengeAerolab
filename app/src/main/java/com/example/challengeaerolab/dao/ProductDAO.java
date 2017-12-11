package com.example.challengeaerolab.dao;

import android.content.Context;
import android.os.AsyncTask;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.challengeaerolab.model.Product;
import com.example.challengeaerolab.model.User;
import com.example.challengeaerolab.util.ResultListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Juan on 06/12/2017.
 */

public class ProductDAO {
    private Context context;

    public ProductDAO(Context context) {
        this.context = context;
    }


    public void getProductList(final ResultListener<List<Product>> listener){

        AndroidNetworking.initialize(context);

        final Gson gson = new Gson();
        final Type listType = new TypeToken<ArrayList<Product>>(){}.getType();

        AndroidNetworking.get("https://aerolab-challenge.now.sh/products")
                .addHeaders("Accept", "application/json")
                .addHeaders("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YTIwODE4ZmYzNWEzYjAwNzk2N2FkNzIiLCJpYXQiOjE1MTIwNzk3NTl9.n4_9W867WTA9S01zIrhXHVOYFYVOs7ppbQnSo6j8e2U")
                .build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                List<Product> productList = gson.fromJson(response, listType);
                listener.finish(productList);
            }

            @Override
            public void onError(ANError anError) {

            }
        });

    }

    public void reedemAProduct(final ResultListener<String> listener, String productID){

        AndroidNetworking.initialize(context);

        String json = "{  'productId': " + productID + "}";

        JSONObject obj = null;

        try {
            obj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        AndroidNetworking.post("https://aerolab-challenge.now.sh/redeem")
                .addHeaders("Accept", "application/json")
                .addHeaders("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YTIwODE4ZmYzNWEzYjAwNzk2N2FkNzIiLCJpYXQiOjE1MTIwNzk3NTl9.n4_9W867WTA9S01zIrhXHVOYFYVOs7ppbQnSo6j8e2U")
                .addJSONObjectBody(obj)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        listener.finish(response);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}
