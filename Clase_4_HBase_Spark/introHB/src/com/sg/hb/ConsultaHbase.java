package com.sg.hb;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class ConsultaHbase {

	public static void main(String[] args) throws IOException, Exception
	   {
	   
	      Configuration config = HBaseConfiguration.create();
		  HTable table = new HTable(config, "empleados");

	      Get g = new Get(Bytes.toBytes("fila1"));
		  Result result = table.get(g);

	      byte [] value = result.getValue(Bytes.toBytes("datos"),Bytes.toBytes("nombre"));
		  byte [] value1 = result.getValue(Bytes.toBytes("datos"),Bytes.toBytes("salario"));

	      String name = Bytes.toString(value);
	      String city = Bytes.toString(value1);
	      
	      System.out.println("nombre: " + name + " salario: " + city);
	   }

}
