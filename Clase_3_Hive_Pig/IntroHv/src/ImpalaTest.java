import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImpalaTest {

	    //private static final String SQL = "SELECT description FROM sample_07 limit 10";
		private static final String SQL = "select * from football";
		private static final String CONNECTION = "jdbc:hive2://localhost:21050"+"/;auth=noSasl";
		private static final String DRIVER = "org.apache.hive.jdbc.HiveDriver";

		public static void main(String[] args) {

			System.out.println("Cloudera Impala ");
			System.out.println("Conexion: " + CONNECTION);
			System.out.println("Query: " + SQL);

			Connection con = null;

			try {

				Class.forName(DRIVER);

				con = DriverManager.getConnection(CONNECTION);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);

				System.out.println("\n== Inicia Resultado ==");

				while (rs.next()) {
					System.out.println(rs.getString(1));
				}

				System.out.println("== Termina Resultado =======================\n\n");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
					} catch (Exception e) {
					
					}
			}
	}

}
