<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
    <%
        String fname = (String) session.getAttribute("username");
        String lname = (String) session.getAttribute("lastname");
        String email = (String) session.getAttribute("useremail");
        String rollno = (String) session.getAttribute("rollno");

        if (fname == null) fname = "Guest";
        if (lname == null) lname = "";
        if (email == null) email = "Not provided";
        if (rollno == null) rollno = "Not provided";
    %>

    <h2>Welcome, <%= fname %> <%= lname %>!</h2>
    <p><strong>Email:</strong> <%= email %></p>
    <p><strong>Roll Number:</strong> <%= rollno %></p>
</body>
</html>
