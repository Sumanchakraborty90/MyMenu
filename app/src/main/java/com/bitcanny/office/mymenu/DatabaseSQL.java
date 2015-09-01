package com.bitcanny.office.mymenu;

/**
 * Created by OFFICE on 24-06-2015.
 */
public class DatabaseSQL {

    public static int DATABASEVERSION = 1;
    public static String DATABASENAME = "MenuDb.db";
    public static String RETURANTTABLENAME = "resturant";
    public static String ITEMTAG = "itemtag";
    public static String CATEGORY = "category";
    public static String RESTURANTIMAGE = "resturantimage";
    public static String TAG = "tag";
    public static String ITEMCATEGORY = "itemcategory";
    public static String MENUITEM = "menuitem";
    public static String FRONTENDMENU = "frontendmenu";
    public static String PLACEORDER = "order_cart";


    public static  String resturant_Id = "ResturantID";
    public static  String resturant_log_image = "resturant_log_image";
    public static  String resturant_name = "resturant_name";
    public static  String resturant_description = "resturant_description";
    public static  String resturant_address = "resturant_address";
    public static  String resturant_state = "resturant_state";
    public static  String resturant_city = "resturant_city";
    public static  String post_code = "post_code";
    public static  String phone1 = "phone1";
    public static  String phone2 = "phone2";
    public static  String resturant_code = "resturant_code";
    public static  String resturant_status = "resturant_status";
    public static  String resturant_detete = "resturant_detete";
    public static  String resturant_add_on = "resturant_add_on";
    public static  String resturant_add_by = "resturant_add_by";

///////////////////////////////////RESTURANT_IMAGE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private static  String resturant_image_id = "resturant_image_id";
    private static  String resturant_image_resturant_id = "resturant_image_resturant_id";
    private static  String resturant_image_url = "resturant_image_url";
  //  private static  String resturant_image_status = "resturant_image_status";
    private static  String resturant_image_addon = "resturant_image_addon";
    private static  String resturant_image_addby = "resturant_image_addby";

    ///////////////////////////////////RESTURANT_TAG\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private static  String tag_id = "tag_id";
    private static  String tag_name = "tag_name";
    private static  String tag_status = "tag_status";
    private static  String tag_delete ="tag_delete";
    private static  String tag_add_on = "tag_add_on";
    private static  String tag_add_by = "tag_add_by";

    ///////////////////////////////////RESTURANT_ITEM_TAG\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private static  String item_tag_id = "item_tag_id";
    private static  String tag_ig = "tag_ig";
    private static  String tag_menu_item_id = "menu_item_id";
    private static  String item_tag_add_on = "item_tag_add_on";

    ///////////////////////////////////CATEGORY\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private static  String category_id = "category_id";
    private static  String category_name ="category_name";
    private static  String category_image_url = "category_image_url";
    private static  String category_parent_category_id = "category_parent_category_id";
    private static  String category_status = "category_status";
    private static  String category_delete = "category_delete";
    private static  String category_add_on = "category_add_on";
    private static  String category_add_by = "category_add_by";


    ////////////////////////////////////FRONT END MENU\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private static  String front_end_menu_id = "front_end_menu_id";
    private static  String front_end_menu_name = "front_end_menu_name";
    private static  String front_end_menu_order = "front_end_menu_order";
    private static  String front_end_menu_status = "front_end_menu_status";
    private static  String front_end_menu_add_on = "front_end_menu_add_on";

    ////////////////////////////////////FRONT ITEM CATEGORY\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private static  String item_category_id = "item_category_id";
    private static  String item_menu_item_id = "item_menu_item_id";
    private static  String category_id_val = "category_id";
    private static  String item_category_add_on = "item_category_add_on";


    ////////////////////////////////////MENU ITEM\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private static  String menu_item_id = "menu_item_id";
    private static  String menu_item_name = "menu_item_name";
    private static  String menu_item_desc = "menu_item_desc";
    private static  String menu_item_price = "menu_item_price";
    private static  String menu_item_image_url = "menu_item_image_url";
    private static  String menu_item_rating = "menu_item_rating";
    private static  String menu_item_resturant_id = "menu_item_resturant_id";
    private static  String menu_item_is_chef_recomended = "menu_item_is_chef_recomended";
    private static  String menu_item_status = "menu_item_status";
    private static  String menu_item_is_delete = "menu_item_is_delete";
    private static  String menu_item_add_on = "menu_item_add_on";
    private static  String menu_item_add_by = "menu_item_add_by";


    ////////////////////////////////////////My ORDER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private static  String order_id = "order_id";
    private static  String order_name = "order_name";
    private static  String order_item_price = "order_item_price";
    private static  String order_item_quantity = "order_item_quantity";
    private static  String user_id = "user_id";
    private static String  order_item_image_url = "order_item_image_url";





//////////////////////////////////////////////TABLE CREATE METHODS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


    ///////////////////////////////////////RESTURANT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public String getResturantTable(){

