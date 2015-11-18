package com.bitcanny.office.mymenu;

/**
 * Created by OFFICE on 03-08-2015.
 */
public class MenuInfoModel {

    int menu_id;
    String avg_rating;
    String MenuItemDesc;
    String MenuItemID;
    String MenuItemImageURL;
    String SelectedItem;
    String MenuItemIsChefRecommend;
    String MenuItemName;
    String MenuItemPrice;
    String TagName;
    String category_name;

    public MenuInfoModel() {
    }


    public MenuInfoModel(String category_name, String avg_rating, String menuItemDesc,String menuItemID,  String  menuItemImageURL,String selectedItem, String menuItemIsChefRecommend, String menuItemName, String menuItemPrice, String tagName) {
        this.category_name = category_name;
        this.avg_rating = avg_rating;
        MenuItemDesc = menuItemDesc;
        MenuItemID = menuItemID;
        MenuItemImageURL = menuItemImageURL;
        SelectedItem = selectedItem;
        MenuItemIsChefRecommend = menuItemIsChefRecommend;
        MenuItemName = menuItemName;
        MenuItemPrice = menuItemPrice;
        TagName = tagName;
    }


    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(String avg_rating) {
        this.avg_rating = avg_rating;
    }

    public String getMenuItemDesc() {
        return MenuItemDesc;
    }

    public void setMenuItemDesc(String menuItemDesc) {
        MenuItemDesc = menuItemDesc;
    }

    public String getMenuItemID() {
        return MenuItemID;
    }

    public void setMenuItemID(String menuItemID) {
        MenuItemID = menuItemID;
    }

    public String getMenuItemImageURL() {
        return MenuItemImageURL;
    }

    public void setMenuItemImageURL(String menuItemImageURL) {
        MenuItemImageURL = menuItemImageURL;
    }

    public String getSelectedItem() {
        return SelectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        SelectedItem = selectedItem;
    }

    public String getMenuItemIsChefRecommend() {
        return MenuItemIsChefRecommend;
    }

    public void setMenuItemIsChefRecommend(String menuItemIsChefRecommend) {
        MenuItemIsChefRecommend = menuItemIsChefRecommend;
    }

    public String getMenuItemName() {
        return MenuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        MenuItemName = menuItemName;
    }

    public String getMenuItemPrice() {
        return MenuItemPrice;
    }

    public void setMenuItemPrice(String menuItemPrice) {
        MenuItemPrice = menuItemPrice;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
