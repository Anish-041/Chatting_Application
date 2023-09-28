package com.example.firstprogram;

class calculator {
    public int area(int x,int y) {
        return x*y;
    }
}
public class JavaProject {
    public static void main(String[] args) {
        calculator ops1= new calculator();
        int area= ops1.area(5,5);
        System.out.println(area);
    }
}
