package com.slon.p2;

/**
 * Created by Sergii on 02.02.2017.
 */
public class ConsoleDrawing {

    private int minX, maxX, minY, maxY;

    public ConsoleDrawing(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

/*    public void drawRectangle() {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (rectangleCondition(y, x)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }*/
/*
    public void drawChessBoard() {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {

                if (chessCondition(y, x)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }*/

    public void draw(Condition condition) {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (condition.test(x, y)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public  final Condition RECTANGLE=(int x, int y)-> {
        return y == 0 || y == maxY - 1 || x == 0 || x == maxX - 1;
    };

    public  final Condition CHESS=(int x, int y)-> {
        return x % 2 == 0 ^ y % 2 == 0;
    };

    public  final Condition CROSS=(int x, int y)-> {
        int dx=0, dy=0;
        return RECTANGLE.test(x, y)||x==y||x+y==maxX-1;
    };




    public static void main(String[] a) {
        ConsoleDrawing cosole = new ConsoleDrawing(8, 7);
        cosole.draw(cosole.RECTANGLE);
        System.out.println();
        cosole.draw(cosole.CHESS);
        System.out.println();
        cosole.draw(cosole.CROSS);
    }
}

interface Condition {
    boolean test(int x, int y);
}