import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/banking";
    private static final String dbUser = "root";
    private static final String dbPassword = "gokul";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetching form parameters
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNumber = request.getParameter("mobileNumber");
        String accountType = request.getParameter("accountType");
        double balance = Double.parseDouble(request.getParameter("balance"));
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");

        // JDBC variables
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL query to update data
            String sql = "UPDATE info SET fullname = ?, address = ?, mobile_number = ?, account_type = ?, balance = ?, date_of_birth = ?, id_proof = ? WHERE username = ?";

            // Create prepared statement
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fullName);
            stmt.setString(2, address);
            stmt.setString(3, mobileNumber);
            stmt.setString(4, accountType);
            stmt.setDouble(5, balance);
            stmt.setString(6, dateOfBirth);
            stmt.setString(7, idProof);
            stmt.setString(8, username);

            // Execute the statement
            int rowsAffected = stmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                response.sendRedirect("admindashboard.jsp?message=Customer details edited successfully");
            } else {
                response.sendRedirect("editcustomerdetails.jsp?message=Failed to update customer details");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Database error: " + e.getMessage() + "</h3></body></html>");
        } finally {
            // Clean-up JDBC resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
