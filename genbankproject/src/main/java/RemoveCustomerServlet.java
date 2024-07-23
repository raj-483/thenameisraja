import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveCustomerServlet")
public class RemoveCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/banking";
    private static final String dbUser = "root";
    private static final String dbPassword = "gokul";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Check if user exists
            String checkUserSql = "SELECT username FROM info WHERE username = ?";
            stmt = conn.prepareStatement(checkUserSql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // User exists, proceed to delete
                String deleteUserSql = "DELETE FROM info WHERE username = ?";
                stmt = conn.prepareStatement(deleteUserSql);
                stmt.setString(1, username);
                stmt.executeUpdate();

                response.sendRedirect("admindashboard.jsp?message=Customer removed successfully");
            } else {
                // User does not exist
                request.setAttribute("errorMessage", "User not found");
                RequestDispatcher dispatcher = request.getRequestDispatcher("searchRemoveCustomer.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Database error: " + e.getMessage() + "</h3></body></html>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
