package com.slon.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sergii on 02.02.2017.
 */
public class AverageMultipleNumber {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static double averageMultipleNumber(String... param) {
        double sumParam = 0;
        int countOfNums=0;
        for (int i = 0; i < param.length; i++) {
            if (!param[i].isEmpty()) {
                sumParam += Double.parseDouble(param[i]);
                countOfNums++;
            }
        }
        return sumParam / countOfNums;
    }

    public static void prompt() throws IOException {
        System.out.print("Enter numbers separating with space: ");
        String stringOfNumbers = reader.readLine();
        String[] numbersStr = stringOfNumbers.split(" ");
        System.out.println(averageMultipleNumber(numbersStr));
    }

    public static void main(String[] a) {
        try {
            prompt();
        } catch (IOException e) {
            System.out.println("Something wrong. Chao!");
        }
    }
}
