package examples.pubhub.dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

public class BookTagDAOImpl implements BookTagDAO {
	
	Connection connection = null; // connection to the database
	PreparedStatement stmt = null; //prepared statements to help protect against SQL injection
	//End of Connection to Database statement

	
	//-----------------------------------------
	@Override
	public List<BookTag> getAllBookTags() {
List<BookTag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();	
			
			while (rs.next()) {
				BookTag tag = new BookTag();
				
				tag.setIsbn_13(rs.getString("isbn_13"));
				tag.setTag_names(rs.getString( "tag_names"));
				tags.add(tag);
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// We need to make sure our statements and connections are closed, 
						// or else we could wind up with a memory leak
			closeResources();
		}
		//return the list of Book objects populated by the DB.
		return tags;
	}

	@Override
	public List<BookTag> getAllBook() {
List<BookTag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags";
			stmt = connection.prepareStatement(sql);		
			ResultSet rs = stmt.executeQuery();	
			
			while (rs.next()) {
				BookTag tag = new BookTag();
				
				tag.setIsbn_13(rs.getString("isbn_13"));
				tag.setTag_names(rs.getString( "tag_names"));
				tags.add(tag);
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// We need to make sure our statements and connections are closed, 
						// or else we could wind up with a memory leak
			closeResources();
		}
		//return the list of Book objects populated by the DB.
		return tags;		
	}

	@Override
	public List<BookTag> getTagsByBook(String isbn_13) {
List<BookTag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags";
			stmt = connection.prepareStatement(sql);	
			stmt.setString(1, isbn_13);
			ResultSet rs = stmt.executeQuery();	
			
			while (rs.next()) {
				BookTag tag = new BookTag();
				
				tag.setIsbn_13(rs.getString("isbn_13"));
				tag.setTag_names(rs.getString( "tag_names"));
				tags.add(tag);
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// We need to make sure our statements and connections are closed, 
						// or else we could wind up with a memory leak
			closeResources();
		}
		//return the list of Book objects populated by the DB.
		return tags;		
	}

	@Override
	public List<BookTag> getBooksByTag(String tag_names) {
	List<BookTag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1,tag_names);
			
			ResultSet rs = stmt.executeQuery();	
			
			while (rs.next()) {
				BookTag tag = new BookTag();
				
				tag.setIsbn_13(rs.getString("isbn_13"));
				tag.setTag_names(rs.getString("tag_names"));				
				tags.add(tag);
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// We need to make sure our statements and connections are closed, 
						// or else we could wind up with a memory leak
			closeResources();
		}
		//return the list of Book objects populated by the DB.
		return tags;		
	}

	@Override
	public BookTag getBookTagByISBN(String isbn_13) {
		BookTag bookTag = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn_13);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				bookTag = new BookTag();
				bookTag.setIsbn_13(rs.getString("isbn_13"));
				bookTag.setTag_names(rs.getString("tag_names"));
							
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return bookTag;
	}

	@Override
	public boolean addBookTag(BookTag Booktag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (?, ?)"; // Were using two ?'s here...
			stmt = connection.prepareStatement(sql);
			
			// But that's okay, we can set them all before we execute
			stmt.setString(1, Booktag.getIsbn_13());
			stmt.setString(2,Booktag.getTag_names());			 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public boolean updateBookTag(BookTag Booktag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE book_tags SET isbn_13=?, tag_names=? WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, Booktag.getIsbn_13());
			stmt.setString(2, Booktag.getTag_names());
			
			System.out.println(stmt);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
		
	}
/*-------------------------------------------------------------------------------------------------*/
	@Override
	public boolean deleteBookTag(BookTag Booktag) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE isbn_13=? AND tag_names=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, Booktag.getIsbn_13());
			stmt.setString(2, Booktag.getTag_names());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}


		// Closing all resources is important, to prevent memory leaks. 
			// Ideally, you really want to close them in the reverse-order you open them
		private void closeResources() {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.out.println("Could not close statement!");
				e.printStackTrace();
			}
			
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close connection!");
				e.printStackTrace();
			}
		}
		//----End of Implementation--------

}
