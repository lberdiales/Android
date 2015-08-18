package com.lmb.tictappschallange.model;

import android.util.ArrayMap;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SugarCategory extends SugarRecord<SugarCategory> {
    public Integer categoryId;
    public String title;

    @Ignore
    public List<SugarProduct> productList = new ArrayList<SugarProduct>();

    public SugarCategory() {
    }

    public SugarCategory(Integer categoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
    }

    public static void addProduct(Integer productId, String title, Float price, Integer stock, Integer category, String creationDate, String expirationDate) {
        SugarProduct aProduct = new SugarProduct(productId, title, price, stock, category, creationDate, expirationDate);
        aProduct.save();
    }

    public static void modifyProduct(Long product_ID, Integer productId, String title, Float price, Integer stock, Integer category, String creationDate, String expirationDate) {
        SugarProduct aProduct = SugarProduct.findById(SugarProduct.class, product_ID);
        aProduct.productId = productId;
        aProduct.title = title;
        aProduct.price = price;
        aProduct.stock = stock;
        //aProduct.category = category;
        aProduct.creationDate = creationDate;
        aProduct.expirationDate = expirationDate;

        aProduct.save();
    }

    public static void removeProduct(Long product_ID) {
        SugarProduct aProduct = SugarProduct.findById(SugarProduct.class, product_ID);
        aProduct.delete();
    }

    private static void initialCategories() {
        SugarCategory c1 = new SugarCategory(1,"PC");
        c1.save();

        SugarCategory c2 = new SugarCategory(2,"Notebook");
        c2.save();

        SugarCategory c3 = new SugarCategory(3,"Netbook");
        c3.save();

        SugarCategory c4 = new SugarCategory(4,"Tablet");
        c4.save();

        SugarCategory c5 = new SugarCategory(5,"Cellphone");
        c5.save();
    }

    public static Map<Long, SugarCategory> getCategories() {
        Map<Long, SugarCategory> aMap = new HashMap<Long, SugarCategory>();

        List<SugarCategory> aList = SugarCategory.listAll(SugarCategory.class);
        if(aList.isEmpty()) {
            initialCategories();
            aList = SugarCategory.listAll(SugarCategory.class);
        }
        else {
            for(SugarCategory aCategory : aList) {
                aCategory.productList = SugarProduct.find(SugarProduct.class, "category = ?", String.valueOf(aCategory.categoryId));
            }
        }

        for(SugarCategory aCategory : aList) {
            aMap.put(aCategory.getId(), aCategory);
        }

        return aMap;
    }
}
