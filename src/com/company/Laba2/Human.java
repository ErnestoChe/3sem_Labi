package com.company.Laba2;

import java.time.Year;

public class Human {
    private String name;
    private String surname;
    private String date_of_birth;
    // 1 - мужскойб 0 - женский
    private boolean sex;

    public Human(){}

    public int getAge()
    {
        int year = Integer.parseInt(date_of_birth.split("\\.")[2]);
        return Year.now().getValue()- year;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " +
                date_of_birth + " " + (isSex() ? "мужской":"женский");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
