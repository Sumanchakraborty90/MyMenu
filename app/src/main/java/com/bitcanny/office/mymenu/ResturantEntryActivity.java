package com.bitcanny.office.mymenu;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import com.bitcanny.Jsonutility.JsonFunctions;
import com.bitcanny.Jsonutility.ServiceHandler;
import com.bitcanny.utility.ResEntryModel;
import com.google.android.gms.internal.ft;*/



public class ResturantEntryActivity extends MainActivityqr{
	
	Button btn_submit;
	ImageButton img_cam; 
	EditText edt_rest_code;
	String resturantCode = null;
	ProgressDialog dialog;
	TextView lbl_bitcanny;
	ProgressBar bar;
	ArrayList<Map<String, String>> list;
	static ArrayList<Map<String, String>> list1 = null;
	public static List<Map<String,String>> order = Collections.EMPTY_LIST;

	List<FrontEndMenuModel> frontEndMenuModelList;

	public  static ArrayList<Map<String,String>> list2 ;
	public  static ArrayList<Map<String,String>> list3 ;
	
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
	//private static String MenuItemDesc = "MenuItemDesc";
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

	private static String CATEGORYINFO = "categoryInfo";
	private static String CATEGORYID = "CategoryID";
	private static String CATEGORYIMAGEURL = "CategoryImageURL";
	private static String CATEGORYNAME = "CategoryName";

	//private static String MENUINFO = "menuInfo";
	private static String AVGRATING = "avg_rating";
	private static String MENUITEMDESC = "MenuItemDesc";
	private static String MENUITEMID = "MenuItemID";
	private static String MENUITEMIMAGEURL = "MenuItemImageURL";
	private static String MENUITEMISCHEFRECOMMEND = "MenuItemIsChefRecommend";
	private static String MENUITEMNAME = "MenuItemName";
	private static String MENUITEMPRICE = "MenuItemPrice";
	//private static String TAGNAME = "TagName";

	String CategoryID,CategoryImageURL,CategoryName;
	String MenuItemDesc,MenuItemID,MenuItemImageURL,MenuItemIsChefRecommend,MenuItemName,MenuItemPrice,avg_rating;
	
	private static String LAT = "lat";
	private static String LNG = "lng";
	
	//MySqlLiteClass liteClass;
	ResturantModel resturantModel;
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
	PlaceOrderSqlHelperDao dao;
	ResturantModel model;

	private static String MYPREF = "mypref";
	private static String RESTURANT_CODE = "resturant_code";
	private static  String PASSWORD = "password";
	SharedPreferences sharedPreferences;
	//static ResEntryModel entryModel = new ResEntryModel();
	DatabaseSQL sql;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
	       /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		setContentView(R.layout.my_restro_qr);
		//entryModel.setModel(entryModel);
		dao = new PlaceOrderSqlHelperDao(this);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		sharedPreferences = getSharedPreferences(MYPREF,Context.MODE_PRIVATE);
		img_cam = (ImageButton) findViewById(R.id.img_cam);
		edt_rest_code = (EditText) findViewById(R.id.edt_rest_code);
		lbl_bitcanny = (TextView) findViewById(R.id.lbl_bitcanny);
		bar = (ProgressBar) findViewById(R.id.progressBar1);
		order = new ArrayList<>();
		 list = new ArrayList<Map<String, String>>();
		 list1 = new ArrayList<>();


