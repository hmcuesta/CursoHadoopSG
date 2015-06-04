package com.sg.hb;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.util.Bytes;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

public class HBaseClient {

	 public static void main(String args[]) throws IOException
	   {

	      
	      Configuration config = HBaseConfiguration.create();
	      HTable table = new HTable(config, "empleados");
	      Scan scan = new Scan();
	     
	      scan.addColumn(Bytes.toBytes("datos"), Bytes.toBytes("salario"));
	      scan.addColumn(Bytes.toBytes("datos"), Bytes.toBytes("nombre"));

	      ResultScanner scanner = table.getScanner(scan);

	      for (Result result = scanner.next(); result != null; result = scanner.next())
	    	  	System.out.println(result);
	     
	      scanner.close();
	   }

}