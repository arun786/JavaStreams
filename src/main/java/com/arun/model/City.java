package com.arun.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author arun on 10/12/20
 */

@Getter
@Setter
@ToString
public class City {
    private String name;
    private List<Person> persons;
}
