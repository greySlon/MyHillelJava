package com.slon.lesson1;

/**
 * Created by Sergii on 29.01.2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;
import static java.lang.System.out;

public class Calculator {
    static boolean exit(){
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

    public void setOperation() throws IOException{
        out.print("Enter operation type(+,-,/,*)");
        String operationType = reader.readLine();
        out.println();
        out.println(String.format("Calculator Task 2(result of \"%s\" operations)", operationType));
        switch (operationType){
            case "+":
                sum();
                break;
            case "-":
                difference();
                break;
            case "/":
                division();
                break;
            case "*":
                multiplication();
                break;
                default:
                    out.println(String.format("\n%s isn't supported operation\n", operationType));
                    throw new NumberFormatException();
        }
    }

    public void showResultTask1() {
        out.println();
        out.print("Calculator Task 1(all operations results)");
        sum();
        difference();
        multiplication();
        division();
        out.println();
    }

    private void sum(){
        out.println(String.format("Sum is %-8.3f", customNum1 + customNum2));
    }
    private void difference(){
        out.println(String.format("Difference is %-8.3f", customNum1 - customNum2));
    }
    private void multiplication(){
        out.println(String.format("Multiplication is %-8.3f", customNum1 * customNum2));
    }
    private void division(){
        out.println(String.format("Division is %-8.3f", customNum1 / customNum2));
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
                calculator.setOperation();
                calculator.showResultTask1();
                calculator.reset();
            } catch (IOException e) {
                out.println("Something's wrong");
                return;
            }catch (NumberFormatException e){
               if(exit()) break;
            }
        }
    }

}
