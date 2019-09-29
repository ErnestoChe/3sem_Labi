package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Year;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class lab2 {
    public static void main(String[] args) throws FileNotFoundException {

        //zadanie1();
        zadanie2();
        //zadanie3();
    }

    public static void zadanie1()
    {
        /*System.out.println(stroka(line));
        System.out.println();
        System.out.println("Enter number to stack and deque: ");
        line = scn.nextLine();
        pervoe(line);*/
    }

    public static void zadanie2() throws FileNotFoundException {
        ArrayList<Human> humans = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        Scanner scn = new Scanner(new File("humans.txt"));

        while(scn.hasNext()){
            Human tmp = new Human();
            tmp.setName(scn.next());
            tmp.setSurname(scn.next());
            tmp.setDate_of_birth(scn.next());
            tmp.setSex(scn.next().equals("мужской"));
            humans.add(tmp);
        }
        for (Human h: humans) {
            System.out.println(h.toString());
            System.out.println(h.getAge());
        }
    }
    public static void zadanie3()
    {
        /*Scanner scn = new Scanner(System.in);
        System.out.println("Enter line to check brackets:");
        String line = scn.nextLine();*/

        String[] test = new String[7];
        test[0] = "(([{}])((({}))([]){}))";  //must be true
        test[1] = "(()()(()}[}";             //must be false
        test[2] = "({[";                     //must be false
        test[3] = ")}]";                     //must be false
        test[4] = "([])({})(())";            //must be true
        test[5] = "{)({})([])())(}";         //must be false
        test[6] = "()()()))(}[[]((()";       //must be false

        for (int i = 0; i < test.length; i++) {
            System.out.println(stroka(test[i]));
        }
    }



    //Третье задание
    public static boolean stroka(String line)
    {
        Stack<Character> stack = new Stack<Character>();

        char c;
        for(int i=0; i < line.length(); i++) {
            c = line.charAt(i);
            if(c == '(' || c == '{' || c == '[')
                stack.push(c);
            else if(c == ')')
                if(stack.empty())
                    return false;
                else if(stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            else if(c == '}')
                if(stack.empty())
                    return false;
                else if(stack.peek() == '{')
                    stack.pop();
                else
                    return false;
            else if(c == ']')
                if(stack.empty())
                    return false;
                else if(stack.peek() == '[')
                    stack.pop();
                else
                    return false;
        }
        return stack.empty();
    }

    //Первое задание
    public static void pervoe(String line)
    {
        char[] arr = line.toCharArray();
        Stack<Character> stack = new Stack<>();
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
            deque.addLast(arr[i]);
        }
        System.out.println("стек в обратном порядке");
        while(!stack.empty()) System.out.println(stack.pop());
        for (int i = arr.length-1; i >= 0 ; i--) {
            stack.push(arr[i]);
        }
        System.out.println("стек в нормальном порядке");
        while(!stack.empty()) System.out.println(stack.pop());
        System.out.println("очередь в нормальном порядке");
        while(!deque.isEmpty()) System.out.println(deque.pollFirst());
        for (int i = 0; i < arr.length; i++) {
            deque.addFirst(arr[i]);
        }
        System.out.println("Очередь в обратном порядке");
        while(!deque.isEmpty()) System.out.println(deque.poll());
    }

}

class Human{

    private String name;
    private String surname;
    private String date_of_birth;
    // 1 - мужской, 0 - женский
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
                date_of_birth + " " + (isSex() ? "муж":"жен");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
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
