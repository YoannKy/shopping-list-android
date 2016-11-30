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
import com.esgi.ykeoxay.shopping.Fragment.EditShoppingListFragment;
import com.esgi.ykeoxay.shopping.Model.ShoppingList;
import com.esgi.ykeoxay.shopping.Fragment.ProductListFragment;
import com.esgi.ykeoxay.shopping.R;
import com.esgi.ykeoxay.shopping.Util.Config;
import com.esgi.ykeoxay.shopping.Webservice.ShoppingListService;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykeox on 28/11/2016.
 */

public class ShoppingListAdapter extends BaseAdapter {

    private List<ShoppingList> lShoppingList;
    private LayoutInflater myLayout;
    private Context context;
    private ViewHolder viewHolder;

    public ShoppingListAdapter(Context context, ArrayList<ShoppingList> lShoppingList) {
        this.context = context;
        this.lShoppingList = lShoppingList;
        myLayout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewHolder = new ViewHolder();
    }

    @Override
    public int getCount() {
        return lShoppingList.size();
    }

    @Override
    public Object getItem(int position) {
        return lShoppingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = myLayout.inflate(R.layout.shopping_list_content, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.shopping_list_name);
            viewHolder.id = (TextView) convertView.findViewById(R.id.shopping_list_id);
            viewHolder.completed = (TextView) convertView.findViewById(R.id.shopping_list_completed);
            viewHolder.completedLabel = (TextView) convertView.findViewById(R.id.shopping_list_completed_label);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShoppingList currentShoppingList = lShoppingList.get(position);
        viewHolder.name.setText(currentShoppingList.getName());
        viewHolder.id.setText(String.valueOf(currentShoppingList.getId()));
        viewHolder.completed.setText(String.valueOf(currentShoppingList.getCompleted()));
        if(currentShoppingList.getCompleted()) {
            viewHolder.completedLabel.setText(context.getResources().getString(R.string.open_shopping_list));
        } else {
            viewHolder.completedLabel.setText(context.getResources().getString(R.string.closed_shopping_list));
        }
        Button edit = (Button) convertView.findViewById(R.id.edit_shopping_list);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            EditShoppingListFragment editShoppingFragment = new EditShoppingListFragment();
            ShoppingList shoppingList = lShoppingList.get(position);
            Bundle shoppingListBundle = new Bundle();
            shoppingListBundle.putString("id", String.valueOf(shoppingList.getId()));
            shoppingListBundle.putString("name", shoppingList.getName());
            shoppingListBundle.putString("completed", String.valueOf(shoppingList.getCompleted()));
            editShoppingFragment.setArguments(shoppingListBundle);

            FragmentManager fm = ((Activity) context).getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.container, editShoppingFragment);
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();
            }
        });

        Button remove = (Button) convertView.findViewById(R.id.remove_shopping_list);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               removeShoppingList(position);
            }
        });

        Button show = (Button) convertView.findViewById(R.id.show_shopping_list);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ProductListFragment productListFragment = new ProductListFragment();
            ShoppingList shoppingList = lShoppingList.get(position);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("shopping_list_id", String.valueOf(shoppingList.getId()));
            editor.apply();
            editor.commit();
            FragmentManager fm = ((Activity) context).getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.container, productListFragment);
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();
            }
        });

        return convertView;
    }

    public class ViewHolder {
        TextView name;
        TextView id;
        TextView completed;
        TextView completedLabel;
    }

    protected void removeShoppingList(final Integer position)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String token = sharedPreferences.getString("token", "");
        RequestParams params = new RequestParams();
        final ShoppingList shoppingList = lShoppingList.get(position);
        params.put("token", token);
        params.put("id", shoppingList.getId());
        ShoppingListService.removeShoppingList(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (Config.DISPLAY_LOG) {
                    Log.i(Config.LOG_PREFIX, "Success! WS Response :" + new String(bytes));
                }
                lShoppingList.remove(shoppingList);
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