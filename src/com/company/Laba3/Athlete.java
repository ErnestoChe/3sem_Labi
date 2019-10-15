package com.company.Laba3;

import java.util.List;

public class Athlete{
    private String date_of_birth;
    private String sex;
    private String name;
    private List<Competition> comps;

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Competition> getComps() {
        return comps;
    }

    public void setComps(List<Competition> comps) {
        this.comps = comps;
    }

    @Override
    public String toString() {
        return "Имя: " + getName() + ", дата рождения " + getDate_of_birth();
    }

    public int getMedals()
    {
        int k = 0;
        for (Competition comp:comps) {
            if(!comp.getAward().equals(""))k++;
        }
        return k;
    }
}
