package com.sg.hb;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class InsertaHbase {

	public static void main(String[] args) throws IOException {

	      Configuration config = HBaseConfiguration.create();
	      HTable hTable = new HTable(config, "empleados");

	      Put p = new Put(Bytes.toBytes("fila2")); 

	      p.add(Bytes.toBytes("datos"),
	      Bytes.toBytes("nombre"),Bytes.toBytes("juan"));

	      p.add(Bytes.toBytes("datos"),
	      Bytes.toBytes("ciudad"),Bytes.toBytes("xalapa"));

	      p.add(Bytes.toBytes("datos"),Bytes.toBytes("puesto"),
	      Bytes.toBytes("ceo"));

	      p.add(Bytes.toBytes("datos"),Bytes.toBytes("salario"),
	      Bytes.toBytes("20000"));
	      
	      hTable.put(p);
	      System.out.println("Fin");
	      
	      hTable.close();
	   }

}
