package com.arun.collectdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author arun on 10/17/20
 */
public class UseOfGroupBy {

    public static void main(String[] args) {

        Function<String, City> lineToCity = line -> {
            String[] split = line.split(";");

            String city = split[1];
            String state = split[2];

            String populationAsString = split[3];
            populationAsString = populationAsString.replace(" ", "");
            int population = Integer.parseInt(populationAsString);

            String landAreaAsString = split[4];
            landAreaAsString = landAreaAsString.replace(" ", "").replace(",", ".");
            double landArea = Double.parseDouble(landAreaAsString);

            return new City().setCity(city).setState(state).setPopulation(population).setLandArea(landArea);
        };


        Path path = Paths.get("src/main/resources/cities.csv");


        Set<City> cities = null;

        try (Stream<String> lines = Files.lines(path);) {
            cities = lines.skip(2)
                    .map(lineToCity)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //use of group by

        Map<String, List<City>> allStates = cities != null ? cities.stream().collect(Collectors.groupingBy(City::getState)) : null;

        //get all the cities which are in North Carolina
        List<City> northCarolina = allStates.get("North Carolina");
        System.out.println(northCarolina);


        //if I want to get the number of cities per state
        Map<String, Long> totalCountOfCitiesPerState = cities != null ? cities.stream().collect(Collectors.groupingBy(City::getState, Collectors.counting())) : null;
        Long countOfCitiesForNorthCarolina = totalCountOfCitiesPerState.get("North Carolina");
        System.out.println(countOfCitiesForNorthCarolina);

        //get the state with most number of cities
        Map.Entry<String, Long> stateWithMostNumberOfCities = totalCountOfCitiesPerState.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow();

        System.out.println(stateWithMostNumberOfCities);

        //calculate the sum of population of the cities for a state

        int totalPopulationOfUtah = allStates.get("Utah").stream()
                .mapToInt(City::getPopulation)
                .sum();
        System.out.println("totalPopulationOfUtah : " + totalPopulationOfUtah);

        //other way to do it is
        Integer populationOfUtah = allStates.get("Utah").stream()
                .collect(Collectors.summingInt(City::getPopulation));
        System.out.println("populationOfUtah : " + populationOfUtah);

        //to check the population of all the states
        Map<String, Integer> statesWithPopulation = cities.stream().collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
        System.out.println("statesWithPopulation : " + statesWithPopulation);

        //state with Max Population
        Map.Entry<String, Integer> stateWithMaximumPopulation = statesWithPopulation.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow();

        System.out.println(stateWithMaximumPopulation);
    }
}
