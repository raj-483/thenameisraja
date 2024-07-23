<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e9ecef;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            text-align: center;
            background-color: #ffffff;
            padding: 30px;
            border: 1px solid #dcdcdc;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            max-width: 600px;
            width: 100%;
        }

        h1 {
            color: #343a40;
            margin-bottom: 20px;
            font-size: 24px;
        }

        p {
            font-size: 18px;
            color: #495057;
            margin-bottom: 20px;
        }

        .button-container {
            margin-top: 20px;
        }

        button {
            background-color: #007bff;
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .success-icon {
            font-size: 50px;
            color: green;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <i class="fas fa-check-circle success-icon"></i>
        <h1>THANK YOU, <%= session.getAttribute("fullname") %></h1>
        <p>Current Balance: <%= session.getAttribute("balance") %></p>
        <p>YOUR TRANSACTION IS SUCCESSFUL</p>
        <div class="button-container">
            <button onclick="window.location.href='dashboard.jsp'">Back to Dashboard</button>
        </div>
    </div>
</body>
</html>
