<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IMDB Movie Search Page</title>
</head>
<body>
 	<c:url value="/movies/search" var="actionUrl" />
	<form action="${actionUrl}" method="post">
	   <table>
	   	<tr>
	   		<td>From:</td>
	   		<td><input type="text" 
	   		           name="fromYear" 
	   		           value="${param.fromYear}"/>
	   		</td>           
	   	</tr>	
	   	<tr>
	   		<td>To:</td>
	   		<td><input type="text" 
	   		           name="toYear" 
	   		           value="${param.toYear}"/>
	   		</td>           
	   	</tr>	
	   	<tr>
	   		<td>Genre:</td>
	   		<td>
	   		    <select name="genre">
	   		    	<c:forEach items="${genres}" var="genre">
	   		    	    <c:choose>
	   		    	      <c:when test="${param.genre eq genre.id}">
	   		    	         <option label="${genre.name}"
	   		    	         		 selected
	   		    	                 value="${genre.id}">${genre.name}</option>
	   		    	      </c:when>
	   		    	      <c:otherwise>
	   		    	         <option label="${genre.name}"
	   		    	                 value="${genre.id}">${genre.name}</option>
	   		    	      </c:otherwise>
	   		    	    </c:choose>
	   		    	</c:forEach>
	   		    </select>
	   		    <input type="submit" 
	   		           value="Search"/>
	   		</td>           
	   	</tr>		   	
	   </table>
	</form>
	<c:if test="${not empty imdb.movies}">
		<table>
			<thead>
				<tr>
					<th>Year</th>
					<th>Title</th>
					<th>IMDB</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${imdb.movies}" var="movie">
					<tr>
						<td>${movie.year}</td>
						<td>${movie.title}</td>
						<td><a href="http://www.imdb.com/title/${movie.imdb}" target="_blank">Visit me!</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>