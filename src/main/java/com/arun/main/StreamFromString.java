package com.arun.main;

import java.util.stream.Stream;

/**
 * @author arun on 10/13/20
 */

public class StreamFromString {

    public static void main(String[] args) {
        String sentence = "the quick brown fox jumps over the lazy dog";

        sentence.chars().mapToObj(Character::toString)
                .distinct()
                .sorted()
                .forEach(System.out::print);
    }

}
