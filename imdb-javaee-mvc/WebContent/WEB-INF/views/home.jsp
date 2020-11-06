<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Imdb Search Page</title>
<style type="text/css">
table tbody tr:NTH-CHILD(2n) {
	background-color: #efefef;
}

table tbody tr:NTH-CHILD(2n+1) {
	background-color: #cfcfcf;
}

</style>
</head>
<body>
	<c:url var="actionUrl" value="/movies/search" />
	<form action="${actionUrl}" method="post">
		<table>
			<tbody>
				<tr>
					<td>From:</td>
					<td><input type="text" name="fromYear" value="${param.fromYear}" /></td>
				</tr>
				<tr>
					<td>To:</td>
					<td><input type="text" name="toYear" value="${param.toYear}" /></td>
				</tr>
				<tr>
					<td>Genre:</td>
					<td colspan="2"><select name="genre">
							<c:forEach items="${genres}" var="genre">
								<c:choose>
									<c:when test="${param.genre eq genre.id}">
										<option label="${genre.name}" selected value="${genre.id}">${genre.name}</option>
									</c:when>
									<c:otherwise>
										<option label="${genre.name}" value="${genre.id}">${genre.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select> <input type="submit" value="Search" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<c:if test="${not empty movies}">
		<table>
			<thead>
				<tr>
					<th>Title</th>
					<th>Year</th>
					<th>Imdb Page</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${movies}" var="movie">
					<tr>
						<td>${movie.title}</td>
						<td>${movie.year}</td>
						<td><a href="http://www.imdb.com/title/${movie.imdb}"
							target="_blank">Visit!</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>