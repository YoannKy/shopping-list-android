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
    * Minimal length = 3
    * Maximum lenght = 10
    * */
    public static boolean checkName(String name) {
        pattern = pattern.compile("^([a-zA-Z0-9]{3,10})$");
        matcher = pattern.matcher(name.trim());
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /*
    * Integer
    * */
    public static  boolean checkQuantity(String quantity) {
        pattern = pattern.compile("[0-9]{1,10}");
        matcher = pattern.matcher(quantity.trim());
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    };

    /*
    * Integer
    * */
    public static boolean checkPrice(String price) {
        pattern = pattern.compile("[0-9]+([,.][0-9]+)?");
        matcher = pattern.matcher(price.trim());
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFormValid(EditText nameInput, EditText priceInput, EditText quantityInput) {
        ProductValidation productValidation = new ProductValidation();
        Integer errorCount = 0;
        if(!productValidation.checkName(nameInput.getText().toString())){
            nameInput.setError(Config.regexMsg.get("name"));
            errorCount+=1;
        }
        if(!productValidation.checkPrice(priceInput.getText().toString())) {
            priceInput.setError(Config.regexMsg.get("price"));
            errorCount +=1;
        }
        if(!productValidation.checkQuantity(quantityInput.getText().toString())) {
            quantityInput.setError(Config.regexMsg.get("quantity"));
            errorCount +=1;
        }
        return errorCount == 0;
    }

}
