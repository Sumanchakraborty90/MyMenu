package com.bitcanny.office.mymenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 20-07-2015.
 */
public class PlaceOrderSqlHelperDao extends SQLiteOpenHelper {

    public static String PLACEORDER = "order_cart";
    public static String RETURANTTABLENAME = "resturant";
    public static String FRONTENDMENU = "frontendmenu";


    private static  String order_id = "order_id";
    private static  String order_name = "order_name";
    private static  String order_item_price = "order_item_price";
    private static  String order_item_quantity = "order_item_quantity";
    private static  String user_id = "user_id";
    private static String  order_item_image_url = "order_item_image_url";


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

    private static  String front_end_menu_id = "front_end_menu_id";
    private static  String front_end_menu_name = "front_end_menu_name";
    private static  String front_end_menu_order = "front_end_menu_order";
    private static  String front_end_menu_status = "front_end_menu_status";
    private static  String front_end_menu_add_on = "front_end_menu_add_on";

    DatabaseSQL sql = new DatabaseSQL();
    String returnVal;


    public PlaceOrderSqlHelperDao(Context context) {
        super(context, "MenuDb", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        returnVal  = "CREATE TABLE "+PLACEORDER+"( "
                +order_id+" INTEGER PRIMARY KEY ,"
                +order_name+" TEXT ,"
                +order_item_price+" TEXT ,"
                +order_item_quantity+" TEXT ,"
                +user_id+" TEXT ,"
                +order_item_image_url+" TEXT "

                +");";

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



        db.execSQL(resturant);
        db.execSQL(getFrontEndMenu());
        db.execSQL(returnVal);
        db.execSQL(sql.getCategory());

    }

    public String getFrontEndMenu(){

        String returnVal = "CREATE TABLE "+FRONTENDMENU+"( "
                +front_end_menu_id+" INTEGER PRIMAY KEY, "
                +front_end_menu_name+" TEXT , "
                +front_end_menu_order+" TEXT , "
                +front_end_menu_status+" YEXT , "
                +front_end_menu_add_on+" TEXT  "
                +" );";
        return returnVal;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       db.execSQL(sql.dropIfExists()+PLACEORDER);
        db.execSQL(sql.dropIfExists()+DatabaseSQL.RETURANTTABLENAME);
        db.execSQL(sql.dropIfExists()+FRONTENDMENU);
       onCreate(db);
    }

    public void addToOrder(OrderToCartAdapterModel model){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("order_name",model.getOrder_name());
        values.put("order_item_price",model.getOrder_item_price());
        values.put("order_item_quantity",model.getOrder_item_quantity());
        values.put("user_id", model.getOrder_item_quantity());
        values.put("order_item_image_url", model.getOrder_item_image_url());
        sqLiteDatabase.insert(DatabaseSQL.PLACEORDER, null, values);
        sqLiteDatabase.close();

    }


    public void addToFrontEndMenu(FrontEndMenuModel frontEndMenuModel){

        SQLiteDatabase database =this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("front_end_menu_name",frontEndMenuModel.getFront_end_menu_name());
       // values.put("front_end_menu_order",front_end_menu_order);

        database.insert(FRONTENDMENU, null, values);
        database.close();
    }

    public List<FrontEndMenuModel> getAllMenu(){

        List<FrontEndMenuModel> modelList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(sql.getFrontEndMenuSql(),null);

        if(cursor.moveToFirst()){

        do {
            FrontEndMenuModel model = new FrontEndMenuModel();

            model.setFront_end_menu_name(cursor.getString(1));

            modelList.add(model);

        }while (cursor.moveToNext());

        }

        return modelList;
    }
    public List<OrderToCartAdapterModel> getAllOrderDetails(){

        List<OrderToCartAdapterModel> modelList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(sql.getAllOrder(),null);

        if(cursor.moveToFirst()){

            do{
            OrderToCartAdapterModel orderToCartAdapterModel = new OrderToCartAdapterModel();

                orderToCartAdapterModel.setOrder_name(cursor.getString(1));
                orderToCartAdapterModel.setOrder_item_price(cursor.getString(2));
                orderToCartAdapterModel.setOrder_item_quantity(cursor.getString(3));
                orderToCartAdapterModel.setUser_id(cursor.getString(4));
                orderToCartAdapterModel.setOrder_item_image_url(cursor.getString(5));
                modelList.add(orderToCartAdapterModel);
            }while(cursor.moveToNext());


        }
        database.close();
        cursor.close();
        return modelList;
    }
    public void dropTable(){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + "order_cart");
        database.close();

    }

