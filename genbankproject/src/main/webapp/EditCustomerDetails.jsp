<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer Details - Banking Application</title>
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

        .edit-container {
            background: linear-gradient(135deg, #ffffff, #f0f0f0);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .edit-container h2 {
            margin-bottom: 20px;
            color: #333;
            font-weight: 700;
            font-size: 28px;
            letter-spacing: 1px;
        }

        .edit-container table {
            width: 100%;
            border-collapse: collapse;
        }

        .edit-container table, .edit-container th, .edit-container td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        .edit-container th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        .edit-container td {
            text-align: left;
        }

        .edit-container input[type="text"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
            margin-bottom: 20px;
        }

        .edit-container button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .edit-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="edit-container">
        <h2>Edit Customer Details</h2>
        <form action="UpdateCustomerServlet" method="post">
            <input type="hidden" name="username" value="${username}">
            <table>
                <tr><td>Full Name:</td><td><input type="text" name="fullName" value="${fullName}" required></td></tr>
                <tr><td>Address:</td><td><input type="text" name="address" value="${address}" required></td></tr>
                <tr><td>Mobile Number:</td><td><input type="text" name="mobileNumber" value="${mobileNumber}" required></td></tr>
                <tr><td>Account Type:</td><td><input type="text" name="accountType" value="${accountType}" required></td></tr>
                <tr><td>Balance:</td><td><input type="text" name="balance" value="${balance}" required></td></tr>
                <tr><td>Date of Birth:</td><td><input type="text" name="dateOfBirth" value="${dateOfBirth}" required></td></tr>
                <tr><td>ID Proof:</td><td><input type="text" name="idProof" value="${idProof}" required></td></tr>
            </table>
            <button type="submit">Update Details</button>
        </form>
    </div>
</body>
</html>

