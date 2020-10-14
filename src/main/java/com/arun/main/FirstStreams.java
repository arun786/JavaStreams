package com.arun.main;

import com.arun.model.Person;
import com.arun.util.Builder;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author arun on 10/12/20
 */

public class FirstStreams {

    public static void main(String[] args) {
        List<Person> persons = Builder.personBuilder();

        //intermediate operations
        Stream<Person> personStream = persons.stream();
        //intermediate operations
        Stream<String> nameStream = personStream.map(Person::getName);
        //intermediate operations
        Stream<String> filteredNames = nameStream.filter(String::isEmpty);
        //terminal operations
        long count = filteredNames.count();

        System.out.println("Empty names " + count);

        //right way of using the stream
        long nonEmptyNames = persons.stream()
                .map(Person::getName)
                .filter(n -> !n.isEmpty())
                .count();

        System.out.println("Non Empty names " + nonEmptyNames);
    }
}
