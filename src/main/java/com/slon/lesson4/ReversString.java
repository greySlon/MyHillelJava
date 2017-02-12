package com.slon.lesson4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Sergii on 12.02.2017.
 */
public class ReversString {
    public String revers(String s) {
        char[] chars = new char[s.length()];
        for (int j = 0, i = chars.length - 1; j < s.length(); i--, j++) {
            chars[i] = s.charAt(j);
        }
        return new String(chars);
    }

    public String revers(int num){
        return revers(String.valueOf(num));
    }

    public boolean isPalindrom(String str){
        for (int i = 0, j=str.length()-1; i <= str.length()/2; i++, j--) {
            if(str.charAt(i)!=str.charAt(j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] a) throws IOException {
        ReversString reversString = new ReversString();

        System.out.println("REVERS STRING OR NUMBER");
        System.out.println(reversString.revers("234567"));
        System.out.println(reversString.revers(6789));
        System.out.println("PALINDROM");
        System.out.println(reversString.isPalindrom("1234567"));
        System.out.println(reversString.isPalindrom("123321"));
        System.out.println(reversString.isPalindrom("9239329"));
    }
}
