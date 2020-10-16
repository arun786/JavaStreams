package com.arun.reduction;

import java.util.List;
import java.util.Optional;


/**
 * @author arun on 10/15/20
 */

public class SimpleReduction {

    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 1, 1, 1, 1);
        //if we dont pass the identity element, we get back the optional method
        Optional<Integer> reduce = nums.stream().reduce(Integer::sum);

        Integer integer = reduce.get();
        Integer integer1 = reduce.orElseThrow();

        System.out.println(integer);
        System.out.println(integer1);

        //if we pass the identity element, we get the number
        Integer sum = nums.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
