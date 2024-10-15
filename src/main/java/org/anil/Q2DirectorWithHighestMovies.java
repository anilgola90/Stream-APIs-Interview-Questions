package org.anil;

import com.opencsv.exceptions.CsvValidationException;
import org.anil.model.Movie;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Q2DirectorWithHighestMovies {
    public static void main(String[] args) throws IOException, CsvValidationException {
        List<Movie> movies = Service.prepareMovieData();

        //Question -> print the name of the director who have directed the most movies

        Map.Entry<Long, List<String>> answer = movies.stream()
                .flatMap(movie -> movie.directors().stream())
                .filter(directorName -> !directorName.equalsIgnoreCase("")) // Stream of Director Names
                .collect(Collectors.groupingBy(directorName -> directorName, Collectors.counting()))
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(entry -> entry.getValue(),
                        Collectors.mapping(entry -> entry.getKey(), Collectors.toList())))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> -entry.getKey()))
                .toList().get(0);


        System.out.println(answer.getValue() + " have/has directed " + answer.getKey() + " movies");


    }
}
