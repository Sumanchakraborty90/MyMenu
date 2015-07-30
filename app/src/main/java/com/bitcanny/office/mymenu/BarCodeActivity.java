package com.bitcanny.office.mymenu;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;


public class BarCodeActivity extends ActionBarActivity implements ZBarScannerView.ResultHandler{

    private ZBarScannerView mScannerView;
    String qrValue="";

    private static String RESTURANTINFO = "restaurantInfo";
    private static String INFO = "Info";
    //private static String IMAGEINFO = "imageInfo";
    private static String MENUINFO = "menuInfo";
    private static String RESTAURANTADDRESS = "RestaurantAddress";
    private static String RESTAURANTCITY = "RestaurantCity";
    private static String RESTAURANTID = "RestaurantID";
    private static String RESTAURANTLOGIMAGE = "RestaurantLogImage";
    private static String RESTAURANTNAME = "RestaurantName";
    private static String RESTAURANTPHONE1 = "RestaurantPhone1";
    private static String RESTAURANTPHONE2 = "RestaurantPhone2";
    private static String RESTAURANTPOSTCODE = "RestaurantPostCode";
    private static String RESTAURANTSTATE = "RestaurantState";
    private static String MenuItemDesc = "MenuItemDesc";
    private static String LATLONGINFO = "latLongInfo";
    private static String RESTAURANTDESCRIPTION = "RestaurantDescription";

    private static String FRONTENDMENUID = "FrontendMenuID";
    private static String FRONTENDMENUNAME = "FrontendMenuName";
    private static String FRONTENDMENUORDER = "FrontendMenuOrder";
    private static String MENUTAGINFO = "menuTagInfo";
    private static String TAGID = "TagID";
    private static String TAGNAME = "TagName";

    private static String IMAGEINFO = "imageInfo";
    private static String RESTAURANTIMAGEID = "RestaurantImageID";
    private static String RESTAURANTIMAGEURL = "RestaurantImageUrl";

    private static String REVIEWINFO = "reviewInfo";
    private static String RESTAURANTRATINGID = "RestaurantRatingID";
    private static String RESTAURANTRATINGRATE = "RestaurantRatingRate";
    private static String RESTAURANTRATINGREVIEW= "RestaurantRatingReview";

    private static String LAT = "lat";
    private static String LNG = "lng";

    ArrayList<Map<String, String>> list;
    static ArrayList<Map<String, String>> list1 = null;

    public static  ArrayList<Map<String,String>> list2 ;
    public static  ArrayList<Map<String,String>> list3 ;
    private static String TYPE = "type";

    String resturantId;
    String restaurantLogImage;
    String restaurantName ;
    String restaurantPhone1 ;
    String restaurantPhone2;
    String restaurantPostCode ;
    String restaurantState ;
    String restaurantAddress ;
    String restaurantDescription ;

    String RestaurantRatingID,RestaurantRatingRate,RestaurantRatingReview;

