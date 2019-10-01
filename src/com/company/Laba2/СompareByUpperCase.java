package com.company.Laba2;

import java.util.Comparator;

class ÑompareByUpperCase implements Comparator<String> {
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
