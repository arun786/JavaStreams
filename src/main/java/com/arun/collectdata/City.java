package com.arun.collectdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author arun on 10/17/20
 */

@Getter
@Setter
@ToString
public class City {
    private String city;
    private String state;
    private int population;
    private double landArea;
}
