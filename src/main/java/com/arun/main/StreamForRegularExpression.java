package com.arun.main;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author arun on 10/13/20
 */

public class StreamForRegularExpression {

    public static void main(String[] args) {

        String sentence = "the quick brown fox jumps over the lazy dog";

        //Not the best way to do it
        String[] words = sentence.split(" ");
        Stream<String> stream = Arrays.stream(words);
        stream.forEach(System.out::println);

        //best way
        Pattern pattern = Pattern.compile(" ");
        Stream<String> stringStream = pattern.splitAsStream(sentence);
        stringStream.forEach(System.out::println);
    }
}
