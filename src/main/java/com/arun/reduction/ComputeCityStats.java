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
