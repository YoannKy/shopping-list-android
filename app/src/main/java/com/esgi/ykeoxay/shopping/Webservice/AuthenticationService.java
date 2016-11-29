package com.esgi.ykeoxay.shopping.Webservice;

import android.util.Log;

import com.esgi.ykeoxay.shopping.Util.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by ykeox on 29/11/2016.
 */

public class AuthenticationService {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static  void  login(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "Login request sent => " + Config.BASE_URL + Config.URL_LOGIN);
        }
        client.get(Config.BASE_URL+ Config.URL_LOGIN,params, responseH);
        Log.i(Config.LOG_PREFIX,"Request login => "+Config.BASE_URL+Config.URL_LOGIN+" "+responseH);
    }


    public static  void  register(AsyncHttpResponseHandler responseH, RequestParams params) {
        if(Config.DISPLAY_LOG) {
            Log.i(Config.LOG_PREFIX, "Register request sent => " + Config.BASE_URL + Config.URL_REGISTER);
        }
        client.get(Config.BASE_URL+Config.URL_REGISTER, params, responseH);
        Log.i(Config.LOG_PREFIX,"Request register received => "+Config.BASE_URL+Config.URL_REGISTER+" "+responseH);
    }
}
