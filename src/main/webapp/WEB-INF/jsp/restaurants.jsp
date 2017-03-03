<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Restaurants list</title>

</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meal list</h2>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Restaurant</th>
            <th>Menu</th>
            <th>Rating</th>
            <th></th>
        </tr>
        </thead>

        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="ru.fatvinyl.votesystem.model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td>
                <c:forEach items="${restaurant.getmealList()}" var="meal">
                    ${meal.name}... ${meal.price}р<br />
                </c:forEach>

                </td>
                <td>${restaurant.amountVotes}</td>
                <td></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>