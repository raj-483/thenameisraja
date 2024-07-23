<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Banking Application</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&family=Open+Sans:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .dashboard-container {
            background-color: #ffffff;
            padding: 50px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .dashboard-container h1 {
            font-family: 'Open Sans', sans-serif;
            color: #333333;
            margin-bottom: 30px;
            font-weight: 700;
        }

        .dashboard-container .button {
            display: inline-block;
            margin: 15px;
            padding: 15px 30px;
            font-size: 18px;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .dashboard-container .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <h1>Admin Dashboard</h1>
        <a class="button" href="viewcustomer.jsp">View Customer</a>
        <a class="button" href="customerregistration.jsp">Register Customer</a>
        <a class="button" href="SearchCustomer.jsp">Edit Customer</a>
        <a class="button" href="searchRemoveCustomer.jsp">Delete Customer</a>
    </div>
</body>
</html>
