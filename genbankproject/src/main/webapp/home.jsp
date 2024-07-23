<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Banking Application</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f9;
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
            background: url('D:\images\WhatsApp Image 2024-07-21 at 8.31.33 PM (1).jpeg') no-repeat center center;
            background-size: cover;
            height: 200px;
            position: relative;
        }

        .header h1 {
            color: white;
            text-align: center;
            padding-top: 60px;
            font-size: 36px;
            margin: 0;
            font-weight: 700;
            letter-spacing: 2px;
        }

        .welcome-container {
            text-align: center;
            background: #ffffff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            margin-top: -100px; /* Adjusts to overlap header */
            z-index: 1;
        }

        .welcome-container h2 {
            color: #333;
            font-weight: 700;
            margin-bottom: 30px;
            font-size: 32px;
        }

        .button {
            display: inline-block;
            margin: 10px 15px;
            padding: 15px 35px;
            font-size: 18px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 50px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .button:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }

        .footer {
            text-align: center;
            padding: 20px;
            background: #007bff;
            color: white;
            width: 100%;
            position: absolute;
            bottom: 0;
            font-size: 14px;
        }

        .footer a {
            color: white;
            text-decoration: none;
        }

        .footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Banking Application</h1>
    </div>

    <div class="welcome-container">
        <h2>Welcome to Your Banking Portal</h2>
        <a class="button" href="adminlogin.jsp">Admin Login</a>
        <a class="button" href="login.jsp">Customer Login</a>
        <a class="button" href="AboutUs.jsp">About Us</a>
    </div>

    <div class="footer">
        <p>&copy; 2024 Banking Application | <a href="privacy.jsp">Privacy Policy</a> | <a href="terms.jsp">Terms of Service</a></p>
    </div>
</body>
</html>