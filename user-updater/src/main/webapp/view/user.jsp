<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
  <head>
    <%@ include file="bootstrap.jsp" %>
    <%@ include file="style.jsp" %>
    <title>Users</title>
  </head>

  <body class="text-center">

    <div class="form-select">
    <p style="color:green;"><c:out value="${successAlert}"/></p>
    <p style="color:red;"><c:out value="${failAlert}"/></p>
      <h1 class="mb-3">Select User</h1>
      <form:form action="/edit/${id}" method="GET">

          <label for="inputId" class="form-label">User Id</label>
          <input type="number" id="inputId" class="form-control" name='id' required
            value="${id}" >

          <p class="mb-3" style="color:red;"><c:out value="${idAlert}"/></p>

          <button class="w-100 btn btn-lg btn-primary" type="submit">Edit</button>
      </form:form>
    </div>

    <div class="list">
      <c:if test="${!empty listUsers}">
        <h3>User List</h3>
        <table class="table tg">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Username</th>
              <th scope="col">Password</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Email</th>
              <th scope="col">Birthday</th>
              <th scope="col">Date Created</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${listUsers}" var="user">
              <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <fmt:formatDate value="${user.birthday}" var="formattedBirthday" type="date" pattern="MMMM dd, yyyy" timeZone="z" />
                <td><c:out value="${formattedBirthday}"/></td>
                <fmt:formatDate value="${user.dateCreated}" var="formattedDateCreated" type="date" pattern="MMMM dd, yyyy" timeZone="z"/>
                <td><c:out value="${formattedDateCreated}"/></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>
    </div>
  </body>
</html>