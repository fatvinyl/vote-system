<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/notyUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuDatatables.js" defer></script>
<script type="text/javascript" src="resources/js/datetimepicker.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container">
    <span class="head-txt"> <h3><spring:message code="app.menu"/></h3></span>
    <div class="page-header-cstm">
        <div class="row">
            <br>
            <form:form class="navbar-form navbar-left" id="filter">
                <span class="glyphicon glyphicon-calendar" style="color:black; font-size: 20px"></span>
                <div class="form-group">
                    <div class="col-xs-9">
                        <input class="form-control" name="menuDate" id="menuDate">
                    </div>
                </div>
                <a class="btn btn-success btn-circle glyphicon glyphicon-menu-hamburger"
                   onclick="openModal('<spring:message code="menu.on"/>', 'dish_edit')"></a>
                <span class="dropdown">
                            <a class="btn btn-success btn-circle glyphicon glyphicon-cutlery" type="button"
                               id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a onclick="openModal('<spring:message code="restaurant.create"/>', 'rest_create')"><spring:message
                                        code="restaurant.create"/></a></li>
                                 <li><a onclick="openModal('<spring:message
                                         code="restaurant.edit"/>', 'rest_edit')"><spring:message
                                         code="restaurant.edit"/></a></li>
                            </ul>
                        </span>
            </form:form>
        </div>
    </div>
    <table class="table table-striped table-hover" id="datatable">
        <thead>
        <tr class="bg-success">
            <th></th>
            <th><spring:message code="restaurant.name"/></th>
            <th><spring:message code="restaurant.menu"/></th>
            <th><spring:message code="restaurant.price"/></th>
        </tr>
        </thead>
    </table>
</div>

<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editDishes">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle"></h4>
            </div>

            <div class="modal-body"></div>

            <div class="modal-footer">
                <%--dishes form--%>
                <div id="dishes_form"></div>

                <%--restaurant form--%>
                <div id="restaurant_form"></div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
