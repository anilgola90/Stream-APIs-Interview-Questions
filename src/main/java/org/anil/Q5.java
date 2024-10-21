package org.anil;

import com.opencsv.exceptions.CsvValidationException;
import org.anil.model.Bollywood;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Q5 {
    public static void main(String[] args) throws CsvValidationException, IOException {
        List<Bollywood> bollywoods = Service.prepareBollywoodStats();
        // Hard -> find the actor who has done the maximum movies in a year
        // If there is tie between years - print all those year
        // Also, if there is tie between actors within a year, print all those actors as well


        // Map of key as year , Value will be List of Bollywood
         // Map of key as year , Value as Map | key as Actor , value as number of movies the have done
         // for examp  {1984 = [Amitabh - 4, Rekha - 2, Shatrughan - 4]}
         //             {1994 = [Shahrukh - 4, Raveena - 3, Akshay - 4]}   
        

        // Map as key will be year and value will be 
         //    {1984 = [Amitabh - 4, Shatrughan - 4]}           
         //  {1994 = [Shahrukh - 4 Akshay - 4]}   


      // 
         // {4 - [{1984-Amitabh, Shatrughan},
        //      {1197-Shahrukh, Amitabh}]




        Map<Integer, Map.Entry<Long, List<Map.Entry<String, Long>>>> collect = bollywoods.stream()
                .collect(Collectors.groupingBy(bolyy -> bolyy.releaseYear(),

                        Collectors.collectingAndThen(
                                Collectors.flatMapping(bolly -> bolly.actors().stream(),

                                        Collectors.groupingBy(actor -> actor,
                                                Collectors.counting()
                                        )

                                ),
                                map -> map.entrySet().stream()
                                        .collect(Collectors.groupingBy(entry -> entry.getValue()))
                                        .entrySet()
                                        .stream()
                                        .max(Map.Entry.comparingByKey())
                                        .get()
                        )

                ));
       // System.out.println(collect);
        //1989=6=[Madhuri Dixit=6],
        record Bo (int year, List<String> actors){}
        Map.Entry<Long, List<Bo>> ans = collect
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        en -> en.getValue().getKey(),
                        Collectors.mapping(en -> new Bo(en.getKey(),
                                en.getValue().getValue().stream()
                                        .map(e -> e.getKey())
                                        .toList()
                        ), Collectors.toList())
                )).entrySet().
                stream().max(Map.Entry.comparingByKey()).get();
        System.out.println(ans);

    }
}
