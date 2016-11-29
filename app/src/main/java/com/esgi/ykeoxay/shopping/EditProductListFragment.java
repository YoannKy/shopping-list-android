package com.esgi.ykeoxay.shopping;

import android.app.FragmentManager;
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
import com.esgi.ykeoxay.shopping.Model.Product;
import com.esgi.ykeoxay.shopping.Util.Config;
import com.esgi.ykeoxay.shopping.Webservice.Webservice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

public class EditProductListFragment extends Fragment {

    private  Bundle editProductBundle;
    private Product product;

    public EditProductListFragment() {
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
        return inflater.inflate(R.layout.fragment_edit_product_list, container, false);
    }

    @Override
    public void onResume() {
        this.editProductBundle = getArguments();
        product = new Product();
        product.setId(Integer.valueOf(this.editProductBundle.getString("id")));
        product.setName(this.editProductBundle.getString("name"));
        product.setPrice(Double.valueOf(editProductBundle.getString("price")));
        product.setQuantity(Integer.valueOf(editProductBundle.getString("quantity")));
        EditText name = (EditText) getActivity().findViewById(R.id.edit_product_name);
        EditText price = (EditText) getActivity().findViewById(R.id.edit_product_price);
        EditText quantity = (EditText) getActivity().findViewById(R.id.edit_product_quantity);

        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));
        quantity.setText(String.valueOf(product.getQuantity()));

        Button submit = (Button) getActivity().findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) getActivity().findViewById(R.id.edit_product_name);
                EditText price = (EditText) getActivity().findViewById(R.id.edit_product_price);
                EditText quantity = (EditText) getActivity().findViewById(R.id.edit_product_quantity);

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                String token = sharedPreferences.getString("token", "");
                RequestParams params = new RequestParams();
                params.put("token", token);
                params.put("name", name.getText().toString());
                params.put("id", product.getId());
                params.put("quantity", quantity.getText().toString());
                params.put("price", price.getText().toString());
                updateData(params);
            }
        });
        super.onResume();
    }

    protected void updateData(RequestParams params) {
        Webservice ws = new Webservice();
        ws.editProductList(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (Config.DISPLAY_LOG) {
                    Log.i(Config.LOG_PREFIX, "Success! WS Response :" + new String(bytes));
                }
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
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
