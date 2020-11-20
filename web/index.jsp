<%--
  Created by IntelliJ IDEA.
  User: Nikolay Shvedov
  Date: 22.10.2020
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="IndexServlet" method="post">
    Введите размер таблицы для игры в крестики-нолики (например, введите 3 для 3x3):
    <br /><br />
    Ввести : <input type="number" name="tableSize" placeholder="Размерность таблицы" />
    <input type="submit" value="Начать" />
    <br /><br />
</form>
</body>
</html>
