package com.lmb.tictappschallange.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmb.tictappschallange.R;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context mContext;
    private List<Category> mCategories;

    public CategoryAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CategoryAdapter(Context context, int resource, List<Category> categories) {
        super(context, resource, categories);

        mContext = context;
        mCategories = categories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder = new ViewHolder();

        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.category_item, null);
            holder.mTextviewName = (TextView) v.findViewById(R.id.categoryName);
            holder.mTextviewNumOfProducts = (TextView) v.findViewById(R.id.categoryNumOfProducts);
            holder.mImageviewImg = (ImageView) v.findViewById(R.id.categoryImg);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        Category p = mCategories.get(position);
        if(p != null) {
            holder.mTextviewName.setText(p.getTitle());
            holder.mTextviewNumOfProducts.setText("Contiene " + p.getProductList().size() + " productos");
        }

        return v;
    }

    public static class ViewHolder {
        TextView mTextviewName;
        TextView mTextviewNumOfProducts;
        ImageView mImageviewImg;
    }
}
