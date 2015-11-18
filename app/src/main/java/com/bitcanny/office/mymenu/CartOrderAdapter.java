package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 22-07-2015.
 */
public class CartOrderAdapter extends ArrayAdapter {

    Context context;
   // List<Map<String,String>> maps;
    List<OrderToCartAdapterModel> maps;
    Typeface typeface;
    TextView txt_amount_payable,sub_total;
    List<TagInfoModel> tagInfoModels;
    static int totalAmt=0;
    static  Double totalAmountMoney=0.0;
    static int pos = 0;
    boolean check = false;
    int flag = 0;
    PlaceOrderSqlHelperDao dao;
    List<Map<String,String>> previousOrder;
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    List<TaxChargesModel> chargesValue;

    public CartOrderAdapter(Context context, int resource, List<OrderToCartAdapterModel> maps,TextView sub_total,TextView txt_amount_payable, List<TaxChargesModel> chargesValue,List<Map<String,String>> previousOrder) {
        super(context, resource, maps);
        this.maps = maps;
        this.context = context;
        this.sub_total = sub_total;
        this.txt_amount_payable = txt_amount_payable;
        this.chargesValue = chargesValue;
        tagInfoModels = new ArrayList<>();
        this.previousOrder = previousOrder;
        dao = new PlaceOrderSqlHelperDao(context);
        sharedPreferences = context.getSharedPreferences(MYPREF,Context.MODE_PRIVATE);
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ufonts.com_century-gothic.ttf");

        txt_amount_payable.setTypeface(typeface);
        sub_total.setTypeface(typeface);
        //HorizontalNumberPicker1.

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.order_to_cart,parent,false);


        pos = position;

        RelativeLayout rel1 = (RelativeLayout) convertView.findViewById(R.id.rel1);
        ImageView plus = (ImageView) convertView.findViewById(R.id.btn_plus);
        ImageView minus = (ImageView) convertView.findViewById(R.id.btn_minus);
        final TextView edit_text = (TextView)convertView.findViewById(R.id.edit_text);

        TextView txt_ordered = (TextView) convertView.findViewById(R.id.txt_ordered);
        edit_text.setTypeface(typeface);

        Log.d("previousOrderCart", previousOrder.size() + "");


        for(int index = 0;index<previousOrder.size();index++) {
            if (maps.get(position).getOrder_name().equals(previousOrder.get(index).get("OrderMenuItemName"))) {
                txt_ordered.setVisibility(View.VISIBLE);
                txt_ordered.setText(String.valueOf(previousOrder.get(index).get("OrderMenuItemQty"))+" Ordered");

            }
        }

      /*  if(maps.get(position).getUser_order_flag().equals("0") && maps.get(position).getOrder_item_quantity().equals("0")){


            rel1.setVisibility(View.GONE);

            // edit_text.setText(String.valueOf("0"));


        }*/


/*
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);

        TextView txt_item_name = (TextView) convertView.findViewById(R.id.txt_item_name);

        TextView txt_price = (TextView) convertView.findViewById(R.id.txt_price);

        Spinner spinner = (Spinner) convertView.findViewById(R.id.spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, getItems());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Picasso.with(context).load(JsonFunctions.BASE_URL+maps.get(position).get("order_item_image_url"))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(img_icon);

        txt_item_name.setText(maps.get(position).get("order_name"));
        txt_price.setText("Rs. "+maps.get(position).get("order_item_price"));


*/  /* map.put("MenuItemName",userCart.get(index).getOrder_name());
        map.put("MenuItemPrice",userCart.get(index).getOrder_item_price());
        map.put("OrderMenuItemQty",userCart.get(index).getOrder_item_quantity());
        map.put("MenuItemIMage", userCart.get(index).getOrder_item_image_url());
        map.put("menu_id", userCart.get(index).getMenu_id());*/


        tagInfoModels = dao.getTagDetails(maps.get(position).getMenu_id());
        Log.d("TagINfoModelSize",tagInfoModels.size()+"");
        totalAmountMoney = Double.valueOf(maps.get(position).getOrder_item_price());
        CartOrderActivity.setAmountPayable(chargesValue, GridViewAdapter.mnyAmt, txt_amount_payable);
     //   Log.d("numberOfitems selected",maps.get(position).get("selectedItems"));


