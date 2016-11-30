package com.esgi.ykeoxay.shopping.Fragment;

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
import com.esgi.ykeoxay.shopping.Adapter.ShoppingListAdapter;
import com.esgi.ykeoxay.shopping.Interface.ShoppingListParserResponse;
import com.esgi.ykeoxay.shopping.Model.ShoppingList;
import com.esgi.ykeoxay.shopping.Parser.ShoppingListParser;
import com.esgi.ykeoxay.shopping.R;
import com.esgi.ykeoxay.shopping.Util.Config;
import com.esgi.ykeoxay.shopping.Webservice.ShoppingListService;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment implements ShoppingListParserResponse {

    private ListView listView;
    private ShoppingListAdapter adapter;

    public ShoppingListFragment() {
        // Required empty public constructor
    }

    public ShoppingListFragment getCurrentFragment() {
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
        return inflater.inflate(R.layout.fragment_shopping_list, container, false);
    }

    @Override
    public void onResume() {
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.container, new CreateShoppingListFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            }
        });
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String token = sharedPreferences.getString("token", "");
        RequestParams params = new RequestParams();
        params.put("token", token);
        ShoppingListService.shoppingList(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (Config.DISPLAY_LOG) {
                    Log.i(Config.LOG_PREFIX, "Success! WS Response :" + new String(bytes));
                }
                ShoppingListParser shoppingListParser = new ShoppingListParser(getCurrentFragment());
                shoppingListParser.execute(new String(bytes));
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

    private void initViewContent(final ArrayList<ShoppingList> shoppingList) {
        this.listView = (ListView) getView().findViewById(R.id.list_view);
        this.adapter = new ShoppingListAdapter(getActivity(), shoppingList);
        this.listView.setAdapter(this.adapter);
    }

    @Override
    public void responseParsed(ArrayList<ShoppingList> shoppingList) {
        initViewContent(shoppingList);
    }
}
