package com.arun.reduction;

import java.util.function.ToDoubleFunction;

/**
 * @author arun on 10/17/20
 */

public class UseOfDoubleFunction {

    public static void main(String[] args) {
        String newYork = "1;New York;New York;8 336 817;780";

        ToDoubleFunction<String> average = line -> {
            String[] split = line.split(";");

            //get the population
            String populationAsString = split[3];
            populationAsString = populationAsString.replace(" ", "");
            int population = Integer.parseInt(populationAsString);

            //get the landArea
            String landAreaAsString = split[4];
            landAreaAsString = landAreaAsString.replace(" ", "").replace(",", ".");
            double landArea = Double.parseDouble(landAreaAsString);
            return population / landArea;
        };

        double averageAs = average.applyAsDouble(newYork);
        System.out.println(averageAs);
    }
}