        if(check == false && flag<=maps.size()-1 ) {
            int a=dao.updateSelectedItems(maps.get(position).getOrder_name(),maps.get(position).getOrder_item_quantity());
            putSharedPreference(String.valueOf(GridViewAdapter.amount), String.valueOf(GridViewAdapter.mnyAmt));
            Log.d("updated", position + "updated" + a);
            flag++;

        }else{
            check = true;
        }

        //edit_text.setText(String.valueOf(totalAmt));
       // Log.d("actual selected items", maps.get(position).getOrder_item_quantity());


        edit_text.setText(String.valueOf(dao.getUpdatedSelectedItems(maps.get(position).getOrder_name())));
        //edit_text.setText(maps.get(position).getOrder_item_quantity());

        Log.d("quantity",maps.get(position).getOrder_name()+"------------>"+String.valueOf(dao.getUpdatedSelectedItems(maps.get(position).getOrder_name())));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("clicked", "plus");

                int value = dao.getUpdatedSelectedItems(maps.get(position).getOrder_name()) + 1;

                Log.d("value", value + "");
                GridViewAdapter.mnyAmt = GridViewAdapter.mnyAmt + Double.valueOf(maps.get(position).getOrder_item_price());
                txt_amount_payable.setText(String.valueOf(GridViewAdapter.mnyAmt));
                CartOrderActivity.setAmountPayable(chargesValue, GridViewAdapter.mnyAmt, txt_amount_payable);
                GridViewAdapter.amount++;

