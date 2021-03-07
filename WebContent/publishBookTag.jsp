
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
			PUBHUB <small>Publish</small>
		</h1>
		<hr class="book-primary">

		<!-- NOTE: This form uses the enctype="multipart/form-data" attribute because it contains a file upload control (<input type="file" ... />).
				To support this special enctype, the PublishBookServlet also has the @MultiPartConfig annotation. You only need to use this
				enctype and its corresponding annotation if you need to use a file upload control. Do not use it otherwise. -->

		<form action="PublishBookTag" method="post" class="form-horizontal"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="isbn_13" class="col-sm-4 control-label">ISBN 13</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="isbn_13" name="isbn_13"
						placeholder="ISBN 13" required="required"
						value="${param.isbn_13 }" />
				</div>
			</div>
			<div class="form-group">
				<label for="tag_names" class="col-sm-4 control-label">Tag
					Name</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="tag_names"
						name="tag_names" placeholder="Tag Name" required="required"
						value="${param.tag_names }" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-1">
					<button type="submit" class="btn btn-info">Add</button>
				</div>
			</div>
		</form>

	</div>
</header>



<!-- Footer -->
<jsp:include page="footer.jsp" />
