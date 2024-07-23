<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .header {
            width: 100%;
            height: 150px;
            background: url('path/to/your/header-image.jpg') no-repeat center center;
            background-size: cover;
            border-bottom: 2px solid #007bff;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 24px;
            font-weight: bold;
        }

        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: -60px; /* Adjust this to overlap header if needed */
            width: 90%;
            max-width: 600px;
            text-align: center;
        }

        h1 {
            color: #343a40;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            color: #495057;
            margin-bottom: 20px;
        }

        form {
            background-color: #f8f9fa;
            padding: 20px;
            border: 1px solid #dee2e6;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        input[type="text"], 
        input[type="number"] {
            padding: 10px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            margin-bottom: 15px;
            width: calc(100% - 22px);
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        .back-to-login {
            background-color: #28a745;
            padding: 12px 20px;
            border-radius: 5px;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }

        .back-to-login:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="header">
        Welcome to Your Dashboard
    </div>
    <div class="container">
        <h1>Welcome, <%= session.getAttribute("fullname") %></h1>
        <p>Username: <%= session.getAttribute("username") %></p>
        <p>Account Number: <%= session.getAttribute("account_number") %></p>
        <p>Current Balance: $<%= session.getAttribute("balance") %></p>

        <form action="DashboardServlet" method="post">
            <input type="hidden" name="action" value="add">
            Amount to Add: <input type="number" name="amount" step="0.01" required>
            <button type="submit">Add Money</button>
        </form>

        <form action="DashboardServlet" method="post">
            <input type="hidden" name="action" value="withdraw">
            Amount to Withdraw: <input type="number" name="amount" step="0.01" required>
            <button type="submit">Withdraw Money</button>
        </form>

        <form action="DashboardServlet" method="post">
            <input type="hidden" name="action" value="transfer">
            Amount to Transfer: <input type="number" name="amount" step="0.01" required>
            Recipient Account Number: <input type="text" name="recipient_account_number" required>
            <button type="submit">Send Money</button>
        </form>

        <form action="DashboardServlet" method="post">
            <input type="hidden" name="action" value="viewTransactions">
            <button type="submit">View Last Transactions</button>
        </form>
        
        <button class="back-to-login" onclick="window.location.href='login.jsp'">Back to Login</button>
    </div>
</body>
</html>
