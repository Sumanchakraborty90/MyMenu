package com.bitcanny.office.mymenu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

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
	static final String LOGIN = BASE_URL + "/userAuthentication";
	static final String REGISTRATION = BASE_URL + "/userRegistration";
	static final String INSERTRESTURANTORMENUREVIEW = BASE_URL + "/insertReview";
	static final String GETRESTURANTREVIEWRATING = BASE_URL + "/getReviewInfo";
	static final String PREVIOUSRESTURANTAPI ="http://localhost/my_api/resturantApi.html";



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

////////////////////////////////USERLOGIN\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public String logInAuthentication(String userId,String password){

		List<NameValuePair> pairs = new ArrayList<NameValuePair>();

		pairs.add(new BasicNameValuePair("email",userId));
		pairs.add(new BasicNameValuePair("password",password));

		String returnValue = handler.makeServiceCall(LOGIN, ServiceHandler.POST,pairs);

		return returnValue;

	}

/////////////////////////////////Registration\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public String userRegistration(String user_name,String user_email,String user_password,String user_phone){

		List<NameValuePair> pairs = new ArrayList<NameValuePair>();

		pairs.add(new BasicNameValuePair("user_name",user_name));
		pairs.add(new BasicNameValuePair("user_email",user_email));
		pairs.add(new BasicNameValuePair("user_password",user_password));
		pairs.add(new BasicNameValuePair("user_phone",user_phone));


		String returnValue = handler.makeServiceCall(REGISTRATION, ServiceHandler.POST,pairs);

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

	public String insertResturantReview(String review,String rate,String restaurantID,String userid){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("review",review));
		pairs.add(new BasicNameValuePair("rate",rate));
		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));
		//pairs.add(new BasicNameValuePair("menuID",menuID));
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

	public String insertMenuReview(String review,String rate,String restaurantID,String menuID,String userid){


		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("review",review));
		pairs.add(new BasicNameValuePair("rate",rate));
		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));
		pairs.add(new BasicNameValuePair("menuID",menuID));
		pairs.add(new BasicNameValuePair("userid",userid));

		String returnVal = handler.makeServiceCall(INSERTRESTURANTORMENUREVIEW,ServiceHandler.POST,pairs);



		return returnVal;

	}

	public String getPreviousResturant(){


		String returnVal = handler.makeServiceCall(PREVIOUSRESTURANTAPI,ServiceHandler.GET);
		return returnVal;
	}

	public String getResturantReviewRating(String restaurantID){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));

		String returnVal = handler.makeServiceCall(GETRESTURANTREVIEWRATING,ServiceHandler.POST,pairs);

		return returnVal;


	}
}