                putSharedPreference(String.valueOf(GridViewAdapter.amount), String.valueOf(GridViewAdapter.mnyAmt));
                sub_total.setText("Rs. " + String.valueOf(GridViewAdapter.mnyAmt));
                dao.updateSelectedItems(maps.get(position).getOrder_name(), String.valueOf(value));
                dao.updateSelectedItemsInMenu(maps.get(position).getOrder_name(), String.valueOf(value));
                value = dao.getUpdatedSelectedItems(maps.get(position).getOrder_name());
                edit_text.setText(String.valueOf(value));
                // totalAmt = value;
            }
        });

     /*   plus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int val = totalAmt + 1;
                edit_text.setText(val);
                return false;
            }
        });

        minus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int val = totalAmt - 1;
                edit_text.setText(val);
                return false;
            }
        });*/
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("clicked", "minus");

                    if (dao.getUpdatedSelectedItems(maps.get(position).getOrder_name()) > 0) {
                        int value = dao.getUpdatedSelectedItems(maps.get(position).getOrder_name()) - 1;

                        Log.d("value", value + "");
                        if (GridViewAdapter.amount > 0) {
                            GridViewAdapter.amount--;

                        }
                        if (GridViewAdapter.mnyAmt > 0) {
                            GridViewAdapter.mnyAmt = GridViewAdapter.mnyAmt - Double.valueOf(maps.get(position).getOrder_item_price());
                        }

                        putSharedPreference(String.valueOf(GridViewAdapter.amount), String.valueOf(GridViewAdapter.mnyAmt));
                        txt_amount_payable.setText(String.valueOf(GridViewAdapter.mnyAmt));

                        CartOrderActivity.setAmountPayable(chargesValue, GridViewAdapter.mnyAmt, txt_amount_payable);
                        sub_total.setText("Rs. " + String.valueOf(GridViewAdapter.mnyAmt));
                        dao.updateSelectedItems(maps.get(position).getOrder_name(), String.valueOf(value));
                        dao.updateSelectedItemsInMenu(maps.get(position).getOrder_name(), String.valueOf(value));
                        value = dao.getUpdatedSelectedItems(maps.get(position).getOrder_name());
                        edit_text.setText(String.valueOf(value));

                    }
                }
            });

       /// HorizontalBean bean = new HorizontalBean(Integer.valueOf(maps.get(position).getOrder_item_quantity()));

       // HorizontalNumberPicker1.amount1 = Integer.valueOf(maps.get(position).get("selectedItems"));
       // HorizontalNumberPicker1 picker1 = new HorizontalNumberPicker1(context,null,Integer.valueOf(maps.get(position).get("selectedItems")));

       // HorizontalBean bean = new HorizontalBean(totalAmt);
        HorizontalNumberPicker1 numberPicker1 = (HorizontalNumberPicker1)convertView.findViewById(R.id.horizontal_number_picker);


        HorizontalNumberPicker1.amount1 = totalAmt;
     /*   TextView edit_text = (TextView)numberPicker1.findViewById(R.id.edit_text);

        edit_text.setText(String.valueOf(maps.get(position).getOrder_item_quantity()));*/

        ImageView grid_img = (ImageView)convertView.findViewById(R.id.grid_img);



        TextView txt_food_name = (TextView)convertView.findViewById(R.id.txt_food_name);

        TextView food_price = (TextView) convertView.findViewById(R.id.food_price);

        txt_food_name.setTypeface(typeface);

        food_price.setTypeface(typeface);



        txt_food_name.setText(String.valueOf(maps.get(position).getOrder_name()));



        food_price.setText(String.valueOf(maps.get(position).getOrder_item_price()));

      /*  Log.d("image_url", maps.get(position).getOrder_item_image_url());
        Log.d("image_url", maps.get(position).getMenu_id());*/


        ImageView img_view1 = (ImageView)convertView.findViewById(R.id.img_view1);

        ImageView img_view2 = (ImageView)convertView.findViewById(R.id.img_view2);

        ImageView img_view3 = (ImageView)convertView.findViewById(R.id.img_view3);

        ImageView img_view4 = (ImageView)convertView.findViewById(R.id.img_view4);

        ImageView img_view5 = (ImageView)convertView.findViewById(R.id.img_view5);

        ImageView img_view6 = (ImageView)convertView.findViewById(R.id.img_view6);

        /*View vv = parent.getRootView();
        final TextView texto = (TextView) vv.findViewById(R.id.txt_item_select);*/
       // tagInfoModels = dao.getTagDetails(maps.get(position).getmenu);



        ImageView img_share = (ImageView)convertView.findViewById(R.id.img_share);
        RelativeLayout rel_amount = (RelativeLayout)convertView.findViewById(R.id.rel_amount);

       // final HorizontalNumberPicker1 numberPicker = (HorizontalNumberPicker1) convertView.findViewById(R.id.horizontal_number_picker);




        ImageView img_plus = (ImageView)numberPicker1.findViewById(R.id.btn_plus);



        ImageView img_minus = (ImageView) numberPicker1.findViewById(R.id.btn_minus);


        img_plus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


               // MainActivity.numberOfItemsSelected = String.valueOf(HorizontalNumberPicker.finalAmount + 1);
                sub_total.setText(String.valueOf(HorizontalNumberPicker1.amountMoney+totalAmountMoney));
                //MainActivity.totalAmtValue = String.valueOf(HorizontalNumberPicker.amountMoney+totalAmountMoney);

                return false;
            }
        });
        img_minus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //MainActivity.numberOfItemsSelected = String.valueOf(HorizontalNumberPicker.finalAmount + 1);
                sub_total.setText(String.valueOf(HorizontalNumberPicker1.amountMoney-totalAmountMoney));
               // HorizontalNumberPicker1.amountMoney = String.valueOf(HorizontalNumberPicker.amountMoney-totalAmountMoney);
                return false;
            }
        });


        try {

            for(int index = 0;index<getFromSdcard("/MenuApp/MenuItemGrid/").size();index++){

                // Log.d("all images......",getFromSdcard("/MenuApp/MenuItemGrid/").get(position));
                boolean value = Utility.getItemImageName(getFromSdcard("/MenuApp/MenuItemGrid/").get(index)).equals(maps.get(position).getOrder_name());
                if(value == true) {
//                    Log.d("bitcannyImageOrderPage",maps.get(index).getOrder_item_image_url());
                    // indexFlag= index;
                    Picasso.with(context)
                           // .load(new File(maps.get(index).getOrder_item_image_url()))
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
       /* img_view1.setImageResource(R.drawable.veg);
        img_view2.setImageResource(R.drawable.non_veg);
        img_view3.setImageResource(R.drawable.fish);
        img_view4.setImageResource(R.drawable.high_chilli);
        img_view5.setImageResource(R.drawable.chef_reco);
        img_view6.setImageResource(R.drawable.high_rated);*/


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




        return convertView;
    }

    public List<String> getItems(){
        ArrayList<String> list = new ArrayList<>();
        for (int index = 0;index<10;index++){

            list.add(String.valueOf(index));


        }
        return list;
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

  /*  public List<String> getFromSdcard(String path)
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
    }*/


    public static int getSelection(){

       return totalAmt;
    }


    public void putSharedPreference(String selectedItems,String totalAmt){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedItems",selectedItems);
        editor.putString("totalAmt", totalAmt);

        editor.commit();

    }

}
