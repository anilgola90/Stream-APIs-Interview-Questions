package org.anil;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.anil.model.Bollywood;
import org.anil.model.Movie;
import org.anil.model.YoutubeStats;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class Service {

    public static List<Movie> prepareMovieData() throws IOException, CsvValidationException {
        //  Path path = Paths.get("src/main/resources/netflix_titles.csv");
        //  Stream<String> lines = Files.lines(path);


        String filePath = "src/main/resources/netflix_titles.csv";  // Path to your CSV file
        List<Movie> list = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] header = csvReader.readNext();
            String[] nextLine;
            // Reading all lines from the CSV
            while ((nextLine = csvReader.readNext()) != null) {

                // nextLine[] is an array of values from the current row
                Movie movie = new Movie(
                        nextLine[0],
                        nextLine[1],
                        nextLine[2],
                        Pattern.compile(",").splitAsStream(nextLine[3]).map(s -> s.trim()).toList(),
                        Pattern.compile(",").splitAsStream(nextLine[4]).map(s -> s.trim()).toList(),
                        nextLine[5],
                        Integer.parseInt(nextLine[7].toString()));
                list.add(movie);
            }
            return list;
        }
    }

    public static void main(String[] args) {
        String d = "2017-11-12T12:20:39.000Z";
        OffsetDateTime odt = OffsetDateTime.parse(d);

        // Convert to LocalDateTime (if you don't need the timezone part)
        LocalDateTime ldt = odt.toLocalDateTime();
        System.out.println(ldt);
    }


    public static List<YoutubeStats> prepareYoutubeStats() throws IOException, CsvValidationException {
        //  Path path = Paths.get("src/main/resources/netflix_titles.csv");
        //  Stream<String> lines = Files.lines(path);


        String filePath = "src/main/resources/youtube_in.csv";  // Path to your CSV file
        List<YoutubeStats> list = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] header = csvReader.readNext();
            String[] nextLine;
            // Reading all lines from the CSV
            while ((nextLine = csvReader.readNext()) != null) {
                // nextLine[] is an array of values from the current row
                YoutubeStats movie = new YoutubeStats(
                        nextLine[0],
                        nextLine[1],
                        nextLine[2],
                        nextLine[3],
                        OffsetDateTime.parse(nextLine[4]).toLocalDateTime(),
                        Long.parseLong(nextLine[5]),
                        Long.parseLong(nextLine[6]),
                        Long.parseLong(nextLine[7]),
                        Long.parseLong(nextLine[8])
                      );
                list.add(movie);
            }
            return list;

        }
    }




    public static List<Bollywood> prepareBollywoodStats() throws IOException, CsvValidationException {
        //  Path path = Paths.get("src/main/resources/netflix_titles.csv");
        //  Stream<String> lines = Files.lines(path);


        String filePath = "src/main/resources/bollywood_movies.csv";  // Path to your CSV file
        List<Bollywood> list = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] header = csvReader.readNext();
            String[] nextLine;
            // Reading all lines from the CSV
            while ((nextLine = csvReader.readNext()) != null) {
                // nextLine[] is an array of values from the current row
                if(StringUtils.isEmpty(nextLine[2])){
                    continue;
                }

                String[] genreArray = nextLine[3].toString().split(",");
                String[] actorArray = nextLine[5].toString().split(",");
                String[] directorArray = nextLine[4].toString().split(",");
                Bollywood movie = new Bollywood(
                        nextLine[0],
                        nextLine[1],
                        Integer.parseInt(nextLine[2]),
                        Arrays.stream(genreArray).map(s->s.trim()).toList(),
                        Arrays.stream(actorArray).map(s->s.trim()).toList(),
                        Arrays.stream(directorArray).map(s->s.trim()).toList()
                );
                list.add(movie);
            }
            return list;
        }
    }


}


