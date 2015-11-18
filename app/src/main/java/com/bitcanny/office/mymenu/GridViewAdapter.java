package com.bitcanny.office.mymenu;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 17-06-2015.
 */
public  class GridViewAdapter extends BaseAdapter{
    String offer_id,title,description,imageUrl,value,type;
    List<Map<String,String>> sharedDocumnents;
    List<TagInfoModel> tagInfoModels;
    private Context mContext;
    PopupWindow mPopupWindow;
    private List<Map<String,String>> maps;
    PopupWindow pwindo;
    PlaceOrderSqlHelperDao dao;
    Typeface typeface;
    static int amount;
    static double mnyAmt;
    boolean amount_flag = false;
    static double totalAmountMoney = 0.0;
    boolean viewFlag;
   // static double totalAmountMoney1 = 0.0;
    List<List<Integer>> listOrder;
    boolean boolFlag= false;
    boolean check = false;
    int flag = 0;
    Button button;
    Map<String,Double> totalAmtVal = new HashMap<>();
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    TextView txt_share_content,txt_share;
    int indexFlag;
    //ShareDialog shareDialog;
    List<Integer> list ;

    TextView textView,txt_price_val,txt_item_select;
    List<Map<String,String>> previousOrder1 = Collections.emptyList();
    List<Map<Integer,Integer>> list1 = new ArrayList<>();
    static Map<String,String> selectedValue = Collections.emptyMap();

    static double totalAmt = 0.0;

   static Map<Integer,Integer> selectedItems = Collections.emptyMap();

    //static  Double moneyAmount = 0.0;

    public GridViewAdapter(Context mContext, List<Map<String, String>> maps, List<Integer> list,TextView txt_price_val,TextView txt_item_select,boolean viewFlag,List<Map<String,String>> previousOrder) {
        this.mContext = mContext;
        this.maps = maps;
       this.list = list;
        selectedItems = new HashMap<>();
        tagInfoModels = new ArrayList<>();
        previousOrder1= new ArrayList<>();

        previousOrder1 = previousOrder;
       // this.shareDialog = shareDialog;
        this.viewFlag = viewFlag;
        ResturantInfo.orderedItems.clear();
        this.txt_item_select = txt_item_select;
        this.txt_price_val = txt_price_val;
        dao= new PlaceOrderSqlHelperDao(mContext);
        sharedDocumnents= new ArrayList<>();
        sharedPreferences = mContext.getSharedPreferences(MYPREF,Context.MODE_PRIVATE);
        try {
            amount = Integer.valueOf(sharedPreferences.getString("selectedItems", ""));
            mnyAmt = Double.valueOf(sharedPreferences.getString("totalAmt", ""));
        }catch (Exception e){

            e.printStackTrace();
        }
        HorizontalNumberPicker.finalAmount = Integer.valueOf(MainActivity.numberOfItemsSelected);

       /* textView = new MainActivity().getTextViewReference();*/
           }

          @Override
          public int getCount() {
              return maps.size();
          }

          @Override
          public Object getItem(int position) {
              return null;
          }

          @Override
          public long getItemId(int position) {
              return 0;
          }

