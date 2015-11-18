package com.bitcanny.office.mymenu;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//import com.bitcanny.jsonparser.ServiceHandler;

public class JsonFunctions {
	
////////////////////DEFINES THE JSON PARSER SERVICE HANDLER\\\\\\\\\\\\\\\\\\\	
	
	private ServiceHandler handler;
	
	
//////////////////// INITIALIZE URLS AS CONSTANT STRINGS FOR LATER USE\\\\\\\\\\\\\\\\\\\
	
	static final String BASE_URL = "http://projects.bitcanny.com/menuapp";
	static final String BASE_URL1 ="http://192.168.0.212/menuapp/";
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
	static final String PREVIOUSRESTURANTAPI = BASE_URL +"/getUserResturantHistory";
	static final String USERFACEBOOKLOGIN = BASE_URL +"/userFacebookLogin";

	static final String POSTJSON = BASE_URL + "/insertOrder";
	static final String BOOKTABLE = BASE_URL + "/verifyResturantTable";
	static final String GENERATETABLECODE = BASE_URL + "/generateTableCode";
	static final String RESTURANTSHAREOFFER = BASE_URL + "/getRestaurantOffer";
	static final String RESTURANTREVIEWOFFER = BASE_URL + "/getRestaurantReviewOffer";
	static final String MENUREVIEWOFFER = BASE_URL + "/getMenuReviewOffer";
	static final String GETMENUOFFER = BASE_URL + "/getMenuOffer";
	static final String GETORDER = BASE_URL + "/getOrders";
	static final String ENDORDER = BASE_URL + "/endOrder";






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

	public String getResturantReviewRating(String restaurantID,String cacheKey){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));
		pairs.add(new BasicNameValuePair("cacheKey",cacheKey));

		String returnVal = handler.makeServiceCall(GETRESTURANTREVIEWRATING,ServiceHandler.POST,pairs);

		return returnVal;


	}

	public String getMenuReviewRating(String restaurantID,String menuID,String cacheKey){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));
		pairs.add(new BasicNameValuePair("menuID",menuID));
		pairs.add(new BasicNameValuePair("cacheKey",cacheKey));

		String returnVal = handler.makeServiceCall(GETRESTURANTREVIEWRATING,ServiceHandler.POST,pairs);

		return returnVal;


	}


	public String postJsonFacebook(String json){

		InputStream inputStream = null;
		String result = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(USERFACEBOOKLOGIN);
			StringEntity se = new StringEntity(json);
			httpPost.setEntity(se);

			// 7. Set some headers to inform server about the type of the content
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			// 8. Execute POST request to the given URL
			HttpResponse httpResponse = httpClient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();

			// 10. convert inputstream to string
			if(inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}
		return result;

	}
	public String postJson(String json){

		InputStream inputStream = null;
		String result = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(POSTJSON);
			StringEntity se = new StringEntity(json);
			httpPost.setEntity(se);

			// 7. Set some headers to inform server about the type of the content
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			// 8. Execute POST request to the given URL
			HttpResponse httpResponse = httpClient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();

			// 10. convert inputstream to string
			if(inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}
		return result;

	}
	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	public String getPreviousresturantapi(String userName){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("userName", userName));


		String returnVal = handler.makeServiceCall(PREVIOUSRESTURANTAPI,ServiceHandler.POST,pairs);

		return returnVal;
	}

	public String authenticateOrderCode(String restaurantID,String tableCode){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));
		pairs.add(new BasicNameValuePair("tableCode",tableCode));

		String returnVal = handler.makeServiceCall(BOOKTABLE,ServiceHandler.POST,pairs);

		return returnVal;


	}


	public String getTableCode(String restaurantID,String tableNumber){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));
		pairs.add(new BasicNameValuePair("tableNumber",tableNumber));

		String returnVal = handler.makeServiceCall(GENERATETABLECODE,ServiceHandler.POST,pairs);

		return returnVal;


	}

	public String GetResturantShareOffer(String resturantID){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("resturantID",resturantID));

		String returnVal = handler.makeServiceCall(RESTURANTSHAREOFFER,ServiceHandler.POST,pairs);

		return  returnVal;

	}


	public String getResturantReviewOffer(String resturantID){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("resturantID",resturantID));

		String returnVal = handler.makeServiceCall(RESTURANTREVIEWOFFER,ServiceHandler.POST,pairs);

		return  returnVal;

	}

	public String getMenuReviewOffer(String menuID){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("menuID",menuID));

		String returnVal = handler.makeServiceCall(MENUREVIEWOFFER,ServiceHandler.POST,pairs);

		return  returnVal;

	}

	public String getMenuShareOffer(String menuID){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("menuID",menuID));

		String returnVal = handler.makeServiceCall(GETMENUOFFER,ServiceHandler.POST,pairs);

		return  returnVal;

	}

	public String getPreviousOrder(String restaurantID,String tableCode){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));
		pairs.add(new BasicNameValuePair("tableCode",tableCode));
		String returnVal = handler.makeServiceCall(GETORDER,ServiceHandler.POST,pairs);

		return returnVal;

	}
	public String endOrder(String restaurantID,String tableCode){

		List<NameValuePair> pairs = new ArrayList<>();

		pairs.add(new BasicNameValuePair("restaurantID",restaurantID));
		pairs.add(new BasicNameValuePair("tableCode",tableCode));
		String returnVal = handler.makeServiceCall(ENDORDER,ServiceHandler.POST,pairs);

		return returnVal;


	}
}
