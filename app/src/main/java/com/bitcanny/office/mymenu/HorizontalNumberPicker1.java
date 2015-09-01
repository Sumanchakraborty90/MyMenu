package com.bitcanny.office.mymenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by OFFICE on 17-08-2015.
 */
public class HorizontalNumberPicker1 extends LinearLayout  {
    static int finalAmount = 0;
     static int amount1=0 ;
    static double amountMoney = Double.valueOf(MainActivity.totalAmtValue);
    static double totalVal = 0.0;
    PlaceOrderSqlHelperDao dao;
    HorizontalBean bean;




    public HorizontalNumberPicker1(Context context, AttributeSet attrs) {
        super(context, attrs);


        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = layoutInflater.inflate(R.layout.horizontal_number_picker, this);
       // amount1 = amount;

      /*  View view = View.inflate(context, R.layout.navigation_drawer, this);

        final TextView txt_item_select = (TextView) view.findViewById(R.id.txt_item_select);*/

        ImageView img_plus = (ImageView) root.findViewById(R.id.btn_plus);

        ImageView img_minus = (ImageView) root.findViewById(R.id.btn_minus);

        final TextView edit_amount = (TextView) root.findViewById(R.id.edit_text);
         bean = new HorizontalBean( CartOrderAdapter.totalAmt);

        //amount1 = CartOrderAdapter.totalAmt;
        //amount1 =0;


        amount1 =bean.getAmount();
       // amount1=bean.getAmount();

      //  amount1=getAmountValue();

//        amount1 =Integer.valueOf(edit_amount.getText().toString());
        edit_amount.setText(String.valueOf(amount1));


        img_plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                amount1++;
                finalAmount =finalAmount+1;
                //  txt_item_select.setText(String.valueOf(finalAmount));

                amountMoney = amountMoney+CartOrderAdapter.totalAmountMoney;

             //   GridViewAdapter.getTotalAmt(amountMoney);
                edit_amount.setText(String.valueOf(amount1));

                bean.setAmount(amount1);
            }
        });

        img_minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(amount1>0) {
                    amount1--;
                    finalAmount =finalAmount-1;
                    // txt_item_select.setText(String.valueOf(finalAmount));

                    amountMoney = amountMoney-CartOrderAdapter.totalAmountMoney;

                    edit_amount.setText(String.valueOf(amount1));

                    bean.setAmount(amount1);
                }
            }
        });



    }
    public int getAmount(){


        return  CartOrderAdapter.getSelection();
    }

    public int getFinalAmount(){



        return finalAmount;
    }

    static  double getFinalAmountValue(){


        return amountMoney;
    }

    void getamt(double value){

        amountMoney = value;

    }


}
