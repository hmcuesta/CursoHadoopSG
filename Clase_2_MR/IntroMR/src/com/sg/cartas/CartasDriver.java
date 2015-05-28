package com.sg.cartas;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class CartasDriver  extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		
		String input = "input/cardinput.txt";
		String output = "salida";
		

		Job job = new Job(getConf());
		job.setJarByClass(CartasDriver.class);
		job.setJobName(this.getClass().getName());

		FileInputFormat.setInputPaths(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));

		job.setMapperClass(CartasMapper.class);
		job.setReducerClass(CartasReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		CartasDriver driver = new CartasDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}

	

}
