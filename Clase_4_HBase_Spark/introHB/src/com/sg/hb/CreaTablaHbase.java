package com.sg.hb;

import java.io.IOException;


import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.conf.Configuration;

public class CreaTablaHbase {

	      
	 public static void main(String[] args) throws IOException {

		   Configuration con = HBaseConfiguration.create();
		   HBaseAdmin admin = new HBaseAdmin(con);

		   HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("empleados"));

		   tableDescriptor.addFamily(new HColumnDescriptor("datos"));
		 

		   admin.createTable(tableDescriptor);
		   System.out.println(" Tabla Creada ");
	}
		

}
