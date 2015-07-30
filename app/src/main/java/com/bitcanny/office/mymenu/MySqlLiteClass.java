package com.bitcanny.office.mymenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OFFICE on 23-06-2015.
 */
public class MySqlLiteClass extends SQLiteOpenHelper{


    private static int DATABASEVERSION = 1;
    private static String DATABASENAME = "MenuDb.db";

    DatabaseSQL sql = new DatabaseSQL();

    public MySqlLiteClass(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sql.getResturantTable());
      /*  db.execSQL(sql.getResturantTag());
        db.execSQL(sql.getResturantImage());
        db.execSQL(sql.getMainMenu());
        db.execSQL(sql.getFrontEndMenu());
        db.execSQL(sql.getItemTag());
        db.execSQL(sql.getCategory());
        db.execSQL(sql.getItemCategory());*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(sql.dropIfExists()+DatabaseSQL.RETURANTTABLENAME);
      /*  db.execSQL(sql.dropIfExists()+DatabaseSQL.TAG);
        db.execSQL(sql.dropIfExists()+DatabaseSQL.RESTURANTIMAGE);
        db.execSQL(sql.dropIfExists()+DatabaseSQL.MENUITEM);
        db.execSQL(sql.dropIfExists()+DatabaseSQL.FRONTENDMENU);
        db.execSQL(sql.dropIfExists()+DatabaseSQL.ITEMTAG);
        db.execSQL(sql.dropIfExists()+DatabaseSQL.CATEGORY);
        db.execSQL(sql.dropIfExists()+DatabaseSQL.ITEMCATEGORY);*/

        onCreate(db);

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


}
