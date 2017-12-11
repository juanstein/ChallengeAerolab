package com.example.challengeaerolab.model;

import android.support.annotation.NonNull;

/**
 * Created by Juan on 06/12/2017.
 */

public class Product implements Comparable<Product>{

    private String _id;
    private String name;
    private Integer cost;
    private String category;
    private Img img;

    public Product() {
    }

    public Product(String _id, String name, Integer cost, String category, Img img) {
        this._id = _id;
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.img = img;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Img getImg() {
        return img;
    }

    public void setImg(Img img) {
        this.img = img;
    }

    @Override
    public int compareTo(@NonNull Product product) {
        return cost.compareTo(product.getCost());
    }
}
