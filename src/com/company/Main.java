package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

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

            String pal = palindrom(line)? "+":"-";
            int num_of_symb = numOfSymbols(line);
            int num_of_bytes = getBytes(line);
            double hartley = bitHartley(line);

            vivod(j, line, pal, num_of_symb, num_of_bytes, hartley);

            line = reader.readLine();

            j++;
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
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

    public static ArrayList unique(String line)
    {
        ArrayList list = new ArrayList();
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
        ArrayList<String> list = unique(line);
        for (String str : list) {

        }
        return 0;
    }

    public static void vivod(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" № | слово                                                  |           |            Количество информации ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("    |                                                        |           | кол-во   | байт, размер |     бит,   |    бит,");
        System.out.println("    |                                                        | палиндром | символов | в программе  |  по Хартли | по Шеннону ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }
    public static void vivod(int i, String line, String palindrom, int numOfSymb, int numOfBytes, double bitHartley/*, double bitShennon*/)
    {
        System.out.printf("%-4.4s", i);
        System.out.printf("%-57.57s", "| "+line);
        System.out.printf("%-12.12s", "| " + palindrom);
        System.out.printf("%-11.11s", "| " + numOfSymb);
        System.out.printf("|");
        System.out.printf("%15.5s", numOfBytes + " |");
        System.out.printf("%11.2f", bitHartley);
        System.out.printf(" |");
        System.out.println();
    }
}
