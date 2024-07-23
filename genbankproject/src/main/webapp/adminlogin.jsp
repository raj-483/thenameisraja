<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login - Banking Application</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #e0e0e0;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
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
            font-weight: 700;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }

        .login-container {
            background: linear-gradient(135deg, #ffffff, #f0f0f0);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 100%;
            max-width: 400px;
            margin-top: -75px; /* Adjust to overlap header if needed */
        }

        .login-container h2 {
            margin-bottom: 20px;
            color: #333;
            font-weight: 700;
            font-size: 28px;
            letter-spacing: 1px;
        }

        .login-container label {
            display: block;
            margin: 10px 0 5px;
            font-size: 16px;
            color: #666;
        }

        .login-container input {
            width: calc(100% - 22px); /* Adjust for padding */
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .login-button {
            width: 100%;
            padding: 15px;
            font-size: 18px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .login-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="header">
        Banking Application - Admin Login
    </div>
    <div class="login-container">
        <h2>Admin Login</h2>
        <form action="adminloginservlet" method="post">
            <label for="adminId">Admin ID</label>
            <input type="text" id="adminId" name="adminId" required>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            
            <button type="submit" class="login-button">Login</button>
        </form>
    </div>
</body>
</html>
