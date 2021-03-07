
<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- 	Just some stuff you need -->

<header>
	<div class="container">

		<c:choose>
			<c:when test="${not empty message }">
				<p class="alert ${messageClass}">${message }</p>
				<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
			</c:when>
		</c:choose>

		<h1>
			PUBHUB <small>BookTag Publishing</small>
		</h1>
		<hr class="book-primary">

		<table
			class="table table-striped table-hover table-responsive pubhub-datatable">
			<thead>
				<tr>
					<td>ISBN-13:</td>
					<td>Tag Names</td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bookTag" items="${bookTags}">
					<tr>
						<td><c:out value="${bookTag.isbn_13}" /></td>

						<td><c:out value="${bookTag.tag_names}" /></td>
						<td><form action="ViewBookTagDetails?isbn=${bookTag.isbn_13}"
								method="get">
								<input type="hidden" name="isbn_13" value="${bookTag.isbn_13}">
								<button class="btn btn-primary">Details</button>
							</form></td>
								<td>
								<form action="DeleteBookTag" method="post" class="form-horizontal">
									<input type="hidden" value="${bookTag.isbn_13}" name="isbn_13"  />
									<input type="hidden"  value="${bookTag.tag_names}" name="tag_names"
										name="Tag Name" />
									<button class="btn btn-danger popover-bookpub-removetag"
										type="submit">Delete</button>
								</form>
							</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</header>

<!-- Footer -->
<jsp:include page="footer.jsp" />