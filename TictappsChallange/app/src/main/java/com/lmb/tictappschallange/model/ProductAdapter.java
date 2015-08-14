package com.lmb.tictappschallange.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Filterable;

import com.lmb.tictappschallange.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<SugarProduct> implements Filterable {
    ProductAdapterListener mListener = null;

    public interface ProductAdapterListener {
        public void onRemoveProduct(int position, View v);
    }

    private Context mContext;
    private List<SugarProduct> mProducts;
    private Filter mFilter;
    private List<SugarProduct> mOrigProducts;

    public ProductAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ProductAdapter(Context context, int resource, List<SugarProduct> products) {
        super(context, resource, products);

        mContext = context;
        mProducts = products;
    }

    public ProductAdapter(Context context, int resource, List<SugarProduct> products, ProductAdapterListener buttonListener) {
        super(context, resource, products);

        mContext = context;
        mProducts = products;
        mListener = buttonListener;
        mOrigProducts = mProducts;
    }

    public int getCount() {
        return mProducts.size();
    }

    public SugarProduct getItem(int position) {
        return mProducts.get(position);
    }

    public long getItemId(int position) {
        return mProducts.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder = new ViewHolder();

        if(v == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            v = inflater.inflate(R.layout.product_item, null);
            holder.mTextviewName = (TextView) v.findViewById(R.id.productName);
            holder.mTextviewPrice = (TextView) v.findViewById(R.id.productPrice);
            holder.mButtonRemove = (Button) v.findViewById(R.id.productRemoveBtn);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        SugarProduct p = mProducts.get(position);
        if (p != null) {
            holder.mTextviewName.setText(p.title);
            holder.mTextviewPrice.setText("$" + String.valueOf(p.price));
        }

        Button anRemoveProductButton = (Button) v.findViewById(R.id.productRemoveBtn);
        anRemoveProductButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onRemoveProduct(position, v);
            }
        });

        return v;
    }

    public void resetData() {
        mProducts = mOrigProducts;
    }

    public static class ViewHolder {
        TextView mTextviewName;
        TextView mTextviewPrice;
        Button mButtonRemove;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null)
            mFilter = new ProductFilter();

        return mFilter;
    }

    public class ProductFilter extends Filter {
        public static final String EXPIRADOS = "Expired products";
        public static final String STOCK = "Products with stock";
        public static final String ULTIMOS90D = "Products created within 90 days";
        public static final String NO_FILTER = "";

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = mOrigProducts;
                results.count = mOrigProducts.size();
            }
            else {
                // We perform filtering operation
                ArrayList<SugarProduct> aProductList = new ArrayList<SugarProduct>();

                for (SugarProduct p : mProducts) {
                    switch(constraint.toString()) {
                        case EXPIRADOS:
                            if(p.hasExpired())
                                aProductList.add(p);
                            break;

                        case STOCK:
                            if(p.hasStock())
                                aProductList.add(p);
                            break;

                        case ULTIMOS90D:
                            if(!p.creationDate.isEmpty()) {
                                try {
                                    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

                                    Calendar cal = GregorianCalendar.getInstance();
                                    cal.add(Calendar.DAY_OF_YEAR, -90);
                                    Date today90 = cal.getTime();

                                    if(sdf.parse(p.creationDate).after(today90))
                                        aProductList.add(p);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;

                        default:
                    }
                }

                results.values = aProductList;
                results.count = aProductList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            if(results.count == 0)
                notifyDataSetInvalidated();
            else {
                mProducts = (ArrayList<SugarProduct>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
