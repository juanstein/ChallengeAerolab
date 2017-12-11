package com.example.challengeaerolab.controller;

import android.content.Context;

import com.example.challengeaerolab.dao.UserDAO;
import com.example.challengeaerolab.model.User;
import com.example.challengeaerolab.util.ResultListener;

/**
 * Created by Juan on 06/12/2017.
 */

public class UserController {
    private Context context;

    public UserController(Context context) {
        this.context = context;
    }

    public void getUser(final ResultListener<User> listener){
        UserDAO userDAO = new UserDAO(context);
        userDAO.getUser(new ResultListener<User>() {
            @Override
            public void finish(User resultado) {
                listener.finish(resultado);
            }
        });
    }
    public void postPoints(final ResultListener<String> listener, String points){
        UserDAO userDAO = new UserDAO(context);
        userDAO.postPoints(new ResultListener<String>() {
            @Override
            public void finish(String resultado) {
                listener.finish(resultado);
            }
        }, points);
    }
}
