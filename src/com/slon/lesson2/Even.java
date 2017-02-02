package com.slon.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sergii on 02.02.2017.
 */
public class Even {
    public static boolean isEven(int x){
        return x%2==0;
    }
    public static void main(String[]a){
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter number: ");
        try {
            int x=Integer.parseInt(reader.readLine());
            if (isEven(x)) {
                System.out.println("number is even");
            }else {
                System.out.printf("number is odd");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error input");
        } catch (IOException e) {
            System.out.println("Something wrong");
        }
    }
}
