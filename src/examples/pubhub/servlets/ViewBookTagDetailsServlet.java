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
 * Servlet implementation class ViewBookTagDetailsServlet
 */
@WebServlet("/ViewBookTagDetails")
public class ViewBookTagDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBookTagDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// The bookTagDetails.jsp page will have the details of the selected isbn_13 saved to the request,
		// Otherwise it won't know what details to display. Ergo, we need to fetch those details before we
		// Actually redirect the user.
		
		String isbn_13 = request.getParameter("isbn_13");
		//String tag_names = request .getParameter("tag_names");
		BookTagDAO doa = DAOUtilities.getBookTagDAO();
		BookTag bookTag = doa.getBookTagByISBN(isbn_13);		
		
		request.setAttribute("bookTag", bookTag);
		
		// We can use a forward here, because if a user wants to refresh their browser on this page,
		// it will just show them the most recent details for their tag. There's no risk of data
		// miss-handling here.
		request.getRequestDispatcher("bookTagDetails.jsp").forward(request, response);
		
	}



}
