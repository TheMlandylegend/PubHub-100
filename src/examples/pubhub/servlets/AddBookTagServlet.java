package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class AddBookTagServlet
 */
@WebServlet("/AddBookTag")
public class AddBookTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("bookTagDetails.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 boolean  isSuccess = false;
	String isbn_13 = request.getParameter("isbn_13");
	
	BookTagDAO dao = DAOUtilities.getBookTagDAO();
	
		 BookTag bookTag = new BookTag();
		 bookTag.setIsbn_13(request.getParameter("isbn_13"));
		 bookTag.setTag_names(request.getParameter("tag_names"));
	
		isSuccess = dao.addBookTag(bookTag);

	 if(isSuccess){
			request.getSession().setAttribute("message", "Book tag successfully added");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect(request.getContextPath() + "/ViewBookTagDetails?isbn_13=" + isbn_13);
	 }else {
			request.getSession().setAttribute("message", "There was a problem adding this book tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookTagDetails.jsp").forward(request, response);
	}	 
	 }	

}
