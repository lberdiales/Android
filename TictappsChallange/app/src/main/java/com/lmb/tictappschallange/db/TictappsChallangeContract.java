package com.lmb.tictappschallange.db;

import android.provider.BaseColumns;

public class TictappsChallangeContract {
    //Column types
    private static final String TEXT_TYPE = " TEXT";

    //Constants
    private static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public TictappsChallangeContract() {}

    /* Inner class that defines the table contents */
    public static abstract class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
        public static final String COLUMN_NAME_TITLE = "category_title";

        //TODO: Revisar porque cambie de private a public, ver si es mejor dejarlo en la DbHelper
        //TODO: Ver de agregar los valores por defecto
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + CategoryEntry.TABLE_NAME + " (" +
                        CategoryEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                        CategoryEntry.COLUMN_NAME_CATEGORY_ID + TEXT_TYPE + COMMA_SEP +
                        CategoryEntry.COLUMN_NAME_TITLE + TEXT_TYPE +
                " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + CategoryEntry.TABLE_NAME;
    }

    /* Inner class that defines the table contents */
    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME_PRODUCT_ID = "product_id";
        public static final String COLUMN_NAME_TITLE = "product_title";
        public static final String COLUMN_NAME_PRICE = "product_price";
        public static final String COLUMN_NAME_STOCK = "product_stock";
        public static final String COLUMN_NAME_CATEGORY = "product_category";
        public static final String COLUMN_NAME_CREATION_DATE = "product_creationdate";
        public static final String COLUMN_NAME_EXPIRATION_DATE = "product_expirationdate";

        //TODO: Revisar porque cambie de private a public, ver si es mejor dejarlo en la DbHelper
        //TODO: Ver de agregar los valores por defecto
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + ProductEntry.TABLE_NAME + " (" +
                        ProductEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                        ProductEntry.COLUMN_NAME_PRODUCT_ID + TEXT_TYPE + COMMA_SEP +
                        ProductEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        ProductEntry.COLUMN_NAME_PRICE + TEXT_TYPE + COMMA_SEP +
                        ProductEntry.COLUMN_NAME_STOCK + TEXT_TYPE + COMMA_SEP +
                        ProductEntry.COLUMN_NAME_CATEGORY + TEXT_TYPE + COMMA_SEP +
                        ProductEntry.COLUMN_NAME_CREATION_DATE + TEXT_TYPE + COMMA_SEP +
                        ProductEntry.COLUMN_NAME_EXPIRATION_DATE + TEXT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME;
    }
}
