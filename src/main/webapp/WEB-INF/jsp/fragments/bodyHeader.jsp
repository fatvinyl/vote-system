<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="dishes" class="navbar-header navbar-brand"><spring:message code="app.title"/></a>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form:form class="navbar-form " action="logout" method="post">
                        <%--отрисовывается только если пользователь isAuthenticated--%>
                        <sec:authorize access="isAuthenticated()">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="btn btn-default btn-circle" href="users"><spring:message code="users.title"/></a>
                                <a class="btn btn-default btn-circle" href="menu"><spring:message code="restaurant.edit"/></a>
                            </sec:authorize>
                            <%--если обычный юзер, то разрешаем ему редактировать свой профайл и делать logout--%>
                            <a class="btn btn-default btn-circle" role="button" href="profile">${userTo.name} <spring:message code="app.profile"/></a>
                            <button class="btn btn-danger btn-circle glyphicon glyphicon-log-out" type="submit"></button>
                        </sec:authorize>
                    </form:form>
                </li>
                <%--<jsp:include page="lang.jsp"/>--%>
            </ul>
        </div>
    </div>
</div>

