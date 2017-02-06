package com.slon.lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Sum {
    public static int sum(String input) throws WrongInput{
        int result=0;
        try {
            for (String s : input.split("")) {
                result+=Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
        }
        if(result==0) {
            throw new WrongInput("Wrong data");
        }
        return result;
    }
    public static void main(String[]a) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter a number: ");
            System.out.println();
            System.out.println(sum(reader.readLine()));
        } catch (WrongInput e) {
            System.out.println(e.getMessage());
        }
    }
}
class WrongInput extends Exception{
    public WrongInput(String message) {
        super(message);
    }
}
