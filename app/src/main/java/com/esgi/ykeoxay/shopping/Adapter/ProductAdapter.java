package com.esgi.ykeoxay.shopping.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.esgi.ykeoxay.shopping.EditProductListFragment;
import com.esgi.ykeoxay.shopping.Model.Product;
import com.esgi.ykeoxay.shopping.R;
import com.esgi.ykeoxay.shopping.Util.Config;
import com.esgi.ykeoxay.shopping.Webservice.Webservice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykeox on 28/11/2016.
 */

public class ProductAdapter extends BaseAdapter {

    private List<Product> lProduct;
    private LayoutInflater myLayout;
    private Context context;
    private ViewHolder viewHolder;

    public ProductAdapter(Context context, ArrayList<Product> lProduct) {
        this.context = context;
        this.lProduct = lProduct;
        myLayout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewHolder = new ViewHolder();
    }

    @Override
    public int getCount() {
        return lProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return lProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = myLayout.inflate(R.layout.product_list_content, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.product_name);
            viewHolder.id = (TextView) convertView.findViewById(R.id.product_id);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.product_quantity);
            viewHolder.price = (TextView) convertView.findViewById(R.id.product_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Product currentProduct = lProduct.get(position);
        viewHolder.name.setText(currentProduct.getName());
        viewHolder.id.setText(String.valueOf(currentProduct.getId()));
        viewHolder.quantity.setText(String.valueOf(currentProduct.getQuantity()));
        viewHolder.price.setText(String.valueOf(currentProduct.getPrice()));

        Button edit = (Button) convertView.findViewById(R.id.edit_product_list);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProductListFragment editProductListFragment = new EditProductListFragment();
                Product product = lProduct.get(position);
                Bundle productBundle = new Bundle();
                productBundle.putString("id", String.valueOf(product.getId()));
                productBundle.putString("name", product.getName());
                productBundle.putString("price", String.valueOf(product.getPrice()));
                productBundle.putString("quantity", String.valueOf(product.getQuantity()));
                editProductListFragment.setArguments(productBundle);

                FragmentManager fm = ((Activity) context).getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, editProductListFragment);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });

        Button remove = (Button) convertView.findViewById(R.id.remove_product_list);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProduct(position);
            }
        });

        return convertView;
    }

    public class ViewHolder {
        TextView name;
        TextView id;
        TextView price;
        TextView quantity;
    }

    protected void removeProduct(final Integer position)
    {
        Webservice ws = new Webservice();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String token = sharedPreferences.getString("token", "");
        RequestParams params = new RequestParams();
        final Product product = lProduct.get(position);
        params.put("token", token);
        params.put("id", product.getId());
        ws.removeProduct(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (Config.DISPLAY_LOG) {
                    Log.i(Config.LOG_PREFIX, "Success! WS Response :" + new String(bytes));
                }
                lProduct.remove(product);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                String failedReponse = "";
                if (bytes != null) {
                    failedReponse = new String(bytes);
                }
                Log.i(Config.LOG_PREFIX, "Failure! WS Response :" + failedReponse);
            }
        }, params);
    }
}