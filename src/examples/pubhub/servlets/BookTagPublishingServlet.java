package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class BookTagPublishingServlet
 */
@WebServlet("/BookTagPublishing")
public class BookTagPublishingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookTagPublishingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Grab the list of Tags from the Database
		BookTagDAO dao = DAOUtilities.getBookTagDAO();
		List<BookTag> bookTagList = dao.getAllBookTags();
		
		//Populate the list into a varaible that will be stored im the session
		request.getSession().setAttribute("bookTags", bookTagList);
		request.getRequestDispatcher("bookTagPublishingHome.jsp").forward(request, response);
	}

}
