package com.slon.lesson4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergii on 12.02.2017.
 */
public class WeightParser {
    Pattern pattern = Pattern.compile("^(\\d+(\\.)?|\\.{1})(\\d*)?", Pattern.CASE_INSENSITIVE);
    private int miligrams;
    private int grams;
    private int kilos;
    private int tons;

    private void clear() {
        miligrams = grams = kilos = tons = 0;
    }

    private void takeValue(String s) throws WrongDataInputException {
        clear();
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            String[] numArray = matcher.group().split("\\.");

            if (numArray.length > 1) {
                int beforeDot = numArray[0].isEmpty() ? 0 : Integer.parseInt(numArray[0]);
                devide(beforeDot);
                miligrams = numArray[1].isEmpty() ? 0 : Integer.parseInt(numArray[1]);
            } else {
                int beforeDot = numArray[0].isEmpty() ? 0 : Integer.parseInt(numArray[0]);
                devide(beforeDot);
            }
        } else {
            throw new WrongDataInputException("Wrong input");
        }
    }

    private void devide(int x) {
        if (x != 0) {
            this.grams = x % 1000;
            this.kilos = x / 1000 % 1_000;
            this.tons = (x / 1_000_000);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (tons != 0) {
            sb.append(tons).append(" tons ");
        }
        if (kilos != 0) {
            sb.append(kilos).append(" kg ");
        }
        if (grams != 0) {
            sb.append(grams).append(" g ");
        }
        if (miligrams != 0) {
            sb.append(miligrams).append(" mg");
        }
        return sb.toString();
    }

    public static void main(String[] a) throws WrongDataInputException {
        WeightParser wp = new WeightParser();
        wp.takeValue("234.");
        System.out.println(wp);
        wp.takeValue("13000234.");
        System.out.println(wp);
        wp.takeValue("1344234.");
        System.out.println(wp);
        wp.takeValue("234.456");
        System.out.println(wp);
        wp.takeValue(".2459");
        System.out.println(wp);
    }
}
