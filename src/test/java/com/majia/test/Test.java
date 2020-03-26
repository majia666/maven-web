package com.majia.test;

public class Test {

    public static void main(String[] args) {
        System.err.println(method());
    }
    public static boolean method() {
        try {
            return true;
        } finally {
            return false;
        }
    }
}
