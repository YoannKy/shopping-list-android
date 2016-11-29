package com.esgi.ykeoxay.shopping.Webservice;

import android.util.Log;

import com.esgi.ykeoxay.shopping.Util.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by ykeox on 27/11/2016.
 */

public class Webservice {

    private static AsyncHttpClient client = new AsyncHttpClient();
    public Webservice() {

    }

    public  void  login(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "Login request sent => " + Config.BASE_URL + Config.URL_LOGIN);
        }
        client.get(Config.BASE_URL+ Config.URL_LOGIN,params, responseH);
        Log.i(Config.LOG_PREFIX,"Request login => "+Config.BASE_URL+Config.URL_LOGIN+" "+responseH);
    }


    public  void  register(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "Register request sent => " + Config.BASE_URL + Config.URL_REGISTER);
        }
        client.get(Config.BASE_URL+Config.URL_REGISTER, params, responseH);
        Log.i(Config.LOG_PREFIX,"Request register received => "+Config.BASE_URL+Config.URL_REGISTER+" "+responseH);
    }

    public  void  shoppingList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "create shopping list request sent => " + Config.BASE_URL + Config.URL_SHOPPING_LIST);
        }
        client.get(Config.BASE_URL+Config.URL_SHOPPING_LIST, params, responseH);
        Log.i(Config.LOG_PREFIX, "create shopping list request received => " + Config.BASE_URL + Config.URL_SHOPPING_LIST);
    }

    public  void  createShoppingList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "create shopping list request sent => " + Config.BASE_URL + Config.URL_CREATE_SHOPPING_LIST);
        }
        client.get(Config.BASE_URL+Config.URL_CREATE_SHOPPING_LIST, params, responseH);
        Log.i(Config.LOG_PREFIX, "create shopping list request received => " + Config.BASE_URL + Config.URL_CREATE_SHOPPING_LIST);
    }

    public  void  editShoppingList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "edit shopping list request sent => " + Config.BASE_URL + Config.URL_EDIT_SHOPPING_LIST);
            Log.i(Config.LOG_PREFIX, params.toString());
        }
        client.get(Config.BASE_URL+Config.URL_EDIT_SHOPPING_LIST, params, responseH);
        Log.i(Config.LOG_PREFIX, "edit shopping list request received => " + Config.BASE_URL + Config.URL_EDIT_SHOPPING_LIST);
    }

    public  void  removeShoppingList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "remove shopping list request sent => " + Config.BASE_URL + Config.URL_REMOVE_SHOPPING_LIST);
            Log.i(Config.LOG_PREFIX, params.toString());
        }
        client.get(Config.BASE_URL+Config.URL_REMOVE_SHOPPING_LIST, params, responseH);
        Log.i(Config.LOG_PREFIX, "remove shopping list request received => " + Config.BASE_URL + Config.URL_REMOVE_SHOPPING_LIST);
    }

    public  void  editProductList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "edit product request sent => " + Config.BASE_URL + Config.URL_EDIT_PRODUCT);
            Log.i(Config.LOG_PREFIX, params.toString());
        }
        client.get(Config.BASE_URL+Config.URL_EDIT_PRODUCT, params, responseH);
        Log.i(Config.LOG_PREFIX, "edit product request received => " + Config.BASE_URL + Config.URL_EDIT_PRODUCT);
    }

    public  void  listProduct(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "create shopping list request sent => " + Config.BASE_URL + Config.URL_PRODUCT);
        }
        client.get(Config.BASE_URL+Config.URL_PRODUCT, params, responseH);
        Log.i(Config.LOG_PREFIX, "create shopping list request received => " + Config.BASE_URL + Config.URL_PRODUCT);
    }

    public  void  createProduct(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "create shopping list request sent => " + Config.BASE_URL + Config.URL_CREATE_PRODUCT);
        }
        client.get(Config.BASE_URL+Config.URL_CREATE_PRODUCT, params, responseH);
        Log.i(Config.LOG_PREFIX, "create shopping list request received => " + Config.BASE_URL + Config.URL_CREATE_PRODUCT);
    }

    public  void  removeProduct(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "remove product list request sent => " + Config.BASE_URL + Config.URL_REMOVE_PRODUCT);
            Log.i(Config.LOG_PREFIX, params.toString());
        }
        client.get(Config.BASE_URL+Config.URL_REMOVE_PRODUCT, params, responseH);
        Log.i(Config.LOG_PREFIX, "remove product list request received => " + Config.BASE_URL + Config.URL_REMOVE_PRODUCT);
    }


}
