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


## TakeWhile

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

## mapToInt

    package com.arun.forloop;
    
    import com.arun.model.Person;
    import com.arun.util.Builder;
    
    import java.util.List;
    
    /**
     * @author arun on 10/14/20
     */
    
    public class ForLoopRefactor {
    
        public static void main(String[] args) {
            List<Person> people = Builder.personBuilder();
    
            //to get the count of people above 20years and find the average
            //normal way
            int count = 0;
            double sum = 0;
            for (Person person : people) {
                if (person.getAge() > 30) {
                    count++;
                    sum += person.getAge();
                }
            }
    
            double average = 0d;
            if (count > 0) {
                average = sum / count;
            }
    
            System.out.println("Count : " + count + " average : " + average);
    
            //refactor
            long count1 = people.stream().mapToInt(Person::getAge).filter(age -> age > 30).count();
            System.out.println(count1);
    
            double averageRefactored = people.stream().mapToInt(Person::getAge).filter(age -> age > 30).average().orElseThrow();
            System.out.println(averageRefactored);
        }
    }


## Reduction

    package com.arun;
    
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


## use of Function in a proper way

    package com.arun.reduction;
    
    import java.util.function.Function;
    
    /**
     * @author arun on 10/15/20
     * <p>
     * requirement to calculate the average Density from a String
     */
    
    public class ComputeCityStats {
    
        public static void main(String[] args) {
    
            String newYork = "1;New York;New York;8 336 817;780";
    
            //requirement to calculate the average density for a city
            //pass a string and get back the density
    
            Function<String, Double> lineDensity = (line) -> {
                String[] split = line.split(";");
    
                //get the density
                String estimateDensity = split[3];
                estimateDensity = estimateDensity.replace(" ", "");
                int density = Integer.parseInt(estimateDensity);
    
                //get the land area
                String landArea = split[4];
                landArea = landArea.replace(" ", "").replace(",", ".");
                double landAreaD = Double.parseDouble(landArea);
    
                return density / landAreaD;
            };
    
            Double averageDensityOfNewYork = lineDensity.apply(newYork);
            System.out.println("Average Density of New York " + averageDensityOfNewYork);
        }
    
    }
