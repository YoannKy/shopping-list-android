package com.esgi.ykeoxay.shopping.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.esgi.ykeoxay.shopping.R;
import com.esgi.ykeoxay.shopping.Util.Config;
import com.esgi.ykeoxay.shopping.Validation.ProductValidation;
import com.esgi.ykeoxay.shopping.Webservice.ProductService;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


public class CreateProductListFragment extends Fragment {

    public CreateProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_product_list, container, false);
    }

    @Override
    public void onResume() {

        Button submit = (Button) getActivity().findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                String shoppingListId = sharedPreferences.getString("shopping_list_id", "");
                EditText name = (EditText) getActivity().findViewById(R.id.new_product_list_name);
                EditText quantity = (EditText) getActivity().findViewById(R.id.new_product_list_quantity);
                EditText price = (EditText) getActivity().findViewById(R.id.new_product_list_price);
                if(ProductValidation.isFormValid(name, price, quantity)) {
                    String token = sharedPreferences.getString("token", "");
                    RequestParams params = new RequestParams();
                    params.put("token", token);
                    params.put("shopping_list_id", shoppingListId);
                    params.put("name", name.getText().toString());
                    params.put("quantity", quantity.getText().toString());
                    params.put("price", price.getText().toString());
                    ProductService.createProduct(new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            if (Config.DISPLAY_LOG) {
                                Log.i(Config.LOG_PREFIX, "Success! WS Response :" + new String(bytes));
                            }
                            ProductListFragment productListFragment = new ProductListFragment();
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fm.beginTransaction();
                            fragmentTransaction.replace(R.id.container, productListFragment);
                            fragmentTransaction.commit();
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

            ;
        });
        super.onResume();
    }
}
