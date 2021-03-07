package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class PublishBookTagServlet
 */
@MultipartConfig
public class PublishBookTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("publishBookTag.jsp").forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn_13 = request.getParameter("isbn_13");
		String tag_names = request.getParameter("tag_names");
		
		BookTagDAO database = DAOUtilities.getBookTagDAO();
		BookTag tempBook = database.getBookTagByISBN(isbn_13);
		if(tempBook != null) {
			//Assert: tag with isbn aready exists
			
			request.getSession().setAttribute("message", "ISBN of " + isbn_13 + " is already in use");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("publishBookTag.jsp").forward(request, response);
		}else {
			BookTag tag = new BookTag();
			tag.setIsbn_13(request.getParameter(isbn_13));
			tag.setTag_names(request.getParameter(tag_names));
		}
		boolean isSuccess = database.addBookTag(tempBook);
		
		if(isSuccess) {
			request.getSession().setAttribute("message", "Tag succesfully published");
			request.getSession().setAttribute("messageClass", "alert-success");
			((HttpServletResponse) request).sendRedirect(request.getContextPath() + "/BookTagPublishing");
		}else {
			request.getSession().setAttribute("message", "There was a problem publishing the book");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("publishBookTag.jsp").forward(request, response);
			
		}
	}

}