    public void createTable(){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(returnVal);
        database.close();

    }
    public void createTableResturant(){

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


        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(resturant);
        database.close();

    }


    public void createTableFrontEndMenu(){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(getFrontEndMenu());
        database.close();

    }

    public void AddResturantValues(ResturantModel model){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();



        values.put("resturant_name",model.getRestaurantName());
        values.put("phone1",model.getRestaurantPhone1());
        values.put("phone2",model.getRestaurantPhone2());
        values.put("post_code",model.getRestaurantPostCode());
        values.put("resturant_state",model.getRestaurantState());
        values.put("resturant_address",model.getRestaurantAddress());
        values.put("resturant_description",model.getRestaurantDescription());
        values.put("resturant_code",model.getResturantCode());


       /* values.put("resturant_name","baler resturant");
        values.put("phone1","978664535");
        values.put("phone2","76567575");
        values.put("post_code","556788");
        values.put("resturant_state","west bengal");
        values.put("resturant_address","kolktaa");
        values.put("resturant_description","jhbjhsca");
        values.put("resturant_code","24354534");
*/
        // values.put("RestaurantRatingID",model.getresturantrati);
      /*  values.put("RestaurantRatingRate",model.getRestaurantRatingRate());
        values.put("RestaurantRatingReview",model.getRestaurantRatingReview());
       // values.put("FrontendMenuID","");
        values.put("FrontendMenuName",model.getFrontendMenuName());
        values.put("FrontendMenuOrder",model.getFrontendMenuOrder());
        values.put("menuTagInfo",model.getMenuTagInfo());
        values.put("TagID",model.getTagID());
        values.put("TagName",model.getTagName());
        values.put("lat",model.getLat());
        values.put("lng",model.getLng());
        values.put("RestaurantImageID",model.getRestaurantImageID());
        values.put("RestaurantImageUrl",model.getRestaurantImageUrl());*/

        database.insert(DatabaseSQL.RETURANTTABLENAME,null,values);
        database.close();

    }


    public void addResturantDetails(ResturantModel model){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DatabaseSQL.resturant_name,model.getRestaurantName());
        values.put(DatabaseSQL.resturant_log_image,model.getRestaurantLogImage());
        values.put(DatabaseSQL.phone1,model.getRestaurantPhone1());
        values.put(DatabaseSQL.phone2,model.getRestaurantPhone2());
        values.put(DatabaseSQL.post_code,model.getRestaurantPostCode());
        values.put(DatabaseSQL.resturant_address,model.getRestaurantAddress());
        values.put(DatabaseSQL.resturant_state,model.getRestaurantState());
        values.put(DatabaseSQL.resturant_description,model.getRestaurantDescription());


        sqLiteDatabase.insert(DatabaseSQL.RETURANTTABLENAME, null, values);
        sqLiteDatabase.close();

    }

    public  List<ResturantModel> getResturantDetails(){

        List<ResturantModel> modelList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(sql.getSingleResturantDetails(),null);

        if(cursor.moveToFirst()){

            do{
                ResturantModel model = new ResturantModel();

                model.setRestaurantName(cursor.getString(1));

                model.setRestaurantPhone1(cursor.getString(8));
                model.setRestaurantPhone2(cursor.getString(7));
                model.setRestaurantPostCode(cursor.getString(5));
                model.setRestaurantState(cursor.getString(5));
                model.setRestaurantAddress(cursor.getString(4));

                model.setRestaurantDescription(cursor.getString(3));


             /*   model.setRestaurantPhone1(cursor.getString(8));
                model.setRestaurantPhone2(cursor.getString(0));
                model.setRestaurantDescription(cursor.getString(3));
                model.setRestaurantAddress(cursor.getString(4));
                model.setRestaurantState(cursor.getString(5));
                model.setRestaurantPostCode(cursor.getString(6));
                model.setRestaurantName(cursor.getString(7));*/

                modelList.add(model);
            }while(cursor.moveToNext());


        }
        database.close();
        cursor.close();
        return modelList;


    }

    public void dropTable(String tableName){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + tableName);
        database.close();

    }

    public void dropFrntEndMenu(){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + FRONTENDMENU);
        database.close();

    }

    public void addMenuCategory(){



    }
}
