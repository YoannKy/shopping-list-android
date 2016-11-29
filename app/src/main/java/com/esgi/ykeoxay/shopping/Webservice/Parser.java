package com.esgi.ykeoxay.shopping.Webservice;


import com.esgi.ykeoxay.shopping.Model.Product;
import com.esgi.ykeoxay.shopping.Model.ShoppingList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by ykeox on 27/11/2016.
 */

public class Parser {

    public static String parseAuthentication(String response) throws JSONException {

        JSONObject contentJSON = new JSONObject(response);

        if(contentJSON.getInt("code") == 0) {
             return contentJSON.getJSONObject("result").getString("token");
        } else {
            return "";
        }
    }

    public static ArrayList<Product> parseProduct(String response) throws JSONException {

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

    public static ArrayList<ShoppingList> parseShoppingList(String response) throws JSONException {

        JSONObject contentJSON = new JSONObject(response);
        ArrayList<ShoppingList> shoppingList = new ArrayList<ShoppingList>();
        JSONArray resultJSON = contentJSON.getJSONArray("result");
        for (int i = 0; i < resultJSON.length(); i++) {
            ShoppingList parsedShoppingList = new ShoppingList();
            JSONObject shoppingListJSON = resultJSON.getJSONObject(i);
            parsedShoppingList.setId(shoppingListJSON.getInt("id"));
            parsedShoppingList.setName(shoppingListJSON.getString("name"));
            Boolean completed;
            if(shoppingListJSON.getInt("completed") == 0){
                completed = false;
            } else {
                completed = true;
            }
            parsedShoppingList.setCompleted(completed);
            shoppingList.add(parsedShoppingList);
        }
        return shoppingList;
    }
}
