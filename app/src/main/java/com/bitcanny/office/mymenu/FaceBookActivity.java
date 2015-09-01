package com.bitcanny.office.mymenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONObject;

import java.util.Arrays;

public class FaceBookActivity extends FragmentActivity {


    ShareDialog shareDialog;
    String Item_name,Desc_item,MenuItemImageURL;
    CallbackManager callbackManager;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_book);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(FaceBookActivity.this);
        sp=getPreferences(MODE_PRIVATE);

        Bundle bundle = getIntent().getExtras();


        Item_name = bundle.getString("Item_name");
        Desc_item = bundle.getString("Desc_item");
        MenuItemImageURL = bundle.getString("MenuItemImageURL");

      /*  LoginButton loginButton = (LoginButton) findViewById(R.id.authButton);
        loginButton.setReadPermissions("user_friends");*/




       checkLogin();
        try {


          /* if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle(Item_name)
                        .setContentDescription(Desc_item)
                        .setContentUrl(Uri.parse(MenuItemImageURL))
                        .build();

                shareDialog.show(linkContent);
            }
*/
          //  checkLogin();


       /*     Log.d("accesstoken",sp.getString("access token",null)+"");
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                            Log.d("Suman","onCompleted jsonObject: "+jsonObject);
                            Log.d("Suman","onCompleted response: "+response);
                            // Application code
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,cover,email");
            request.setParameters(parameters);
            request.executeAsync();*/
            shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                @Override
                public void onSuccess(Sharer.Result result) {


                    Log.d("Success", "Success");




                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException e) {

                }

                 });
        }catch (Exception e){

            e.printStackTrace();
        }


    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Log.d("accesstoken", data + "");
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_face_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    public void checkLogin() {
        //Login Callback registration
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
              //  Toast.makeText(getApplicationContext(), "in LoginResult on success", Toast.LENGTH_LONG).show();



                //Login success - process to Post
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(Item_name)
                            .setContentDescription(Desc_item)
                            .setContentUrl(Uri.parse(MenuItemImageURL))
                            .build();

                    shareDialog.show(linkContent);
                }


                GraphRequest request = GraphRequest.newMeRequest(
                       loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {

                                Log.d("responcs",response+"---->"+object);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
               // Toast.makeText(getApplicationContext(), "in LoginResult on cancel", Toast.LENGTH_LONG).show();

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(Item_name)
                            .setContentDescription(Desc_item)
                            .setContentUrl(Uri.parse(MenuItemImageURL))
                            .build();

                    shareDialog.show(linkContent);
                }
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "in LoginResult on error", Toast.LENGTH_LONG).show();
            }
        });

        LoginManager manager = LoginManager.getInstance();
        manager.logInWithReadPermissions(this, Arrays.asList("public_profile, email, user_birthday, user_friends")); //Log in to FB
    }
}
