<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <div class="container">
        <nav class="navbar navbar-inverse" style="border-radius:5px;">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/"><spring:message code="app.title"/></a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="menu"><spring:message code="app.menu"/></a></li>
                            <li><a href="users"><spring:message code="app.users"/></a></li>
                        </sec:authorize>
                        <li><a href="profile"> ${userTo.name} <spring:message code="app.profile"/></a></li>
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
