<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Details - Banking Application</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #e0e0e0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .result-container {
            background: linear-gradient(135deg, #ffffff, #f0f0f0);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .result-container h2 {
            margin-bottom: 20px;
            color: #333;
            font-weight: 700;
            font-size: 28px;
            letter-spacing: 1px;
        }

        .result-container table {
            width: 100%;
            border-collapse: collapse;
        }

        .result-container table, .result-container th, .result-container td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        .result-container th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        .result-container td {
            text-align: left;
        }

        .back-button {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="result-container">
        <h2>Customer Details</h2>
        <table>
            <tr>
                <th>Field</th>
                <th>Details</th>
            </tr>
            <tr><td>Full Name:</td><td>${fullname}</td></tr>
            <tr><td>Address:</td><td>${address}</td></tr>
            <tr><td>Mobile:</td><td>${mobile}</td></tr>
            <tr><td>Account Type:</td><td>${accountType}</td></tr>
            <tr><td>Balance:</td><td>${balance}</td></tr>
            <tr><td>Date of Birth:</td><td>${dob}</td></tr>
            <tr><td>ID Proof:</td><td>${idProof}</td></tr>
        </table>
        <a href="admindashboard.jsp" class="back-button">Back to Dashboard</a>
    </div>
</body>
</html>