		if(sharedPreferences.getString("resturant_code", "").equals("")) {


		}else{
			new Thread(new Runnable() {
				public void run() {
					try {
						if (getFromSdcard("/MenuApp/Resturant/").size() == dao.getSliderImageUrl().size()) {

							for (int index = 0; index < dao.getSliderImageUrl().size(); index++) {

								DownloadFromUrl("/MenuApp/Resturant/", index + ".jpg", JsonFunctions.BASE_URL + dao.getSliderImageUrl().get(index).getSlider_image_url());

							}

						}

					} catch (Exception e) {

						e.printStackTrace();
					}

				}
			}).start();



			/*try {

				for(int index = 0;index<dao.getAllOrderDetails().size();index++){


					dao.updateSelectedItems(dao.getAllOrderDetails().get(index).getOrder_name(),"0");


				}
				for (int index = 0; index < dao.getMEnuSize().size(); index++) {

					Log.d("ValueUpdatedBitcanny", index + "");


					int a=dao.updateSelectedItemsInMenu(dao.getMEnuSize().get(index).getMenuItemName(), "0");

					Log.d("ValueUpdatedvalue", dao.getUpdatedSelectedItemsMenu(dao.getMEnuSize().get(index).getMenuItemName()) + "");

					Log.d("ValueUpdatedValueBitca", a+"");

				}



			}catch (Exception e){
				e.printStackTrace();
			}*/
			Intent intent = new Intent(ResturantEntryActivity.this,ResturantInfo.class);
			startActivity(intent);
		}
		edt_rest_code.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

