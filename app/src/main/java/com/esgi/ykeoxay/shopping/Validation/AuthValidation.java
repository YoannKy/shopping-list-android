package com.esgi.ykeoxay.shopping.Validation;

import android.util.Log;
import android.widget.EditText;

import com.esgi.ykeoxay.shopping.Util.Config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AuthValidation {

    private static Pattern pattern;
    private static Matcher matcher;

    public AuthValidation(){

    }

    /*
     * email pattern
     * */
    public static  boolean checkEmail(EditText input) {
        pattern = pattern.compile("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})");
        matcher = pattern.matcher(input.getText().toString().trim());
        if (matcher.matches()) {
            return true;
        } else {
            input.setError(Config.regexMsg.get("email"));
            return false;
        }
    }

    /*
    * Minimal length = 0
    * Maximum lenght = 10
    * */
    public static boolean checkFirstName(EditText input) {
        pattern = pattern.compile("^([a-zA-Z0-9]{3,10})$");
        matcher = pattern.matcher(input.getText().toString().trim());
        if (matcher.matches()) {
            return true;
        } else {
            input.setError(Config.regexMsg.get("firstName"));
            return false;
        }
    }

    /*
    * Minimal length = 0
    * Maximum lenght = 10
    * */
    public static  boolean checkPassword(EditText input) {
        pattern = pattern.compile("^([a-zA-Z0-9]{3,10})$");
        matcher = pattern.matcher(input.getText().toString());
        if (matcher.matches()) {
            return true;
        } else {
            input.setError(Config.regexMsg.get("password"));
            return false;
        }
    }

    public static boolean isFormValid(EditText emailInput, EditText passwordInput) {
        AuthValidation authValidation = new AuthValidation();
        Integer errorCount = 0;
        if(!authValidation.checkEmail(emailInput)){
            errorCount+=1;
        }
        if(!authValidation.checkPassword(passwordInput)) {
            errorCount +=1;
        }
        return errorCount == 0;
    }

    public static boolean isFormValid(EditText emailInput, EditText passwordInput, EditText firstNameInput) {
        AuthValidation authValidation = new AuthValidation();
        Integer errorCount = 0;
        if(!authValidation.checkEmail(emailInput)){
            errorCount+=1;
        }
        if(!authValidation.checkPassword(passwordInput)){
            errorCount+=1;
        }
        if(!authValidation.checkFirstName(firstNameInput)){
            errorCount+=1;
        }
        return errorCount == 0;
    }

}
