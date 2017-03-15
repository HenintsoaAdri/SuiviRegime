package s6.suiviRegime.daoGenere;
import java.sql.Connection;
import java.sql.DriverManager;

public class UtilDB {
	public static Connection conn;

	public static Connection getConnexion()throws Exception{
		try{
			Class.forName("org.postgresql.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/suiviRegime","postgres","adri");
			conn.setAutoCommit(false);
			if(conn == null){
				throw new Exception("Aucune connexion \u00e9tablie avec la base");
			}
		}
		catch(Exception e){
			throw e;
		}
		return conn;
	}
}
