
import java.sql.*;

public class JDBCTest {

    public static void main(String args[]) {

        Connection con = null; // The connection to the database.
        Statement stmt = null;
        CallableStatement cstmt = null;
        // The following code can throw errors, so they must be caught.
        try{

            // First, tell Java what driver to use and where to find it.
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            // Next, create a connection to your data source.
            // Specify that you are using the ODBC-JDBC Bridge.
            // And specify the data source from ODBC.
            con = DriverManager.getConnection("jdbc:odbc:dim12", "cm_typical", "cm_typical");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:dim12", "cm_typical", "cm_typical");
            
            
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:databaseName";
            String username = "userName";
            String password = "password";
            
            // Create an SQL statement.
            stmt = con.createStatement();
            // Execute some SQL to create a table in your database.
            // If the table already exists, an exception is thrown!
            ResultSet rs = stmt.executeQuery("SELECT product_id, user_name, group_id FROM users_profile WHERE  user_uid = 4214546");
            while(rs.next()){
            	System.out.println("product id : " + rs.getString("product_id"));
            	System.out.println("user_name : " + rs.getString(2));
            	System.out.println("group_id : " + rs.getString(3));
            }
            
            cstmt = con.prepareCall("{call proc_name(?)}");
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.executeQuery();
            
           cstmt.getString(1);
        }
        // Catch any exceptions that are thrown.
        catch(ClassNotFoundException e){

            System.out.println(e.toString());

        }
        catch(SQLException e){

            System.out.println(e.toString());

        }finally{
        	 
        	try {
        		if (stmt != null) { stmt.close();};
           	 	if (con != null) { con.close();};
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

    }

}