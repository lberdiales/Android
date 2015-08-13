package com.lmb.tictappschallange;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lmb.tictappschallange.db.TictappsChallangeDbHelper;
import com.lmb.tictappschallange.model.Category;
import com.lmb.tictappschallange.model.CategoryAdapter;

import java.util.ArrayList;

public class CategoryFragment extends ListFragment {
    private static final String TAG = "CategoryFragment";

    //Listener for interface callbacks
    private CategorySelectionListener mListener;

    private Context mContext;
    private CategoryAdapter mAdapter;
    private ArrayList<Category> mCategories = new ArrayList<Category>();

    //DB access
    private TictappsChallangeDbHelper mDbHelper;

    public interface CategorySelectionListener {
        public void onCategorySelection(Integer categoryId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedState);

        setRetainInstance(true);

        mContext = getActivity();
        mDbHelper = new TictappsChallangeDbHelper(getActivity());
        mCategories = mDbHelper.getCategories();

        // Set the list adapter for the ListView
        mAdapter = new CategoryAdapter(getActivity(), R.layout.category_item, mCategories);
        setListAdapter(mAdapter);

        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (CategorySelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement CategorySelectionListener");
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
            mListener.onCategorySelection(mCategories.get(pos).getId());
        }
	}
}
