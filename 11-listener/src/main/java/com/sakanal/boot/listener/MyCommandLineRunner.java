package com.sakanal.boot.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *  当项目启动后执行run方法
 */

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner.........run");
        System.out.println(Arrays.asList(args));
    }
}
