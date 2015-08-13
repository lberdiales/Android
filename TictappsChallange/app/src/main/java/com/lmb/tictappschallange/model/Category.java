package com.lmb.tictappschallange.model;

import java.util.ArrayList;

public class Category {
    private Integer mId;
    private String mTitle;
    private ArrayList<Product> mProductList;

    public Category(Integer theId, String theTitle) {
        mId = theId;
        mTitle = theTitle;
        mProductList = new ArrayList<Product>();
    }

    public Category(Integer theId, String theTitle, ArrayList<Product> theProductList) {
        mId = theId;
        mTitle = theTitle;
        mProductList = theProductList;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public ArrayList<Product> getProductList() {
        return mProductList;
    }

    public void addProduct(Product theProduct) {
        if(mProductList.indexOf(theProduct) != -1)
            mProductList.add(theProduct);
    }

    public void removeProduct(Product theProduct) {
        if(mProductList.indexOf(theProduct) != -1)
            mProductList.remove(theProduct);
    }
}
