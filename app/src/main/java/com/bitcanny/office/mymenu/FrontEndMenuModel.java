package com.bitcanny.office.mymenu;

/**
 * Created by OFFICE on 22-07-2015.
 */
public class FrontEndMenuModel {

    String front_end_menu_name;
    String front_end_menu_id;

    public FrontEndMenuModel() {
    }

    public FrontEndMenuModel(String front_end_menu_name/*, String front_end_menu_id*/) {
        this.front_end_menu_name = front_end_menu_name;
       // this.front_end_menu_id = front_end_menu_id;
    }



    public String getFront_end_menu_name() {
        return front_end_menu_name;
    }

    public void setFront_end_menu_name(String front_end_menu_name) {
        this.front_end_menu_name = front_end_menu_name;
    }

    public String getFront_end_menu_id() {
        return front_end_menu_id;
    }

    public void setFront_end_menu_id(String front_end_menu_id) {
        this.front_end_menu_id = front_end_menu_id;
    }
}
