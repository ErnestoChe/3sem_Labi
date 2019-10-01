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

    public void addDot(Dot2D dot)
    {
        list.add(dot);
        countOfDots++;
    }

    public double length(){
        double res = 0;
        for (int i = 0; i < countOfDots-1; i++) {
            res+= list.get(i).distToDot(list.get(i+1));
        }
        return res;
    }
}
