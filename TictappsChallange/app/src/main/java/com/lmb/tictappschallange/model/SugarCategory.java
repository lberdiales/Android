package com.lmb.tictappschallange.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.List;

public class SugarCategory extends SugarRecord<SugarCategory> {
    public Integer categoryId;
    public String title;

    @Ignore
    public List<SugarProduct> productList = new ArrayList<SugarProduct>();

    public SugarCategory() {
    }

    public SugarCategory(Integer id, String title) {
        this.categoryId = id;
        this.title = title;
    }

    public void addProduct(SugarProduct theProduct) {
        if(productList.indexOf(theProduct) != -1) {
            theProduct.save();
            productList.add(theProduct);
        }
    }

    public void removeProduct(SugarProduct theProduct) {
        if(productList.indexOf(theProduct) != -1) {
            theProduct.delete();
            productList.remove(theProduct);
        }
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

    public static List<SugarCategory> getCategories() {
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

        return aList;
    }
}
