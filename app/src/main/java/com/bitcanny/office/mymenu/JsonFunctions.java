package com.bitcanny.office.mymenu;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.R.string;

//import com.bitcanny.jsonparser.ServiceHandler;

public class JsonFunctions {
	
////////////////////DEFINES THE JSON PARSER SERVICE HANDLER\\\\\\\\\\\\\\\\\\\	
	
	private ServiceHandler handler;
	
	
//////////////////// INITIALIZE URLS AS CONSTANT STRINGS FOR LATER USE\\\\\\\\\\\\\\\\\\\
	
	static final String BASE_URL = "http://projects.bitcanny.com/menuapp";
	
	static final String RES_LOG_IN_AUTHENTICATION = BASE_URL+"/restaurantInfo.php";
	static final String CATEGORY_ITEMS = BASE_URL+"/restaurantMenuCategory.php";
	static final String MENUINFORMATION = BASE_URL+"/categoryWiseRestaurantMenu";
	static final String FOODINFO = BASE_URL+"/getFoodInfo";
	static final String FAVOURITEMENU = BASE_URL + "/getFavouritesMenu";
	static final String REVIEW=BASE_URL+"/insertReview";
	static final String CHEFSRECOMMENDATION =BASE_URL+"/chafsRecommendation";


////////////////////INITIALIZES THE JSON PARSER SERVICE HANDLER IN THE CONSTRUCTOR\\\\\\\\\\\\\\\\\\\	

	public JsonFunctions(ServiceHandler handler) {
		super();
		this.handler = new ServiceHandler();
	}
	
//////////////////RESTURANT LOGIN\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
public String logInAuthentication(String restaurantCode){
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		
		pairs.add(new BasicNameValuePair("restaurantCode",restaurantCode));

		String returnValue = handler.makeServiceCall(RES_LOG_IN_AUTHENTICATION, ServiceHandler.POST,pairs);
		
		return returnValue;
	
	}
	
////////////////////GET CATEGORY\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

public String getCategory(String resturantID){
	
	List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	
	pairs.add(new BasicNameValuePair("restaurantID", resturantID));

	String returnValue = handler.makeServiceCall(CATEGORY_ITEMS, ServiceHandler.POST,pairs);
	
	return returnValue;
	
}

	////////////////////GET MENU INFO\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
public String getMenuInformation(String CategoryID ,String RestaurantID){

	List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	pairs.add(new BasicNameValuePair("CategoryID", CategoryID));
	pairs.add(new BasicNameValuePair("RestaurantID", RestaurantID));

	String returnValue = handler.makeServiceCall(MENUINFORMATION, ServiceHandler.POST, pairs);

	return returnValue; 
	
}

	////////////////////GET FOOD INFO\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public String getFoodinfo(String menuItemID){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("menuItemID",menuItemID));

		String returnValue = handler.makeServiceCall(FOODINFO,ServiceHandler.POST,pairs);

		return returnValue;

	}

	////////////////////////////GET FAVOURITES MENU\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public String getFavouritemenu(String restaurantID,String menuID,String userid){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));

		pairs.add(new BasicNameValuePair("menuID",menuID));

		pairs.add(new BasicNameValuePair("userid",userid));

		String returnVal = handler.makeServiceCall(FAVOURITEMENU,ServiceHandler.POST,pairs);

		return returnVal;
	}

	public String insertMenuReview(String review,String rate,String restaurantID,String menuID,String userid){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("review",review));
		pairs.add(new BasicNameValuePair("rate",rate));
		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));
		pairs.add(new BasicNameValuePair("menuID",menuID));
		pairs.add(new BasicNameValuePair("userid",userid));

		String returnVal = handler.makeServiceCall(REVIEW,ServiceHandler.POST,pairs);

		return returnVal;

	}

	public String getChefsrecommendation(String RestaurantID){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("RestaurantID",RestaurantID));

		String returnVal = handler.makeServiceCall(CHEFSRECOMMENDATION,ServiceHandler.POST,pairs);

		return  returnVal;
	}

}
