package com.arun.main;

import java.util.Arrays;
import java.util.List;

/**
 * @author arun on 10/14/20
 */

public class DropWhileTakeWhile {

    public static void main(String[] args) {

        //print the numbers till you encounter 30
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        numbers.stream().takeWhile(n -> n != 30).forEach(System.out::println);
    }
}
