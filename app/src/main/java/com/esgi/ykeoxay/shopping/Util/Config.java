package com.esgi.ykeoxay.shopping.Util;

import java.util.HashMap;

public class Config {
    public final static Boolean DISPLAY_LOG = true;
    public final static String LOG_PREFIX = "ESGI";
    public final static String BASE_URL = "http://appspaces.fr/esgi/shopping_list/";
    public final static String URL_REGISTER = "account/subscribe.php";
    public final static String URL_LOGIN = "account/login.php";
    public final static String URL_CREATE_SHOPPING_LIST = "shopping_list/create.php";
    public final static String URL_SHOPPING_LIST = "shopping_list/list.php";
    public final static String URL_EDIT_SHOPPING_LIST = "shopping_list/edit.php";
    public final static String URL_REMOVE_SHOPPING_LIST = "shopping_list/remove.php";
    public final static String URL_CREATE_PRODUCT = "product/create.php";
    public final static String URL_PRODUCT = "product/list.php";
    public final static String URL_EDIT_PRODUCT = "product/edit.php";
    public final static String URL_REMOVE_PRODUCT = "product/remove.php";
    public final static HashMap<String, String> regexMsg = new HashMap<String, String>() {
        {
            put("email", "The email is not valid");
            put("firstName", "The name must be between 3 and 10 characters");
            put("name", "The name must be between 3 and 10 characters");
            put("password", "The password must be between 3 and 10 characters");
            put("quantity", "The quantity must be a number with minimum value of 1");
            put("price", "The price must be a number with minimum value of 1");
        }
    };
}