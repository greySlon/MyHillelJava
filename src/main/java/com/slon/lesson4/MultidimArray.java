package com.slon.lesson4;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Sergii on 12.02.2017.
 */
public class MultidimArray implements Iterable<Integer> {
    private int[][] arr;
    private final int X, Y;

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int x = 0, y = 0;

            @Override
            public boolean hasNext() {
                if (y == Y) {
                    x++;
                    y = 0;
                }
                if (x < X) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public Integer next() {
                return arr[x][y++];
            }
        };
    }

    public MultidimArray(int x, int y) {
        X = x;
        Y = y;
        arr = new int[X][Y];
    }

    public void randomFill() {
        Random rand = new Random();
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                int tmp = rand.nextInt(41) - 20;
                arr[x][y] = tmp;
            }
        }
    }

    public void sumLine() {
        int sum = 0;
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                sum += arr[x][y];
            }
            System.out.println(String.format("Line %d sum: %d", x + 1, sum));
            sum = 0;
        }
    }

    public int elementSum() {
        int sum = 0;
        for (Integer num : this) {
            sum += num;
        }
        return sum;
    }

    public double average() {
        return elementSum() / ((double) X * Y);
    }

    public int min() {
        int min = arr[0][0];
        for (Integer num : this) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public int max() {
        int max = arr[0][0];
        for (Integer num : this) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public int positiveCount() {
        int count = 0;
        for (Integer num : this) {
            if (num > 0) {
                count++;
            }
        }
        return count;
    }

    public void view() {
        for (Integer num : this) {
            System.out.println(num);
        }
    }

    public static void main(String[] a) {
        MultidimArray multidimArray = new MultidimArray(2, 2);
        multidimArray.randomFill();

        System.out.println("*****TO_STRING*****");
        multidimArray.view();

        System.out.println("*****SUM******");
        System.out.println(multidimArray.elementSum());

        System.out.println("*****AVERAGE******");
        System.out.println(multidimArray.average());

        System.out.println("*****MIN******");
        System.out.println(multidimArray.min());

        System.out.println("*****MAX******");
        System.out.println(multidimArray.max());

        System.out.println("*****POSITIVE_COUNT******");
        System.out.println(multidimArray.positiveCount());

        System.out.println("*****SUM_LINE******");
        multidimArray.sumLine();
    }
}
