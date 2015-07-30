package com.bitcanny.office.mymenu;

/**
 * Created by OFFICE on 06-07-2015.
 */
public class ResturantModel {


    private static String TYPE = "type";

    String resturantId;
    String restaurantLogImage;
    String restaurantName ;
    String restaurantPhone1 ;
    String restaurantPhone2;
    String restaurantPostCode ;
    String restaurantState ;
    String restaurantAddress ;
    String restaurantDescription ;



    String resturantCode ;


    String RestaurantRatingID,RestaurantRatingRate,RestaurantRatingReview;

    String FrontendMenuID ;
    String FrontendMenuName ;
    String FrontendMenuOrder ;
    String menuTagInfo ;
    String TagID ;
    String TagName ;
    String lat ;
    String lng ;
    String RestaurantImageID;
    String RestaurantImageUrl;



    public ResturantModel(){

    }

    public ResturantModel(String restaurantLogImage, String restaurantName, String resturantId, String restaurantPhone1, String restaurantPhone2, String restaurantPostCode, String restaurantAddress, String restaurantState, String restaurantDescription) {
        this.restaurantLogImage = restaurantLogImage;
        this.restaurantName = restaurantName;
        this.resturantId = resturantId;
        this.restaurantPhone1 = restaurantPhone1;
        this.restaurantPhone2 = restaurantPhone2;
        this.restaurantPostCode = restaurantPostCode;
        this.restaurantAddress = restaurantAddress;
        this.restaurantState = restaurantState;
        this.restaurantDescription = restaurantDescription;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public static void setTYPE(String TYPE) {
        ResturantModel.TYPE = TYPE;
    }

    public String getResturantId() {
        return resturantId;
    }

    public void setResturantId(String resturantId) {
        this.resturantId = resturantId;
    }

    public String getRestaurantLogImage() {
        return restaurantLogImage;
    }

    public void setRestaurantLogImage(String restaurantLogImage) {
        this.restaurantLogImage = restaurantLogImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPhone1() {
        return restaurantPhone1;
    }

    public void setRestaurantPhone1(String restaurantPhone1) {
        this.restaurantPhone1 = restaurantPhone1;
    }

    public String getRestaurantPhone2() {
        return restaurantPhone2;
    }

    public void setRestaurantPhone2(String restaurantPhone2) {
        this.restaurantPhone2 = restaurantPhone2;
    }

    public String getRestaurantPostCode() {
        return restaurantPostCode;
    }

    public void setRestaurantPostCode(String restaurantPostCode) {
        this.restaurantPostCode = restaurantPostCode;
    }

    public String getRestaurantState() {
        return restaurantState;
    }

    public void setRestaurantState(String restaurantState) {
        this.restaurantState = restaurantState;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public String getRestaurantRatingID() {
        return RestaurantRatingID;
    }

    public void setRestaurantRatingID(String restaurantRatingID) {
        RestaurantRatingID = restaurantRatingID;
    }

    public String getRestaurantRatingRate() {
        return RestaurantRatingRate;
    }

    public void setRestaurantRatingRate(String restaurantRatingRate) {
        RestaurantRatingRate = restaurantRatingRate;
    }

    public String getRestaurantRatingReview() {
        return RestaurantRatingReview;
    }

    public void setRestaurantRatingReview(String restaurantRatingReview) {
        RestaurantRatingReview = restaurantRatingReview;
    }

    public String getFrontendMenuID() {
        return FrontendMenuID;
    }

    public void setFrontendMenuID(String frontendMenuID) {
        FrontendMenuID = frontendMenuID;
    }

    public String getFrontendMenuName() {
        return FrontendMenuName;
    }

    public void setFrontendMenuName(String frontendMenuName) {
        FrontendMenuName = frontendMenuName;
    }

    public String getFrontendMenuOrder() {
        return FrontendMenuOrder;
    }

    public void setFrontendMenuOrder(String frontendMenuOrder) {
        FrontendMenuOrder = frontendMenuOrder;
    }

    public String getMenuTagInfo() {
        return menuTagInfo;
    }

    public void setMenuTagInfo(String menuTagInfo) {
        this.menuTagInfo = menuTagInfo;
    }

    public String getTagID() {
        return TagID;
    }

    public void setTagID(String tagID) {
        TagID = tagID;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getRestaurantImageID() {
        return RestaurantImageID;
    }

    public void setRestaurantImageID(String restaurantImageID) {
        RestaurantImageID = restaurantImageID;
    }

    public String getRestaurantImageUrl() {
        return RestaurantImageUrl;
    }

    public void setRestaurantImageUrl(String restaurantImageUrl) {
        RestaurantImageUrl = restaurantImageUrl;
    }

    public String getResturantCode() {
        return resturantCode;
    }

    public void setResturantCode(String resturantCode) {
        this.resturantCode = resturantCode;
    }
}
