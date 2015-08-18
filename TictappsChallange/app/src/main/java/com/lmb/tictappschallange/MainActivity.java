package com.lmb.tictappschallange;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.lmb.tictappschallange.model.SugarCategory;
import com.lmb.tictappschallange.model.SugarProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements CategoryFragment.CategorySelectionListener, ProductFragment.ProductSeleciontListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create fragment
            CategoryFragment aCategoryFragment = new CategoryFragment();

            // Get a reference to the FragmentManager
            getFragmentManager().beginTransaction().add(R.id.fragment_container, aCategoryFragment).commit();
        }
    }

    @Override
    public List<SugarCategory> onGetCategories() {
        return new ArrayList<SugarCategory>(SugarCategory.getCategories().values());
    }

    // Called when the user selects a category in the CategoryFragment
    @Override
    public void onCategorySelection(Integer theCategoryId) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCategorySelection(theCategoryId => " + theCategoryId + ")");

        // Create fragment and give it an argument specifying the category it should show
        ProductFragment aProductFragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(ProductFragment.ARG_CATEGORY_ID, theCategoryId);
        aProductFragment.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, aProductFragment).addToBackStack(null).commit();
    }

    @Override
    public List<SugarProduct> onGetProducts(Integer theCategory) {
        return new ArrayList<SugarProduct>(SugarProduct.getProductsByCategory(theCategory).values());
    }

    @Override
    public void onAddProduct(Integer productId, String title, Float price, Integer stock, Integer category, String creationDate, String expirationDate) {
        SugarCategory.addProduct(productId, title, price, stock, category, creationDate, expirationDate);
    }

    @Override
    public void onModifyProduct(Long product_ID, Integer productId, String title, Float price, Integer stock, Integer category, String creationDate, String expirationDate) {
        SugarCategory.modifyProduct(product_ID, productId, title, price, stock, category, creationDate, expirationDate);
    }

    @Override
    public void onRemoveProduct(Long product_ID) {
        SugarCategory.removeProduct(product_ID);
    }

    // Called when the user selects a category in the CategoryFragment
    @Override
    public void onProductSelection(Integer theProductId) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onProductSelection(theProductId => " + theProductId + ")");
    }
}