        String resturant = "CREATE TABLE "+ RETURANTTABLENAME +" ( "
                            +resturant_Id+" INTEGER PRIMARY KEY ,"
                            +resturant_log_image+" TEXT,"
                            +resturant_name+" TEXT ,"
                            +resturant_description+" TEXT ,"
                            +resturant_address+" TEXT ,"
                            +resturant_state+" TEXT ,"
                           /* +resturant_city+"TEXT,"*/
                            +post_code+" TEXT ,"
                            +phone1+" TEXT ,"
                            +phone2+" TEXT ,"
                            +resturant_code+" TEXT ,"
                            +resturant_status+" TEXT ,"
                            +resturant_detete+" TEXT ,"
                            +resturant_add_on+" TEXT ,"
                            +resturant_add_by+" TEXT "
                            +");";
        return resturant;
    }

    ///////////////////////////////////RESTURANT_IMAGE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


        public String getResturantImage(){

            String returnVal="CREATE TABLE"+RESTURANTIMAGE+"("
                    +resturant_image_id+"INTEGER PRIMARY KEY,"
                    +resturant_image_resturant_id+"TEXT,"
                    +resturant_image_url+"TEXT,"
                    +resturant_image_addon+"TEXT,"
                    +resturant_image_addby+"TEXT"
                    +"FOREIGN KEY("+resturant_image_resturant_id+") REFERENCES" +MENUITEM+"("+menu_item_resturant_id+")"
                    +")";

            return returnVal;
        }

    ///////////////////////////////////RESTURANT_TAG\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public  String getResturantTag(){

        String returnVal = "CREATE TABLE"+TAG+"("
                +tag_id+"INTEGER PRIMARY KEY,"
                +tag_name+"TEXT,"
                +tag_status+"TEXT,"
                +tag_delete+"TEXT,"
                +tag_add_on+"TEXT,"
                +tag_add_by+"TEXT"
                +")";
        return returnVal;
    }
    ///////////////////////////////////RESTURANT_ITEM_TAG\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public String getItemTag(){

        String returnVal = "CREATE TABLE"+ITEMTAG+"("
                +item_tag_id+"INTEGER PRIMARY KEY,"
                +tag_ig+"INTEGER,"
                +tag_menu_item_id+"INTEGER,"
                +item_tag_add_on+"TEXT"
                +"FOREIGN KEY("+tag_menu_item_id+") REFERENCES" +MENUITEM+"("+menu_item_id+")"
                +")";


        return  returnVal;
    }
    /////////////////////////////////////////////CATEGORY\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public String getCategory(){

        String returnVal = "CREATE TABLE "+CATEGORY+"( "
                +category_id+" INTEGER PRIMARY KEY ,"
                +category_name+" TEXT , "
                +category_image_url+" TEXT , "
                +category_parent_category_id+" INTEGER , "
                +category_status+" TEXT , "
                +category_delete+" TEXT , "
                +category_add_on+" TEXT , "
                +category_add_by+" TEXT "

                +");";

        return returnVal;

    }

    ////////////////////////////////////////////////////ITEM CATEGORY\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public String getItemCategory(){

        String returnVal = "CREATE TABLE"+ITEMCATEGORY+"(,"
                +item_category_id+"INTEGER PRIMARY KEY,"
                +item_menu_item_id+"INTEGER,"
                +category_id_val+"INTEGER,"
                +item_category_add_on+"TEXT"
                +"FOREIGN KEY("+item_menu_item_id+") REFERENCES" +MENUITEM+"("+menu_item_id+")"
                +"FOREIGN KEY("+category_id_val+") REFERENCES" +CATEGORY+"("+category_id+")"
                +")";
        return  returnVal;

    }

    //////////////////////////////////////////////////FRONTENDMENU\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public String getFrontEndMenu(){

        String returnVal = "CREATE TABLE"+FRONTENDMENU+"("
                +front_end_menu_id+"INTEGER PRIMAY KEY,"
                +front_end_menu_name+"TEXT,"
                +front_end_menu_order+"TEXT,"
                +front_end_menu_status+"YEXT,"
                +front_end_menu_add_on+"TEXT,"
                +")";
        return returnVal;
    }

    //////////////////////////////////////////////////////MENU\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

   public String getMainMenu(){


        String returnVal = "CREATE TABLE"+MENUITEM+"("
                +menu_item_id+"INTEGER PRIMARY KEY,"
                +order_name+"TEXT,"
                +menu_item_desc+"TEXT,"
                +menu_item_price+"TEXT,"
                +menu_item_image_url+"TEXT,"
                +menu_item_rating+"TEXT,"
                +menu_item_resturant_id+"INTEGER,"
                +menu_item_is_chef_recomended+"INTEGER,"
                +menu_item_status+"TEXT,"
                +menu_item_is_delete+"TEXT,"
                +menu_item_add_on+"TEXT,"
                +menu_item_add_by+"TEXT"
                +")";

       return  returnVal;

    }

    ////////////////////////////////////////////////////////PLACE ORDER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public String placeOrder(){

        String returnVal = "CREATE TABLE"+PLACEORDER+"("
                +order_id+"INTEGER PRIMARY KEY,"
                +menu_item_name+"TEXT,"
                +order_item_price+"TEXT,"
                +order_item_quantity+"TEXT,"
                +user_id+"TEXT,"
                +order_item_image_url+"TEXT,"

                +")";
        return returnVal;

    }


    ////////////////////////////////////////////////////////GENERIC QUERYS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public String dropIfExists(){

        String returnVal = "DROP TABLE IF EXISTS ";

        return  returnVal;

    }



    public String getAllOrder(){

        String returnVal = "Select * from "+ PLACEORDER +" where order_item_quantity <> 0";

        return returnVal;


    }



    public String getSingleResturantDetails(){

        String returnVal = "Select * from "+ RETURANTTABLENAME;

        return returnVal;
    }

    public String getFrontEndMenuSql(){

        String returnVal = "Select * from "+FRONTENDMENU;

        return returnVal;
    }
}
