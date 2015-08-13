package com.lmb.tictappschallange;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lmb.tictappschallange.db.TictappsChallangeDbHelper;
import com.lmb.tictappschallange.model.Product;
import com.lmb.tictappschallange.model.ProductAdapter;

import java.util.ArrayList;

public class ProductFragment extends ListFragment implements ProductAdapter.ProductAdapterListener {
    private static final String TAG = "ProductFragment";

    public static final String ARG_CATEGORY_ID = "category_id";

    //Listener for interface callbacks
    private ProductSeleciontListener mListener;

    private Context mContext;
    private Integer mCategoryId;
    private ProductAdapter mAdapter;
    private ArrayList<Product> mProducts = new ArrayList<Product>();

    //DB access
    private TictappsChallangeDbHelper mDbHelper;

    public interface ProductSeleciontListener {
        public void onProductSelection(Integer theProductId);
    }

    public void showProductsByCategory(Integer theCategoryId) {
        mProducts = mDbHelper.getProducts(theCategoryId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        View view = inflater.inflate(R.layout.product_main, null);

        Button anAddProductButton = (Button) view.findViewById(R.id.productAddBtn);
        anAddProductButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Dialog d = new Dialog(mContext);

                d.setContentView(R.layout.dialog_product);
                d.setTitle(getResources().getString(R.string.dial_product_add));
                d.setCancelable(true);

                final EditText anEditId = (EditText) d.findViewById(R.id.dialog_add_product_id);
                final EditText anEditTitle = (EditText) d.findViewById(R.id.dialog_add_product_title);
                final EditText anEditPrice = (EditText) d.findViewById(R.id.dialog_add_product_price);
                final EditText anEditStock = (EditText) d.findViewById(R.id.dialog_add_product_stock);
                final EditText anEditCreationDate = (EditText) d.findViewById(R.id.dialog_add_product_creationdate);
                final EditText anEditExpirationDate = (EditText) d.findViewById(R.id.dialog_add_product_expirationdate);

                Button anAccept = (Button) d.findViewById(R.id.dialog_add_product_accept);

                anAccept.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //TODO: Validate input Dialog data
                        if (!anEditId.getText().toString().isEmpty() &&
                                !anEditTitle.getText().toString().isEmpty() &&
                                !anEditPrice.getText().toString().isEmpty() &&
                                !anEditStock.getText().toString().isEmpty() &&
                                !anEditCreationDate.getText().toString().isEmpty()) {

                            Product aNewProduct = new Product(
                                    Integer.valueOf(anEditId.getText().toString()),
                                    anEditTitle.getText().toString(),
                                    Float.valueOf(anEditPrice.getText().toString().replace(",", ".")),
                                    Integer.valueOf(anEditStock.getText().toString()),
                                    anEditCreationDate.getText().toString(),
                                    anEditExpirationDate.getText().toString(),
                                    mCategoryId
                            );

                            mDbHelper.insertProduct(aNewProduct);

                            mProducts.add(aNewProduct);

                            // We notify the data model is changed
                            mAdapter.notifyDataSetChanged();

                            d.dismiss();
                        }
                    }
                });

                d.show();
            }
        });

        Button anFilterButton = (Button) view.findViewById(R.id.productFilterBtn);
        anFilterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final CharSequence[] items = new CharSequence[]{
                        ProductAdapter.ProductFilter.EXPIRADOS,
                        ProductAdapter.ProductFilter.STOCK,
                        ProductAdapter.ProductFilter.ULTIMOS90D,
                };

                new AlertDialog.Builder(mContext)
                        .setSingleChoiceItems(items, 0, null)
                        .setPositiveButton("FILTER", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                                mAdapter.getFilter().filter(items[selectedPosition]);
                            }
                        })
                        .setNegativeButton("DISMISS FILTER", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                                mAdapter.getFilter().filter(ProductAdapter.ProductFilter.NO_FILTER);
                            }
                        })
                        .show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedState);

        setRetainInstance(true);

        mContext = getActivity();
        mDbHelper = new TictappsChallangeDbHelper(getActivity());
        mCategoryId = getArguments().getInt(ARG_CATEGORY_ID);
        showProductsByCategory(mCategoryId);

        // Set the list adapter for the ListView
        mAdapter = new ProductAdapter(getActivity(), R.layout.product_item, mProducts, this);
        setListAdapter(mAdapter);

        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onAttach(Activity activity){
            super.onAttach(activity);
        try {
            mListener = (ProductSeleciontListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ProductSeleciontListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        // Indicates the selected item has been checked
        getListView().setItemChecked(pos, true);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onProductSelection(mProducts.get(pos).getId());
        }
    }

    @Override
    public void onModifyProduct(final int position, View v) {
        final Dialog d = new Dialog(mContext);

        d.setContentView(R.layout.dialog_product);
        d.setTitle(getResources().getString(R.string.dial_product_modify));
        d.setCancelable(true);

        final EditText anEditId = (EditText) d.findViewById(R.id.dialog_add_product_id);
        final EditText anEditTitle = (EditText) d.findViewById(R.id.dialog_add_product_title);
        final EditText anEditPrice = (EditText) d.findViewById(R.id.dialog_add_product_price);
        final EditText anEditStock = (EditText) d.findViewById(R.id.dialog_add_product_stock);
        final EditText anEditCreationDate = (EditText) d.findViewById(R.id.dialog_add_product_creationdate);
        final EditText anEditExpirationDate = (EditText) d.findViewById(R.id.dialog_add_product_expirationdate);

        anEditId.setText(mProducts.get(position).getId().toString());
        anEditTitle.setText(mProducts.get(position).getTitle());
        anEditPrice.setText(mProducts.get(position).getPrice().toString());
        anEditStock.setText(mProducts.get(position).getStock().toString());
        anEditCreationDate.setText(mProducts.get(position).getCreationDate());
        anEditExpirationDate.setText(mProducts.get(position).getExpirationDate());

        Button anAccept = (Button) d.findViewById(R.id.dialog_add_product_accept);

        anAccept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: Validate input Dialog data
                if (!anEditId.getText().toString().isEmpty() &&
                        !anEditTitle.getText().toString().isEmpty() &&
                        !anEditPrice.getText().toString().isEmpty() &&
                        !anEditStock.getText().toString().isEmpty() &&
                        !anEditCreationDate.getText().toString().isEmpty()) {

                    mProducts.get(position).setId(Integer.valueOf(anEditId.getText().toString()));
                    mProducts.get(position).setTitle(anEditTitle.getText().toString());
                    mProducts.get(position).setPrice(Float.valueOf(anEditPrice.getText().toString().replace(",", ".")));
                    mProducts.get(position).setStock(Integer.valueOf(anEditStock.getText().toString()));
                    mProducts.get(position).setCreationDate(anEditCreationDate.getText().toString());
                    mProducts.get(position).setExpirationDate(anEditExpirationDate.getText().toString());

                    mDbHelper.updateProduct(mProducts.get(position));

                    // We notify the data model is changed
                    mAdapter.notifyDataSetChanged();

                    d.dismiss();
                }
            }
        });

        d.show();
    }

    @Override
    public void onRemoveProduct(int position, View v) {
        mDbHelper.deleteProduct(mProducts.get(position));

        mProducts.remove(position);

        // We notify the data model is changed
        mAdapter.notifyDataSetChanged();
    }
}
