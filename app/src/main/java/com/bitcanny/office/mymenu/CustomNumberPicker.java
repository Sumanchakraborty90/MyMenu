package com.bitcanny.office.mymenu;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Method;

/**
 * Created by OFFICE on 19-08-2015.
 */
public class CustomNumberPicker {


        private Object picker;
        private Class<?> classPicker;

        public CustomNumberPicker(LinearLayout numberPickerView) {

            try {
            picker = numberPickerView;
            classPicker = picker.getClass();

            // Кнопка '+', тип - NumberPickerButton
            View upButton = numberPickerView.getChildAt(0);
            upButton.setBackgroundResource(R.drawable.plus);

            // Текстовое поле, тип - EditText


                EditText edDate = (EditText) numberPickerView.getChildAt(1);
               /* edDate.setText("0");
                edDate.setTextSize(17);
                edDate.setBackgroundResource(R.drawable.fish);*/


            // Кнопка '-', тип - NumberPickerButton
            View downButton = numberPickerView.getChildAt(2);
//            downButton.setBackgroundResource(R.drawable.minus);

            }catch (Exception e){

                e.printStackTrace();
            }
        }

        public void setRange(int start, int end) {
            try {
                Method m = classPicker.getMethod("setRange", int.class, int.class);
                m.invoke(picker, start, end);
            } catch (Exception e) {
            }
        }

        public Integer getCurrent() {
            Integer current = -1;
            try {
                Method m = classPicker.getMethod("getCurrent");
                current = (Integer) m.invoke(picker);
            } catch (Exception e) {
            }
            return current;
        }

        public void setCurrent(int current) {
            try {
                Method m = classPicker.getMethod("setCurrent", int.class);
                m.invoke(picker, current);
            } catch (Exception e) {
            }
        }

}
