package com.sakanal.bean;

import java.util.ArrayList;

public class Customer {
    public static void main(String[] args){
        System.out.println("Hello world!!");
        String[] arr=new String[]{"Tom","Jerry","HanMeiMei","LiLei"};
        System.out.println("x.for");
        for (String s : arr) {
            System.out.println("s = " + s);
        }
        System.out.println("x.fori");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr = " + arr[i]);
        }
        ArrayList list = new ArrayList();
        list.add("123");
        list.add("456");
        list.add("789");
        for (Object o : list) {
            System.out.println(o);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list);
        }
    }
    public void test(){
        System.out.println();
    }
    ArrayList list=new ArrayList();

}
