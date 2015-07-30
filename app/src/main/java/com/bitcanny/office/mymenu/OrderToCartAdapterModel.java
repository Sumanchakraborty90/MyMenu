package com.bitcanny.office.mymenu;

/**
 * Created by OFFICE on 21-07-2015.
 */
public class OrderToCartAdapterModel {

    String order_id;
    String order_name;
    String order_item_price;
    String order_item_quantity;
    String user_id;
    String order_item_image_url;

    public OrderToCartAdapterModel() {
    }

    public OrderToCartAdapterModel( String order_name, String order_item_price, String order_item_quantity, String user_id, String order_item_image_url) {

        this.order_name = order_name;
        this.order_item_price = order_item_price;
        this.order_item_quantity = order_item_quantity;
        this.user_id = user_id;
        this.order_item_image_url = order_item_image_url;
    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_item_price() {
        return order_item_price;
    }

    public void setOrder_item_price(String order_item_price) {
        this.order_item_price = order_item_price;
    }

    public String getOrder_item_quantity() {
        return order_item_quantity;
    }

    public void setOrder_item_quantity(String order_item_quantity) {
        this.order_item_quantity = order_item_quantity;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_item_image_url() {
        return order_item_image_url;
    }

    public void setOrder_item_image_url(String order_item_image_url) {
        this.order_item_image_url = order_item_image_url;
    }
}
