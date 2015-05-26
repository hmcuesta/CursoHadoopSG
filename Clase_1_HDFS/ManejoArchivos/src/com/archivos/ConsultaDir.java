package com.archivos;

import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class ConsultaDir {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		String uri = "hdfs://localhost/user/training/";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		Path[] paths = new Path[1];
		paths[0] = new Path(uri);
		
		Path ruta = new Path(uri);
	
		FileStatus[] status = fs.listStatus(ruta);
		Path[] listedPaths = FileUtil.stat2Paths(status);
		
		for (Path p : listedPaths) {
			System.out.println(p);
		}

		

	}

}
