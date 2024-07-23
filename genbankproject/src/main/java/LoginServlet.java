import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String jdbcURL = "jdbc:mysql://localhost:3306/banking";
        String jdbcUsername = "root";
        String jdbcPassword = "gokul";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            // Check credentials in newcustomer table
            String checkNewCustomerSQL = "SELECT * FROM newcustomer WHERE username = ? AND password = ?";
            try (PreparedStatement checkNewCustomerStatement = connection.prepareStatement(checkNewCustomerSQL)) {
                checkNewCustomerStatement.setString(1, username);
                checkNewCustomerStatement.setString(2, password);
                ResultSet newCustomerResultSet = checkNewCustomerStatement.executeQuery();

                if (newCustomerResultSet.next()) {
                    // Password matches in newcustomer table, redirect to createpassword.jsp
                    response.sendRedirect("createNewPassword.jsp?username=" + URLEncoder.encode(username, "UTF-8"));
                    return; // Exit the servlet to avoid further processing
                }
            }

            // Password does not match in newcustomer table, check info table
            String checkInfoSQL = "SELECT * FROM info WHERE username = ? AND password = ?";
            try (PreparedStatement checkInfoStatement = connection.prepareStatement(checkInfoSQL)) {
                checkInfoStatement.setString(1, username);
                checkInfoStatement.setString(2, password);
                ResultSet infoResultSet = checkInfoStatement.executeQuery();

                if (infoResultSet.next()) {
                    // Password matches in info table, set session attributes and redirect to dashboard.jsp
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("balance", infoResultSet.getDouble("balance"));
                    session.setAttribute("account_number", infoResultSet.getString("account_number"));
                    session.setAttribute("fullname", infoResultSet.getString("fullname"));
                    response.sendRedirect("dashboard.jsp");
                } else {
                    // Credentials do not match in info table, redirect to login.jsp with error
                    String encodedError = URLEncoder.encode("Invalid credentials", "UTF-8");
                    response.sendRedirect("login.jsp?error=" + encodedError);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String encodedError = URLEncoder.encode("Database error", "UTF-8");
            response.sendRedirect("login.jsp?error=" + encodedError);
        }
    }
}
