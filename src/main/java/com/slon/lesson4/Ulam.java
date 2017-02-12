package com.slon.lesson4;


/**
 * Created by Sergii on 12.02.2017.
 */
public class Ulam {
    private final int M;
    private int[][] arr;
    private int x, y;
    private int currentNum = 1;

    public Ulam(int m) {
        this.M = m;
        this.arr = new int[M][M];
        x = y = M / 2;
        arr[x][y] = currentNum;
    }

    public void draw() {
        for (int i = 1; true; ) {
            try {
                moveRight(i);
                moveUp(i++);

                moveLeft(i);
                moveDown(i++);
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%3d", arr[j][i]);
            }
            System.out.println();
        }
    }

    private void moveRight(int d) {
        for (int i = 1; i <= d; i++) {
            arr[++x][y] = ++currentNum;
        }
    }

    private void moveUp(int d) {
        for (int i = 1; i <= d; i++) {
            arr[x][--y] = ++currentNum;
        }
    }

    private void moveLeft(int d) {
        for (int i = 1; i <= d; i++) {
            arr[--x][y] = ++currentNum;
        }
    }

    private void moveDown(int d) {
        for (int i = 1; i <= d; i++) {
            arr[x][++y] = ++currentNum;
        }
    }


    public static void main(String[] a) {
        Ulam ulam = new Ulam(11);
        ulam.draw();
    }
}
