import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
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

@WebServlet("/CustomerRegistrationServlet")
public class CustomerRegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/banking";
    private static final String dbUser = "root";
    private static final String dbPassword = "gokul";

    // SecureRandom for generating random account numbers and passwords
    private static final SecureRandom random = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetching form parameters
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNumber = request.getParameter("mobileNumber");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");

        // Generate random account number and password
        String accountNumber = generateRandomNumber(10);
        String password = generateRandomString(8);

        // JDBC variables
        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL query to insert data into the info table
            String sql1 = "INSERT INTO info (username, password, fullname, balance, address, mobile_number, account_type, date_of_birth, id_proof, account_number) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // SQL query to insert data into the newcustomer table
            String sql2 = "INSERT INTO newcustomer (username, password, accountnumber) VALUES (?, ?, ?)";

            // Create prepared statement for info table
            stmt1 = conn.prepareStatement(sql1);
            stmt1.setString(1, username);
            stmt1.setString(2, password);
            stmt1.setString(3, fullName);
            stmt1.setDouble(4, initialBalance);
            stmt1.setString(5, address);
            stmt1.setString(6, mobileNumber);
            stmt1.setString(7, accountType);
            stmt1.setString(8, dateOfBirth);
            stmt1.setString(9, idProof);
            stmt1.setString(10, accountNumber);

            // Create prepared statement for newcustomer table
            stmt2 = conn.prepareStatement(sql2);
            stmt2.setString(1, username);
            stmt2.setString(2, password);
            stmt2.setString(3, accountNumber);

            // Execute the statements
            int rowsAffected1 = stmt1.executeUpdate();
            int rowsAffected2 = stmt2.executeUpdate();

            // Check if the registration was successful
            if (rowsAffected1 > 0 && rowsAffected2 > 0) {
                // Set the registration details as request attributes
                request.setAttribute("username", username);
                request.setAttribute("fullName", fullName);
                request.setAttribute("address", address);
                request.setAttribute("mobileNumber", mobileNumber);
                request.setAttribute("accountType", accountType);
                request.setAttribute("initialBalance", initialBalance);
                request.setAttribute("dateOfBirth", dateOfBirth);
                request.setAttribute("idProof", idProof);
                request.setAttribute("accountNumber", accountNumber);
                request.setAttribute("password", password);

                // Forward to the JSP page to display registration details
                RequestDispatcher dispatcher = request.getRequestDispatcher("RegistrationDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body><h3>Registration failed.</h3></body></html>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Database error: " + e.getMessage() + "</h3></body></html>");
        } finally {
            // Clean-up JDBC resources
            try {
                if (stmt1 != null) stmt1.close();
                if (stmt2 != null) stmt2.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    // Method to generate a random string of specified length
    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    // Method to generate a random number of specified length
    private String generateRandomNumber(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
