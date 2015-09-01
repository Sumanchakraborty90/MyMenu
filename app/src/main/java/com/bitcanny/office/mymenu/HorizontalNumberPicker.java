package com.bitcanny.office.mymenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by OFFICE on 06-08-2015.
 */
public class HorizontalNumberPicker extends LinearLayout {

    static int finalAmount =0;
    int amount = 0;
    static double amountMoney = GridViewAdapter.totalAmountMoney;
    static double totalVal = 0.0;
    
    public HorizontalNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = layoutInflater.inflate(R.layout.horizontal_number_picker, this);


      /*  View view = View.inflate(context, R.layout.navigation_drawer, this);

        final TextView txt_item_select = (TextView) view.findViewById(R.id.txt_item_select);*/

        ImageView img_plus = (ImageView) root.findViewById(R.id.btn_plus);

        ImageView img_minus = (ImageView) root.findViewById(R.id.btn_minus);

        final TextView edit_amount = (TextView) root.findViewById(R.id.edit_text);

        edit_amount.setText(String.valueOf(amount));
        img_plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                amount++;
                finalAmount =finalAmount+1;
             //  txt_item_select.setText(String.valueOf(finalAmount));

                amountMoney = amountMoney+GridViewAdapter.totalAmountMoney;

                GridViewAdapter.getTotalAmt(amountMoney);
                edit_amount.setText(String.valueOf(amount));
            }
        });

        img_minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(amount>0) {
                    amount--;
                    finalAmount =finalAmount-1;
                  // txt_item_select.setText(String.valueOf(finalAmount));

                    amountMoney =amountMoney-GridViewAdapter.totalAmountMoney;
                    edit_amount.setText(String.valueOf(amount));
                }
            }
        });



    }
    public int getAmount(){

        return  amount;
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





