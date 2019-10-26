package com.company.Laba4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        String test = "19385";
//        one_a("abcd111111102019");
//        one_b(test);
//        one_c("А3.14рдвдадаш23.68ллвд");
//        one_d("russifikator telega patch computer technologies infomathics");
//        isGUID("e02fd0e4-00fd-090A-ca30-0d00a0038ba0");
//        isColor("#FF00FF");
//        directory("C:\\Users\\pc\\IdeaProjects\\3sem\\newFile.xml");
//        suffix("столик, карандашик");
//        prices("104.5 USD , 103.8 EU , 45.23 RUR , 413.3 RD , 18.85 RUR , 13.44 EU , 64.48 USD");
//        isMACAdress("aE:dC:cA:56:76:54");
//        isURLAdress("http://www.zcontest.ru");
//        isDateFormat("01/01/1603");
//        isEmail("erph0511@gmail.com");
//        isIP("22.22.22.22");
//        isSixDigit("234561");
//        twelve("3+5+12");

    }

    //1a
    public static void one_a(String s)
    {
        boolean ans = Pattern.matches("abcd1{7}02019", s);
        System.out.println("1a) " + ans);
    }
    //1б
    public static void one_b(String s)
    {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(s);
        ArrayList<Integer> list = new ArrayList<>();
        while (matcher.find()){
            list.add(Integer.parseInt(s.substring(matcher.start(), matcher.end())));
        }
        if(!list.isEmpty()){
            int max = list.get(0);
            int index = 0;
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i);
                if(max < list.get(i)){
                    max = list.get(i);
                    index = i;
                }
            }
            System.out.println(list);
            System.out.println(
                    "sum " + sum +
                            " ind " + index +
                            " " + list.get(index));
        }else System.out.println("пустая строка");
        System.out.println();
    }
    //1в
    public static void one_c(String s)
    {
        Pattern pattern = Pattern.compile("(\\d*([.,]\\d*|\\d))");
        Matcher matcher = pattern.matcher(s);
        ArrayList<String> list = new ArrayList<>();
        while (matcher.find()){
            list.add(s.substring(matcher.start(), matcher.end()));
        }
        for (String a: list) {
            System.out.println(a);
        }
        System.out.println();
    }
    //1г
    public static void one_d(String s){

        Pattern pattern = Pattern.compile("\\w{10,}");

        Matcher matcher = pattern.matcher(s);
        String replaceForStars = s;
        String replaceForFirstLetters = s;
        while(matcher.find()){
            replaceForStars = matcher.replaceAll("*");
        }
        System.out.println(replaceForStars);

        matcher = pattern.matcher(replaceForFirstLetters);
        while (matcher.find()) {
            replaceForFirstLetters = replaceForFirstLetters.replace(
                    replaceForFirstLetters.substring(matcher.start(), matcher.end()),
                    String.valueOf(replaceForFirstLetters.charAt(matcher.start())));
            matcher = pattern.matcher(replaceForFirstLetters);
        }
        System.out.println(replaceForFirstLetters);

        matcher = pattern.matcher(s);
        StringBuffer replaceForLetters = new StringBuffer(s);
        while(matcher.find()){
            int k = matcher.start();
            while (k!=matcher.end()){
                replaceForLetters.setCharAt(k, s.charAt(matcher.start()));
                k++;
            }
        }
        System.out.println(replaceForLetters);
        System.out.println();
    }
    //1д
    public static boolean isGUID(String s){
        boolean ans = Pattern.matches("[0-9A-Fa-f]{8}-([0-9A-Fa-f]{4}-){3}[0-9A-Fa-f]{12}", s);
        System.out.println(s);
        System.out.println(ans);
        System.out.println();
        return ans;
    }
    //1е
    public static boolean isColor(String s){
        boolean ans = Pattern.matches("#([0-9A-Fa-f]{6})", s);
        System.out.println(s);
        System.out.println(ans);
        System.out.println();
        return ans;
    }
    //1ж
    public static void directory(String s){
        Pattern pattern = Pattern.compile("[0-9A-Za-z]+");
        Matcher matcher = pattern.matcher(s);
        ArrayList<String> list = new ArrayList<>();
        while(matcher.find()){
            list.add(s.substring(matcher.start(), matcher.end()));
        }
        //удаляет формат файла
        list.remove(list.size()-1);
        System.out.println(list);
        System.out.println();
    }
    //1з
    public static void suffix(String s){
        Pattern pattern = Pattern.compile("ик");
        Matcher matcher = pattern.matcher(s);
        System.out.println(s);
        s = matcher.replaceAll("");
        System.out.println(s);
        System.out.println();
    }
    //1и
    public static void prices(String s){
        Pattern pattern_dollar = Pattern.compile("(([0-9]*[.])?[0-9])+\\s(USD)");
        Pattern pattern_rub = Pattern.compile("([0-9]*[.])?[0-9]+\\s(RUR)");
        Pattern pattern_euro = Pattern.compile("([0-9]*[.])?[0-9]+\\s(EU)");

        ArrayList<String> rur = new ArrayList<>();
        ArrayList<String> usd = new ArrayList<>();
        ArrayList<String> eur = new ArrayList<>();

        Matcher matcher = pattern_dollar.matcher(s);
        while (matcher.find()) {
            usd.add(s.substring(matcher.start(), matcher.end()-4));
        }

        matcher = pattern_euro.matcher(s);
        while (matcher.find()) {
            eur.add(s.substring(matcher.start(), matcher.end()-3));
        }

        matcher = pattern_rub.matcher(s);
        while (matcher.find()) {
            rur.add(s.substring(matcher.start(), matcher.end()-4));
        }

        System.out.println(usd);
        System.out.println(rur);
        System.out.println(eur);

        Scanner scn = new Scanner(System.in);
        System.out.println("введите валюту ");
        String name = scn.next();
        System.out.println("введите курсы");
        double to_usd, to_rub, to_euro;
        System.out.print("1 USD = ");
        to_usd = Double.parseDouble(scn.next());
        System.out.print("1 RUR = ");
        to_rub = Double.parseDouble(scn.next());
        System.out.print("1 EU = ");
        to_euro = Double.parseDouble(scn.next());

        String answer = "";
        for (String val : usd) {
            answer += Double.parseDouble(val) * to_usd + " " + name + " ";
        }
        answer += "\n";
        for (String val : rur) {
            answer += Double.parseDouble(val) * to_rub + " " + name + " ";
        }
        answer += "\n";
        for (String val : eur) {
            answer += Double.parseDouble(val) * to_euro + " " + name + " ";
        }
        System.out.println(answer);
    }

    //3
    public static boolean isMACAdress(String s){
        boolean answer = Pattern.matches("([0-9A-Fa-f]{2}[:\\-\\.]){5}[0-9A-Fa-f]{2}", s);
        System.out.println(s);
        System.out.println(answer);
        System.out.println();
        return answer;
    }
    //4
    public static boolean isURLAdress(String s){
        boolean answer1 = Pattern.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", s);
        System.out.println(s);
        System.out.println(answer1);
        return answer1;
    }
    //5
    public static boolean isHexColor(String s){
        boolean ans = Pattern.matches("#([0-9A-Fa-f]{6})", s);
        System.out.println(s);
        System.out.println(ans);
        System.out.println();
        return ans;
    }
    //6 (dd/mm/yyyy)
    public static boolean isDateFormat(String s){
        boolean ans = Pattern.matches("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/(1[6-9]||[2-9][0-9])?[0-9][0-9]$", s);
        System.out.println(s);
        System.out.println(ans);
        return ans;
    }
    //7
    public static boolean isEmail(String s){
        boolean ans = Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+", s);
        System.out.println(s);
        System.out.println(ans);
        return ans;
    }
    //8
    public static boolean isIP(String s){
        String IPADDRESS_PATTERN =
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        boolean ans = Pattern.matches(IPADDRESS_PATTERN, s);
        System.out.println(s);
        System.out.println(ans);
        return ans;
    }
    //9
    public static boolean isSecurePass(String s){
        boolean ans = Pattern.matches("(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}", s);
        System.out.println(s);
        System.out.println(ans);
        System.out.println();
        return ans;
    }
    //10
    public static boolean isSixDigit(String s){
        boolean ans = Pattern.matches("^[1-9]\\d{5}", s);
        System.out.println(s);
        System.out.println(ans);
        System.out.println();
        return ans;
    }
    //12
    public static boolean twelve(String s){
        Pattern pattern = Pattern.compile("\\d(\\+)");
        Matcher matcher = pattern.matcher(s);
        ArrayList<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        System.out.println(!list.isEmpty());
        System.out.println(list);
        return !list.isEmpty();
    }

}


