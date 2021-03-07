package examples.pubhub.model;

public class BookTag {
private String isbn_13;
private String tag_names;

// Default Constructor
public BookTag() {
	this.isbn_13 = null;
	this.tag_names = null;
	
}
public String getIsbn_13() {
	return isbn_13;
}
public void setIsbn_13(String isbn_13) {
	this.isbn_13 = isbn_13;
}
public String getTag_names() {
	return tag_names;
}
public void setTag_names(String tag_names) {
	this.tag_names = tag_names;
}

}
