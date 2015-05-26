package com.archivos;

import java.io.InputStream;
import java.net.URL;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;


public class Carga {

	/**
	 * @param args
	 */
	
	static {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
		}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String uri = "hdfs://localhost/user/training/shakespeare/Macbeth.txt";
		InputStream in = null;
		try {
			in = new URL(uri).openStream();
			IOUtils.copyBytes(in, System.out, 4096, false);
		} finally {
			IOUtils.closeStream(in);
		}

	}

}
