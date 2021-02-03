<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.hcl.userupdater.domain.User"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <%@ include file="bootstrap.jsp" %>
    <%@ include file="style.jsp" %>
    <title>Edit User</title>
  </head>
  <body class="text-left">
    <main class="form-edit">
      <h1 class="mb-3 text-center">Edit User</h1>

      <form:form action="/user/update" commandName="user">
        <form:input path="id" value="${user.id}" type="hidden"/>

        <div class="mb-3">
          <label for="usernameInput" class="form-label">Username</label>
          <input type="text" class="form-control" id="usernameInput" name='username' required
            value="${user.username}">
          <p style="color:red;"><c:out value="${usernameAlert}"/></p>
        </div>

        <div class="mb-3">
          <label for="passwordInput" class="form-label">Password</label>
          <input type="text" class="form-control" id="passwordInput" name='password' required
          value="${user.password}">
          <p style="color:red;"><c:out value="${passwordAlert}"/></p>
        </div>

        <div class="mb-3">
          <label for="firstNameInput" class="form-label">First Name</label>
          <input type="text" class="form-control" id="firstNameInput" name='firstName' required
            value="${user.firstName}">
          <p style="color:red;"><c:out value="${firstNameAlert}"/></p>
        </div>

        <div class="mb-3">
          <label for="lastNameInput" class="form-label">Last Name</label>
          <input type="text" class="form-control" id="lastNameInput" name='lastName' required
            value="${user.lastName}">
          <p style="color:red;"><c:out value="${lastNameAlert}"/></p>
        </div>

        <div class="mb-3">
          <label for="emailInput" class="form-label">Email Address</label>
          <input type="email" class="form-control" id="emailInput" aria-describedby="emailHelp" name='email' required
            value="${user.email}">
        </div>

        <div class="mb-3">
          <fmt:formatDate value="${user.birthday}" var="formattedBirthday" type="date" pattern="yyyy-MM-dd" timeZone="z"/>
          <label for="birthdayInput" class="form-label">Birthday</label>
          <input class="form-control" type="date" id="birthdayInput" name='birthday' required
            value="${formattedBirthday}">
          <p style="color:red;"><c:out value="${birthdayAlert}"/></p>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Update</button>
      </form:form>

    </main>
  </body>
</html>