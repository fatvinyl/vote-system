<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div>
    <%--<jsp:useBean id="authorizedUser" scope="page" type="ru.fatvinyl.votesystem.AuthorizedUser"/>--%>
    <%--<jsp:useBean id="user" scope="page" type="ru.fatvinyl.votesystem.model.User"/>--%>
    <c:if test="${user.getVote().getId() != null}">
    <h4>
        Вы проголосовали за ресторан 4 &nbsp &nbsp

        <a class="btn btn-danger" role="button" onclick="add('<spring:message code="users.add"/>')"> <spring:message code="restaurant.deleteVote"/></a>
        <h4>
            </c:if>
</div>