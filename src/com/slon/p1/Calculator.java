package com.slon.p1;

/**
 * Created by Sergii on 29.01.2017.
 */
/*
Please input first number:
Your input <inputted number> as a first number.
Please input second number:
Your input <inputted number> as a second number.

Sum is <sum>
Difference is <difference>
Multiplication is <multiplication>
Division is <division>*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;
import static java.lang.System.out;

public class Calculator {
    private float customNum1;
    private float customNum2;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(in));

    public void setCustomNum1() throws IOException {
        this.customNum1 = prompt("Please input first number:");
        out.println(String.format("Your input %-8.3f as a first number.", customNum1));
    }

    public void setCustomNum2() throws IOException {
        this.customNum2 = prompt("Please input second number:");
        out.println(String.format("Your input %-8.3f as a second number.", customNum2));
    }

    public void showResult() {
        out.println();
        out.println(String.format("Sum is %-8.3f", customNum1 + customNum2));
        out.println(String.format("Difference is %-8.3f", customNum1 - customNum2));
        out.println(String.format("Multiplication is %-8.3f", customNum1 * customNum2));
        out.println(String.format("Division is %-8.3f", customNum1 / customNum2));
        out.println();
    }

    public void reset() {
        this.customNum1 = 0;
        this.customNum2 = 0;
    }

    private float prompt(String promptString) throws IOException {
        out.println();
        out.print(promptString);
        String input=null;
        try {
            input = reader.readLine();
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            out.println(String.format("\n%s isn't supported data format\n", input));
            throw e;
        }
    }

    public static void main(String[] a){
        Calculator calculator = new Calculator();
        while (true) {
            try {
                calculator.setCustomNum1();
                calculator.setCustomNum2();
                calculator.showResult();
                calculator.reset();
            } catch (IOException e) {
                out.println("Something's wrong");
                return;
            }catch (NumberFormatException e){
               if(isStop()) break;
            }
        }
    }
    static boolean isStop(){
        BufferedReader br=new BufferedReader(new InputStreamReader(in));

        while (true) {
            out.print("Would you like to continue? Y/N");
            try {
                switch (br.readLine().toLowerCase()){
                    case "y":
                        return false;
                    case "n":
                        return true;
                        default:
                            out.println("I don't get you. So let's start again.");
                            continue;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            out.println();
        }
    }
}
