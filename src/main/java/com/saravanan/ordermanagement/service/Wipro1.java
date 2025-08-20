package com.saravanan.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sarav on 30 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.service
 * @class Wipro1
 */

public class Wipro1 {

    // weekDays = {sunday,wednesday,thursday}


    public static void main(String[] args) {
        List<String> weekDays = List.of( "sunday", "wednesday", "thursday");

        Map<Character, Long> counts =
        weekDays.stream().flatMap(word ->
        {
            char[]  chars = word.toCharArray();

            List<Character> charList = new ArrayList<>();
            for (char c : chars) {
                charList.add(c);
            }
            return charList.stream();

        }).filter(c -> "aeiou".indexOf(c) != -1)
                .collect(Collectors.groupingBy(
                        Character::toLowerCase,Collectors.counting()
                ));

        System.out.println(counts);


    }
}
