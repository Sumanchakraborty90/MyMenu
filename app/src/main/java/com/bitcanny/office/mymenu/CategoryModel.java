package com.bitcanny.office.mymenu;

/**
 * Created by OFFICE on 03-08-2015.
 */
public class CategoryModel {

    int categoryId;
    String categoryImageURL;
    String categoryName;


    public CategoryModel() {
    }

    public CategoryModel( String categoryImageURL, String categoryName) {
        //this.categoryId = categoryId;
        this.categoryImageURL = categoryImageURL;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryImageURL() {
        return categoryImageURL;
    }

    public void setCategoryImageURL(String categoryImageURL) {
        this.categoryImageURL = categoryImageURL;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
