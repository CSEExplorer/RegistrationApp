<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
    <%
        String name = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("useremail");

        if (name == null) name = "Guest";
        if (email == null) email = "Not provided";
    %>
    <h2>Welcome, <%= name %>!</h2>
    <p>Your email: <%= email %></p>
</body>
</html>
