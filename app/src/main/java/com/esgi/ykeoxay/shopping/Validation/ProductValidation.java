package com.esgi.ykeoxay.shopping.Validation;

import android.widget.EditText;

import com.esgi.ykeoxay.shopping.Util.Config;

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
    public static boolean checkName(EditText input) {
        pattern = pattern.compile("^([a-zA-Z0-9]{3,10})$");
        matcher = pattern.matcher(input.getText().toString());
        if (matcher.matches()) {
            return true;
        } else {
            input.setError(Config.regexMsg.get("name"));
            return false;
        }
    }

    /*
    * Integer
    * */
    public static  boolean checkQuantity(EditText input) {
        pattern = pattern.compile("[0-9]{1,10}");
        matcher = pattern.matcher(input.getText().toString());
        if (matcher.matches()) {
            return true;
        } else {
            input.setError(Config.regexMsg.get("quantity"));
            return false;
        }
    };

    /*
    * Integer
    * */
    public static boolean checkPrice(EditText input) {
        pattern = pattern.compile("[0-9]+([,.][0-9]+)?");
        matcher = pattern.matcher(input.getText().toString());
        if (matcher.matches()) {
            return true;
        } else {
            input.setError(Config.regexMsg.get("price"));
            return false;
        }
    }

    public static boolean isFormValid(EditText nameInput, EditText priceInput, EditText quantityInput) {
        ProductValidation productValidation = new ProductValidation();
        Integer errorCount = 0;
        if(!productValidation.checkName(nameInput)){
            errorCount+=1;
        }
        if(!productValidation.checkPrice(priceInput)) {
            errorCount +=1;
        }
        if(!productValidation.checkQuantity(quantityInput)) {
            errorCount +=1;
        }
        return errorCount == 0;
    }

}
