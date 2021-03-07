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
 * Servlet implementation class DeleteBookTagServlet
 */
@WebServlet("/DeleteBookTag")
public class DeleteBookTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("bookTagDetails.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//Connection to database
			BookTagDAO dao = DAOUtilities.getBookTagDAO();
			BookTag bookTag =new BookTag();
		
				//request the parameter values
			bookTag.setIsbn_13(request.getParameter("isbn_13"));
			bookTag.setTag_names(request.getParameter("tag_names"));	
		// set isSuccess to delete the parameter
			boolean isSuccess = dao.deleteBookTag(bookTag);
			
		if(isSuccess){
			request.getSession().setAttribute("message", "Book Tag successfully removed");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect(request.getContextPath() + "/BookTagPublishing");
		}else {
			request.getSession().setAttribute("message", "There was a problem deleting this tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookTagDetails.jsp").forward(request, response);
		}
		
	}

}
