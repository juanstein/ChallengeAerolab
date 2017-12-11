package com.example.challengeaerolab.model;

/**
 * Created by Juan on 06/12/2017.
 */

public class ReedemHistory {

    private String productId;
    private String name;
    private Integer cost;
    private String category;
    private String _id;
    private String createDate;
    private Img img;

    public ReedemHistory() {
    }

    public ReedemHistory(String productId, String name, Integer cost, String category, String _id, String createDate, Img img) {
        this.productId = productId;
        this.name = name;
        this.cost = cost;
        this.category = category;
        this._id = _id;
        this.createDate = createDate;
        this.img = img;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Img getImg() {
        return img;
    }

    public void setImg(Img img) {
        this.img = img;
    }
}
