package com.sg.spark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.SparkConf;
import scala.Tuple2;

public class SparkTest {
	public static void main(String[] args) {
		
		SparkConf conf = new SparkConf();
	    JavaSparkContext sc = new JavaSparkContext("local", "Cuenta Letras", conf);
	    
		final int threshold = Integer.parseInt("1");
		
	    
	    // palabras
	    JavaRDD<String> tokenized = sc.textFile("hdfs://localhost/user/training/palabras.txt").flatMap(
	      new FlatMapFunction<String, String>() {
	        @Override
	        public Iterable<String> call(String s) {
	          return Arrays.asList(s.split(" "));
	        }
	      }
	    );
	    
	    // count
	    JavaPairRDD<String, Integer> counts = tokenized.mapToPair(
	      new PairFunction<String, String, Integer>() {
	        @Override
	        public Tuple2<String, Integer> call(String s) {
	          return new Tuple2<String, Integer>(s, 1);
	        }
	      }
	    ).reduceByKey(
	      new Function2<Integer, Integer, Integer>() {
	        @Override
	        public Integer call(Integer i1, Integer i2) {
	          return i1 + i2;
	        }
	      }
	    );
	    
	    // filtro abajo del umbral 
	    JavaPairRDD<String, Integer> filtered = counts.filter(
	      new Function<Tuple2<String, Integer>, Boolean>() {
	        @Override
	        public Boolean call(Tuple2<String, Integer> tup) {
	          return tup._2() >= threshold;
	        }
	      }
	    );
	    
	    // count caracteres
	    JavaPairRDD<Character, Integer> charCounts = filtered.flatMap(
	      new FlatMapFunction<Tuple2<String, Integer>, Character>() {
	        @Override
	        public Iterable<Character> call(Tuple2<String, Integer> s) {
	          Collection<Character> chars = new ArrayList<Character>(s._1().length());
	          for (char c : s._1().toCharArray()) {
	            chars.add(c);
	          }
	          return chars;
	        }
	      }
	    ).mapToPair(
	      new PairFunction<Character, Character, Integer>() {
	        @Override
	        public Tuple2<Character, Integer> call(Character c) {
	          return new Tuple2<Character, Integer>(c, 1);
	        }
	      }
	    ).reduceByKey(
	      new Function2<Integer, Integer, Integer>() {
	        @Override
	        public Integer call(Integer i1, Integer i2) {
	          return i1 + i2;
	        }
	      }
	    );
	    
	    System.out.println(charCounts.collect());
	  }
}
