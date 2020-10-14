package com.arun.main;

import java.util.stream.IntStream;

/**
 * @author arun on 10/13/20
 */

public class SkippingAndLimiting {

    public static void main(String[] args) {
        //this will print out numbers from 0 to 30
        IntStream.range(0, 30)
                .forEach(System.out::println);

        System.out.println("-----------");

        //requirement : skip the 10 numbers and print
        IntStream.range(0, 30)
                .skip(10)
                .forEach(System.out::println);

        System.out.println("------------");
        //requirement : skip the first 10 numbers and print the next 10
        IntStream.range(0,30)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);
    }
}
