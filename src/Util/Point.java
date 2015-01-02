package Util;

/**
 * Created by serinyoon on 9/8/14.
 * Modified by serinyoon on 12/28/14.
 */
public class Point {
    private int x;
    private int y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point getLocation() {
        return this;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Point pt) {
        return this.x == pt.getX() && this.y == pt.getY();
    }

}
