package com.ghostCoder.invoicePro.validator;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {

    //patter for valid email address
    private static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    //numerical validator
    public TextFormatter numericValidator(){
        UnaryOperator<TextFormatter.Change> filter = change ->{
            String text = change.getText();
            if(text.matches("[0-9]")){
                return change;
            }
            if(text.isEmpty()){
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter= new TextFormatter<String>(filter);
        return textFormatter;
    }

    //numeric and range validator
    public TextFormatter numericAndRangeValidator(int range){
        UnaryOperator<TextFormatter.Change> filter = change ->{
            String text = change.getText();
            if(text.matches("[0-9]")){
                if(change.getControlNewText().length() <= range) {
                    return change;
                }
            }
            if(text.isEmpty()){
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter= new TextFormatter<String>(filter);
        return textFormatter;
    }

    //email validator
    public boolean emailValidator(String emailId){
        Matcher matcher = VALID_EMAIL.matcher(emailId);
        return matcher.find();
    }

    public TextFormatter rangeValidator(int range){
        UnaryOperator<TextFormatter.Change> filter = change ->{
            String text = change.getText();
            if(change.getControlNewText().length() <= range) {
                    return change;
            }
            if(text.isEmpty()){
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter= new TextFormatter<String>(filter);
        return textFormatter;
    }

}
