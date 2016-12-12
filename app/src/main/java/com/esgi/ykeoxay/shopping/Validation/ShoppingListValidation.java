package com.esgi.ykeoxay.shopping.Validation;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingListValidation {
    private static Pattern pattern;
    private static Matcher matcher;
    private  HashMap<String, String> errors;

    public ShoppingListValidation(){
    }

    /*
    * Minimal length = 0
    * Maximum lenght = 20
    * */
    public  static boolean checkName(String name) {
        pattern = pattern.compile("^([a-zA-Z0-9]{3,10})$");
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isFormValid(String name) {
        ShoppingListValidation shoppingListValidation = new ShoppingListValidation();
        return shoppingListValidation.checkName(name);
    }
}
