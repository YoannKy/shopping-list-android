package com.esgi.ykeoxay.shopping.Webservice;

import android.util.Log;

import com.esgi.ykeoxay.shopping.Util.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ProductService {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public ProductService(){}

    public static void  editProductList(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "edit product request sent => " + Config.BASE_URL + Config.URL_EDIT_PRODUCT);
            Log.i(Config.LOG_PREFIX, params.toString());
        }
        client.get(Config.BASE_URL+Config.URL_EDIT_PRODUCT, params, responseH);
        Log.i(Config.LOG_PREFIX, "edit product request received => " + Config.BASE_URL + Config.URL_EDIT_PRODUCT);
    }

    public static void  listProduct(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "create shopping list request sent => " + Config.BASE_URL + Config.URL_PRODUCT);
        }
        client.get(Config.BASE_URL+Config.URL_PRODUCT, params, responseH);
        Log.i(Config.LOG_PREFIX, "create shopping list request received => " + Config.BASE_URL + Config.URL_PRODUCT);
    }

    public static void  createProduct(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "create shopping list request sent => " + Config.BASE_URL + Config.URL_CREATE_PRODUCT);
        }
        client.get(Config.BASE_URL+Config.URL_CREATE_PRODUCT, params, responseH);
        Log.i(Config.LOG_PREFIX, "create shopping list request received => " + Config.BASE_URL + Config.URL_CREATE_PRODUCT);
    }

    public static void  removeProduct(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "remove product list request sent => " + Config.BASE_URL + Config.URL_REMOVE_PRODUCT);
            Log.i(Config.LOG_PREFIX, params.toString());
        }
        client.get(Config.BASE_URL+Config.URL_REMOVE_PRODUCT, params, responseH);
        Log.i(Config.LOG_PREFIX, "remove product list request received => " + Config.BASE_URL + Config.URL_REMOVE_PRODUCT);
    }
}
