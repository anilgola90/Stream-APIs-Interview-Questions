package org.anil;

import com.opencsv.exceptions.CsvValidationException;
import org.anil.model.YoutubeStats;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class Q3 {
    public static void main(String[] args) throws CsvValidationException, IOException {
        List<YoutubeStats> youtubeStats =
                Service.prepareYoutubeStats();

        // 1. which video has got most likes -- easy
        // 2. which channel has got most views combined -- easy
        // 3. which channel has got most view per video -- easy
        // 4. which channel has got most views,likes,comments together -- easy


       //
        YoutubeStats ans = youtubeStats.stream()
                .max(Comparator.comparing(stat -> stat.likes()))
                .get();

        // System.out.println(ans);


        // Question 2
        Map.Entry<String, Long> answer2 = youtubeStats.stream()
                .collect(Collectors.groupingBy(YoutubeStats::channel,
                        Collectors.summingLong(stat -> stat.views())

                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();

       // System.out.println(answer2);



        var answer3  = youtubeStats.stream()
                .collect(Collectors.groupingBy(YoutubeStats::channel,
                        Collectors.averagingLong(YoutubeStats::views)
                        ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();
       // System.out.println(answer3);



     var answer4 =    youtubeStats.stream()
                .collect(Collectors.groupingBy(YoutubeStats::channel,
                        Collectors.summingLong(stat ->
                                stat.likes() + stat.commentCount() + stat.views() -stat.dislikes())
                        ))
             .entrySet()
             .stream()
             .max(Map.Entry.comparingByValue())
             .get();

        System.out.println(answer4);



    }
}
