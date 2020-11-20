<%--
  Created by IntelliJ IDEA.
  User: Nikolay Shvedov
  Date: 22.10.2020
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.game.tic_tac_toe.servlet.Players" %>
<jsp:useBean id="gameBean" scope="session" class="com.game.tic_tac_toe.servlet.Players" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<c:set var="ROWS" value="<%= Players.ROWS %>" scope="request" />
<c:set var="COLS" value="<%= Players.COLS %>" scope="request" />
<c:set var="TABLESIZE" value="${sessionScope.tableSize}"  scope="request" />

<body>
<h1>Крестики нолики</h1>

<form action="GameServlet" method="post">
    Текущий игрок :
    <c:choose>
        <c:when test="${currentPlayer == '1'}">
            <b>USER1</b>
            <input type="hidden" name="currentPlayer" value="${currentPlayer}"  />
        </c:when>
        <c:when test="${currentPlayer == '2'}">
            <b>USER2</b>
            <input type="hidden" name="currentPlayer" value="${currentPlayer}"  />
        </c:when>
        <c:otherwise>
            <b>USER1</b>
            <input type="hidden" name="currentPlayer" value="1"  />
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${status == '1'}">
            <br /><br />
            Статус игры : <b>DRAW</b>
        </c:when>
        <c:when test="${status == '2'}">
            <br /><br />
            Статус игры : <b>USER1 WON</b>
        </c:when>
        <c:when test="${status == '3'}">
            <br /><br />
            Статус игры : <b>USER2 WON</b>
        </c:when>
    </c:choose>

    <br /><br />
    Ввести : <input type="number" name="row" placeholder="строка" /> <input type="number" name="col" placeholder="столбец" /> <input type="submit" value="Сходить" />
    <br /><br />
    <input type="hidden" name="tableSize" value="${TABLESIZE }"  />
</form>

<table border="1">
    <c:forEach var = "i" begin = "1" end = "${TABLESIZE}">
        <tr>
            <c:forEach var = "j" begin = "1" end = "${TABLESIZE}">
                <td>
                    <c:choose>
                        <c:when test="${gameBean.pboard[i-1][j-1] == '1'}">
                            <img src="image/state_X.png" alt="X"/> <%-- <c:out value="${gameBean.pboard[i-1][j-1]}"></c:out> --%>
                        </c:when>
                        <c:when test="${gameBean.pboard[i-1][j-1] == '2'}">
                            <img src="image/state_O.png" alt="O"/> <%-- <c:out value="${gameBean.pboard[i-1][j-1]}"></c:out> --%>
                        </c:when>
                        <c:otherwise>
                            <img src="image/state_NULL.png" alt="null"/> <%-- <c:out value="${gameBean.pboard[i-1][j-1]}"></c:out> --%>
                        </c:otherwise>
                    </c:choose>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
