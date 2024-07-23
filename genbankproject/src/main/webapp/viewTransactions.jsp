<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
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
            border-bottom: 3px solid #007bff;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 28px;
            font-weight: bold;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }

        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: -70px; /* Adjust this if needed to overlap header */
            width: 90%;
            max-width: 1000px;
        }

        h1 {
            color: #343a40;
            margin-bottom: 20px;
            font-size: 24px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #dee2e6;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        .back-button {
            display: block;
            background-color: #28a745;
            padding: 12px 25px;
            border-radius: 5px;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
            margin: 20px auto;
            text-decoration: none;
        }

        .back-button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="header">
        Transaction History
    </div>
    <div class="container">
        <h1>Last 10 Transactions</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Amount</th>
                    <th>Transaction Type</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    ResultSet transactions = (ResultSet) request.getAttribute("transactions");
                    while (transactions != null && transactions.next()) {
                %>
                <tr>
                    <td><%= transactions.getInt("id") %></td>
                    <td><%= transactions.getString("username") %></td>
                    <td>$<%= transactions.getDouble("amount") %></td>
                    <td><%= transactions.getString("transaction_type") %></td>
                    <td><%= transactions.getTimestamp("date") %></td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
        <a class="back-button" href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
