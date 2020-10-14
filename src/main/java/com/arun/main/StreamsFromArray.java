package com.arun.main;

import com.arun.model.Person;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author arun on 10/12/20
 */


public class StreamsFromArray {

    public static void main(String[] args) {
        Person p1 = new Person().setName("Adwiti").setAge(25);
        Person p2 = new Person().setName("Paul").setAge(27);
        Person p3 = new Person().setName("Sarah").setAge(31);
        Person p4 = new Person().setName("Julie").setAge(25);
        Person p5 = new Person().setName("Charlotte").setAge(22);

        Person[] persons = {p1, p2, p3, p4, p5};

        //to create streams of person, first way is to use Stream.of()
        long count = Stream.of(persons).count();

        System.out.println("Count " + count);

        //second way is to create Arrays.stream()
        Arrays.stream(persons).forEach(System.out::println);

    }
}
