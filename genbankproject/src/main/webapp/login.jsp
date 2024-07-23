<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        
        .container {
            width: 100%;
            max-width: 400px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        
        .header {
            background: url('path/to/your/header-image.jpg') no-repeat center center;
            background-size: cover;
            height: 150px;
            border-radius: 10px 10px 0 0;
        }
        
        .header img {
            width: 100%;
            height: auto;
            border-radius: 10px 10px 0 0;
        }
        
        .container h1 {
            margin-top: 20px;
            font-size: 24px;
            color: #333;
        }
        
        .container label {
            display: block;
            margin-bottom: 10px;
            text-align: left;
            color: #666;
        }
        
        .container input[type="text"], 
        .container input[type="password"] {
            width: calc(100% - 22px);
            height: 40px;
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        
        .container input[type="submit"] {
            width: 100%;
            height: 45px;
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        
        .container input[type="submit"]:hover {
            background-color: #0056b3;
        }
        
        .error {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <!-- You can place an image here if you want to -->
            <!-- <img src="path/to/your/header-image.jpg" alt="Header Image"> -->
        </div>
        <h1>Customer Login</h1>
        <form action="LoginServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required/>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required/>
            
            <input type="submit" value="Login"/>
            
            <% if (request.getAttribute("error") != null) { %>
                <p class="error"><%= request.getAttribute("error") %></p>
            <% } %>
        </form>
    </div>
</body>
</html>
