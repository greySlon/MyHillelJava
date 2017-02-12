package com.slon.lesson3;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergii on 03.02.2017.
 */
public class FibonachiCached {
    private Map<Integer, BigInteger> cache = new HashMap<>();

    private BigInteger getItemRequrs(int x) {
        if (x == 0 || x < 0) {
            return BigInteger.ZERO;
        }
        if (x == 1) {
            return BigInteger.ONE;
        } else {
            if (cache.containsKey(x)) {
                return cache.get(x);
            } else {
                BigInteger result = getItemRequrs(x - 1).add(getItemRequrs(x - 2));
                cache.put(x, result);
                return result;
            }
        }
    }

    public BigInteger reqursion(int x) {
        int i = 0;
        while (i < x / 1000) {
            getItemRequrs(++i * 1000);
        }
        return getItemRequrs(x);
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
                n_2 = n_1;
                n_1 = res;
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
                System.out.println(fibonachi.reqursion(x));
//                System.out.println(fibonachi.getItem(x));
            } catch (NumberFormatException e) {
                break;
            }
        }
    }
}

