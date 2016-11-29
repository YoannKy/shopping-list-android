package com.esgi.ykeoxay.shopping.Parser;

import android.os.AsyncTask;
import com.esgi.ykeoxay.shopping.Interface.ShoppingListParserResponse;
import com.esgi.ykeoxay.shopping.Model.ShoppingList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by ykeox on 29/11/2016.
 */

public class ShoppingListParser  extends AsyncTask<String,Void,ArrayList<ShoppingList>> {

    private ShoppingListParserResponse listener;

    public ShoppingListParser(ShoppingListParserResponse listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<ShoppingList> doInBackground(String...params) {
        ArrayList<ShoppingList> shoppingList = new ArrayList<ShoppingList>();
        try {
           shoppingList = this.parse(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return shoppingList;
    }

    protected  void onPostExecute(ArrayList<ShoppingList> shoppingList) {

        this.listener.responseParsed(shoppingList);
    }

    protected ArrayList<ShoppingList> parse(String response) throws JSONException {
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
