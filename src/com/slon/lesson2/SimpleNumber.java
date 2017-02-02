package com.slon.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sergii on 02.02.2017.
 */
public class SimpleNumber {
    public static boolean isSimple(int x) throws Exception{
        if(x>0){
            for (int i = 2; i < x; i++) {
                if(x%i==0){
                    return false;
                }
            }
            return true;
        }else{
            throw new Exception("Error input- number should be more than 0");
        }
    }

    public static void main(String[]a){
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter number to check: ");
            int x=Integer.parseInt(reader.readLine());
            if(isSimple(x)){
                System.out.println(String.format("%d is simple", x));
            }else{
                System.out.println(String.format("%d isn't simple", x));
            }
        } catch (IOException e) {
            System.out.println("Something wrong");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
