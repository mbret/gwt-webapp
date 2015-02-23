package com.google.gwt.sample.stockwatcher.client;

/**
 * Created by Maxime on 2/23/2015.
 */
public class Validator {
    
    public static boolean isValidNumber(String number){
        return number.matches("[0-9]+") && number != null && !number.equals("");
    }
}
