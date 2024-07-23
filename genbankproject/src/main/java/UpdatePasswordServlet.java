import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/banking";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "gokul";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h2>Driver Class Not Found</h2>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("</body></html>");
            return;
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // SQL query to update the password in the info table
            String updateSql = "UPDATE info SET password = ? WHERE username = ?";
            try (PreparedStatement updateStatement = conn.prepareStatement(updateSql)) {
                updateStatement.setString(1, newPassword);
                updateStatement.setString(2, username);
                
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Delete the entry from the newcustomer table
                    String deleteSql = "DELETE FROM newcustomer WHERE username = ?";
                    try (PreparedStatement deleteStatement = conn.prepareStatement(deleteSql)) {
                        deleteStatement.setString(1, username);
                        deleteStatement.executeUpdate();
                    }
                    
                    // Redirect to login.jsp on successful password update
                    response.sendRedirect("login.jsp?success=Password%20updated%20successfully");
                } else {
                    out.println("<html><head><title>Update Failed</title></head><body>");
                    out.println("<h2>Update Failed</h2>");
                    out.println("<p>Unable to update password. Please try again.</p>");
                    out.println("</body></html>");
                }
            }
        } catch (SQLException e) {
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h2>Database Error</h2>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
