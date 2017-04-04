<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/restaurantDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <br>
            <%--<c:if test="${voteId != null}"><h3> Вы проголосовали за ресторан 4<h3></c:if>--%>

                <%--<h3><spring:message code="restaurant.title"/><h3>--%>

            <div class="view-box">
                <%--<table class="table table-striped">--%>
                    <%--<thead>--%>
                    <%--<tr>--%>
                        <%--<th>Restaurant</th>--%>
                        <%--<th>Menu</th>--%>
                        <%--<th>Price</th>--%>
                        <%--<th>Rating</th>--%>
                        <%--<th></th>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<c:forEach items="${restaurants}" var="restaurant">--%>
                        <%--<jsp:useBean id="restaurant" scope="page" type="ru.fatvinyl.votesystem.to.RestaurantWithVote"/>--%>
                        <%--<tr>--%>
                            <%--<td>${restaurant.name}</td>--%>
                            <%--<td>--%>
                                <%--<c:forEach items="${restaurant.menu}" var="dish">--%>
                            <%--${dish.name} <br/>--%>
                                <%--</c:forEach>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--<c:forEach items="${restaurant.menu}" var="dish">--%>
                                    <%--${dish.price} <br/>--%>
                                <%--</c:forEach>--%>
                            <%--</td>--%>
                            <%--<td>${restaurant.vote.amount}</td>--%>
                            <%--<td><a class="container">--%>
                                <%--<span class="btn btn-success" aria-hidden="true">Vote</span>--%>
                            <%--</a></td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>
                <%--</table>--%>
                    <table class="table table-striped display" id="datatable">
                        <thead>
                        <tr>
                            <th><spring:message code="restaurant.name"/></th>
                            <th><spring:message code="restaurant.menu"/></th>
                            <th><spring:message code="restaurant.price"/></th>
                            <th><spring:message code="restaurant.rating"/></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
            </div>
        </div>
    </div>
</div>
<%--<jsp:include page="fragments/footer.jsp"/>--%>
</body>
</html>