          @Override
          public View getView(final int position, View convertView, ViewGroup parent)  {

              typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/ufonts.com_century-gothic.ttf");
              LayoutInflater infalInflater = (LayoutInflater) mContext
                      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

              convertView = infalInflater.inflate(R.layout.grid_row_item,parent,false);



              ImageView grid_img = (ImageView)convertView.findViewById(R.id.grid_img);



              TextView txt_food_name = (TextView)convertView.findViewById(R.id.txt_food_name);

              TextView food_price = (TextView) convertView.findViewById(R.id.food_price);

              ImageView plus = (ImageView)convertView.findViewById(R.id.btn_plus);

              final ImageView minus = (ImageView)convertView.findViewById(R.id.btn_minus);

              final TextView edit_text = (TextView)convertView.findViewById(R.id.edit_text);

              ImageView img_share = (ImageView)convertView.findViewById(R.id.img_share);

              ImageView img_view1 = (ImageView)convertView.findViewById(R.id.img_view1);

              ImageView img_view2 = (ImageView)convertView.findViewById(R.id.img_view2);

              ImageView img_view3 = (ImageView)convertView.findViewById(R.id.img_view3);

              ImageView img_view4 = (ImageView)convertView.findViewById(R.id.img_view4);

              ImageView img_view5 = (ImageView)convertView.findViewById(R.id.img_view5);

              ImageView img_view6 = (ImageView)convertView.findViewById(R.id.img_view6);

              RelativeLayout rl = (RelativeLayout)convertView.findViewById(R.id.rel_val);
              TextView txt_selected_tems = (TextView) convertView.findViewById(R.id.txt_selected_tems);
            //  CardView card_view = (CardView) convertView.findViewById(R.id.card_view);


              if(viewFlag ==  false){

                  plus.setVisibility(View.GONE);
                  minus.setVisibility(View.GONE);
                  edit_text.setVisibility(View.GONE);
                  txt_selected_tems.setVisibility(View.GONE);


                  ObjectAnimator animX = ObjectAnimator.ofFloat(img_share, "Y", 0f,40);
                  animX.setDuration(3000);
                  animX.start();
              }

                try {
                    tagInfoModels = dao.getTagDetails(maps.get(position).get("menu_id"));

                }catch (Exception e){

                    e.printStackTrace();
                }


              Log.d("previousOrder", previousOrder1.size() + "");



              if(previousOrder1.size()>0 && viewFlag ==  true ){

                  for(int index = 0 ;index <previousOrder1.size();index++) {

                      Log.d("menuItemNAme",maps.get(position).get("MenuItemName"));

                      if (maps.get(position).get("MenuItemName").equals(previousOrder1.get(index).get("OrderMenuItemName"))) {
                         // Log.d("NAme", previousOrder1.get(index).get("OrderMenuItemName")+ "<------->"+maps.get(position).get("MenuItemName"));
                          txt_selected_tems.setVisibility(View.VISIBLE);
                          txt_selected_tems.setText(previousOrder1.get(index).get("OrderMenuItemQty") + " Ordered");
                         // dao.addToOrderValue(OrderMenuItemName, OrderMenuItemPrice, OrderMenuItemQty, "", OrderMenuItemID, "1");

                       //   dao.addToOrderValue( maps.get(index).get("OrderMenuItemName"), maps.get(index).get("OrderMenuItemPrice"), "0", sharedPreferences.getString("email", ""), "",maps.get(index).get("OrderMenuItemID"),"1");

                        //  dao.addToOrderValue(maps.get(position).get("MenuItemName"), maps.get(position).get("MenuItemPrice"), String.valueOf(value), getFromSdcard("/MenuApp/MenuItemGrid/").get(indexFlag), maps.get(position).get("menu_id"),"0");

                      }

                  }


              }

                for(int index = 0;index<tagInfoModels.size();index++){

                    Log.d("clili",tagInfoModels.get(index).getChili()+"");
                    Log.d("veg",tagInfoModels.get(index).getVeg()+"");
                    Log.d("NonVeg",tagInfoModels.get(index).getNonveg()+"");
                    Log.d("FIsh",tagInfoModels.get(index).getFish()+"");


                }




              //LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.lin);

             // CustomNumberPicker customNumberPicker = new CustomNumberPicker(linearLayout);

              txt_food_name.setTypeface(typeface);

              food_price.setTypeface(typeface);



        /*View vv = parent.getRootView();
        final TextView texto = (TextView) vv.findViewById(R.id.txt_item_select);*/





             // RelativeLayout rel_amount = (RelativeLayout)convertView.findViewById(R.id.rel_amount);

            /*  final HorizontalNumberPicker numberPicker = (HorizontalNumberPicker) convertView.findViewById(R.id.horizontal_number_picker);




              ImageView img_plus = (ImageView)numberPicker.findViewById(R.id.btn_plus);

              ImageView img_minus = (ImageView) numberPicker.findViewById(R.id.btn_minus);*/

              //   final TextView edit_amount = (TextView)numberPicker.findViewById(R.id.btn_plus);


             /* if(check == false && flag<=maps.size()-1 ) {
                  int a=dao.updateSelectedItemsInMenu(maps.get(position).get("MenuItemName"), "0");

                  Log.d("updated", position + "updated" + a);
                  flag++;

              }else{
                  check = true;
              }*/


              edit_text.setText(String.valueOf(dao.getUpdatedSelectedItems(maps.get(position).get("MenuItemName"))));



              img_share.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      try {
                          if (boolFlag == false) {
                              Log.d("menu_id", maps.get(position).get("menu_id"));
                              showPopupWindow(position);
                              boolFlag = true;
                          } else {
                              mPopupWindow.dismiss();
                              boolFlag = false;
                          }
                      } catch (Exception e) {

                          e.printStackTrace();
                      }

                      // rel_val.setBackgroundResource(R.drawable.increment_decrement_focus);

                     /* ShareLinkContent content = new ShareLinkContent.Builder()
                              .setContentUrl(Uri.parse(JsonFunctions.BASE_URL+maps.get(position).get("MenuItemImageURL")))
                              .build();*/

                     /* Intent intent = new Intent(mContext,FaceBookActivity.class);

                      intent.putExtra("Item_name",maps.get(position).get("MenuItemName"));
                      intent.putExtra("Desc_item",maps.get(position).get("MenuItemDesc"));
                      intent.putExtra("MenuItemImageURL",JsonFunctions.BASE_URL + maps.get(position).get("MenuItemImageURL"));

                      mContext.startActivity(intent);*/


                      /*try {

                          if (ShareDialog.canShow(ShareLinkContent.class)) {
                              ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                      .setContentTitle(maps.get(position).get("MenuItemName"))
                                      .setContentDescription(
                                              maps.get(position).get("MenuItemDesc"))
                                      .setContentUrl(Uri.parse(JsonFunctions.BASE_URL + maps.get(position).get("MenuItemImageURL")))
                                      .build();

                              shareDialog.show(linkContent);
                          }

                      }catch (Exception e){

                          e.printStackTrace();
                      }*/
                  }
              });

              Log.d("totalamt",String.valueOf(sharedPreferences.getString("totalAmt", "")));
              txt_item_select.setText("Rs. "+String.valueOf(sharedPreferences.getString("totalAmt", "")));
              txt_price_val.setText(String.valueOf(sharedPreferences.getString("selectedItems", "")));
              //dao.updateSelectedItemsInMenu(maps.get(position).get("MenuItemName"), String.valueOf(sharedPreferences.getString("selectedItems", "")));
              edit_text.setText(String.valueOf(dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName"))));
              plus.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {


                      Log.d("clicked", "plus");

                      int value = dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName")) + 1;

                      Log.d("valueselectedItems", value + "");
                      dao.updateSelectedItemsInMenu(maps.get(position).get("MenuItemName"), String.valueOf(value));
                      value = dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName"));

                      amount++;
                      mnyAmt = mnyAmt + Double.valueOf(maps.get(position).get("MenuItemPrice"));

                      putSharedPreference(String.valueOf(amount), String.valueOf(mnyAmt));
                      txt_price_val.setText(String.valueOf(amount));
                      txt_item_select.setText("Rs." + String.valueOf(mnyAmt));

                      try {


                          dao.removeIfExists(maps.get(position).get("MenuItemName"));
                          dao.addToOrderValue(maps.get(position).get("MenuItemName"), maps.get(position).get("MenuItemPrice"), String.valueOf(value), getFromSdcard("/MenuApp/MenuItemGrid/").get(indexFlag), maps.get(position).get("menu_id"),"0");


                      } catch (Exception e) {

                          e.printStackTrace();
                      }
                      edit_text.setText(String.valueOf(value));
                  }
              });

              minus.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {






                      Log.d("selectedItemsInMinus",dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName"))+"");
                      if(dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName"))>0) {
                          Log.d("clicked", "minus");
                          int value = dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName")) - 1;
                          if(amount>0) {
                              amount--;
                          }
                          if(mnyAmt>0) {
                              mnyAmt = mnyAmt - Double.valueOf(maps.get(position).get("MenuItemPrice"));

                          }

                          putSharedPreference(String.valueOf(amount),String.valueOf(mnyAmt));
                          txt_price_val.setText(String.valueOf(amount));
                          txt_item_select.setText("Rs."+String.valueOf(mnyAmt));


                          Log.d("value", value + "");
                          dao.updateSelectedItemsInMenu(maps.get(position).get("MenuItemName"), String.valueOf(value));
                          value = dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName"));

                          try {
                              dao.removeIfExists(maps.get(position).get("MenuItemName"));
                              dao.addToOrderValue(maps.get(position).get("MenuItemName"), maps.get(position).get("MenuItemPrice"), String.valueOf(value), getFromSdcard("/MenuApp/MenuItemGrid/").get(indexFlag),maps.get(position).get("menu_id"),"0");
                          } catch (Exception e) {

                              e.printStackTrace();
                          }
                          edit_text.setText(String.valueOf(value));
                      }
                  }
              });

          /*    img_plus.setOnTouchListener(new View.OnTouchListener() {
                  @Override
                  public boolean onTouch(View v, MotionEvent event) {
                      //  HorizontalNumberPicker.finalAmount = HorizontalNumberPicker.finalAmount + 1;
                      totalAmountMoney = Double.valueOf( maps.get(position).get("MenuItemPrice"));

                      //  numberPicker.getamt(totalAmountMoney);

                      //   totalAmtVal.put("amt",Double.valueOf(totalAmtVal.get("amt")+Double.valueOf( maps.get(position).get("MenuItemPrice"))))

                      Log.d("value", getPriceAddValue(Double.valueOf(maps.get(position).get("MenuItemPrice")), Double.valueOf(numberPicker.getAmount())) + "");

                      selectedItems.put(position, numberPicker.getAmount() + 1);


                      txt_price_val.setText(String.valueOf(HorizontalNumberPicker.finalAmount + 1));
              // MainActivity.mainAmount +=Double.valueOf( maps.get(position).get("MenuItemPrice"));

               MainActivity.numberOfItemsSelected = String.valueOf(HorizontalNumberPicker.finalAmount + 1);
                       txt_item_select.setText(String.valueOf(HorizontalNumberPicker.amountMoney + totalAmountMoney));
               MainActivity.totalAmtValue = String.valueOf(HorizontalNumberPicker.amountMoney+totalAmountMoney);
              // txt_item_select.setText(String.valueOf(MainActivity.mainAmount));
              *//* MainActivity.moneyAmount = MainActivity.moneyAmount+ Double.valueOf(maps.get(position).get("MenuItemPrice"));
               Log.d("total Value", HorizontalNumberPicker.finalAmount + 1 + "");

               Log.d("MOney Value",MainActivity.moneyAmount + "");*//*
               // texto.setText(String.valueOf(HorizontalNumberPicker.finalAmount + 1));
               //textView.setText(String.valueOf(HorizontalNumberPicker.finalAmount + 1));

                if(numberPicker.getAmount()+1>0) {

                    try {
                       *//* if (ResturantInfo.orderedItems.get(position).isEmpty()) {*//*

                        for(int index = 0;index<ResturantInfo.orderedItems.size();index++){

                            if(ResturantInfo.orderedItems.get(index).get("MenuItemName").equals(maps.get(position).get("MenuItemName"))){

                              //  if( !ResturantInfo.orderedItems.get(position).equals(null)){

                                    ResturantInfo.orderedItems.remove(index);

                              //  }

                            }

                        }



                      //  ResturantInfo.orderedItems.remove(position);
                    }catch (Exception e){

                        e.printStackTrace();
                    }

                    try {
                        selectedValue = new HashMap<>();
                        selectedValue.put("MenuItemName", maps.get(position).get("MenuItemName"));
                        selectedValue.put("MenuItemPrice", maps.get(position).get("MenuItemPrice"));
                        selectedValue.put("MenuItemImageURL", maps.get(position).get("MenuItemImageURL"));
                        selectedValue.put("selectedItems", String.valueOf(numberPicker.getAmount() + 1));


                        Log.d("Itemselected", String.valueOf(numberPicker.getAmount()+1));
                        dao.removeIfExists(maps.get(position).get("MenuItemName"));
                        dao.addToOrderValue(maps.get(position).get("MenuItemName"), maps.get(position).get("MenuItemPrice"), String.valueOf(numberPicker.getAmount() + 1), maps.get(position).get("MenuItemImageURL"));

                        ResturantInfo.orderedItems.add(selectedValue);

                    }catch (Exception e){

                        e.printStackTrace();
                    }
                      *//*  } else {
                            ResturantInfo.orderedItems.remove(position);
                            selectedValue = new HashMap<>();
                            selectedValue.put("MenuItemName", maps.get(position).get("MenuItemName"));
                            selectedValue.put("MenuItemPrice", maps.get(position).get("MenuItemPrice"));
                            selectedValue.put("MenuItemImageURL", maps.get(position).get("MenuItemImageURL"));
                            selectedValue.put("selectedItems", String.valueOf(numberPicker.getAmount() + 1));
                            ResturantInfo.orderedItems.add(position, selectedValue);

                        }*//*

                }
               return false;
           }
       });*/


     /*   img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HorizontalNumberPicker.finalAmount=HorizontalNumberPicker.finalAmount+1;
                Log.d("total Value", HorizontalNumberPicker.finalAmount  + "");
            }
        });
        img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HorizontalNumberPicker.finalAmount = HorizontalNumberPicker.finalAmount - 1;
                Log.d("total Value", HorizontalNumberPicker.finalAmount + "");
            }
        });
*/
       /* img_minus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d("Value", getPriceMinValue(Double.valueOf(maps.get(position).get("MenuItemPrice")), Double.valueOf(numberPicker.getAmount())) + "");

                if((numberPicker.getAmount()-1)>=0) {
                    totalAmountMoney = Double.valueOf( maps.get(position).get("MenuItemPrice"));
                    selectedItems.put(position, numberPicker.getAmount() - 1);


                    MainActivity.mainAmount -=Double.valueOf( maps.get(position).get("MenuItemPrice"));
                    txt_price_val.setText(String.valueOf(HorizontalNumberPicker.finalAmount - 1));

                    MainActivity.numberOfItemsSelected = String.valueOf(HorizontalNumberPicker.finalAmount + 1);
                    txt_item_select.setText(String.valueOf(HorizontalNumberPicker.amountMoney-totalAmountMoney));

                    MainActivity.totalAmtValue = String.valueOf(HorizontalNumberPicker.amountMoney-totalAmountMoney);
                    //txt_item_select.setText(String.valueOf(MainActivity.mainAmount));
                }

                if(numberPicker.getAmount()+1>0) {
                    try {
                       *//* if (ResturantInfo.orderedItems.get(position).isEmpty()) {*//*

                        for(int index = 0;index<ResturantInfo.orderedItems.size();index++){

                            if(ResturantInfo.orderedItems.get(index).get("MenuItemName").equals(maps.get(position).get("MenuItemName"))){

                                //  if( !ResturantInfo.orderedItems.get(position).equals(null)){

                                ResturantInfo.orderedItems.remove(index);

                                //  }

                            }

                        }

                      *//*  if( !ResturantInfo.orderedItems.get(position).equals(null)){

                            ResturantInfo.orderedItems.remove(ResturantInfo.orderedItems.size()-1);

                        }*//*
                      //  ResturantInfo.orderedItems.remove(position);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    try {
                        selectedValue = new HashMap<>();
                        selectedValue.put("MenuItemName", maps.get(position).get("MenuItemName"));
                        selectedValue.put("MenuItemPrice", maps.get(position).get("MenuItemPrice"));
                        selectedValue.put("MenuItemImageURL", maps.get(position).get("MenuItemImageURL"));
                       // selectedValue.put("selectedItems", String.valueOf(numberPicker.getAmount() - 1));

                        Log.d("Itemselected",String.valueOf(numberPicker.getAmount()));
                        dao.removeIfExists(maps.get(position).get("MenuItemName"));
                        dao.addToOrderValue(maps.get(position).get("MenuItemName"), maps.get(position).get("MenuItemPrice"),String.valueOf(numberPicker.getAmount()), maps.get(position).get("MenuItemImageURL"));

                        ResturantInfo.orderedItems.add(selectedValue);

                    }catch (Exception e){

                        e.printStackTrace();
                    }
                }else{



                }

            *//*    if(numberPicker.getAmount()+1>0) {
                   *//**//* selectedValue = new HashMap<>();
                    selectedValue.put("MenuItemName",maps.get(position).get("MenuItemName"));
                    selectedValue.put("MenuItemPrice",maps.get(position).get("MenuItemPrice"));
                    selectedValue.put("MenuItemImageURL",maps.get(position).get("MenuItemImageURL"));
                    ResturantInfo.orderedItems.add(selectedValue);*//**//*

                    selectedValue.put("MenuItemName",maps.get(position).get("MenuItemName"));
                    selectedValue.put("MenuItemPrice",maps.get(position).get("MenuItemPrice"));
                    selectedValue.put("MenuItemImageURL",maps.get(position).get("MenuItemImageURL"));
                    selectedValue.put("selectedItems",String.valueOf(numberPicker.getAmount()-1));
                    ResturantInfo.orderedItems.add(selectedValue);
                }*//*
              //  HorizontalNumberPicker.finalAmount=HorizontalNumberPicker.finalAmount-1;
            *//*   MainActivity.moneyAmount =MainActivity.moneyAmount- Double.valueOf(maps.get(position).get("MenuItemPrice"));
                Log.d("MOney Value",MainActivity.moneyAmount + "");
                Log.d("total Value", HorizontalNumberPicker.finalAmount-1 + "");*//*
               // textView.setText(String.valueOf(HorizontalNumberPicker.finalAmount + 1));
                return false;
            }
        });
*/
       /* if(numberPicker.getAmount() >0) {
            MainActivity.selectedPositions.add(position);
        }*/
     //  Log.d("total Value", numberPicker.getAmount() + "");
       /* numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });*/

       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = numberPicker.getFinalAmount();
                Log.d("total Value", val + "");
            }
        });*/

      /*  numberPicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {



                return false;
            }
        });*/

       /* */



        txt_food_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)mContext);
                    Intent intent = new Intent(mContext, PlaceOrderActivity.class);

                    intent.putExtra("price", maps.get(position).get("MenuItemPrice"));
                    intent.putExtra("image", maps.get(position).get("MenuItemImageURL"));
                    intent.putExtra("food_name", maps.get(position).get("MenuItemName"));
                    intent.putExtra("food_description", maps.get(position).get("MenuItemDesc"));
                    intent.putExtra("position", String.valueOf(position));
                    intent.putExtra("amount", String.valueOf(amount));
                    intent.putExtra("menu_id", maps.get(position).get("menu_id"));

                    Log.d("amount------>", String.valueOf(amount));

                    HorizontalNumberPicker.finalAmount = 0;
                    mContext.startActivity(intent,options.toBundle());
                }else{

                    Intent intent = new Intent(mContext, PlaceOrderActivity.class);

                    intent.putExtra("price", maps.get(position).get("MenuItemPrice"));
                    intent.putExtra("image", maps.get(position).get("MenuItemImageURL"));
                    intent.putExtra("food_name", maps.get(position).get("MenuItemName"));
                    intent.putExtra("food_description", maps.get(position).get("MenuItemDesc"));
                    intent.putExtra("position", String.valueOf(position));
                    intent.putExtra("amount", String.valueOf(amount));
                    intent.putExtra("menu_id", maps.get(position).get("menu_id"));

                    Log.d("amount------>", String.valueOf(amount));

                    HorizontalNumberPicker.finalAmount = 0;
                    mContext.startActivity(intent);
                  // mContext.overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                }


            }
        });



      /*  edit_amount.setText(String.valueOf(amount));
        amount = list.get(position);
        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount++;
                list.add(position,amount);
                edit_amount.setText(String.valueOf(amount));
            }
        });

        img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount--;
                list.add(position,amount);
                edit_amount.setText(String.valueOf(amount));
            }
        });*/
       //ResturantEntryActivity.DownloadFromUrl("/MenuApp/MenuItemGrid/",position+".jpg",JsonFunctions.BASE_URL +maps.get(position).get("MenuItemImageURL") );
    try {




        for(int index = 0;index<getFromSdcard("/MenuApp/MenuItemGrid/").size();index++){

            Log.d("all images......",maps.get(position).get("MenuItemName"));

            Log.d("all images1......", Utility.getItemImageName(getFromSdcard("/MenuApp/MenuItemGrid/").get(index)));

            boolean value = Utility.getItemImageName(getFromSdcard("/MenuApp/MenuItemGrid/").get(index)).equals(maps.get(position).get("MenuItemName"));
        if(value == true) {

            indexFlag= index;
            Picasso.with(mContext)
                /*.load(JsonFunctions.BASE_URL+maps.get(position).get("MenuItemImageURL"))*/
                    .load(new File(getFromSdcard("/MenuApp/MenuItemGrid/").get(index)))
                    .placeholder(R.mipmap.ic_launcher) // optional
                    .error(R.mipmap.ic_launcher)
                    .transform(new RoundedCorner(4, 0))// optional
                    .into(grid_img);
        }else{


           // Toast.makeText(mContext,"Can't show images",Toast.LENGTH_LONG).show();
        }
        }

    }catch (Exception e){

        e.printStackTrace();
    }

              try {


                  for(int index = 0;index<tagInfoModels.size();index++){
                 // if (tagInfoModels.get(index).getChili().equals("1")) {

                      if (tagInfoModels.get(index).getChili().equals("1")) {
                          img_view4.setVisibility(View.VISIBLE);
                          img_view4.setImageResource(R.drawable.high_chilli);


                      }
                      if (tagInfoModels.get(index).getFish().equals("1")) {
                          img_view3.setVisibility(View.VISIBLE);
                          img_view3.setImageResource(R.drawable.fish);

                      }

                      if (tagInfoModels.get(index).getVeg().equals("1")) {
                          img_view1.setVisibility(View.VISIBLE);
                          img_view1.setImageResource(R.drawable.veg);

                      }

                      if (tagInfoModels.get(index).getNonveg().equals("1")) {
                          img_view2.setVisibility(View.VISIBLE);
                          img_view2.setImageResource(R.drawable.non_veg);

                      }
                  }
                //  }
              }catch (Exception e){

                  e.printStackTrace();
              }




        /*img_view5.setImageResource(R.drawable.chef_reco);
        img_view6.setImageResource(R.drawable.high_rated);*/

        txt_food_name.setText(maps.get(position).get("MenuItemName"));
        food_price.setText("Rs " + maps.get(position).get("MenuItemPrice"));








        list1.add(selectedItems);

        return convertView;
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

                Log.d("val", listFile[i].getAbsolutePath());

            }
        }

        return  f;
    }


    public int getAmount(int amount){

        return amount;


    }


    double getPriceAddValue(Double amt,Double number){

        double val = 0;
        val = amt*number;
        totalAmt = totalAmt+val;


        return totalAmt;

    }

    double getPriceMinValue(Double amt,Double number){

        double val = 0;
        val = amt*number;
        totalAmt = totalAmt-val;
        return totalAmt;

    }

    public static double getAmountMoney() {


        return totalAmountMoney ;

    }


   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        numberPicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount));

                // for(int)
                Log.d("selectedItems", GridViewAdapter.selectedItems + "");

                for (int index = 0; index < GridViewAdapter.selectedItems.size(); index++) {

                    double amount = 0;
                    for (int key : GridViewAdapter.selectedItems.keySet()) {
                        double amount1 = 0;
                        Log.d("key : ", key + "");


                        amount1 = amount1 + Double.valueOf(GridViewAdapter.selectedItems.get(key) * Double.valueOf(menuInfoModelList.get(key).getMenuItemPrice()));
                        amount = amount + amount1;



                        Log.d("value : ", GridViewAdapter.selectedItems.get(key) + "");
                    }


                    if(amount<0) {
                        amount = 0;
                        txt_price_val.setText("₹ " + amount);

                    }
                }
                return false;
            }
        });

    }*/

   static double getTotalAmt(double val){

       double value=val;
       return  value;
    }

     public void  putSharedPreference(String selectedItems,String totalAmt){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedItems",selectedItems);
        editor.putString("totalAmt", totalAmt);

        editor.commit();

    }

