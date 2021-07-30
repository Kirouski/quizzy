package com.company;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {

        validation("r");
    }

    public static boolean validation(String str) {
        Pattern pattern = Pattern.compile("[^абвг]");
        Matcher matcher = pattern.matcher(str.toLowerCase(Locale.ROOT));
        if (matcher.find()) {
            try {
                throw new QuizException();
            } catch (QuizException qe) {
                qe.getMessage("Ответ не может содержать другие буквы и символы, кроме А, Б, В или Г");
                System.out.println("опа");
                return false;
            }
        } else System.out.println("хер");
        return true;
    }
}
