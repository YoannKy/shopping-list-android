package com.esgi.ykeoxay.shopping.Validation;

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
    public static  boolean checkEmail(String email) {
        pattern = pattern.compile("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})");
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /*
    * Minimal length = 0
    * Maximum lenght = 10
    * */
    public static boolean checkFirstName(String firstName) {
        pattern = pattern.compile("^([a-zA-Z0-9]{3,10})$");
        matcher = pattern.matcher(firstName);
        return matcher.matches();
    }

    /*
    * Minimal length = 0
    * Maximum lenght = 10
    * */
    public static  boolean checkPassword(String password) {
        pattern = pattern.compile("^([a-zA-Z0-9]{3,10})$");
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isFormValid(String email, String password) {
        AuthValidation authValidation = new AuthValidation();
        return (authValidation.checkEmail(email)&&
                authValidation.checkPassword(password));
    }

    public static boolean isFormValid(String email, String password, String firstName) {
        AuthValidation authValidation = new AuthValidation();
        return (authValidation.checkEmail(email)&&
                authValidation.checkPassword(password) &&
                authValidation.checkFirstName(firstName));
    }

}
