package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

    private Context mContext;
    private List<Map<String,String>> maps;
    PlaceOrderSqlHelperDao dao;
    Typeface typeface;
   static int amount;
    static double mnyAmt;
    boolean amount_flag = false;
    static double totalAmountMoney = 0.0;
   // static double totalAmountMoney1 = 0.0;
    List<List<Integer>> listOrder;

    boolean check = false;
    int flag = 0;
    Button button;
    Map<String,Double> totalAmtVal = new HashMap<>();
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    int indexFlag;
    //ShareDialog shareDialog;
    List<Integer> list ;

    TextView textView,txt_price_val,txt_item_select;
    List<Map<Integer,Integer>> list1 = new ArrayList<>();
    static Map<String,String> selectedValue = Collections.emptyMap();

    static double totalAmt = 0.0;

   static Map<Integer,Integer> selectedItems = Collections.emptyMap();

    //static  Double moneyAmount = 0.0;

    public GridViewAdapter(Context mContext, List<Map<String, String>> maps, List<Integer> list,TextView txt_price_val,TextView txt_item_select) {
        this.mContext = mContext;
        this.maps = maps;
       this.list = list;
        selectedItems = new HashMap<>();
       // this.shareDialog = shareDialog;

        ResturantInfo.orderedItems.clear();
        this.txt_item_select = txt_item_select;
        this.txt_price_val = txt_price_val;
        dao= new PlaceOrderSqlHelperDao(mContext);
        sharedPreferences = mContext.getSharedPreferences(MYPREF,Context.MODE_PRIVATE);
        try {
            amount = Integer.valueOf(sharedPreferences.getString("selectedItems", ""));
            mnyAmt = Double.valueOf(sharedPreferences.getString("totalAmt", ""));
        }catch (Exception e){

            e.printStackTrace();;
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
              ImageView minus = (ImageView)convertView.findViewById(R.id.btn_minus);
              final TextView edit_text = (TextView)convertView.findViewById(R.id.edit_text);

              //LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.lin);

             // CustomNumberPicker customNumberPicker = new CustomNumberPicker(linearLayout);

              txt_food_name.setTypeface(typeface);

              food_price.setTypeface(typeface);

              ImageView img_view1 = (ImageView)convertView.findViewById(R.id.img_view1);

              ImageView img_view2 = (ImageView)convertView.findViewById(R.id.img_view2);

              ImageView img_view3 = (ImageView)convertView.findViewById(R.id.img_view3);

              ImageView img_view4 = (ImageView)convertView.findViewById(R.id.img_view4);

              ImageView img_view5 = (ImageView)convertView.findViewById(R.id.img_view5);

              ImageView img_view6 = (ImageView)convertView.findViewById(R.id.img_view6);

              final RelativeLayout rel_val = (RelativeLayout)convertView.findViewById(R.id.rel_val);

        /*View vv = parent.getRootView();
        final TextView texto = (TextView) vv.findViewById(R.id.txt_item_select);*/




              ImageView img_share = (ImageView)convertView.findViewById(R.id.img_share);
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

                     // rel_val.setBackgroundResource(R.drawable.increment_decrement_focus);

                     /* ShareLinkContent content = new ShareLinkContent.Builder()
                              .setContentUrl(Uri.parse(JsonFunctions.BASE_URL+maps.get(position).get("MenuItemImageURL")))
                              .build();*/

                      Intent intent = new Intent(mContext,FaceBookActivity.class);

                      intent.putExtra("Item_name",maps.get(position).get("MenuItemName"));
                      intent.putExtra("Desc_item",maps.get(position).get("MenuItemDesc"));
                      intent.putExtra("MenuItemImageURL",JsonFunctions.BASE_URL + maps.get(position).get("MenuItemImageURL"));

                      mContext.startActivity(intent);


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


              plus.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {


                      Log.d("clicked","plus");

                      int value = dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName"))+1;

                      Log.d("value", value + "");
                      dao.updateSelectedItemsInMenu(maps.get(position).get("MenuItemName"), String.valueOf(value));
                      value = dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName"));

                      amount++;
                      mnyAmt = mnyAmt+Double.valueOf(maps.get(position).get("MenuItemPrice"));

                      putSharedPreference(String.valueOf(amount),String.valueOf(mnyAmt));
                      txt_price_val.setText(String.valueOf(amount));
                      txt_item_select.setText(String.valueOf(mnyAmt));

                      try {


                              dao.removeIfExists(maps.get(position).get("MenuItemName"));
                              dao.addToOrderValue(maps.get(position).get("MenuItemName"), maps.get(position).get("MenuItemPrice"), String.valueOf(value), getFromSdcard("/MenuApp/MenuItemGrid/").get(indexFlag));


                      }catch (Exception e){

                          e.printStackTrace();
                      }
                      edit_text.setText(String.valueOf(value));
                  }
              });

              minus.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {


                      Log.d("clicked", "plus");
                      if(dao.getUpdatedSelectedItems(maps.get(position).get("MenuItemName"))>0) {
                          int value = dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName")) - 1;
                          if(amount>0) {
                              amount--;
                          }
                          if(mnyAmt>0) {
                              mnyAmt = mnyAmt - Double.valueOf(maps.get(position).get("MenuItemPrice"));

                          }

                          putSharedPreference(String.valueOf(amount),String.valueOf(mnyAmt));
                          txt_price_val.setText(String.valueOf(amount));
                          txt_item_select.setText(String.valueOf(mnyAmt));

                          Log.d("value", value + "");
                          dao.updateSelectedItemsInMenu(maps.get(position).get("MenuItemName"), String.valueOf(value));
                          value = dao.getUpdatedSelectedItemsMenu(maps.get(position).get("MenuItemName"));

                          try {
                              dao.removeIfExists(maps.get(position).get("MenuItemName"));
                              dao.addToOrderValue(maps.get(position).get("MenuItemName"), maps.get(position).get("MenuItemPrice"), String.valueOf(value), getFromSdcard("/MenuApp/MenuItemGrid/").get(indexFlag));
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

                Intent intent = new Intent(mContext, PlaceOrderActivity.class);

                intent.putExtra("price", maps.get(position).get("MenuItemPrice"));
                intent.putExtra("image", maps.get(position).get("MenuItemImageURL"));
                intent.putExtra("food_name", maps.get(position).get("MenuItemName"));
                intent.putExtra("food_description", maps.get(position).get("MenuItemDesc"));
                intent.putExtra("position", String.valueOf(position));
                intent.putExtra("amount", String.valueOf(amount));

                Log.d("amount------>", String.valueOf(amount));

                HorizontalNumberPicker.finalAmount =0;
                        mContext.startActivity(intent);

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

           // Log.d("all images......",getFromSdcard("/MenuApp/MenuItemGrid/").get(position));
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
        }
        }

    }catch (Exception e){

        e.printStackTrace();
    }
        img_view1.setImageResource(R.drawable.veg);
        img_view2.setImageResource(R.drawable.non_veg);
        img_view3.setImageResource(R.drawable.fish);
        img_view4.setImageResource(R.drawable.high_chilli);
        img_view5.setImageResource(R.drawable.chef_reco);
        img_view6.setImageResource(R.drawable.high_rated);

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

    public void putSharedPreference(String selectedItems,String totalAmt){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedItems",selectedItems);
        editor.putString("totalAmt", totalAmt);

        editor.commit();

    }
}
