package org.anil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


/**
 * This set of exercises covers the collect() terminal operation with the complex collectors.
 * <p>
 * Some of these exercises use a BufferedReader variable
 * named "reader" that the test has set up for you.
 */
public class CollectorPuzzlesAnswer {




    /**
     * Categorize the words from the text file into a map, where the map's key
     * is the length of each word, and the value corresponding to a key is a
     * list of words of that length. Don't bother with uniqueness or lower-
     * casing the words.
     * <p/>
     * As before, use the BufferedReader variable named
     * "reader" that has been set up for you to read from the text file, and
     * use SPLIT_PATTERN for splitting the line into words.
     */
    @Test
    public void o_harderCollector01() {
        Map<Integer, List<String>> result = reader.lines()
                .flatMap(s -> SPLIT_PATTERN.splitAsStream(s))
                .collect(Collectors.groupingBy(String::length));

        assertThat(result).hasSize(11);
        assertThat(result.get(8)).containsExactly("increase", "beauty's", "ornament");
        assertThat(result.get(9)).containsExactly("creatures", "abundance");
        assertThat(result.get(10)).containsExactly("contracted", "niggarding");
        assertThat(result.get(11)).containsExactly("substantial");
    }


    /**
     * Categorize the words from the text file into a map, where the map's key
     * is the length of each word, and the value corresponding to a key is a
     * count of words of that length. Don't bother with uniqueness or lower-
     * casing the words.
     * <p/>
     * This is the same as the previous exercise except
     * the map values are the count of words instead of a list of words.
     */
    @Test
    public void o_harderCollector02() {

        Map<Integer, Long> result = reader.lines()
                .flatMap(s -> SPLIT_PATTERN.splitAsStream(s))
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));

        assertThat(result).hasSize(11);
        assertThat(result.get(1)).isEqualTo(1L);
        assertThat(result.get(2)).isEqualTo(11L);
        assertThat(result.get(3)).isEqualTo(28L);
        assertThat(result.get(4)).isEqualTo(21L);
        assertThat(result.get(5)).isEqualTo(16L);
        assertThat(result.get(6)).isEqualTo(12L);
        assertThat(result.get(7)).isEqualTo(10L);
        assertThat(result.get(8)).isEqualTo(3L);
        assertThat(result.get(9)).isEqualTo(2L);
        assertThat(result.get(10)).isEqualTo(2L);
        assertThat(result.get(11)).isEqualTo(1L);
    }


    /**
     * Gather the words from the text file into a map, accumulating a count of
     * the number of occurrences of each word. Don't worry about upper case and
     * lower case.
     * <p/>
     * Extra challenge: implement two solutions, one that uses
     * groupingBy() and the other that uses toMap().
     */
    @Test
    public void o_harderCollector03() {

//        Map<String, Long> result = reader.lines()
//                .flatMap(s -> SPLIT_PATTERN.splitAsStream(s))
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> result = reader.lines()
                .flatMap(s -> SPLIT_PATTERN.splitAsStream(s))
                .collect(Collectors.toMap(s -> s, s -> 1L, (value1, value2) -> value1 + 1L));

        assertThat(result).hasSize(87);
        assertThat(result.get("tender")).isEqualTo(2L);
        assertThat(result.get("the")).isEqualTo(6L);
        assertThat(result.get("churl")).isEqualTo(1L);
        assertThat(result.get("thine")).isEqualTo(2L);
        assertThat(result.get("world")).isEqualTo(1L);
        assertThat(result.get("thy")).isEqualTo(4L);
        assertThat(result.get("self")).isEqualTo(3L);
    }

    /**
     * Gather all the letters used to write the Sonnet in lower case, and find one of the least used.
     * <p/>
     * Remember to use the BufferedReader named "reader" that has already been
     * opened for you.
     * <p/>
     * You can use a variant of the function created for K_SimpleStreams.simpleStream10().
     */

 
    @Test
    public void o_harderCollector04() {

        Map.Entry<String, Long> stringLongEntry = reader.lines()
                .flatMap(SPLIT_PATTERN::splitAsStream)
                .flatMap(word -> word.chars().boxed())
                .filter(s -> s >= 97)
                .map(s -> Character.toString(s))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get();
         String leastUsedLetter = stringLongEntry.getKey();
         assertThat(leastUsedLetter).isIn("v", "k");
    }

    /**
     * Gather all the letters used to write the Sonnet in lower case, and find all the least used in a list.
     * <p/>
     * Remember to use the BufferedReader named "reader" that has already been
     * opened for you.
     * <p/>
     * You can use a variant of the function created for K_SimpleStreams.simpleStream10().
     */
    @Test
    public void o_harderCollector05() {
        Map.Entry<Long, List<String>> collect = reader.lines()
                .flatMap(SPLIT_PATTERN::splitAsStream)
                .flatMap(word -> word.chars().boxed())
                .map(Character::toString)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(
                        letter -> letter,
                        Collectors.counting()
                )).entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())

                ))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByKey())
                .get();
        List<String> leastUsedLetters = collect.getValue();
        System.out.println(leastUsedLetters);
        assertThat(leastUsedLetters).hasSize(2);
        assertThat(leastUsedLetters).contains("v", "k");
    }


    /**
     * From the words in the text file, create nested maps, where the outer map is a
     * map from the first letter of the word to an inner map. (Use a string of length
     * one as the key.) The inner map, in turn, is a mapping from the length of the
     * word to a list of words with that length. Don't bother with any lowercasing
     * or uniquifying of the words.
     * <p>
     * For example, given the words "foo bar baz bazz foo first" the string
     * representation of the result would be:
     * {b={3=[bar, baz], 4=[bazz]}, f={3=[foo, foo], 4 = [first}}
     */
    @Test
    public void o_harderCollector06() {

       var result =   reader.lines()
                 .flatMap(SPLIT_PATTERN::splitAsStream)
                 .collect(
                         Collectors.groupingBy(
                                 word -> word.substring(0,1),
                                 Collectors.groupingBy(
                                         String::length,
                                         Collectors.toList()
                                 )
                         )
                 );


        assertThat(result).hasSize(25);
        assertThat(result.get("a").get(9).toString()).isEqualTo("[abundance]");
        assertThat(result.get("b").get(2).toString()).isEqualTo("[by, be, by]");
        assertThat(result.get("f").get(5).toString()).isEqualTo("[flame, fresh]");
        assertThat(result.get("g").get(5).toString()).isEqualTo("[gaudy, grave]");
        assertThat(result.get("s").get(6).toString()).isEqualTo("[should, spring]");
        assertThat(result.get("s").get(11).toString()).isEqualTo("[substantial]");
        assertThat(result.get("t").get(3).toString()).isEqualTo("[the, thy, thy, thy, too, the, the, thy, the, the, the]");
        assertThat(result.get("w").get(5).toString()).isEqualTo("[where, waste, world]");
    }


    /**
     * Given a stream of integers, compute separate sums of the even and odd values
     * in this stream. Since the input is a stream, this necessitates making a single
     * pass over the input.
     */
    @Test
    public void o_harderCollector07() {
        IntStream input = new Random(987523).ints(20, 0, 100);

         var answer =  input.boxed()
                  .collect(
                  Collectors.partitioningBy(s -> s%2 == 0,Collectors.summingInt(s -> s)));

       int sumEvens =  answer.get(true);
       int sumOdds = answer.get(false);
        assertEquals(516, sumEvens);
        assertEquals(614, sumOdds);
    }


// ========================================================
//      END OF EXERCISES
//      TEST INFRASTRUCTURE IS BELOW
// ========================================================


    // Pattern for splitting a string into words
    static final Pattern SPLIT_PATTERN = Pattern.compile("[- .:,]+");

    private BufferedReader reader;

    @Before
    public void z_setUpBufferedReader() throws IOException {
        reader = Files.newBufferedReader(
                Paths.get("src/main/resources/Sonnet.txt"), StandardCharsets.UTF_8);
    }

    @After
    public void z_closeBufferedReader() throws IOException {
        reader.close();



    }
}