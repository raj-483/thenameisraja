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

@WebServlet("/SearchCustomerServlet")
public class SearchCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/banking";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gokul";

    // SQL query to search for a customer by username
    private static final String SEARCH_CUSTOMER_SQL = "SELECT username, fullname, address, mobile_number, account_type, balance, date_of_birth, id_proof FROM info WHERE username = ?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetching the username from the request
        String username = request.getParameter("username");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create prepared statement
            preparedStatement = connection.prepareStatement(SEARCH_CUSTOMER_SQL);
            preparedStatement.setString(1, username);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check if a customer with the provided username exists
            if (resultSet.next()) {
                // Set the customer details as request attributes
                request.setAttribute("username", resultSet.getString("username"));
                request.setAttribute("fullName", resultSet.getString("fullname"));
                request.setAttribute("address", resultSet.getString("address"));
                request.setAttribute("mobileNumber", resultSet.getString("mobile_number"));
                request.setAttribute("accountType", resultSet.getString("account_type"));
                request.setAttribute("balance", resultSet.getDouble("balance"));
                request.setAttribute("dateOfBirth", resultSet.getString("date_of_birth"));
                request.setAttribute("idProof", resultSet.getString("id_proof"));

                // Forward to the JSP page to display and edit customer details
                RequestDispatcher dispatcher = request.getRequestDispatcher("EditCustomerDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                // If no customer is found, display an error message
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body><p>No customer found with the username: " + username + "</p></body></html>");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><p>Error searching customer details: " + e.getMessage() + "</p></body></html>");
        } finally {
            // Clean-up JDBC resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
