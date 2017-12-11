package com.example.challengeaerolab.dao;

import android.content.Context;
import android.os.AsyncTask;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.challengeaerolab.model.User;
import com.example.challengeaerolab.util.ResultListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Juan on 06/12/2017.
 */

public class UserDAO {

    private Context context;

    public UserDAO(Context context) {
        this.context = context;
    }


    public void postPoints(final ResultListener<String> listener, String points){

        AndroidNetworking.initialize(context);

        String json = "{  'amount': " + points + "}";

        JSONObject obj = null;

        try {
            obj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("https://aerolab-challenge.now.sh/user/points")
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


    public void getUser(final ResultListener<User> listener){
        AndroidNetworking.initialize(context);

        final Gson gson = new Gson();

        AndroidNetworking.get("https://aerolab-challenge.now.sh/user/me")
                .addHeaders("Accept", "application/json")
                .addHeaders("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YTIwODE4ZmYzNWEzYjAwNzk2N2FkNzIiLCJpYXQiOjE1MTIwNzk3NTl9.n4_9W867WTA9S01zIrhXHVOYFYVOs7ppbQnSo6j8e2U")
                .build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                User user = gson.fromJson(response, User.class);
                listener.finish(user);
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

}
