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
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get form data
        String fname = request.getParameter("firstname");
        String lname = request.getParameter("lastname");
        String pass = request.getParameter("password");
        String roll = request.getParameter("rollno");
        String email = request.getParameter("email");

        // Database credentials
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc"; // Change to your DB name
        String dbUser = "root"; // Change if needed
        String dbPassword = "root"; // Change if needed

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
                System.out.println("Database Connected");

                // Insert into database
                String sql = "INSERT INTO users (firstname, lastname, password, rollno, email) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, fname);
                stmt.setString(2, lname);
                stmt.setString(3, pass);
                stmt.setString(4, roll);
                stmt.setString(5, email);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    // Store user data in session
                    HttpSession session = request.getSession();
                    session.setAttribute("username", fname);
                    session.setAttribute("lastname", lname);
                    session.setAttribute("useremail", email);
                    session.setAttribute("rollno", roll);

                    System.out.println("Session username: " + session.getAttribute("username"));
                    System.out.println("Session email: " + session.getAttribute("useremail"));

                    // Forward to welcome.jsp
                    RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                    dispatcher.forward(request, response);
                } else {
                    out.println("<h2>Registration Failed!</h2>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
