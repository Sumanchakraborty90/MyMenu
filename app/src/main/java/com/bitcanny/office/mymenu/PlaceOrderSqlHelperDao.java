package com.bitcanny.office.mymenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OFFICE on 20-07-2015.
 */
public class PlaceOrderSqlHelperDao extends SQLiteOpenHelper {

    public static String PLACEORDER = "order_cart";
    public static String RETURANTTABLENAME = "resturant";
    public static String FRONTENDMENU = "frontendmenu";
    public static String LATLONG = "latitudelongitude";
    public static String CATEGORY = "category";
    public static String MENU = "menu";
    public static String SLIDERMENU = "slidermenu";
    public static String SUBCATEGORYIMAGE = "subnetcategory";
    public static String TAXCHARGES = "taxcharges";
    public static String TAGINFO = "taginfo";



    private static String order_id = "order_id";
    private static String order_name = "order_name";
    private static String order_item_price = "order_item_price";
    private static String order_item_quantity = "order_item_quantity";
    private static String user_id = "user_id";
    private static String order_item_image_url = "order_item_image_url";
    private static String  user_order_flag = "user_order_flag";



    public static String resturant_Id = "ResturantID";
    public static String resturant_log_image = "resturant_log_image";
    public static String resturant_name = "resturant_name";
    public static String resturant_description = "resturant_description";
    public static String resturant_address = "resturant_address";
    public static String resturant_state = "resturant_state";
    public static String resturant_city = "resturant_city";
    public static String post_code = "post_code";
    public static String phone1 = "phone1";
    public static String phone2 = "phone2";
    public static String resturant_code = "resturant_code";
    public static String resturant_status = "resturant_status";
    public static String resturant_detete = "resturant_detete";
    public static String resturant_add_on = "resturant_add_on";
    public static String resturant_add_by = "resturant_add_by";

    private static String front_end_menu_id = "front_end_menu_id";
    private static String front_end_menu_name = "front_end_menu_name";
    private static String front_end_menu_order = "front_end_menu_order";
    private static String front_end_menu_status = "front_end_menu_status";
    private static String front_end_menu_add_on = "front_end_menu_add_on";

    private static String latlngid = "lat_lng_id";
    private static String latitude = "lat";
    private static String longitude = "lng";

    private static String category_id = "category_id";
    private static String category_image_url = "category_image_url";
    private static String category_name = "category_name";

    private static int menu_id;
    private static String avg_rating = "avg_rating";
    private static String MenuItemDesc = "MenuItemDesc";
    private static String MenuID = "MenuID";
    private static String MenuItemID = "MenuItemID";
    private static String MenuItemImageURL = "MenuItemImageURL";
    private static String SelectedItem = "SelectedItem";
    private static String MenuItemIsChefRecommend = "MenuItemIsChefRecommend";
    private static String MenuItemName = "MenuItemName";
    private static String MenuItemPrice = "MenuItemPrice";
    private static String TagName = "TagName";

    private static String slider_image_id = "slider_image_id";
    private static String slider_image_url = "slider_image_url";

    private static String subcategoryimageid = "subcategoryimageid";
    private static String subcategoryimageurl = "subcategoryimageurl";
    private static String subcategoryname = "subcategoryname";

    private static String tax_charges_id = "slider_image_id";
    private static String tax_charges_type = "tax_charges_type";
    private static String tax_charges_amount = "tax_charges_amount";
    private static String tax_charges_name = "tax_charges_name";

    private static String tag_id = "tag_id";
    private static String chili = "chili";
    private static String fish = "fish";
    private static String nonveg = "nonveg";
    private static String veg = "veg";
    private static String menuItemId = "menuItemId";




    DatabaseSQL sql = new DatabaseSQL();
    String returnVal;


