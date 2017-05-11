<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <div class="container">
        <nav class="navbar navbar-inverse" style="border-radius:5px;">
            <div class="container">
                <div class="navbar-header">
                    <span class="navbar-brand"><spring:message code="app.title"/></span>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <sec:authorize access="isAuthenticated()">
                            <li><a href="/"><span class="glyphicon glyphicon-ok-sign" style="color:darkgrey"></span>
                                <spring:message code="restaurant.vote"/></a></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="menu"><span class="glyphicon glyphicon-pencil" style="color:darkgrey"></span>
                                    <spring:message code="app.menu"/></a></li>
                            <li><a href="users"><span class="glyphicon glyphicon-pencil" style="color:darkgrey"></span>
                                <spring:message code="app.users"/></a></li>
                        </sec:authorize>
                        <li><a href="profile"> <span class="glyphicon glyphicon-user" style="color:darkgrey"></span>
                        ${userTo.name} <spring:message code="app.profile"/></a></li>
                        </sec:authorize>
                    </ul>
                    <ul class="nav navbar-nav pull-right" >
                        <%--отрисовывается только если пользователь isAuthenticated--%>
                            <li>
                            <sec:authorize access="isAuthenticated()">
                            <form:form class="navbar-form " action="logout" method="post">
                            <button class="btn btn-danger btn-circle glyphicon glyphicon-log-out" type="submit"></button>
                            </form:form>
                            </sec:authorize>
                            </li>
                    </ul>
                </div>
            </div>
        </nav>

    </div>
