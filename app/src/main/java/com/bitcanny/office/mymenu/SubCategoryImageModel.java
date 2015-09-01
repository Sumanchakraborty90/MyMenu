package com.bitcanny.office.mymenu;

/**
 * Created by OFFICE on 01-09-2015.
 */
public class SubCategoryImageModel {

    String subcategoryimageurl;
    int subcategoryimageid;
    String subcategoryname;

    public SubCategoryImageModel(String subcategoryimageurl, String subcategoryname) {
        this.subcategoryimageurl = subcategoryimageurl;
        this.subcategoryname = subcategoryname;
    }

    public String getSubcategoryname() {
        return subcategoryname;
    }

    public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname;
    }

    public String getSubcategoryimageurl() {
        return subcategoryimageurl;
    }

    public void setSubcategoryimageurl(String subcategoryimageurl) {
        this.subcategoryimageurl = subcategoryimageurl;
    }
}
