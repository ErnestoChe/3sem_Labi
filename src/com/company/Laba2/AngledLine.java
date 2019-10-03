package com.company.Laba2;

import java.util.ArrayList;

public class AngledLine {
    private ArrayList<Dot2D> list;
    private int countOfDots;

    AngledLine(Dot2D dot){
        list = new ArrayList<>();
        list.add(dot);
        countOfDots = 1;
    }

    AngledLine(Dot2D[] dots){
        list = new ArrayList<>();
        for (int i = 0; i < dots.length; i++) {
            list.add(dots[i]);
        }
        countOfDots = dots.length;
    }

    public void addDot(Dot2D dot)
    {
        list.add(dot);
        countOfDots++;
    }

    public double getLength(){
        double res = 0;
        for (int i = 0; i < countOfDots-1; i++) {
            res+= list.get(i).getDistance(list.get(i+1));
        }
        return res;
    }

    public int getCountOfDots(){
        return countOfDots;
    }
}
