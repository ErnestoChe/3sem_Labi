package com.company.Laba2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class lab2 {
    public static void main(String[] args) throws FileNotFoundException {

        //zadanie1();
        //zadanie2();
        //zadanie3();
        zadanie4();
        //zadanie5();
        //zadanie6();
    }

    public static void zadanie1() {
        /*System.out.println(stroka(line));
        System.out.println();
        System.out.println("Enter number to stack and deque: ");
        line = scn.nextLine();
        pervoe(line);*/
    }

    public static void zadanie2() throws FileNotFoundException {
        String path = "C:\\Users\\pc\\IdeaProjects\\3sem\\src\\com\\company\\Laba2\\humans.txt";
        Stack<Human> humans = new Stack<>();
        Stack<Human> tmp_h = new Stack<>();
        Human tmp;
        Scanner scn = new Scanner(new File(path));

        while (scn.hasNext()) {
            tmp = new Human();
            tmp.setName(scn.next());
            tmp.setSurname(scn.next());
            tmp.setDate_of_birth(scn.next());
            tmp.setSex(scn.next().equals("мужской"));
            humans.push(tmp);
        }
        while (!humans.isEmpty()) {
            tmp = humans.pop();
            if (tmp.getAge() < 40) {
                System.out.println(tmp.toString());
            } else tmp_h.push(tmp);
        }
        System.out.println("Старше сорока");
        while (!tmp_h.isEmpty()) {
            System.out.println(tmp_h.pop().toString());
        }
    }

    public static void zadanie3() {
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

    public static void zadanie4() {

        String test = " M(m(3,5),M(1,2))";
        String test1 = "m(1,2)";
        String test2 = "M(M(M(M(9,1),2),3),4)";
        String test3 = "M(1,M(2,m(3,M(4,M(5,m(6,M(9,0)))))))";
        Scanner scanner = new Scanner(System.in);
        String f =
                //scanner.nextLine();
                test3;
        Stack<Character> someStack = new Stack<>();
        for (int i = 0; i < f.length(); i++) {
            if (f.charAt(i) == ')') {
                int a = someStack.pop();
                int b = someStack.pop();
                someStack.pop();
                if (someStack.pop() == 'M')
                    if (b > a) someStack.push((char) b);
                    else someStack.push((char) a);
                else if (b < a) someStack.push((char) b);
                else someStack.push((char) a);
            } else if (f.charAt(i) != ',') someStack.push(f.charAt(i));
        }
        System.out.println(someStack.pop());
    }

    public static int M(int i1, int i2) {
        return Math.max(i1, i2);
    }

    public static int m(int i1, int i2) {
        return Math.min(i1, i2);
    }

    public static void zadanie5() throws FileNotFoundException {
        String path = "C:\\Users\\pc\\IdeaProjects\\3sem\\src\\com\\company\\Laba2\\lines.txt";
        Scanner scanner = new Scanner(new File(path));
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        //Лексикографический порядок(по алфавиту)
        Collections.sort(lines);
        for (String s : lines) {
            System.out.println(s);
        }
        System.out.println();
        //По длине строки
        Collections.sort(lines, Comparator.comparingInt(String::length));
        for (String s : lines) {
            System.out.println(s);
        }
        System.out.println();
        //По количеству заглавных латинских букв в строке
        Collections.sort(lines, new CompareByUpperCase().reversed());
        for (String s : lines) {
            System.out.println(s);
        }
    }

    public static void zadanie6() {
        Dot2D[] dots = new Dot2D[10];
        dots[0] = new Dot2D(1, 1);
        dots[1] = new Dot2D(2, 1);
        dots[2] = new Dot2D(2, 4);
        dots[3] = new Dot2D(5, 4);
        dots[4] = new Dot2D(5, 2);
        AngledLine line = new AngledLine(dots[0]);
        for (int i = 1; i < 5; i++) {
            line.addDot(dots[i]);
        }

        System.out.println(line.getLength());
        System.out.println(line.getCountOfDots());
    }

    //Первое задание
    public static void pervoe(String line) {
        char[] arr = line.toCharArray();
        Stack<Character> stack = new Stack<>();
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
            deque.addLast(arr[i]);
        }
        System.out.println("стек в обратном порядке");
        while (!stack.empty()) System.out.println(stack.pop());
        for (int i = arr.length - 1; i >= 0; i--) {
            stack.push(arr[i]);
        }
        System.out.println("стек в нормальном порядке");
        while (!stack.empty()) System.out.println(stack.pop());
        System.out.println("очередь в нормальном порядке");
        while (!deque.isEmpty()) System.out.println(deque.pollFirst());
        for (int i = 0; i < arr.length; i++) {
            deque.addFirst(arr[i]);
        }
        System.out.println("Очередь в обратном порядке");
        while (!deque.isEmpty()) System.out.println(deque.poll());
    }

    //Третье задание
    public static boolean stroka(String line) {
        Stack<Character> stack = new Stack<Character>();

        char c;
        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else if (c == ')')
                if (stack.empty())
                    return false;
                else if (stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            else if (c == '}')
                if (stack.empty())
                    return false;
                else if (stack.peek() == '{')
                    stack.pop();
                else
                    return false;
            else if (c == ']')
                if (stack.empty())
                    return false;
                else if (stack.peek() == '[')
                    stack.pop();
                else
                    return false;
        }
        return stack.empty();
    }


}

