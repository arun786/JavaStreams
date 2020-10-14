package com.arun.util;

import com.arun.model.City;
import com.arun.model.Person;

import java.util.List;

/**
 * @author arun on 10/12/20
 */

public class Builder {

    /**
     * Build a list of person
     *
     * @return List of Person
     */
    public static List<Person> personBuilder() {
        Person p1 = new Person().setName("Arun").setAge(25);
        Person p2 = new Person().setName("Paul").setAge(27);
        Person p3 = new Person().setName("Sarah").setAge(31);
        Person p4 = new Person().setName("Julie").setAge(25);
        Person p5 = new Person().setName("Charlotte").setAge(22);
        Person p6 = new Person().setName("Ann").setAge(31);
        Person p7 = new Person().setName("Arun").setAge(27);
        Person p8 = new Person().setName("Boris").setAge(29);
        Person p9 = new Person().setName("Emily").setAge(34);
        Person p10 = new Person().setName("").setAge(34);

        return List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
    }

    public static List<City> cityBuilder() {
        List<Person> people = personBuilder();

        City c1 = new City().setName("New York").setPersons(people.subList(0, 3));
        City c2 = new City().setName("Phoenix").setPersons(people.subList(3, 7));
        City c3 = new City().setName("Scottsdale").setPersons(people.subList(7, people.size()));

        return List.of(c1, c2, c3);

    }
}
