<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header navbar-brand"><spring:message code="app.title"/></div>

            <div class="navbar-collapse collapse" id="navbar-collapse-2">
                <ul class="nav navbar-nav pull-right">
                    <li>
                        <a data-toggle="collapse" href="#nav-collapse2" aria-expanded="false"
                           aria-controls="nav-collapse2"><spring:message code="app.enter"/></a>
                    </li>
                    <li>
                        <a href="register"><spring:message code="app.register"/></a>
                    </li>
                </ul>
                <div class="collapse nav navbar-nav nav-collapse" id="nav-collapse2">
                    <form:form class="navbar-form navbar-right form-inline" role="form" action="spring_security_check"
                               method="post">
                        <div class="form-group">
                            <input type="text" placeholder="<spring:message code="users.password"/>"
                                   class="form-control" name="username">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="<spring:message code="users.email"/>"
                                   class="form-control" name="password">
                        </div>
                        <button type="submit" class="btn btn-success btn-circle glyphicon glyphicon-log-in"></button>
                    </form:form>
                </div>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->
</div>

<div class="container">
    <c:if test="${param.error}">
        <div class="error">
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
    <c:if test="${not empty param.message}">
        <div class="message">
            <spring:message code="${param.message}"/>
        </div>
    </c:if>
</div>
<div class="container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <span class="glyphicon glyphicon-pushpin" style="color:green">&nbsp</span><strong>Тестовые данные:</strong>
        </div>
        <div class="panel-body">
            <p>
                <span class="glyphicon glyphicon-user" style="color:black"></span>
                <strong>Логин : пароль</strong><br>
                admin@mail.com : admin<br>
                user1@mail.com : password_1<br>
                user2@mail.com : password_2<br>
            </p>
            <p>
                <a class="btn btn-success btn-circle" href="updateDb">
                    <span class="glyphicon glyphicon-refresh" style="color:white">&nbsp</span>Обновить базу данных
                </a> - Рекомендуется обновить для заполнения базы тестовыми данными на текущую дату.
            </p>
            <p>
                <span class="glyphicon glyphicon-time" style="color:black"></span>
                <span> <strong>Время окончания голосования:<br/></strong></span>
                <form:form id="changeDeadline" action="/changeDeadline" method="post">
                    <input class="form-control" name="deadline" id="deadline" value="${deadline}">
                </form:form>
            </p>
        </div>
    </div>
</div>

<div class="container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <span class="glyphicon glyphicon-pushpin" style="color:green">&nbsp</span> <strong>Описание:</strong>
        </div>
        <div class="panel-body">
            <p><a href="https://github.com/fatvinyl/vote-system">Приложение</a>
                представляет собой систему голосования за ресторан, в котором пользователь хотел бы пообедать.<br/>
                Голосование производится ежедневно до 11:00 (для тестирования имеется возможность изменить время).<br/>
                Представители ресторанов заранее присылают администратору меню дня,состоящее из 3 - 5 блюд, которые он
                сохраняет в базу данных.<br/>
                В приложении имеется регистрация/авторизация и интерфейс на основе ролей (USER, ADMIN). Администратор
                может создавать/редактировать/удалять пользователей, рестораны и меню, а пользователь - управлять своим
                профилем и голосовать за понравившийся ресторан.<br/>
                Функциональность приложения реализована через UI (по AJAX) и по REST интерфейсу с базовой
                авторизацией.</p>
        </div>
    </div>
</div>
</div>

<div class="container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <span class="glyphicon glyphicon-pushpin" style="color:green">&nbsp</span> <strong>Стек технологий:<br/></strong>
        </div>
        <div class="panel-body">
            Spring Security, Spring MVC, Spring Security Test, Hibernate ORM, Hibernate Validator,
            SLF4J, Json Jackson, JSP, JSTL, Apache Tomcat, WebJars, DataTables plugin, PostgreSQL, JUnit, Hamcrest,
            jQuery, jQuery notification, Bootstrap.
        </div>
    </div>
</div>
</div>

<jsp:include page="fragments/footer.jsp"/>

<script>
    $(document).ready(function () {
        $("#deadline").datetimepicker({
            datepicker: false,
            format: 'H:i',
            onSelectTime: function () {
                $("#changeDeadline").submit();
            }
        });
    });

</script>
</body>
</html>