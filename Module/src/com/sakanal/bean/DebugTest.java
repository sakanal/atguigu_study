package com.sakanal.bean;

import java.util.HashMap;

public class DebugTest {
    public static void main(String[] args) {

        System.out.println("Debug test!");

        HashMap<String, String> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age","18");
        map.put("school","ECUT");
        map.put("major","computer");

        String age = map.get("age");
        System.out.println("age = " + age);

        map.remove("major");
        System.out.println(map);
    }
}