    public PlaceOrderSqlHelperDao(Context context) {
        super(context, "MenuDb", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        returnVal = "CREATE TABLE " + PLACEORDER + "( "
                + order_id + " INTEGER PRIMARY KEY ,"
                + order_name + " TEXT ,"
                + order_item_price + " TEXT ,"
                + order_item_quantity + " TEXT ,"
                + user_id + " TEXT ,"
                + order_item_image_url + " TEXT ,"
                + user_order_flag +" INTEGER ,"
                + MenuID +" TEXT "

                + ");";

        String resturant = "CREATE TABLE " + RETURANTTABLENAME + " ( "
                + resturant_Id + " INTEGER PRIMARY KEY ,"
                + resturant_log_image + " TEXT,"
                + resturant_name + " TEXT ,"
                + resturant_description + " TEXT ,"
                + resturant_address + " TEXT ,"
                + resturant_state + " TEXT ,"
                           /* +resturant_city+"TEXT,"*/
                + post_code + " TEXT ,"
                + phone1 + " TEXT ,"
                + phone2 + " TEXT ,"
                + resturant_code + " TEXT ,"
                + resturant_status + " TEXT ,"
                + resturant_detete + " TEXT ,"
                + resturant_add_on + " TEXT ,"
                + resturant_add_by + " TEXT "
                + ");";

        String latlng = "CREATE TABLE " + LATLONG + " ( "
                + latlngid + " INTEGER PRIMARY KEY , "
                + latitude + " TEXT ,"
                + longitude + " TEXT ,"
                + resturant_Id + " TEXT "
                + " );";

        String category = "CREATE TABLE " + CATEGORY + " ( "
                + category_id + " INTEGER PRIMARY KEY , "
                + category_image_url + " TEXT,"
                + category_name + " TEXT "
                + " );";

        String menu = "CREATE TABLE " + MENU + " ( "
                + MenuID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + MenuItemID + " INTEGER  ,"
                + avg_rating + " TEXT , "

                + MenuItemDesc + " TEXT ,"
                + category_name + " TEXT , "
                + MenuItemImageURL + " TEXT , "
                + SelectedItem + " TEXT , "
                + MenuItemIsChefRecommend + " TEXT , "
                + MenuItemName + " TEXT , "
                + MenuItemPrice + " TEXT , "
                + TagName + " TEXT "
                + " );";
        String tagInfo =  "CREATE TABLE " + TAGINFO + " ( "
                + tag_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + chili + " TEXT , "

                + fish + " TEXT ,"
                + veg + " TEXT , "
                + nonveg + " TEXT , "
                + menuItemId + " TEXT  "

                + " );";

        String slider = "CREATE TABLE "+SLIDERMENU+ " ( "
                +slider_image_id+" INTEGER PRIMARY KEY, "
                +slider_image_url+" TEXT "
                +" ); ";

        String localImage = "CREATE TABLE "+SUBCATEGORYIMAGE+ " ( "
                +subcategoryimageid+" INTEGER PRIMARY KEY, "
                +subcategoryimageurl+" TEXT ,"
                +subcategoryname+" TEXT "
                +" ); ";

        String tax = "CREATE TABLE "+TAXCHARGES+ " ( "
                +tax_charges_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +tax_charges_type+" TEXT ,"
                +tax_charges_amount+" TEXT ,"
                +tax_charges_name+" TEXT "
                +" ); ";

        db.execSQL(resturant);
        db.execSQL(getFrontEndMenu());
        db.execSQL(returnVal);
        db.execSQL(latlng);
        db.execSQL(category);
        db.execSQL(menu);
        db.execSQL(slider);
        db.execSQL(tax);
        db.execSQL(tagInfo);
      //  db.execSQL(localImage);
       // db.close();
        //  db.execSQL(sql.getCategory());

    }

    public String getFrontEndMenu() {

        String returnVal = "CREATE TABLE " + FRONTENDMENU + "( "
                + front_end_menu_id + " INTEGER PRIMAY KEY, "
                + front_end_menu_name + " TEXT , "
                + front_end_menu_order + " TEXT , "
                + front_end_menu_status + " YEXT , "
                + front_end_menu_add_on + " TEXT  "
                + " );";
        return returnVal;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(sql.dropIfExists() + PLACEORDER);
        db.execSQL(sql.dropIfExists() + DatabaseSQL.RETURANTTABLENAME);
        db.execSQL(sql.dropIfExists() + FRONTENDMENU);
        db.execSQL(sql.dropIfExists() + LATLONG);
        db.execSQL(sql.dropIfExists() + CATEGORY);
        db.execSQL(sql.dropIfExists() + MENU);
        db.execSQL(sql.dropIfExists() + SLIDERMENU);
        db.execSQL(sql.dropIfExists() + TAXCHARGES);
        db.execSQL(sql.dropIfExists() + TAGINFO);

     //   db.execSQL(sql.dropIfExists() + SUBCATEGORYIMAGE);
        onCreate(db);
       // db.close();
    }

    public void addToOrder(OrderToCartAdapterModel model) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("order_name", model.getOrder_name());
        values.put("order_item_price", model.getOrder_item_price());
        values.put("order_item_quantity", model.getOrder_item_quantity());
        values.put("user_id", model.getOrder_item_quantity());
        values.put("order_item_image_url", model.getOrder_item_image_url());
        sqLiteDatabase.insert(DatabaseSQL.PLACEORDER, null, values);
        sqLiteDatabase.close();

    }


