package br.com.sidlar.dailyquiz.infrastructure.utilitarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilitário para validação de email
 * @author Admilson
 */
public class ValidadorEmailUtils {

    public static boolean isFormatoCorreto(String email) {
        String emailPattern = "^[\\w-]+(\\.[\\w-]+)*@([\\w-])+[a-zA-Z]{2,7}$";
        if(email.contains(".com")){
            emailPattern = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
        }

        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
