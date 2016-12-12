package com.esgi.ykeoxay.shopping.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductValidation {
    private static Pattern pattern;
    private static Matcher matcher;

    public ProductValidation(){

    }


    /*
    * Minimal length = 0
    * Maximum lenght = 20
    * */
    public static boolean checkName(String name) {
        pattern = pattern.compile("^([a-zA-Z0-9]{3,10})$");
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /*
    * Integer
    * */
    public static  boolean checkQuantity(String quantity) {
        pattern = pattern.compile("[0-9]{1,10}");
        matcher = pattern.matcher(quantity);
        return matcher.matches();
    };

    /*
    * Integer
    * */
    public static boolean checkPrice(String price) {
        pattern = pattern.compile("[0-9]+([,.][0-9]+)?");
        matcher = pattern.matcher(price);
        return matcher.matches();
    }

    public static boolean isFormValid(String name, String price, String quantity) {
        ProductValidation productValidation = new ProductValidation();
        return (productValidation.checkName(name) &&
                productValidation.checkPrice(price) &&
                productValidation.checkQuantity(quantity));
    }

}
