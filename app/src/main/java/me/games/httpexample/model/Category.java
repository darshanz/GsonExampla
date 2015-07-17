package me.games.httpexample.model;

import com.google.gson.annotations.SerializedName;

import me.games.httpexample.model.Content;

/**
 * Created by darshanz on 7/17/15.
 */
public class Category {
    @SerializedName("Category")
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
