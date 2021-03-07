package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.BookTag;
/**
 * Interface for our Data Access Object to handle database queries related to Book_Tags.
 */
public interface BookTagDAO {
	public List<BookTag> getAllBookTags();
	public List<BookTag> getAllBook();
	public List<BookTag> getTagsByBook(String isbn_13);
	public List<BookTag> getBooksByTag(String tag_names);
	
	public BookTag getBookTagByISBN(String isbn_13);
	
	public boolean addBookTag(BookTag Booktag);	
	public boolean updateBookTag(BookTag Booktag);
	public boolean deleteBookTag(BookTag Booktag);
}
