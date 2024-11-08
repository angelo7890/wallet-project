package com.anjox.api_carteira.util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
public class VerifyPassword {
    public static List<String> verifyPassword(String password) {
        List<String> list = new ArrayList<String>();
        if(!(password!=null && password.length()>6)) {
            list.add("a senha deve ter no minimo 6 caracteres");
        }
        if(!Pattern.matches(".*[A-Z].*", password)) {
            list.add("a senha deve conter letras maiusculas");
        }
        if(!Pattern.matches(".*[a-z].*", password)) {
            list.add("a senha deve conter letras minusculas");
        }
        if(!Pattern.matches(".*[0-9].*", password)) {
            list.add("a senha deve conter numeros");
        }
        if(!Pattern.matches(".*[!@#$%?+-].*", password)) {
            list.add("a senha deve conter caracteres especiais: !@#$%?+- ");
        }
        return list;
    }
}
