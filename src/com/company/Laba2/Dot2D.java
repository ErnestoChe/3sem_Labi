package com.company.Laba2;

public class Dot2D {
    private double x, y;

    public Dot2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" +
                "" + x +
                ";" + y +
                ')';
    }

    public double distToDot(Dot2D d){
        return Math.sqrt(Math.pow(this.x - d.getX(), 2) + Math.pow(this.y - d.getY(),2));
    }
}
