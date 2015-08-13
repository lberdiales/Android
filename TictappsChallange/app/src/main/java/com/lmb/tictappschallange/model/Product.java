package com.lmb.tictappschallange.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private Integer mId;
    private String mTitle;
    private Float mPrice;
    private Integer mStock;
    private Integer mCategory;
    private String mCreationDate;
    private String mExpirationDate;

    public Product(Integer theId, String theTitle, Float thePrice, Integer theStock, String theCreationDate, String theExpirationDate) {
        mId = theId;
        mTitle = theTitle;
        mPrice = thePrice;
        mStock = theStock;
        mCategory = 0;
        mCreationDate = theCreationDate;
        mExpirationDate = theExpirationDate;
    }

    public Product(Integer theId, String theTitle, Float thePrice, Integer theStock, String theCreationDate, String theExpirationDate, Integer theCategory) {
        mId = theId;
        mTitle = theTitle;
        mPrice = thePrice;
        mStock = theStock;
        mCategory = theCategory;
        mCreationDate = theCreationDate;
        mExpirationDate = theExpirationDate;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer theId) {
        mId = theId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String theTitle) {
        mTitle = theTitle;
    }

    public Float getPrice() {
        return mPrice;
    }

    public void setPrice(Float thePrice) {
        mPrice = thePrice;
    }

    public Integer getStock() {
        return mStock;
    }

    public void setStock(Integer theStock) {
        mStock = theStock;
    }

    public Integer getCategory() {
        return mCategory;
    }

    public void setCategory(Integer theCategory) {
        mCategory = theCategory;
    }

    public String getCreationDate() {
        return mCreationDate;
    }

    public void setCreationDate(String theCreationDate) {
        mCreationDate = theCreationDate;
    }

    public String getExpirationDate() {
        return mExpirationDate;
    }

    public void setExpirationDate(String theExpirationDate) {
        mExpirationDate = theExpirationDate;
    }

    public Boolean hasExpired() {
        if(!mExpirationDate.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                Date today = sdf.parse(sdf.format(new Date()));

                return sdf.parse(mExpirationDate).before(today);
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    public Boolean hasStock() {
        return mStock > 0;
    }
}
