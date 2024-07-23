<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Password - Banking Application</title>
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

        .new-password-container {
            background: linear-gradient(135deg, #ffffff, #f0f0f0);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .new-password-container h2 {
            margin-bottom: 20px;
            color: #333;
            font-weight: 700;
            font-size: 28px;
            letter-spacing: 1px;
        }

        .new-password-container input[type="password"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
            margin-bottom: 20px;
        }

        .new-password-container button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .new-password-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="new-password-container">
        <h2>Create New Password</h2>
        <form action="UpdatePasswordServlet" method="post">
            <input type="hidden" name="username" value="${param.username}">
            <input type="hidden" name="oldPassword" value="${param.password}">
            <input type="password" name="newPassword" placeholder="New Password" required>
            <button type="submit">Update Password</button>
        </form>
    </div>
</body>
</html>

