package com.slon.lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergii on 03.02.2017.
 */
public class FibonachiCached {
    private Map<Integer, BigInteger> cache = new HashMap<>();

    public BigInteger getItemRequrs(int x) {
        if (x == 0 || x < 0) {
            return new BigInteger("0");
        }
        if (x == 1) {
            return new BigInteger("1");
        } else {
            if (cache.containsKey(x)) {
                return cache.get(x);
            } else {
                BigInteger result =getItemRequrs(x - 1).add(getItemRequrs(x - 2));
                cache.put(x, result);
                return result;
            }
        }
    }


    public BigInteger getItem(int x) {
        BigInteger res = new BigInteger("0"), n_1 = new BigInteger("1"), n_2 = new BigInteger("0");
        if (x == 0 || x < 0) {
            return new BigInteger("0");
        }
        if (x == 1) {
            return new BigInteger("1");
        } else {
            for (int i = 1; i < x; i++) {
                res = n_1.add(n_2);
                n_2=n_1;
                n_1=res;
            }
        }


        return res;
    }

    public static void main(String[] a) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FibonachiCached fibonachi = new FibonachiCached();

        while (true) {
            try {
                System.out.print("Input a number & you'll get its fibonachi(input not number to EXIT): ");
                System.out.println();
                int x = Integer.parseInt(reader.readLine());
//                for (int i=0;i<130;i++) {
//                    System.out.println(String.format("%d - %d",i,fibonachi.getItemRequrs(i)));
//                }
                System.out.println(fibonachi.getItemRequrs(x));
                System.out.println(fibonachi.getItem(x));
            } catch (NumberFormatException e) {
                break;
            }
        }
    }
}
