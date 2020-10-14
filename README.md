# Java Streams

1. Java Stream is an implementation of map/filter/reduce
2. Stream is an object that does not carry any data.
3. it consists of two types
    1. intermediate operations
    2. Terminal operations
    
## Build a Builder

    package com.arun.util;
    
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
    }


Once streams operated, we cannot perform operation on it again

Right way of using streams

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

## FlatMap


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


## Streaming of lines

    package com.arun.main;
    
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.stream.Stream;
    
    /**
     * @author arun on 10/13/20
     */
    
    
    public class StreamFromTextFile {
    
    
        public static void main(String[] args) {
    
            Path path = Path.of("src/main/resources/first-names.txt");
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


## Streaming Regular Expression

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

## Stream from String

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

## Skipping and Limiting