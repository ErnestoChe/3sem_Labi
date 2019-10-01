package com.company.Laba2;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Year;
import java.util.*;

public class lab2 {
    public static void main(String[] args) throws FileNotFoundException {

        //zadanie1();
        //zadanie2();
        //zadanie3();
        //
        zadanie5();
    }

    public static void zadanie1()
    {
        /*System.out.println(stroka(line));
        System.out.println();
        System.out.println("Enter number to stack and deque: ");
        line = scn.nextLine();
        pervoe(line);*/
    }

    public static void zadanie2() throws FileNotFoundException
    {
        String path = "C:\\Users\\pc\\IdeaProjects\\3sem\\src\\com\\company\\Laba2\\humans.txt";
        Stack<Human> humans = new Stack<>();
        Stack<Human> tmp_h = new Stack<>();
        Human tmp;
        Scanner scn = new Scanner(new File(path));

        while(scn.hasNext()){
            tmp = new Human();
            tmp.setName(scn.next());
            tmp.setSurname(scn.next());
            tmp.setDate_of_birth(scn.next());
            tmp.setSex(scn.next().equals("мужской"));
            humans.push(tmp);
        }
        while(!humans.isEmpty()){
            tmp = humans.pop();
            if(tmp.getAge() < 40){
                System.out.println(tmp.toString());
            }
            else tmp_h.push(tmp);
        }
        System.out.println("Старше сорока");
        while(!tmp_h.isEmpty()){
            System.out.println(tmp_h.pop().toString());
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

    public static void zadanie4()
    {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

    }

    public static void zadanie5() throws FileNotFoundException
    {
        String path = "C:\\Users\\pc\\IdeaProjects\\3sem\\src\\com\\company\\Laba2\\lines.txt";
        Scanner scanner = new Scanner(new File(path));
        ArrayList<String> lines = new ArrayList<>();
        while(scanner.hasNext()){
            lines.add(scanner.nextLine());
        }
        //Лексикографический порядок(по алфавиту)
        Collections.sort(lines);
        for (String s:lines) {
            System.out.println(s);
        }
        System.out.println();
        //По длине строки
        Collections.sort(lines, Comparator.comparingInt(String::length));
        for (String s:lines) {
            System.out.println(s);
        }
        System.out.println();
        //По количеству заглавных латинских букв в строке
        Collections.sort(lines, new compareByUpperCase().reversed());
        for (String s:lines) {
            System.out.println(s);
        }
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

class compareByUpperCase implements Comparator<String>{
    public int compare(String s1, String s2){
        int count1 = 0, count2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) <= 'Z' && s1.charAt(i) >= 'A') count1++;
        }

        for (int i = 0; i < s2.length(); i++) {
            if(s2.charAt(i) <= 'Z' && s2.charAt(i) >= 'A') count2++;
        }

        return Integer.compare(count1, count2);
    }
}
