package examples.pubhub.dao;

import examples.pubhub.model.BookTag;

public class BookTagDAOTestClass {

	public static void main(String[] args) {
		BookTagDAO dao = new BookTagDAOImpl();
		BookTag tag = new BookTag();
		boolean isSuccess = dao.deleteBookTag(tag);
		
		if(isSuccess = true) {
			System.out.println("Added Tag");
		}else {
			System.out.println("Error Added Tag");
		}

	}

}