/*private void showPopup(final Activity context, Point p) {
   int popupWidth = 200;
   int popupHeight = 150;

   // Inflate the popup_layout.xml
   LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.relative_lay);
   LayoutInflater layoutInflater = (LayoutInflater) context
     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

   // Creating the PopupWindow
   final PopupWindow popup = new PopupWindow(context);
   popup.setContentView(layout);
   popup.setWidth(popupWidth);
   popup.setHeight(popupHeight);
   popup.setFocusable(true);

   // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
   int OFFSET_X = 30;
   int OFFSET_Y = 30;

   // Clear the default translucent background
   popup.setBackgroundDrawable(new BitmapDrawable());

   // Displaying the popup at the specified location, + offsets.
   popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

   // Getting a reference to Close button, and close the popup when clicked.
   Button close = (Button) layout.findViewById(R.id.close);

}*/

    private void showPopupWindow(final int position){

        //new ResturantOffer().execute();
        new MenuOffer().execute(position);
        LayoutInflater mLayoutInflater=LayoutInflater.from(mContext);

        View mView=mLayoutInflater.inflate(R.layout.share_popup, null /*(ViewGroup)resturant_review_rating*/);



        mPopupWindow=new PopupWindow(mView,  WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);

        // mPopupWindow=new PopupWindow(mView,LayoutParams.WRAP_CONTENT);

        mPopupWindow.showAtLocation(mView, Gravity.CENTER, 0, 0);

        mPopupWindow.setContentView(mView);
       /* mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
                ""));*/

        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(false);
        //  mPopupWindow.setOutsideTouchable(true);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.animation);
        mView.startAnimation(hyperspaceJumpAnimation);


        txt_share_content = (TextView)mView.findViewById(R.id.txt_share_content);
        final RelativeLayout fb_pop= (RelativeLayout)mView.findViewById(R.id.fb_pop);
        txt_share= (TextView)mView.findViewById(R.id.txt_share);

        //  Button btn_submit_rating= (Button)mView.findViewById(R.id.btn_submit_rating);
        ImageView btn_close= (ImageView)mView.findViewById(R.id.btn_close);
      /*  mBtnCancel=(Button) mView.findViewById(R.id.btn_cancel);

        mBtnCancel.setOnClickListener(this);*/

        CardView card_view3 = (CardView)mView.findViewById(R.id.card_view3);

        CardView card_view4 = (CardView)mView.findViewById(R.id.card_view4);

        Drawable d = new ColorDrawable(Color.WHITE);

        d.setAlpha(130);



        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mPopupWindow.dismiss();
                //rel_val.setAlpha(1);
                return true;
            }

        });

        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
                boolFlag = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)mContext);
                    Intent intent = new Intent(mContext,FaceBookActivity.class);

                    intent.putExtra("Item_name",maps.get(position).get("MenuItemName"));
                    intent.putExtra("Desc_item",maps.get(position).get("MenuItemDesc"));
                    intent.putExtra("MenuItemImageURL",JsonFunctions.BASE_URL + maps.get(position).get("MenuItemImageURL"));

                    mContext.startActivity(intent);
                    mContext.startActivity(intent, options.toBundle());

                } else {

                    Intent intent = new Intent(mContext,FaceBookActivity.class);

                    intent.putExtra("Item_name",maps.get(position).get("MenuItemName"));
                    intent.putExtra("Desc_item",maps.get(position).get("MenuItemDesc"));
                    intent.putExtra("MenuItemImageURL", JsonFunctions.BASE_URL + maps.get(position).get("MenuItemImageURL"));

                    mContext.startActivity(intent);
                    mContext.startActivity(intent);
                   // mContext.overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);


                }


            }
        });

        card_view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mPopupWindow.dismiss();
                    boolFlag = false;
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "I Love You.");
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");
                    mContext.startActivity(sendIntent);


                }catch (Exception e){

                    Toast.makeText(mContext, "Please install whatsapp", Toast.LENGTH_LONG).show();
                    mPopupWindow.dismiss();
                    e.printStackTrace();
                }
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // review = edit_text.getText().toString();

                mPopupWindow.dismiss();
               // rel_val.setAlpha(1);
                fb_pop.setAlpha(1);
                boolFlag = false;

            }
        });


        //mPopupWindow.showAsDropDown(mBtnPopUpWindow, 0, 0, Gravity.LEFT);

       // getWindow().setBackgroundDrawable(d);





    }

    class MenuOffer extends AsyncTask<Integer,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }


        @Override
        protected Void doInBackground(Integer... params) {

            ServiceHandler handler = new ServiceHandler();

            JsonFunctions functions = new JsonFunctions(handler);

            String json = functions.getMenuShareOffer(maps.get(params[0]).get("menu_id"));

            try{

                JSONObject jsonObject = new JSONObject(json);

                JSONObject offerInfo = jsonObject.getJSONObject("offerInfo");

                offer_id = offerInfo.getString("offer_id");
                title= offerInfo.getString("title");
                description =offerInfo.getString("description");
                imageUrl = offerInfo.getString("imageUrl");
                value = offerInfo.getString("value");
                type= offerInfo.getString("type");

                Map<String,String> map = new HashMap<>();

                map.put("offer_id",offer_id);
                map.put("title",title);
                map.put("description",description);
                map.put("imageUrl",imageUrl);
                map.put("value",value);
                map.put("type",type);

                sharedDocumnents.add(map);



            }catch (Exception e){


                // Toast.makeText(ResturantInfo.this,"No connection",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }


            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

                for(int index = 0;index<sharedDocumnents.size();index++) {
                    txt_share_content.setText(sharedDocumnents.get(index).get("description"));
                    txt_share.setText(sharedDocumnents.get(index).get("title"));
                }
            }catch (Exception e){

                e.printStackTrace();
            }

        }
    }

}
