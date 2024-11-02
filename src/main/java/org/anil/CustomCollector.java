package org.anil;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.regex.Pattern;
import java.util.stream.Collector;


public class CustomCollector {
public static void main(String[] args) {
 String para = "Spiderman is best among all Superheroes";

 // convert this to this [Spiderman,is,best,among,all,Superheroes]
 String answer = Pattern.compile(" ")
         .splitAsStream(para)
         .collect(
                 Collector.of(
                         StringBuilder::new, // Supplier
                         (sb, s) -> sb.append(s).append(","), // Accumlator
                         StringBuilder::append, // Combiner
                         sb -> sb.deleteCharAt(sb.length()-1).insert(0,"[").insert(sb.length(),"]").toString()
                          // finisher
                 )
         );
 System.out.println(answer);
 // Supplier -> how we are going the collect the elements
 // Accumulator -> how we will add our stream elements in this collector
 // Combiner -> it will have two suppliers -> we have to combine them
 // Finisher -> this is needs to be provided if we have to do some transformation

}
}