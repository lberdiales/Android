package com.lmb.tictappschallange.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lmb.tictappschallange.model.Category;
import com.lmb.tictappschallange.model.Product;

import java.util.ArrayList;

/*
 * Data access class
 * TODO: Improve DB access. Improve transaction manage. Improve process trough background process.
 */
public class TictappsChallangeDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "TictappsChallange.db";

    public TictappsChallangeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TictappsChallangeContract.CategoryEntry.SQL_CREATE_ENTRIES);
        db.execSQL(TictappsChallangeContract.ProductEntry.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Recreate DB
        db.execSQL(TictappsChallangeContract.ProductEntry.SQL_DELETE_ENTRIES);
        db.execSQL(TictappsChallangeContract.CategoryEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private Integer insertInitialCategories() {
        SQLiteDatabase aReadableDb = getReadableDatabase();
        Cursor aCountCursor = aReadableDb.rawQuery("SELECT count(1) FROM " + TictappsChallangeContract.CategoryEntry.TABLE_NAME, null);
        aCountCursor.moveToFirst();
        Integer aRowCount = aCountCursor.getInt(0);
        aCountCursor.close();
        aReadableDb.close();

        if(aRowCount == 0) {
            SQLiteDatabase aWritableDb = getWritableDatabase();
            ContentValues anValuesRow1 = new ContentValues();
            anValuesRow1.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID, 1);
            anValuesRow1.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_TITLE, "PC");
            long newRowId1 = aWritableDb.insert(TictappsChallangeContract.CategoryEntry.TABLE_NAME,
                    null,
                    anValuesRow1);
            aRowCount++;

            ContentValues anValuesRow2 = new ContentValues();
            anValuesRow2.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID, 2);
            anValuesRow2.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_TITLE, "Notebook");
            long newRowId2 = aWritableDb.insert(TictappsChallangeContract.CategoryEntry.TABLE_NAME,
                    null,
                    anValuesRow2);
            aRowCount++;

            ContentValues anValuesRow3 = new ContentValues();
            anValuesRow3.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID, 3);
            anValuesRow3.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_TITLE, "Netbook");
            long newRowId3 = aWritableDb.insert(TictappsChallangeContract.CategoryEntry.TABLE_NAME,
                    null,
                    anValuesRow3);
            aRowCount++;

            ContentValues anValuesRow4 = new ContentValues();
            anValuesRow4.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID, 4);
            anValuesRow4.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_TITLE, "Tablet");
            long newRowId4 = aWritableDb.insert(TictappsChallangeContract.CategoryEntry.TABLE_NAME,
                    null,
                    anValuesRow4);
            aRowCount++;

            ContentValues anValuesRow5 = new ContentValues();
            anValuesRow5.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID, 5);
            anValuesRow5.put(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_TITLE, "Cellphone");
            long newRowId5 = aWritableDb.insert(TictappsChallangeContract.CategoryEntry.TABLE_NAME,
                    null,
                    anValuesRow5);
            aRowCount++;
            aWritableDb.close();
        }

        return aRowCount;
    }

    private Integer insertInitialProducts() {
        SQLiteDatabase aReadableDb = getReadableDatabase();
        Cursor aCountCursor = aReadableDb.rawQuery("SELECT count(1) FROM " + TictappsChallangeContract.ProductEntry.TABLE_NAME, null);
        aCountCursor.moveToFirst();
        Integer aRowCount = aCountCursor.getInt(0);
        aCountCursor.close();
        aReadableDb.close();

        if(aRowCount == 0) {
            SQLiteDatabase aWritableDb = getWritableDatabase();
            ContentValues anValuesRow1 = new ContentValues();
            anValuesRow1.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRODUCT_ID, 1);
            anValuesRow1.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_TITLE, "Gamer PC 1");
            anValuesRow1.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRICE, 10000);
            anValuesRow1.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_STOCK, 10);
            anValuesRow1.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_CATEGORY, 1);
            anValuesRow1.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_CREATION_DATE, "01082015");
            anValuesRow1.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_EXPIRATION_DATE, "");
            long newRowId1 = aWritableDb.insert(TictappsChallangeContract.ProductEntry.TABLE_NAME,
                    null,
                    anValuesRow1);
            aRowCount++;

            ContentValues anValuesRow2 = new ContentValues();
            anValuesRow2.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRODUCT_ID, 2);
            anValuesRow2.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_TITLE, "Gamer PC 2");
            anValuesRow2.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRICE, 9999.99);
            anValuesRow2.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_STOCK, 5);
            anValuesRow2.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_CATEGORY, 1);
            anValuesRow2.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_CREATION_DATE, "02082015");
            anValuesRow2.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_EXPIRATION_DATE, "");
            long newRowId2 = aWritableDb.insert(TictappsChallangeContract.ProductEntry.TABLE_NAME,
                    null,
                    anValuesRow2);
            aRowCount++;
            aWritableDb.close();
        }

        return aRowCount;
    }

    public ArrayList<Category> getCategories() {
        insertInitialCategories();

        ArrayList<Category> aCategoryList = new ArrayList<Category>();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TictappsChallangeContract.CategoryEntry._ID,
                TictappsChallangeContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID,
                TictappsChallangeContract.CategoryEntry.COLUMN_NAME_TITLE
        };

        SQLiteDatabase aReadableDb = getReadableDatabase();
        Cursor aReaderCursor = aReadableDb.query(
                TictappsChallangeContract.CategoryEntry.TABLE_NAME, // The table to query
                projection,                                         // The columns to return
                null,                                               // The columns for the WHERE clause
                null,                                               // The values for the WHERE clause
                null,                                               // don't group the rows
                null,                                               // don't filter by row groups
                null                                                // The sort order
        );
        aReaderCursor.moveToFirst();
        while(!aReaderCursor.isAfterLast()) {
            ArrayList<Product> aProductList = new ArrayList<Product>();
            aProductList = getProducts(aReaderCursor.getInt(aReaderCursor.getColumnIndexOrThrow(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID)));

            aCategoryList.add(new Category(
                    aReaderCursor.getInt(aReaderCursor.getColumnIndexOrThrow(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID)),
                    aReaderCursor.getString(aReaderCursor.getColumnIndexOrThrow(TictappsChallangeContract.CategoryEntry.COLUMN_NAME_TITLE)),
                    aProductList)
            );

            aReaderCursor.moveToNext();
        }
        aReaderCursor.close();
        aReadableDb.close();

        return aCategoryList;
    }

    public ArrayList<Product> getProducts(Integer theCategoryId) {
        insertInitialProducts(); //TODO: ver

        ArrayList<Product> aProductList = new ArrayList<Product>();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TictappsChallangeContract.ProductEntry._ID,
                TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRODUCT_ID,
                TictappsChallangeContract.ProductEntry.COLUMN_NAME_TITLE,
                TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRICE,
                TictappsChallangeContract.ProductEntry.COLUMN_NAME_STOCK,
                TictappsChallangeContract.ProductEntry.COLUMN_NAME_CATEGORY,
                TictappsChallangeContract.ProductEntry.COLUMN_NAME_CREATION_DATE,
                TictappsChallangeContract.ProductEntry.COLUMN_NAME_EXPIRATION_DATE
        };

        // Define 'where' part of query.
        String selection = TictappsChallangeContract.ProductEntry.COLUMN_NAME_CATEGORY + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(theCategoryId) };

        SQLiteDatabase aReadableDb = getReadableDatabase();
        Cursor aReaderCursor = aReadableDb.query(
                TictappsChallangeContract.ProductEntry.TABLE_NAME,  // The table to query
                projection,                                         // The columns to return
                selection,                                          // The columns for the WHERE clause
                selectionArgs,                                      // The values for the WHERE clause
                null,                                               // don't group the rows
                null,                                               // don't filter by row groups
                null                                                // The sort order
        );
        aReaderCursor.moveToFirst();
        while(!aReaderCursor.isAfterLast()) {
            aProductList.add(new Product(
                    aReaderCursor.getInt(aReaderCursor.getColumnIndexOrThrow(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRODUCT_ID)),
                    aReaderCursor.getString(aReaderCursor.getColumnIndexOrThrow(TictappsChallangeContract.ProductEntry.COLUMN_NAME_TITLE)),
                    aReaderCursor.getFloat(aReaderCursor.getColumnIndexOrThrow(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRICE)),
                    aReaderCursor.getInt(aReaderCursor.getColumnIndexOrThrow(TictappsChallangeContract.ProductEntry.COLUMN_NAME_STOCK)),
                    aReaderCursor.getString(aReaderCursor.getColumnIndexOrThrow(TictappsChallangeContract.ProductEntry.COLUMN_NAME_CREATION_DATE)),
                    aReaderCursor.getString(aReaderCursor.getColumnIndexOrThrow(TictappsChallangeContract.ProductEntry.COLUMN_NAME_EXPIRATION_DATE)),
                    theCategoryId
            ));

            aReaderCursor.moveToNext();
        }
        aReaderCursor.close();
        aReadableDb.close();

        return aProductList;
    }

    public void insertProduct(Product theProduct) {
        SQLiteDatabase aWritableDb = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRODUCT_ID, theProduct.getId());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_TITLE, theProduct.getTitle());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRICE, theProduct.getPrice());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_STOCK, theProduct.getStock());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_CATEGORY, theProduct.getCategory());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_CREATION_DATE, theProduct.getCreationDate());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_EXPIRATION_DATE, theProduct.getExpirationDate());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = aWritableDb.insert(
                TictappsChallangeContract.ProductEntry.TABLE_NAME,
                null,
                values);

        aWritableDb.close();
    }

    public void updateProduct(Product theProduct) {
        SQLiteDatabase aWritableDb = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRODUCT_ID, theProduct.getId());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_TITLE, theProduct.getTitle());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRICE, theProduct.getPrice());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_STOCK, theProduct.getStock());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_CATEGORY, theProduct.getCategory());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_CREATION_DATE, theProduct.getCreationDate());
        values.put(TictappsChallangeContract.ProductEntry.COLUMN_NAME_EXPIRATION_DATE, theProduct.getExpirationDate());

        // Which row to update, based on the ID
        String selection = TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRODUCT_ID + " = ?";
        String[] selectionArgs = { String.valueOf(theProduct.getId()) };

        int count = aWritableDb.update(
                TictappsChallangeContract.ProductEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        aWritableDb.close();
    }

    public void deleteProduct(Product theProduct) {
        SQLiteDatabase aWritableDb = getWritableDatabase();

        // Define 'where' part of query.
        String selection = TictappsChallangeContract.ProductEntry.COLUMN_NAME_PRODUCT_ID + " = ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(theProduct.getId()) };

        // Issue SQL statement.
        aWritableDb.delete(
                TictappsChallangeContract.ProductEntry.TABLE_NAME,
                selection,
                selectionArgs);

        aWritableDb.close();
    }
}
