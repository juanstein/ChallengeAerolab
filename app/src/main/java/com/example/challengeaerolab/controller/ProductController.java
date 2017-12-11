package com.example.challengeaerolab.controller;

import android.content.Context;

import com.example.challengeaerolab.dao.ProductDAO;
import com.example.challengeaerolab.model.Product;
import com.example.challengeaerolab.util.ResultListener;

import java.util.List;

/**
 * Created by Juan on 06/12/2017.
 */

public class ProductController {
    private Context context;

    public ProductController(Context context) {
        this.context = context;
    }

    public void getProductList(final ResultListener<List<Product>> listResultListener){
        ProductDAO productDAO = new ProductDAO(context);
        productDAO.getProductList(new ResultListener<List<Product>>() {
            @Override
            public void finish(List<Product> resultado) {
                listResultListener.finish(resultado);
            }
        });
    }

    public void reedemAProduct(final ResultListener<String> listener, String productID){
        ProductDAO productDAO = new ProductDAO(context);
        productDAO.reedemAProduct(new ResultListener<String>() {
            @Override
            public void finish(String resultado) {
                listener.finish(resultado);
            }
        }, productID);
    }
}
