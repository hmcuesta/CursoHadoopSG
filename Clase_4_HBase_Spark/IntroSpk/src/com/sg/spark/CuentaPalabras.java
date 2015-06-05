package com.sg.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;
import java.util.Arrays;

public class CuentaPalabras {

	public static void main(String[] args) throws Exception {
		String entrada = "hdfs://localhost/user/training/palabras.txt";
		String salida = "output";
		// Contexto.
		
		SparkConf conf = new SparkConf();
	    JavaSparkContext sc = new JavaSparkContext("local", "Cuenta Letras", conf);
		
		// cargar datos.
		JavaRDD<String> input = sc.textFile(entrada);
		
		// Split de palabras.
		JavaRDD<String> words = input.flatMap(
				new FlatMapFunction<String, String>() {
					public Iterable<String> call(String x) {
						return Arrays.asList(x.split(" "));
					}});
		// Hash para Cuenta.
		JavaPairRDD<String, Integer> counts = words.mapToPair(
				new PairFunction<String, String, Integer>(){
					public Tuple2<String, Integer> call(String x){
						return new Tuple2(x, 1);
					}}).reduceByKey(new Function2<Integer, Integer, Integer>(){
						public Integer call(Integer x, Integer y){ return x + y;}});
		// guarda resultado
		counts.saveAsTextFile(salida);
	}
}