    String FrontendMenuID ;
    String FrontendMenuName ;
    String FrontendMenuOrder ;
    String menuTagInfo ;
    String TagID ;
    String TagName ;
    String lat ;
    String lng ;
    String RestaurantImageID;
    String RestaurantImageUrl;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);
        list = new ArrayList<Map<String, String>>();
        list1 = new ArrayList<>();// Set the scanner view as the content view

    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("TAG", rawResult.getContents()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
        Toast.makeText(BarCodeActivity.this,rawResult.getContents()+"",Toast.LENGTH_LONG).show();
        if(rawResult.getContents().equals("100001")){

           new CheckResturantCode().execute();

        }
       // qrValue = rawResult.getContents();

    }


    class CheckResturantCode extends AsyncTask<Void,Void, Void> {

        String returnType;
        String json;
        String type;
        ServiceHandler handler = new ServiceHandler();

        @Override
        protected Void doInBackground(Void... params) {

            try{
                list2 = new ArrayList<>();
                list3 = new ArrayList<>();
                JsonFunctions functions = new JsonFunctions(handler);

                json = functions.logInAuthentication("100001") ;

                JSONObject object = new JSONObject(json);

                JSONObject resturantinfo = object.getJSONObject(RESTURANTINFO);

                type = resturantinfo.getString(TYPE);

                JSONObject info = resturantinfo.getJSONObject(INFO);

                resturantId = info.getString(RESTAURANTID);
                restaurantLogImage=info.getString(RESTAURANTLOGIMAGE);
                restaurantName= info.getString(RESTAURANTNAME);
                restaurantPhone1= info.getString(RESTAURANTPHONE1);
                restaurantPhone2 =info.getString(RESTAURANTPHONE2);
                restaurantPostCode=info.getString(RESTAURANTPOSTCODE);
                restaurantState=info.getString(RESTAURANTSTATE);
                restaurantAddress = info.getString(RESTAURANTADDRESS);
                restaurantDescription = info.getString(RESTAURANTDESCRIPTION);


                JSONObject latLong = info.getJSONObject(LATLONGINFO);

                lat = latLong.getString(LAT);
                lng = latLong.getString(LNG);


                JSONArray menuInfo = info.getJSONArray(MENUINFO);

                for(int i =0; i<menuInfo.length();i++){

                    JSONObject object2 = menuInfo.getJSONObject(i);

                    FrontendMenuID = object2.getString(FRONTENDMENUID);
                    FrontendMenuName = object2.getString(FRONTENDMENUNAME);
                    FrontendMenuOrder = object2.getString(FRONTENDMENUORDER);


                    try{
                        JSONArray menuTagInfo = object2.getJSONArray(MENUTAGINFO);

                        for(int index=0;index<menuTagInfo.length();index++){


                            JSONObject object3 = menuTagInfo.getJSONObject(index);

                            TagID = object3.getString(TAGID);
                            TagName = object3.getString(TAGNAME);

                            Map<String, String> map = new HashMap<>();

                            //map.put(TAGID, TagID);
                            map.put(TAGNAME, TagName);


                            list.add(map);
                        }
                    }catch(Exception e){

                        e.printStackTrace();
                    }
                    Map<String, String> map1 = new HashMap<>();
                    map1.put(FRONTENDMENUID, FrontendMenuID);
                    map1.put(FRONTENDMENUNAME, FrontendMenuName);
                    map1.put(FRONTENDMENUORDER, FrontendMenuOrder);
                    list1.add(map1);



                }

                JSONArray imageInfo = info.getJSONArray(IMAGEINFO);

                for(int index = 0;index<imageInfo.length();index++){

                    JSONObject object1 = imageInfo.getJSONObject(index);

                    RestaurantImageID = object1.getString(RESTAURANTIMAGEID);
                    RestaurantImageUrl = object1.getString(RESTAURANTIMAGEURL);

                    Map<String,String> map2 = new HashMap<>();
                    map2.put("RestaurantImageID",RestaurantImageID);
                    Log.d("resimage id",RestaurantImageID);
                    map2.put("RestaurantImageUrl",RestaurantImageUrl);

                    list2.add(map2);
                }

                JSONArray review = info.getJSONArray(REVIEWINFO);

                for(int index = 0; index<review.length();index++){

                    JSONObject jsonObject = review.getJSONObject(index);
                    RestaurantRatingID = jsonObject.getString(RESTAURANTRATINGID);
                    RestaurantRatingRate = jsonObject.getString(RESTAURANTRATINGRATE);
                    RestaurantRatingReview = jsonObject.getString(RESTAURANTRATINGREVIEW);

                    Map<String,String> map = new HashMap<>();
                    map.put(RESTAURANTRATINGID,RestaurantRatingID);
                    map.put(RESTAURANTRATINGRATE,RestaurantRatingRate);
                    map.put(RESTAURANTRATINGREVIEW,RestaurantRatingReview);



                    list3.add(map);


                }
            }catch(Exception e){

                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

			/*dialog = new ProgressDialog(ResturantEntryActivity.this);
			dialog.setCancelable(false);
			dialog.setMessage("Please wait....");
			dialog.show();*/

            //bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

			/*if(dialog.isShowing()){

				dialog.dismiss();
			}*/

            //bar.setVisibility(View.GONE);
            try{

                if(type.equals("success")){

			/*	entryModel.setList(null);
				entryModel.setList(list1);
				entryModel.setList1(list);*/
                    ResturantEntryActivity.list1 = list1;
                    ResturantEntryActivity.list2 = list2;
                    ResturantEntryActivity.list3 = list3;
                    //*Log.d("val", json);
                    //Toast.makeText(ResturantEntryActivity.this,"Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(BarCodeActivity.this,ResturantInfo.class);
                    intent.putExtra(RESTAURANTID, resturantId);
                    intent.putExtra(RESTAURANTLOGIMAGE, restaurantLogImage);
                    intent.putExtra(RESTAURANTNAME, restaurantName);
                    intent.putExtra(RESTAURANTPHONE1, restaurantPhone1);
                    intent.putExtra(RESTAURANTPHONE2, restaurantPhone2);
                    intent.putExtra(RESTAURANTSTATE, restaurantState);
                    intent.putExtra(RESTAURANTADDRESS, restaurantAddress);
                    intent.putExtra(RESTAURANTDESCRIPTION, restaurantDescription);
				/*Bundle b=new Bundle();
		        b.putStringArrayList("KEY",(ArrayList<Map<String, String>>)list); */
                    //intent.putExtra("menu",list);

                    intent.putExtra(LAT, lat);
                    intent.putExtra(LNG, lng);

                    startActivity(intent);

                    //list1.clear();
                    Toast.makeText(BarCodeActivity.this,"Success", Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(BarCodeActivity.this,"Wrong resturant code", Toast.LENGTH_LONG).show();


                }
            }catch(Exception e){

                e.printStackTrace();
            }
        }

    }
}
