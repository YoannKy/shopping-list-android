package com.esgi.ykeoxay.shopping.Parser;

import android.os.AsyncTask;
import com.esgi.ykeoxay.shopping.Interface.ProductListParserResponse;
import com.esgi.ykeoxay.shopping.Model.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by ykeox on 29/11/2016.
 */

public class ProductParser extends AsyncTask<String,Void,ArrayList<Product>> {

    private ProductListParserResponse listener;

    public ProductParser(ProductListParserResponse listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Product> doInBackground(String...params) {
        ArrayList<Product> productList = new ArrayList<Product>();
        try {
            productList = this.parse(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productList;
    }

    protected  void onPostExecute(ArrayList<Product> products) {

        this.listener.responseParsed(products);
    }

    protected ArrayList<Product> parse(String response) throws JSONException {
        JSONObject contentJSON = new JSONObject(response);

        ArrayList<Product> productList = new ArrayList<Product>();
        JSONArray resultJSON = contentJSON.getJSONArray("result");
        for (int i = 0; i < resultJSON.length(); i++) {
            Product parsedProduct = new Product();
            JSONObject productJSON = resultJSON.getJSONObject(i);
            parsedProduct.setId(productJSON.getInt("id"));
            parsedProduct.setName(productJSON.getString("name"));
            parsedProduct.setQuantity(productJSON.getInt("quantity"));
            parsedProduct.setPrice(productJSON.getDouble("price"));
            productList.add(parsedProduct);
        }
        return productList;
    }
}
