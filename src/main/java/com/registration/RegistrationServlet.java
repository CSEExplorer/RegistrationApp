package com.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Get form data
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		// Database credentials
		String jdbcURL = "jdbc:mysql://localhost:3306/jdbc"; // Change to your DB
		String dbUser = "root"; // Change if needed
		String dbPassword = "root"; // Change if needed

		try {
			// Load MySQL Driver
//            Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish database connection
			try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
				System.out.println("Databses Connected ");
				String sql = "INSERT INTO table1 (name, email) VALUES (?, ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setString(2, email);

				int rowsInserted = stmt.executeUpdate();
				if (rowsInserted > 0) {
					out.println("<h2>Registration Successful!</h2>");
					RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
					dispatcher.forward(request, response);
				} else {
					out.println("<h2>Registration Failed!</h2>");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h2>Error: " + e.getMessage() + "</h2>");
		}
	}
}
