package com.sg.hb;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
public class ActualizaHbase {


	   public static void main(String[] args) throws IOException
	   {

	      Configuration config = HBaseConfiguration.create();

	      HTable hTable = new HTable(config, "empleados");

	      Put p = new Put(Bytes.toBytes("fila1"));

	      p.add(Bytes.toBytes("datos"),
	      Bytes.toBytes("ciudad"),Bytes.toBytes("DF"));

	      hTable.put(p);
	      System.out.println("Fin");

	      hTable.close();
	   }

}
