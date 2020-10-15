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
