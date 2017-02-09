package com.esgi.ykeoxay.shopping.Webservice;

import android.util.Log;

import com.esgi.ykeoxay.shopping.Util.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ShoppingListService {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void  shoppingList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "create shopping list request sent => " + Config.BASE_URL + Config.URL_SHOPPING_LIST);
        }
        client.get(Config.BASE_URL+Config.URL_SHOPPING_LIST, params, responseH);
        Log.i(Config.LOG_PREFIX, "create shopping list request received => " + Config.BASE_URL + Config.URL_SHOPPING_LIST);
    }

    public static void  createShoppingList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "create shopping list request sent => " + Config.BASE_URL + Config.URL_CREATE_SHOPPING_LIST);
        }
        client.get(Config.BASE_URL+Config.URL_CREATE_SHOPPING_LIST, params, responseH);
        Log.i(Config.LOG_PREFIX, "create shopping list request received => " + Config.BASE_URL + Config.URL_CREATE_SHOPPING_LIST);
    }

    public static  void  editShoppingList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "edit shopping list request sent => " + Config.BASE_URL + Config.URL_EDIT_SHOPPING_LIST);
            Log.i(Config.LOG_PREFIX, params.toString());
        }
        client.get(Config.BASE_URL+Config.URL_EDIT_SHOPPING_LIST, params, responseH);
        Log.i(Config.LOG_PREFIX, "edit shopping list request received => " + Config.BASE_URL + Config.URL_EDIT_SHOPPING_LIST);
    }

    public static void  removeShoppingList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "remove shopping list request sent => " + Config.BASE_URL + Config.URL_REMOVE_SHOPPING_LIST);
            Log.i(Config.LOG_PREFIX, params.toString());
        }
        client.get(Config.BASE_URL+Config.URL_REMOVE_SHOPPING_LIST, params, responseH);
        Log.i(Config.LOG_PREFIX, "remove shopping list request received => " + Config.BASE_URL + Config.URL_REMOVE_SHOPPING_LIST);
    }
}
