package me.games.httpexample.model;

import java.util.ArrayList;

/**
 * Created by darshanz on 7/17/15.
 */
public class CategoryResponse {
    private ArrayList<Category> data;
    private String error ;

    public ArrayList<Category> getData() {
        return data;
    }

    public void setData(ArrayList<Category> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
