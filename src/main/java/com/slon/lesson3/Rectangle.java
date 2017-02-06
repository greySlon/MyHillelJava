package com.slon.lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Rectangle {

    private Point topLeft;
    private Point bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public boolean isIn(Point checkPoint) {
        if (checkPoint.X >= topLeft.X && checkPoint.X <= bottomRight.X) {
            if (checkPoint.Y >= topLeft.Y && checkPoint.Y <= bottomRight.Y) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[]a) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Input coordinates X & Y(separate with space) of top-left point: ");
            Point topLeft=Point.tryParse(reader.readLine());
            System.out.println("Input coordinates X & Y(separate with space) of bottom-right point: ");
            Point bottomRight=Point.tryParse(reader.readLine());

            Rectangle rectangle=new Rectangle(topLeft, bottomRight);

            System.out.println("Input coordinates X & Y(separate with space) of some point to check: ");
            Point somePoint=Point.tryParse(reader.readLine());

            if(rectangle.isIn(somePoint)){
                System.out.println("Point is in the Rectangle");
            }else {
                System.out.println("Point is out in the Rectangle");
            }

        } catch (WrongInput e) {
            System.out.println(e.getMessage());
        }
    }
}

class Point {
    public static Point tryParse(String str) throws WrongInput{
        int x=0, y=0, i = 0;
        try {
            for (String s : str.split(" ")) {
                if (!s.isEmpty()) {
                    if (i == 0) {
                        x = Integer.parseInt(s);
                        i++;
                    } else {
                        y=Integer.parseInt(s);
                        i++;
                        break;
                    }
                }
            }
            if(i!=2){
                throw new NumberFormatException();
            }
            return new Point(x,y);
        } catch (NumberFormatException e) {
            throw new WrongInput("Wrong input data");
        }
    }

    public final int X;
    public final int Y;

    public Point(int x, int y) {
        X = x;
        Y = y;
    }
}
