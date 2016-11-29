package com.esgi.ykeoxay.shopping;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.esgi.ykeoxay.shopping.Adapter.ProductAdapter;
import com.esgi.ykeoxay.shopping.Interface.ProductListParserResponse;
import com.esgi.ykeoxay.shopping.Model.Product;
import com.esgi.ykeoxay.shopping.Parser.ProductParser;
import com.esgi.ykeoxay.shopping.Util.Config;
import com.esgi.ykeoxay.shopping.Webservice.Webservice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import java.util.ArrayList;


public class ProductListFragment extends Fragment implements ProductListParserResponse {

    private ListView listView;
    private ProductAdapter adapter;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public ProductListFragment getCurrentFragment() {
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onResume() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String shoppingListId = sharedPreferences.getString("shopping_list_id", "");
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateProductListFragment createShoppingListFragment = new CreateProductListFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, createShoppingListFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        FloatingActionButton returnBack = (FloatingActionButton) getActivity().findViewById(R.id.fab_return);
        returnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingListFragment shoppingListFragment = new ShoppingListFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, shoppingListFragment);
                fragmentTransaction.commit();
            }
        });
        Webservice ws = new Webservice();
        String token = sharedPreferences.getString("token", "");
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopping_list_id", shoppingListId);
        ws.listProduct(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (Config.DISPLAY_LOG) {
                    Log.i(Config.LOG_PREFIX, "Success! WS Response :" + new String(bytes));
                }
                ProductParser productParser = new ProductParser(getCurrentFragment());
                productParser.execute(new String(bytes));
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
        super.onResume();
    }

    private void initViewContent(final ArrayList<Product> productList) {
        this.listView = (ListView) getView().findViewById(R.id.list_view);
        this.adapter = new ProductAdapter(getActivity(), productList);
        this.listView.setAdapter(this.adapter);
    }

    @Override
    public void responseParsed(ArrayList<Product> products) {
        initViewContent(products);
    }
}
