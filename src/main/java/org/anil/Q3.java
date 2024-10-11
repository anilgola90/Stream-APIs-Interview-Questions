package org.anil;

import com.opencsv.exceptions.CsvValidationException;
import org.anil.model.Movie;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Q3 {


    public static void main(String[] args) throws CsvValidationException, IOException {
        // which actor has participated in maximum movies after year 2020
        List<Movie> movies = Service.prepareMovieData();

        Map<String, Long> collect =
                movies.stream().filter(movie -> movie.year() >= 2020)
                .flatMap(movie -> movie.actors().stream())
                        .filter(actor -> !actor.equalsIgnoreCase(""))// Stream<String> // Stream of Actors
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      //  System.out.println(collect);

        Map<Long, List<String>> collect1 = collect.entrySet().stream()
                .collect(Collectors.groupingBy(entry -> entry.getValue(), Collectors.mapping(entry -> entry.getKey(), Collectors.toList())));

        List<Map.Entry<Long, List<String>>> list = collect1.entrySet()
                .stream().sorted(Comparator.comparing(entry -> -entry.getKey()))
                .toList();

        System.out.println(list.get(0));

        //        Map.Entry<String, Long> stringLongEntry = collect.entrySet()
//                .stream()
//                .max(Comparator.comparing(entry -> entry.getValue()))
//                .get();


    //    System.out.println(stringLongEntry);


    }



}
