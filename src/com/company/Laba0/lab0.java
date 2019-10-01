package com.company.Laba0;

import java.io.*;
import java.util.ArrayList;

class lab0 {

    final public static String space10 = new String(new char[128]).replace('\0', '-');

    public static void main(String[] args)  throws IOException{
        String path = "C:\\Users\\pc\\Desktop\\lab0.txt";

        File readFile = new File(path);
        FileReader fr = null;
        fr = new FileReader(readFile);
        BufferedReader reader = new BufferedReader(fr);

        System.out.println("Размер данных в файле: " + readFile.length() + " байт");

        vivod();

        String line = null;
        line = reader.readLine();
        int j = 1;
        while(line!=null){
            vivod(
                    j,
                    line,
                    palindrom(line)? "+":"-",
                    numOfSymbols(line),
                    getBytes(line),
                    bitHartley(line),
                    bitShennon(line)
            );
            line = reader.readLine();
            j++;
        }
        System.out.println(space10);
    }

    public static boolean palindrom(String line)
    {
        int n = line.length();
        for (int i = 0; i < (n/2); ++i) {
            if (line.charAt(i) != line.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static int numOfSymbols(String line)
    {
        return line.length();
    }

    public static int getBytes(String line) throws UnsupportedEncodingException {
        final byte[] utf8Bytes = line.getBytes("UTF-8");
        return utf8Bytes.length;
    }

    public static double bitHartley(String line)
    {
        ArrayList list = unique(line);
        int uniqueChar = list.size();

        return numOfSymbols(line) * Math.log(uniqueChar)/Math.log(2.0);
    }

    public static ArrayList<Character> unique(String line)
    {
        ArrayList<Character> list = new ArrayList<Character>();
        for ( int i =0; i<line.length();i++) {
            char character = line.charAt(i);
            if(!list.contains(character)) {
                list.add(character);
            }
        }
        return list;
    }

    public static double bitShennon(String line)
    {
        ArrayList<Character> list = unique(line);
        double ans = 0;
        int tmp;
        for (char ch : list) {
            tmp = 0;
            for (int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == ch) tmp++;
            }
            ans -= (double)tmp/line.length()*Math.log((double)tmp/line.length())/Math.log(2.0);
        }
        return ans;
    }

    public static void vivod(){
        System.out.println(space10);
        System.out.println(" № | слово                                                  |           |            Количество информации ");
        System.out.println(space10);
        System.out.println("    |                                                        |           | кол-во   | байт, размер |     бит,   |    бит,");
        System.out.println("    |                                                        | палиндром | символов | в программе  |  по Хартли | по Шеннону ");

        System.out.println(space10);
    }

    public static void vivod(
            int i,
            String line,
            String palindrom,
            int numOfSymb,
            int numOfBytes,
            double bitHartley,
            double bitShennon
    )
    {
        System.out.printf(
                "%-4.4s|%-56.56s|%-11.11s|%-10.10s|%14.5s|%12.2f|%12.9f|",
                i,
                line,
                palindrom,
                numOfSymb,
                numOfBytes,
                bitHartley,
                bitShennon
        );
        System.out.println();
    }
}
