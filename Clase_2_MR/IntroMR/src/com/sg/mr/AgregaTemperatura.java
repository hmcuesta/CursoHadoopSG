package com.sg.mr;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class AgregaTemperatura {
	public static void main(String[] args) throws Exception {
	
		Job job = new Job();
		job.setJarByClass(AgregaTemperatura.class);
		job.setJobName("Temperatura Maxima");
		FileInputFormat.addInputPath(job, new Path("input/datos.txt"));
		FileOutputFormat.setOutputPath(job, new Path("output"));
		job.setMapperClass(TemperaturaMapper.class);
		job.setReducerClass(TemperaturaReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
