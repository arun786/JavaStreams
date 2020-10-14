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
