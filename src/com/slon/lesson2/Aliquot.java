package com.slon.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sergii on 02.02.2017.
 */
public class Aliquot {
    public static void test(int x){
        for (int i = 1; i <= x; i++) {
            if(x%i==0){
                System.out.println(i);
            }
        }
    }

    public static void main(String[]a){
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter number: ");
        try {
            int x = Integer.parseInt(reader.readLine());
            test(x);
        }catch (IOException e){
            System.out.println("Something wrong");
        }
    }
}
