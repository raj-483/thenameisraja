import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminloginservlet")
public class adminloginservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/banking";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gokul";

    // SQL query to check admin credentials
    private static final String CHECK_ADMIN_SQL = "SELECT * FROM admin WHERE admin_id = ? AND password = ?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminId = request.getParameter("adminId");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ADMIN_SQL)) {

            preparedStatement.setString(1, adminId);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Redirect to admin dashboard if credentials are valid
                response.sendRedirect("admindashboard.jsp");
            } else {
                // Send error message if credentials are invalid
                out.println("<html><body><p>Invalid Admin ID or Password.</p></body></html>");
                request.getRequestDispatcher("adminlogin.jsp").include(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><p>Error connecting to the database.</p></body></html>");
        }
    }
}
