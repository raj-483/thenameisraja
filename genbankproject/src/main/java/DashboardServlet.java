import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Double currentBalance = (Double) session.getAttribute("balance");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String jdbcURL = "jdbc:mysql://localhost:3306/banking";
        String jdbcUsername = "root";
        String jdbcPassword = "gokul";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            if ("add".equals(action)) {
                double amount = Double.parseDouble(request.getParameter("amount"));
                String sql = "UPDATE info SET balance = balance + ? WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setDouble(1, amount);
                    statement.setString(2, username);

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        session.setAttribute("balance", currentBalance + amount);
                        logTransaction(connection, username, amount, "Deposit");
                    }
                }
                response.sendRedirect("dashboardDetails.jsp");

            } else if ("withdraw".equals(action)) {
                double amount = Double.parseDouble(request.getParameter("amount"));
                String sql = "UPDATE info SET balance = balance - ? WHERE username = ? AND balance >= ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setDouble(1, amount);
                    statement.setString(2, username);
                    statement.setDouble(3, amount);

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        session.setAttribute("balance", currentBalance - amount);
                        logTransaction(connection, username, amount, "Withdrawal");
                    }
                }
                response.sendRedirect("dashboardDetails.jsp");

            } else if ("transfer".equals(action)) {
                double amount = Double.parseDouble(request.getParameter("amount"));
                String recipientAccountNumber = request.getParameter("recipient_account_number");

                if (amount <= currentBalance) {
                    try {
                        connection.setAutoCommit(false);

                        // Check if the recipient exists and get their balance
                        String sqlCheckRecipient = "SELECT balance FROM info WHERE account_number = ?";
                        try (PreparedStatement statementCheckRecipient = connection.prepareStatement(sqlCheckRecipient)) {
                            statementCheckRecipient.setString(1, recipientAccountNumber);
                            ResultSet resultSet = statementCheckRecipient.executeQuery();

                            if (resultSet.next()) {
                                double recipientBalance = resultSet.getDouble("balance");

                                // Update sender's balance
                                String sqlUpdateSender = "UPDATE info SET balance = balance - ? WHERE username = ?";
                                try (PreparedStatement statementUpdateSender = connection.prepareStatement(sqlUpdateSender)) {
                                    statementUpdateSender.setDouble(1, amount);
                                    statementUpdateSender.setString(2, username);
                                    int rowsUpdatedSender = statementUpdateSender.executeUpdate();
                                    if (rowsUpdatedSender <= 0) {
                                        connection.rollback();
                                        throw new SQLException("Failed to update sender's balance");
                                    }
                                }

                                // Update recipient's balance
                                String sqlUpdateRecipient = "UPDATE info SET balance = balance + ? WHERE account_number = ?";
                                try (PreparedStatement statementUpdateRecipient = connection.prepareStatement(sqlUpdateRecipient)) {
                                    statementUpdateRecipient.setDouble(1, amount);
                                    statementUpdateRecipient.setString(2, recipientAccountNumber);
                                    int rowsUpdatedRecipient = statementUpdateRecipient.executeUpdate();
                                    if (rowsUpdatedRecipient <= 0) {
                                        connection.rollback();
                                        throw new SQLException("Failed to update recipient's balance");
                                    }
                                }

                                connection.commit();
                                session.setAttribute("balance", currentBalance - amount);
                                logTransaction(connection, username, amount, "Transfer to " + recipientAccountNumber);
                                logTransaction(connection, recipientAccountNumber, amount, "Transfer from " + username);
                                response.sendRedirect("dashboardDetails.jsp");
                            } else {
                                connection.rollback();
                                session.setAttribute("error", "Recipient account not found");
                                response.sendRedirect("dashboardDetails.jsp");
                            }
                        }
                    } catch (SQLException e) {
                        connection.rollback();
                        e.printStackTrace();
                        session.setAttribute("error", "Error processing transfer");
                        response.sendRedirect("dashboardDetails.jsp");
                    } finally {
                        connection.setAutoCommit(true);
                    }
                } else {
                    session.setAttribute("error", "Insufficient funds");
                    response.sendRedirect("dashboardDetails.jsp");
                }
            } else if ("viewTransactions".equals(action)) {
                // Fetch and display the last transactions
                String transactionSql = "SELECT * FROM transactions WHERE username = ? ORDER BY date DESC LIMIT 10";
                try (PreparedStatement transactionStatement = connection.prepareStatement(transactionSql)) {
                    transactionStatement.setString(1, username);
                    ResultSet transactionResultSet = transactionStatement.executeQuery();

                    request.setAttribute("transactions", transactionResultSet);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("viewTransactions.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    session.setAttribute("error", "Error fetching transactions");
                    response.sendRedirect("dashboardDetails.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", "Database error");
            response.sendRedirect("dashboardDetails.jsp");
        }
    }

    private void logTransaction(Connection connection, String username, double amount, String transactionType) throws SQLException {
        String sql = "INSERT INTO transactions (username, amount, transaction_type) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setDouble(2, amount);
            statement.setString(3, transactionType);
            statement.executeUpdate();
        }
    }
}
