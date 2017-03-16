<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3>Vote System</h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                </a>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Restaurant</th>
                        <th>Menu</th>
                        <th>Rating</th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach items="${restaurants}" var="restaurant">
                        <jsp:useBean id="restaurant" scope="page" type="ru.fatvinyl.votesystem.to.RestaurantWithVote"/>
                        <tr>
                            <td>${restaurant.name}</td>
                            <td>
                                <c:forEach items="${restaurant.menu}" var="dish">
                                    ${dish.name}... ${dish.price}р<br />
                                </c:forEach>
                            </td>
                            <td>${restaurant.amountVotes}</td>
                            <td><a class="btn btn-xs btn-primary">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a></td>
                            <td><a class="btn btn-xs btn-danger">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<%--<jsp:include page="fragments/footer.jsp"/>--%>
</body>
</html>
