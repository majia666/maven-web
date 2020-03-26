package com.majia.test;

import org.junit.Test;

import java.util.Arrays;

public class TestMianShi {

    @Test
    public void test1(){
//        String s="hello";
//        String t ="hello";
//        char[] c = {'h','e','l','l','o'};
//        System.out.println(s.equals(t));
//        System.out.println(t.equals(c));
//        System.out.println(s==t);
//        System.out.println(t.equals(new String("hello")));
//        int a[ ] = {5,4,9,8,7,6,0,1,3,2};
//        Arrays.stream(a).sorted().forEach(i->{
//            System.out.println(i);
//        });
//        String sentence = "I am Lucy";
//        String[] words = sentence.split(" ");
//        int count = 0;
//        for (int i = 0; i <words.length;i++) {
//            if (words[i] != null && words[i].length() >0) {
//                count++;
//            }
//        }
//        System.out.println(count);

        String ss ="7i8hy4jjnb2";

        char[] chars = ss.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c:chars) {
           if(!Character.isAlphabetic(c)){
               sb.append(c);
           }
        }
        System.out.println(sb);

    }
}