				if ((actionId & EditorInfo.IME_MASK_ACTION) == EditorInfo.IME_ACTION_DONE) {
					resturantCode = edt_rest_code.getText().toString();
					hideKeyboard();


						new CheckResturantCode().execute();

					return true;
				}
				return false;
			}
		});
		btn_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				resturantCode = edt_rest_code.getText().toString();
				hideKeyboard();


					new CheckResturantCode().execute();
				//finish();
				//new MyAsnyc().execute();

				/*for(int index=0;index<list2.size();index++) {
					DownloadFromUrl("menu/sliderimages/"+index+".jpg",JsonFunctions.BASE_URL + list2.get(index).get("RestaurantImageUrl"));
				}*/
			}
		});
		
		img_cam.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			Intent intent = new Intent(ResturantEntryActivity.this,BarCodeActivity.class);

				startActivity(intent);

				
			}
		});
		
		lbl_bitcanny.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String url = "http://bitcanny.com/contact-us/";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
				
			}
		});
		/*String url = "https://win-me-now.com/forgot/";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);*/
		//liteClass = new MySqlLiteClass(ResturantEntryActivity.this);
		resturantModel = new ResturantModel();

	}

	public ResturantModel setValue(ResturantModel resturantModel){

		//resturantModel.setFrontendMenuID();
		resturantModel.setRestaurantPhone1(restaurantPhone1);
		resturantModel.setRestaurantPhone2(restaurantPhone2);
		resturantModel.setRestaurantPostCode(restaurantPostCode);
		resturantModel.setRestaurantState(restaurantPostCode);
		resturantModel.setRestaurantAddress(restaurantAddress);
		resturantModel.setRestaurantDescription(restaurantDescription);
		resturantModel.setResturantCode(resturantCode);

		return resturantModel;
	}

	private void hideKeyboard() {
		// Check if no view has focus:
		View view = this.getCurrentFocus();
		if (view != null) {
			InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public List<Map<String,String>> getList3(){

		return list3;
	}

	public List<Map<String,String>> getImage(){

		return list2;
	}
	class CheckResturantCode extends AsyncTask<Void,Void, Void>{
		
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
			
			json = functions.logInAuthentication(resturantCode) ;
			
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

			/*model = new ResturantModel(restaurantLogImage,restaurantName,resturantId,
					restaurantPhone1,restaurantPhone2,restaurantPostCode,restaurantAddress,restaurantState,restaurantDescription);*/
			dao.addResturantDetails(new ResturantModel(restaurantLogImage,restaurantName,resturantId,
					restaurantPhone1,restaurantPhone2,restaurantPostCode,restaurantAddress,restaurantState,restaurantDescription));
			resturantModel = setValue(resturantModel);
			//liteClass.AddResturantValues(resturantModel);

			try {
				JSONObject latLong = info.getJSONObject(LATLONGINFO);

				lat = latLong.getString(LAT);
				lng = latLong.getString(LNG);

				dao.addToLatLng(new LatLngModel(lat, lng, resturantId));
			}catch (Exception e){

				e.printStackTrace();
			}
			//////////////////////////////////////////////Category json\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

			JSONArray categoryInfoVal = info.getJSONArray(CATEGORYINFO);

			for(int index = 0;index < categoryInfoVal.length();index++){

				JSONObject object1 = categoryInfoVal.getJSONObject(index);

				CategoryID = object1.getString(CATEGORYID);
				CategoryImageURL = object1.getString(CATEGORYIMAGEURL);
				CategoryName = object1.getString(CATEGORYNAME);
				Log.d("val",CategoryImageURL);
				dao.addToCategory(new CategoryModel(CategoryImageURL,CategoryName));
				JSONObject menuInfo = object1.getJSONObject(MENUINFO);

				JSONArray menuInfoJSONArray = menuInfo.getJSONArray(INFO);

				for(int index1 = 0 ; index1<menuInfoJSONArray.length() ;index1++){

					JSONObject jsonObject = menuInfoJSONArray.getJSONObject(index1);
					avg_rating = jsonObject.getString(AVGRATING);
					MenuItemDesc = jsonObject.getString(MENUITEMDESC);
					MenuItemID = jsonObject.getString(MENUITEMID);
					MenuItemImageURL =jsonObject.getString(MENUITEMIMAGEURL);
					MenuItemIsChefRecommend = jsonObject.getString(MENUITEMISCHEFRECOMMEND);
					MenuItemName = jsonObject.getString(MENUITEMNAME);
					MenuItemPrice = jsonObject.getString(MENUITEMPRICE);
					TagName = jsonObject.getString(TAGNAME);

					dao.addToMenu(new MenuInfoModel(CategoryName,avg_rating,MenuItemDesc,MenuItemImageURL,"0",MenuItemIsChefRecommend,MenuItemName,MenuItemPrice,TagName));

				}



			}

			//////////////////////////////////////////////Category json\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
				dao.addToFrontEndMenu(new FrontEndMenuModel(map1.get(FRONTENDMENUNAME)));
				//PlaceOrderSqlHelperDao.getAllMenu();
			//	list1.add(map1);


			}


			frontEndMenuModelList = dao.getAllMenu();


			for(int index = 0;index <frontEndMenuModelList.size();index++){

				Map<String,String> map = new HashMap<>();
				Log.d("val",frontEndMenuModelList.get(index).getFront_end_menu_name());
				map.put(FRONTENDMENUNAME, frontEndMenuModelList.get(index).getFront_end_menu_name());
				list1.add(map);



			}
			Map<String,String> map1 = new HashMap<>();

			map1.put(FRONTENDMENUNAME, "LogIn");

			list1.add(map1);

			Map<String,String> map3 = new HashMap<>();

			map3.put(FRONTENDMENUNAME, "SignUp");

			list1.add(map3);

			JSONArray imageInfo = info.getJSONArray(IMAGEINFO);

			for(int index = 0;index<imageInfo.length();index++){

				JSONObject object1 = imageInfo.getJSONObject(index);

				RestaurantImageID = object1.getString(RESTAURANTIMAGEID);
				RestaurantImageUrl = object1.getString(RESTAURANTIMAGEURL);
				dao.addToSlider(new SliderImageModel(RestaurantImageUrl));
				DownloadFromUrl("/MenuApp/Resturant/",index+".jpg",JsonFunctions.BASE_URL +RestaurantImageUrl );
				Map<String,String> map2 = new HashMap<>();
				map2.put("RestaurantImageID", RestaurantImageID);
				Log.d("resimage id", RestaurantImageID);
				map2.put("RestaurantImageUrl", RestaurantImageUrl);

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
			
			bar.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onPostExecute(Void result) {
			
			super.onPostExecute(result);
			
			/*if(dialog.isShowing()){
				
				dialog.dismiss();
			}*/
			
			bar.setVisibility(View.GONE);

			//liteClass.AddResturantValues(new ResturantModel());
			try{

			if(type.equals("success")){

				addResturant(resturantCode);

				
			/*	entryModel.setList(null);
				entryModel.setList(list1);
				entryModel.setList1(list);*/
				
				//*Log.d("val", json);

				//Toast.makeText(ResturantEntryActivity.this,"Success", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(ResturantEntryActivity.this,ResturantInfo.class);
				intent.putExtra(RESTAURANTID, resturantId);
				intent.putExtra(RESTAURANTLOGIMAGE, restaurantLogImage);
				intent.putExtra(RESTAURANTNAME, restaurantName);
				intent.putExtra(RESTAURANTPHONE1, restaurantPhone1);
				intent.putExtra(RESTAURANTPHONE2, restaurantPhone2);
				intent.putExtra(RESTAURANTSTATE, restaurantState);
				intent.putExtra(RESTAURANTADDRESS, restaurantAddress);
				intent.putExtra(RESTAURANTPOSTCODE, restaurantPostCode);
				intent.putExtra(RESTAURANTDESCRIPTION, restaurantDescription);
				/*Bundle b=new Bundle();
		        b.putStringArrayList("KEY",(ArrayList<Map<String, String>>)list); */
				//intent.putExtra("menu",list);

				intent.putExtra(LAT, lat);
				intent.putExtra(LNG, lng);
				
				startActivity(intent);
				//finish();
				//list1.clear();
				//Toast.makeText(ResturantEntryActivity.this,"Success", Toast.LENGTH_LONG).show();
			}else{
				
				Toast.makeText(ResturantEntryActivity.this,"Wrong resturant code", Toast.LENGTH_LONG).show();
				
				
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
		}
		
	}

	public static class MyAsnyc extends AsyncTask<Void, Void, Void> {
		public static File file;
		InputStream is;

		protected void doInBackground() throws IOException {

			File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			file = new File(path, "Pic.png");
			try {
				// Make sure the Pictures directory exists.
				path.mkdirs();

				URL url = new URL(JsonFunctions.BASE_URL+list2.get(2).get("RestaurantImageUrl"));
            /* Open a connection to that URL. */
				URLConnection ucon = url.openConnection();

            /*
             * Define InputStreams to read from the URLConnection.
             */
				is = ucon.getInputStream();

				OutputStream os = new FileOutputStream(file);
				byte[] data = new byte[is.available()];
				is.read(data);
				os.write(data);
				is.close();
				os.close();

			} catch (IOException e) {
				Log.d("ImageManager", "Error: " + e);
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				doInBackground();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute() {
			try {
				// Tell the media scanner about the new file so that it is
				// immediately available to the user.
				MediaScannerConnection.scanFile(null,
						new String[]{file.toString()}, null,
						new MediaScannerConnection.OnScanCompletedListener() {
							public void onScanCompleted(String path, Uri uri) {
								Log.d("ExternalStorage", "Scanned " + path + ":");
								Log.d("ExternalStorage", "-> uri=" + uri);
							}
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void addResturant(String resturantCode){


		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("resturant_code",resturantCode);
		//editor.putString("password", password);

		editor.commit();

	}
	static public void DownloadFromUrl(String pathVal,String fileName,String urlVal) {
		try {

			File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+pathVal);
			path.mkdirs();
			URL url = new URL(urlVal); //you can write here any link
			File file = new File(path,fileName);

			long startTime = System.currentTimeMillis();
			//tv.setText("Starting download......from " + url);

			Log.d("Start downladed from ",urlVal+"file name ----->   "+path+fileName);
			URLConnection ucon = url.openConnection();
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
                    /*
                     * Read bytes to the Buffer until there is nothing more to read(-1).
                     */
			ByteArrayBuffer baf = new ByteArrayBuffer(50);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}

			FileOutputStream fos = new FileOutputStream(file);
			fos.write(baf.toByteArray());
			fos.close();
			Log.d(" downloded......from ", urlVal);
		} catch (IOException e) {
			//tv.setText("Error: " + e);
		}
	}

	public List<String> getFromSdcard(String path)
	{
		ArrayList<String> f = new ArrayList<String>();
		File[] listFile;
		File file= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+path);

		if (file.isDirectory())
		{
			listFile = file.listFiles();


			for (int i = 0; i < listFile.length; i++)
			{

				f.add(listFile[i].getAbsolutePath());

				Log.d("val",listFile[i].getAbsolutePath());

			}
		}

		return  f;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		dao.close();
	}
}
