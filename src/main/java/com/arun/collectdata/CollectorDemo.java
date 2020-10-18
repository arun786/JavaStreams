package com.arun.collectdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

/**
 * @author arun on 10/17/20
 */

public class CollectorDemo {

    public static void main(String[] args) {
        Path path = Path.of("src/main/resources/cities.csv");

        Function<String, String> lineToName = line -> {
            String[] split = line.split(";");
            return split[1];
        };
        Set<String> cities = null;
        try (Stream<String> lines = Files.lines(path, ISO_8859_1)) {
            cities = lines.skip(2).map(lineToName).collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String citiesStartingWithA = cities != null ? cities.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.joining(", ", "[", "]")) : null;
        System.out.println(citiesStartingWithA);

        //to convert a stream to an array

        String[] citiesArray = cities != null ? cities.toArray(String[]::new) : new String[0];
        System.out.println(citiesArray.length);
        System.out.println(cities.size());

    }
}
