package me.games.httpexample.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by darshanz on 7/17/15.
 */
public class Content {

    @SerializedName("category_name")
    private String categoryName;

    private int id;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
