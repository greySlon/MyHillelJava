package com.slon.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sergii on 02.02.2017.
 */
public class Average {
    public static double average(double x, double y) {
        return (x + y) / 2;
    }

    public static void main(String[] a) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter first number: ");
        double x=Double.parseDouble(reader.readLine());
        System.out.println();
        System.out.print("Enter second number: ");
        double y=Double.parseDouble(reader.readLine());
        System.out.println();
        System.out.println(average(x,y));
    }
}
