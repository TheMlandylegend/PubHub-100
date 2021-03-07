package examples.pubhub.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MCBootstrapper {
public static void main(String[] args) {
		
		Connection conn = null;	
		PreparedStatement stmt = null;	
		
		
		String sql= "INSERT INTO book_tags VALUES ('1230', 'Action')";
	

		try {
			conn = DAOUtilities.getConnection();
			stmt = conn.prepareStatement(sql);
		
			//stmt.executeQuery(sql);
			System.out.println("Executing SQL Statement: " + sql);
			System.out.println("Connection is valid: " + conn.isValid(5));
			System.out.println("Rows updated: " + stmt.executeUpdate());
			System.out.println("Success!");
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
		try {
			if(stmt!=null) {
				conn.close();
			}
		}catch(SQLException se){
			
		}try {
			if(conn!=null)
				conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}//end finally try
		
		}//end try

	System.out.println("GoodBye!");
}	
}
