package org.anil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Q1HighestPopulation {
    public static class Country{
        public String countryName;
        public List<State> states;
    }


    public static class State{
        public String stateName;
        public int population;
    }
    public static void main(String[] args) {
        Country country1 = new Country();
        country1.countryName = "India";
        State state1 = new State();
        state1.stateName = "Haryana";
        state1.population = 1500;

        State state2 = new State();
        state2.stateName = "Rajashtan";
        state2.population = 900;

        State state3 = new State();
        state3.stateName = "Gujarat";
        state3.population = 900;
        country1.states = List.of(state1,state2,state3);

        Country country2 = new Country();
        country2.countryName = "America";
        State state4 = new State();
        state4.stateName = "Texas";
        state4.population = 1300;

        State state5 = new State();
        state5.stateName = "California";
        state5.population = 1400;

        State state6 = new State();
        state6.stateName = "Florida";
        state6.population = 1500;
        country2.states = List.of(state4,state5,state6);

        var countryList =  List.of(country1,country2);

        // name of the state having the highest population
        // print the name of all the states in case there is a tie

//      State state =  countryList.stream()
//                .flatMap(country -> country.states.stream())
//                .max((s1, s2) -> s1.population - s2.population)
//                .get();
//        System.out.println(state.stateName);

        List<Map.Entry<Integer, List<State>>> answer = countryList.stream()
                .flatMap(country -> country.states.stream())
                .collect(Collectors.groupingBy(s -> s.population, Collectors.toList()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> -entry.getKey()))
                .collect(Collectors.toList());

        Map.Entry<Integer, List<State>> integerListEntry = answer.get(1);
      integerListEntry.getValue().forEach(
                state -> System.out.println(state.stateName)

        );


    }

}
