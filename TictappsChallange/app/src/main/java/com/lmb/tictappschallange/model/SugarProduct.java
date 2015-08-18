package com.lmb.tictappschallange.model;

import com.orm.SugarRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SugarProduct extends SugarRecord<SugarProduct> {
    public Integer productId;
    public String title;
    public Float price;
    public Integer stock;
    public Integer category;
    public String creationDate;
    public String expirationDate;

    public SugarProduct() {
    }

    public SugarProduct(Integer id, String title, Float price, Integer stock, Integer category, String creationDate, String expirationDate) {
        this.productId = id;
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public Boolean hasExpired() {
        if(!expirationDate.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                Date today = sdf.parse(sdf.format(new Date()));

                return sdf.parse(expirationDate).before(today);
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    public Boolean hasStock() {
        return stock > 0;
    }

    public static Map<Long, SugarProduct> getProductsByCategory(Integer category) {
        Map<Long, SugarProduct> aMap = new HashMap<Long, SugarProduct>();

        List<SugarProduct> aList = SugarCategory.find(SugarProduct.class, "category = ?", String.valueOf(category));

        for(SugarProduct aProduct : aList) {
            aMap.put(aProduct.getId(), aProduct);
        }

        return aMap;
    }
}
