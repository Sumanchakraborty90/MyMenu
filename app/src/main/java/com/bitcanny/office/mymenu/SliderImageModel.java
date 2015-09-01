package com.bitcanny.office.mymenu;

/**
 * Created by OFFICE on 05-08-2015.
 */
public class SliderImageModel {

    int slider_image_id;
    private  String slider_image_url;

    public SliderImageModel() {
    }

    public SliderImageModel(String slider_image_url) {
        this.slider_image_url = slider_image_url;
    }

    public String getSlider_image_url() {
        return slider_image_url;
    }

    public void setSlider_image_url(String slider_image_url) {
        this.slider_image_url = slider_image_url;
    }
}
