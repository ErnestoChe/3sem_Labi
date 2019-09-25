package com.company;

import java.util.Random;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class lab1 {
    public static void main(String[] args) {
        TrigFunctional func[] = new TrigFunctional[4];
        func[0] = (double x) -> 2 * Math.sin(x) + 1;
        func[1] = (double x) ->((x/Math.PI - 1) * (x/Math.PI - 1));
        func[2] = (double x) ->(-(x/Math.PI)*(x/Math.PI) - 2 * x + 5*Math.PI);
        func[3] = (double x) ->(0.5 * Math.cos(x) * Math.cos(x) + 1);

        TrigFunctional equations[] = new TrigFunctional[4];
        equations[0] = (double x)->(x * Math.sin(x)-0.5);
        equations[1] = (double x)->(Math.log(x*x - 3 * x +2));
        equations[2] = (double x)->(0.5 * Math.tan(2*(x * Math.PI/4)/3)-1);


        /*
        Первое и второе задание
         */
        //function(-2*Math.PI, 2*Math.PI, Math.PI/6, func);
        //второе задание и третье
        //func2b(func, 10000);
        /*
        Четвертое задание
         */
        System.out.println("Четвертое задание");
        getRoot(0, Math.PI, equations[0]);
        getRoot(0, 0.9, equations[1]);
        getRoot(2.1, 5.0, equations[1]);
        getRoot(Math.PI, 2 * Math.PI, equations[2]);
        /*
        Пятое задание
         */
        System.out.println("Пятое задание");
        System.out.println(integral(-Math.PI, Math.PI, 20, func[0]));
        System.out.println(integral(-Math.PI, Math.PI, 20, func[2]));
        System.out.println(integral(-Math.PI, Math.PI, 20, func[3]));
        /*
        Шестое задание
         */
        System.out.println("Шестое задание");
        String[] arr = {"топор", "wopor", "wok", "wow", "gogog"};
        Vector<String> vtr = new Vector<>();
        for (int i = 0; i < arr.length; i++) {
            vtr.add(arr[i]);
        }
        stroki(vtr);

    }

    //первое и второе задание
    public static void function(double left, double right, double step, TrigFunctional[] func)
    {
        int gl_negative = 0;
        for (int i = 0; i < func.length; i++) {
            int negative = 0;
            for (double j = left; j <= right; j +=step){
                if(func[i].calc(j)<0){
                    negative++;
                    gl_negative++;
                }
                System.out.printf("%.3f", func[i].calc(j));
                System.out.println();
            }
            System.out.println("for " + i + " func negative values " + negative);
            System.out.println();
        }
        System.out.println("negatives in total " + gl_negative);
        System.out.println();
    }

    public static void func2b(TrigFunctional[] func, int n)
    {
        Random rnd = new Random();
        double min, max;
        double[] arr = new double[n];
        for (int j = 0; j < func.length; j++) {
            min = 1000000; max = -min;
            for (int i = 0; i < n; i++) {
                arr[i] = -10 + (20) * rnd.nextDouble();
                if(func[j].calc(arr[i]) < min) min = func[j].calc(arr[i]);
                if(func[j].calc(arr[i]) > max) max = func[j].calc(arr[i]);
            }
            System.out.println("min for " + j +" 'th func is " + min);
            System.out.println("max for " + j +" 'th func is " + max);
            System.out.println();
        }
    }
    //Четвертое задание
    public static double getRoot(double left, double right, TrigFunctional func)
    {
        double root;
        double epsilon = 0.0001;
        double mid = 0;
        while((right - left)>epsilon) {
            mid =(right + left)/2;
            if(func.calc(mid)==0) {
                root = mid;
                System.out.println(root);
                return root;
            }else if(func.calc(mid)*func.calc(left)<0) {
                right = mid;
            }else {
                left = mid ;
            }
        }
        System.out.println(mid);
        return mid;
    }
    //Пятое задание
    public static double integral(double left, double right, double dx, TrigFunctional func)
    {
        double res = 0;
        double step = (right - left)/dx;

        for (double i = left; i < right; i+=step) {
            res+=func.calc(i) * step;
        }
        return res;
    }
    //Шестое задание
    public static void stroki(Vector<String> vctr){
        //6а
        Function<String, Integer> function = s -> (s.length() == 5 ? 1:0);
        int char5 = 0;
        //6b
        int sum_palindrom = 0;
        Predicate<String> predicate = s -> {
            int n = s.length();
            for (int i = 0; i < (n/2); ++i) {
                if (s.charAt(i) != s.charAt(n - i - 1)) {
                    return false;
                }
            }
            return true;
        };
        //6c
        Consumer<String> consumer = s -> {
            if(s.charAt(0) == 'w' || s.charAt(0) == 'W') System.out.println(s);
        };
        //вывод
        for(String str:vctr){
            char5+=function.apply(str);
            if(predicate.test(str)) sum_palindrom++;
            consumer.accept(str);
        }
        System.out.println("Пятибуквенных слов = " + char5 );
        System.out.println("Палиндромов всего = " + sum_palindrom);
    }
}

interface TrigFunctional{
    double calc(double x);
}