    public void addToTagInfo(TagInfoModel model){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put("tag_id",model.ge);
        values.put("chili",model.getChili());
        values.put("fish",model.getFish());
        values.put("veg",model.getVeg());
        values.put("nonveg",model.getNonveg());
        values.put("menuItemId",model.getMenuItemId());

        database.insert(TAGINFO,null,values);
        database.close();


    }

    public void addToTaxCHarges(TaxChargesModel model) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("tax_charges_type", model.getTax_charges_type());
        values.put("tax_charges_amount", model.getTax_charges_amount());
        values.put("tax_charges_name", model.getTax_charges_name());

        sqLiteDatabase.insert(TAXCHARGES, null, values);
        sqLiteDatabase.close();

    }

    public List<TaxChargesModel> getTaxDetails() {

        List<TaxChargesModel> list = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select tax_charges_type,tax_charges_amount,tax_charges_name from " + TAXCHARGES , null);

        if (cursor.moveToFirst()) {

            do {

                TaxChargesModel model = new TaxChargesModel();

                model.setTax_charges_type(cursor.getString(0));
                model.setTax_charges_amount(cursor.getString(1));
                //model.setCategory_name(cursor.getString(2));
                model.setTax_charges_name(cursor.getString(2));
               /* model.setMenuItemIsChefRecommend(cursor.getString(3));
                model.setMenuItemName(cursor.getString(4));
                model.setMenuItemPrice(cursor.getString(5));
                model.setTagName(cursor.getString(6));
                model.setMenu_id(Integer.valueOf(cursor.getString(7)));*/
                list.add(model);

            } while (cursor.moveToNext());


        }

        database.close();
        cursor.close();

        return list;

    }

    public void addToSubCategoryImage(SubCategoryImageModel model){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(subcategoryimageurl,model.getSubcategoryimageurl());
        values.put(subcategoryname,model.getSubcategoryname());
        sqLiteDatabase.insert(SUBCATEGORYIMAGE, null, values);

        sqLiteDatabase.close();


    }


    public String getSubcategoryImage(String subCategoryName) {

        String selectQuery = "SELECT  subcategoryimageurl FROM " + SUBCATEGORYIMAGE+ " where subcategoryname =?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(subCategoryName)});

        String value = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Log.d("value from database",Integer.parseInt(cursor.getString(0))+"");
                value =  (cursor.getString(0));

                // Adding contact to list
                // contactList.add(contact);
            } while (cursor.moveToNext());
        }


        db.close();
        // return contact
        return value;
    }
    public void addToOrderValue(String order_name,String item_price,String order_item_quantity,String url,String menuID,String user_order_flag) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("order_name",order_name);
        values.put("order_item_price",item_price);
        values.put("order_item_quantity", order_item_quantity);
       // values.put("user_id", model.getOrder_item_quantity());
        values.put("order_item_image_url",url);
        values.put("user_order_flag",user_order_flag);
        values.put("MenuID",menuID);

        sqLiteDatabase.insert(DatabaseSQL.PLACEORDER, null, values);
        sqLiteDatabase.close();

    }
    public void removeIfExists(String itemName){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(PLACEORDER, "order_name=?", new String[]{itemName});




    }

    public void addToMenu(MenuInfoModel model) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("MenuItemID", model.getMenuItemID());
        values.put("avg_rating", model.getAvg_rating());
        values.put("MenuItemDesc", model.getMenuItemDesc());
        values.put("category_name", model.getCategory_name());
        values.put("MenuItemImageURL", model.getMenuItemImageURL());
        values.put("SelectedItem",model.getSelectedItem());
        values.put("MenuItemIsChefRecommend", model.getMenuItemIsChefRecommend());
        values.put("MenuItemName", model.getMenuItemName());
        values.put("MenuItemPrice", model.getMenuItemPrice());
        values.put("TagName", model.getTagName());

        database.insert(MENU, null, values);
        database.close();

    }

    public List<MenuInfoModel> getMenuDetails(String category_name) {

        List<MenuInfoModel> list = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select DISTINCT avg_rating,MenuItemDesc,MenuItemImageURL,MenuItemIsChefRecommend,MenuItemName,MenuItemPrice,TagName,MenuItemID from " + MENU + " where category_name =?", new String[]{category_name});

        if (cursor.moveToFirst()) {

            do {

                MenuInfoModel model = new MenuInfoModel();

                model.setAvg_rating(cursor.getString(0));
                model.setMenuItemDesc(cursor.getString(1));
                //model.setCategory_name(cursor.getString(2));
                model.setMenuItemImageURL(cursor.getString(2));
                model.setMenuItemIsChefRecommend(cursor.getString(3));
                model.setMenuItemName(cursor.getString(4));
                model.setMenuItemPrice(cursor.getString(5));
                model.setTagName(cursor.getString(6));
                model.setMenu_id(Integer.valueOf(cursor.getString(7)));
                list.add(model);

            } while (cursor.moveToNext());


        }

        database.close();
        cursor.close();

        return list;

    }

    public List<TagInfoModel> getTagDetails(String menuItemId) {

        List<TagInfoModel> list = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select tag_id,chili,fish,veg,nonveg from " + TAGINFO + " where menuItemId =?", new String[]{menuItemId});



        if (cursor.moveToFirst()) {

            do {

                TagInfoModel model = new TagInfoModel();

                model.setTag_id(cursor.getString(0));
                Log.d("tagId", cursor.getString(0));
                model.setChili(cursor.getString(1));
                Log.d("ChiliVAl", cursor.getString(1));
                //model.setCategory_name(cursor.getString(2));
                model.setFish(cursor.getString(2));
                model.setVeg(cursor.getString(3));
                model.setNonveg(cursor.getString(4));
               // model.setMenuItemId(cursor.getString(5));

                list.add(model);

            } while (cursor.moveToNext());


        }

        database.close();
        cursor.close();

        return list;

    }

    public void addToCategory(CategoryModel model) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put("category_id",model.getCategoryId());
        values.put("category_image_url", model.getCategoryImageURL());
        values.put("category_name", model.getCategoryName());

        database.insert(CATEGORY, null, values);
        database.close();

    }

    public  void addToSlider(SliderImageModel model){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("slider_image_url",model.getSlider_image_url());

        database.insert(SLIDERMENU, null, values);
        database.close();

    }


    public void addToLatLng(LatLngModel model) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put("latlngid",model.getLatlngid());
        values.put(latitude, model.getLatitude());
        values.put(longitude, model.getLongitude());
        values.put(resturant_Id, model.getResturant_Id());

        database.insert(LATLONG, null, values);
        database.close();

    }

    public void addToFrontEndMenu(FrontEndMenuModel frontEndMenuModel) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("front_end_menu_name", frontEndMenuModel.getFront_end_menu_name());
        // values.put("front_end_menu_order",front_end_menu_order);

        database.insert(FRONTENDMENU, null, values);
        database.close();
    }

    public List<SliderImageModel> getSliderImageUrl(){

        List<SliderImageModel> list = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select slider_image_url from "+SLIDERMENU,null);
        if(cursor.moveToFirst()){

            do {

                SliderImageModel model = new SliderImageModel();

                model.setSlider_image_url(cursor.getString(0));

                list.add(model);

            }while (cursor.moveToNext());
        }


    return list;
    }
    public List<CategoryModel> getCategory() {

        List<CategoryModel> list = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select DISTINCT category_image_url,category_name from category", null);

        if (cursor.moveToFirst()) {
            do {

                CategoryModel model = new CategoryModel();

                // model.setCategoryId(cursor.getInt(0));
                model.setCategoryImageURL(cursor.getString(0));
                model.setCategoryName(cursor.getString(1));


                list.add(model);
            } while (cursor.moveToNext());
        }


        /*database.close();
       cursor.close();*/

        return list;

    }


    public List<LatLngModel> getLatLng(/*String resturant_Id*/) {

        List<LatLngModel> list = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select lat,lng from latitudelongitude",/*new String[] { resturant_Id}*/null);

        if (cursor.moveToFirst()) {

            do {

                LatLngModel model = new LatLngModel();

                model.setLatitude(cursor.getString(0));
                model.setLongitude(cursor.getString(1));

                list.add(model);

            } while (cursor.moveToNext());

        }
        database.close();
        cursor.close();

        return list;
    }

    public List<FrontEndMenuModel> getAllMenu() {

        List<FrontEndMenuModel> modelList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(sql.getFrontEndMenuSql(), null);

        if (cursor.moveToFirst()) {

            do {
                FrontEndMenuModel model = new FrontEndMenuModel();

                model.setFront_end_menu_name(cursor.getString(0));

                modelList.add(model);

            } while (cursor.moveToNext());

        }
        database.close();
        cursor.close();

        return modelList;
    }

    public List<OrderToCartAdapterModel> getAllOrderDetails() {

        List<OrderToCartAdapterModel> modelList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(sql.getAllOrder(), null);

        if (cursor.moveToFirst()) {

            do {
                OrderToCartAdapterModel orderToCartAdapterModel = new OrderToCartAdapterModel();

                orderToCartAdapterModel.setOrder_name(cursor.getString(1));
                orderToCartAdapterModel.setOrder_item_price(cursor.getString(2));
                orderToCartAdapterModel.setOrder_item_quantity(cursor.getString(3));
                orderToCartAdapterModel.setUser_id(cursor.getString(4));
                orderToCartAdapterModel.setOrder_item_image_url(cursor.getString(5));
                orderToCartAdapterModel.setUser_order_flag(cursor.getString(6));

                orderToCartAdapterModel.setMenu_id(cursor.getString(7));

                modelList.add(orderToCartAdapterModel);
            } while (cursor.moveToNext());


        }
        database.close();
        cursor.close();
        return modelList;
    }


    public List<OrderToCartAdapterModel> getAllOrderDetailsOfOtherUsers() {

        List<OrderToCartAdapterModel> modelList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(sql.getAllOrderOfOtherUsers(), new String[]{"1","0"});

        if (cursor.moveToFirst()) {

            do {
                OrderToCartAdapterModel orderToCartAdapterModel = new OrderToCartAdapterModel();

                orderToCartAdapterModel.setOrder_name(cursor.getString(0));
                orderToCartAdapterModel.setOrder_item_price(cursor.getString(1));
                orderToCartAdapterModel.setOrder_item_quantity(cursor.getString(2));
                orderToCartAdapterModel.setUser_id(cursor.getString(3));
                orderToCartAdapterModel.setOrder_item_image_url(cursor.getString(4));
                orderToCartAdapterModel.setUser_order_flag(cursor.getString(5));

                orderToCartAdapterModel.setMenu_id(cursor.getString(6));

                modelList.add(orderToCartAdapterModel);
            } while (cursor.moveToNext());


        }
        database.close();
        cursor.close();
        return modelList;
    }


    public int updateSelectedItems(String orderName ,String selecteditems){

//        Log.d("selecteditems",selecteditems);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("order_item_quantity", selecteditems);
       // values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        int val = db.update(PLACEORDER, values, "order_name" + " = ?",
                new String[]{orderName});

        db.close();
        return val;

    }

    public void deleteOrderedItems(){

        //Log.d("selecteditems",selecteditems);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + PLACEORDER);

       /* ContentValues values = new ContentValues();
        values.put("order_item_quantity", selecteditems);
        // values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        int val = db.update(PLACEORDER, values, "order_name" + " = ?",
                new String[]{orderName});*/

        db.close();


    }


    public int getSelectedItemsSummation(){

        List<LatLngModel> list = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select SelectedItem from " + MENU,/*new String[] { resturant_Id}*/null);

        int value = 0;
        if (cursor.moveToFirst()) {

            do {

                LatLngModel model = new LatLngModel();

                value = value+Integer.valueOf(cursor.getString(0));


               // list.add(model);

            } while (cursor.moveToNext());

        }
        database.close();
        cursor.close();

        return value;



    }

    public int updateSelectedItemsInMenu(String orderName ,String selecteditems){

        Log.d("selecteditems",selecteditems);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("SelectedItem", selecteditems);
        // values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(MENU, values, MenuItemName + " = ?",
                new String[]{orderName});

    }

    public List<MenuInfoModel> getMEnuSize(){

        List<MenuInfoModel> list = new ArrayList<>();

        int flag =0;

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select * from " + MENU,null );

        if (cursor.moveToFirst()) {

            do {

                flag++;
                MenuInfoModel model = new MenuInfoModel();

                model.setAvg_rating(cursor.getString(0));
                model.setMenuItemDesc(cursor.getString(1));
                //model.setCategory_name(cursor.getString(2));
                model.setMenuItemImageURL(cursor.getString(2));
                model.setMenuItemIsChefRecommend(cursor.getString(3));
                model.setMenuItemName(cursor.getString(4));
                model.setMenuItemPrice(cursor.getString(5));
                model.setTagName(cursor.getString(6));

                list.add(model);

            } while (cursor.moveToNext());


        }

        database.close();
        cursor.close();

        return list;



    }
    public int updateSelectedItemsInMenuInSTart(String orderName ,String selecteditems){

        Log.d("selecteditems",selecteditems);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("SelectedItem", selecteditems);
        // values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(MENU, values, MenuItemName + " = ?",
                new String[]{String.valueOf(orderName)});

    }

    public int getUpdatedSelectedItems(String order_name) {
      /*  SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACEORDER, new String[] { "order_item_quantity",
                      }, order_id + "=?",
                new String[] { String.valueOf(position) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();*/

       // Log.d("order_name",order_name);
        String selectQuery = "SELECT  order_item_quantity FROM " + PLACEORDER+ " where order_name =?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(order_name)});
        int value = 0;
    try {

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

              Log.d("value from database",Integer.parseInt(cursor.getString(0))+"");
                value = Integer.parseInt(cursor.getString(0));

                // Adding contact to list
                // contactList.add(contact);
            } while (cursor.moveToNext());
        }

    }catch (Exception e){

        e.printStackTrace();
    }
        db.close();
        // return contact
        return value;
    }

    public int getUpdatedSelectedItemsMenu(String MenuItemName) {
      /*  SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACEORDER, new String[] { "order_item_quantity",
                      }, order_id + "=?",
                new String[] { String.valueOf(position) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();*/

        Log.d("MenuItemName",MenuItemName);
        String selectQuery = "SELECT  SelectedItem FROM " + MENU+ " where MenuItemName =?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(MenuItemName)});

        int value=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Log.d("value from database",Integer.parseInt(cursor.getString(0))+"");
                value =  Integer.parseInt(cursor.getString(0));

                // Adding contact to list
                // contactList.add(contact);
            } while (cursor.moveToNext());
        }


        db.close();
        cursor.close();
        // return contact
        return value;
    }


    public void dropTable() {

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + "order_cart");
        database.close();

    }

    public void createTable() {

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(returnVal);
        database.close();

    }

    public void createMenuTable() {

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("CREATE TABLE " + MENU + " ( "
                + MenuItemID + " INTEGER PRIMARY KEY ,"
                + avg_rating + " TEXT , "
                + MenuItemDesc + " TEXT ,"
                + category_name + " TEXT , "
                + MenuItemImageURL + " TEXT , "
                + MenuItemIsChefRecommend + " TEXT , "
                + MenuItemName + " TEXT , "
                + MenuItemPrice + " TEXT , "
                + TagName + " TEXT "
                + " );");
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

    public void createTableCategory(){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("CREATE TABLE " + CATEGORY + " ( "
                +category_id+" INTEGER PRIMARY KEY , "
                +category_image_url+ " TEXT,"
                +category_name+ " TEXT "
                +" );");
        database.close();

    }

    public void AddResturantValues(ResturantModel model){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("resturant_Id",model.getResturantId());
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
       // values.put(DatabaseSQL.resturant_Id,model.getResturantId());

        Log.d("resturant_name",model.getRestaurantName());
        values.put(DatabaseSQL.resturant_name, model.getRestaurantName());
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

                model.setRestaurantName(cursor.getString(2));
                model.setResturantId(cursor.getString(0));
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

    public List<MenuInfoModel> getMenuDetailsRespectToChefsRecomendation() {

        List<MenuInfoModel> list = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select avg_rating,MenuItemDesc,category_name,MenuItemImageURL,MenuItemIsChefRecommend,MenuItemName,MenuItemPrice,TagName from " + MENU + " where MenuItemIsChefRecommend =?", new String[]{"1"});

        if (cursor.moveToFirst()) {

            do {

                MenuInfoModel model = new MenuInfoModel();

                model.setAvg_rating(cursor.getString(0));
                model.setMenuItemDesc(cursor.getString(1));
                model.setCategory_name(cursor.getString(2));
                model.setMenuItemImageURL(cursor.getString(3));
                model.setMenuItemIsChefRecommend(cursor.getString(4));
                model.setMenuItemName(cursor.getString(5));
                model.setMenuItemPrice(cursor.getString(6));
                model.setTagName(cursor.getString(7));

                list.add(model);

            } while (cursor.moveToNext());


        }

        database.close();
        cursor.close();

        return list;

    }

    public void updateItemSelectedToZero(String menuName){

        SQLiteDatabase database = this.getWritableDatabase();
           /* ContentValues args = new ContentValues();
            args.put(SelectedItem, "0");

            return database.update(MENU, args,null, new String[{"menuName"}]) > 0;*/
        String strSQL = "UPDATE "+ MENU +" SET "+ SelectedItem +" = "+ 0/*+" WHERE "+ MenuItemName +" = "+ menuName*/;

        database.execSQL(strSQL);



    }
}
