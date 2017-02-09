package com.esgi.ykeoxay.shopping.Validation;

import android.widget.EditText;

import com.esgi.ykeoxay.shopping.Util.Config;

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

    public static boolean isFormValid(EditText input) {
        ShoppingListValidation shoppingListValidation = new ShoppingListValidation();
        Integer errorCount = 0;
        if(!shoppingListValidation.checkName(input.getText().toString())){
            input.setError(Config.regexMsg.get("name"));
            errorCount+=1;
        }
        return errorCount == 0;     
    }
}
