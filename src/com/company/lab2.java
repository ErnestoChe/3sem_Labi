package com.company;

import java.util.Scanner;
import java.util.Stack;

public class lab2 {
    public static void main(String[] args)
    {
        Scanner scn = new Scanner(System.in);
        String line = scn.nextLine();
        pervoe(line);
    }


    //Третье задание
    public static boolean stroka(String line)
    {
        Stack<Character> stack = new Stack<Character>();

        char c;
        for(int i=0; i < line.length(); i++) {
            c = line.charAt(i);

            if(c == '(')
                stack.push(c);
            else if(c == '{')
                stack.push(c);
            else if(c == '[')
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

    public static void pervoe(String line)
    {

    }
}
