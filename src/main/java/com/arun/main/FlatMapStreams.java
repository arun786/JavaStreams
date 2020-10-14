package com.arun.main;

import com.arun.model.City;
import com.arun.model.Person;
import com.arun.util.Builder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author arun on 10/12/20
 */

public class FlatMapStreams {

    public static void main(String[] args) {
        List<City> cities = Builder.cityBuilder();

        System.out.println("List of cities " + cities);

        //the below is the output

        /*
         * List of cities
         * [       City(name=New York, persons=[Person(name=Arun, age=25), Person(name=Paul, age=27), Person(name=Sarah, age=31)]),
         *         City(name=Phoenix, persons=[Person(name=Julie, age=25), Person(name=Charlotte, age=22), Person(name=Ann, age=31), Person(name=Arun, age=27)]),
         *         City(name=Scottsdale, persons=[Person(name=Boris, age=29), Person(name=Emily, age=34), Person(name=, age=34)])
         * ]
         */

        //requirement is to get all the Persons from all the cities
        List<Person> persons = cities.stream()
                .flatMap(city -> city.getPersons().stream())
                .collect(Collectors.toList());

        System.out.println("Persons " + persons);

        //output is as below

        /*
         * Persons
         * [
         *  Person(name=Arun, age=25), Person(name=Paul, age=27), Person(name=Sarah, age=31),
         *  Person(name=Julie, age=25), Person(name=Charlotte, age=22), Person(name=Ann, age=31),
         *  Person(name=Arun, age=27), Person(name=Boris, age=29), Person(name=Emily, age=34), Person(name=, age=34)]
         */

        //requirement is to get only the names and display
        cities.stream()
                .flatMap(city -> city.getPersons().stream())
                .map(Person::getName)
                .forEach(System.out::println);
    }
}
