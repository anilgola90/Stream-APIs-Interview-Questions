package org.anil;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.anil.model.Movie;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
}


//
//        return lines.skip(1)
//                .map(str -> str.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1))
//                .map(arr -> new Movie(
//                        arr[0],
//                        arr[1],
//                        arr[2],
//                        Pattern.compile(",").splitAsStream(arr[3]).map(s->s.trim()).toList(),
//                        Pattern.compile(",").splitAsStream(arr[4]).map(s->s.trim()).toList(),
//                        arr[5] ,
//                        Integer.parseInt(arr[7].toString())))
//                .toList();


