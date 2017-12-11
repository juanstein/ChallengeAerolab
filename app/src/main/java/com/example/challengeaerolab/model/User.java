package com.example.challengeaerolab.model;

import java.util.List;

/**
 * Created by Juan on 06/12/2017.
 */

public class User {

    private String _id;
    private String name;
    private Integer points;
    private List<ReedemHistory> reedemHistory;
    private String createDate;

    public User() {
    }

    public User(String _id, String name, Integer points, List<ReedemHistory> reedemHistory, String createDate) {
        this._id = _id;
        this.name = name;
        this.points = points;
        this.reedemHistory = reedemHistory;
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<ReedemHistory> getReedemHistory() {
        return reedemHistory;
    }

    public void setReedemHistory(List<ReedemHistory> reedemHistory) {
        this.reedemHistory = reedemHistory;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
