<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <span class="head-txt"><h3><spring:message code="restaurant.edit"/></h3></span>

            <div class="view-box">
                <div class="row">
                    <div class="text-right">
                        <form:form class="navbar-form navbar-left" id="filter">
                            <div class="form-group">
                            <div class="col-xs-9">
                                <input class="form-control" name="menuDate" id="menuDate">
                            </div>
                            </div>
                            <%--<div class="control-label col-sm-3">--%>
                                <%--<input class="form-control" placeholder="Date" id="menuDate">--%>
                            <%--</div>--%>
                            <a class="btn btn-success btn-circle glyphicon glyphicon-ok" type="button" onclick="filterTable()"></a>
                            <a class="btn btn-success btn-circle glyphicon glyphicon-plus" onclick="add('<spring:message code="users.add"/>')"></a>
                        </form:form>

                        </div>
                    </div>
                </div>
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><spring:message code="restaurant.name"/></th>
                        <th><spring:message code="restaurant.menu"/></th>
                        <th><spring:message code="restaurant.price"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
<h2 class="modal-title" id="modalTitle"></h2>
</div>
<div class="modal-body">
<form:form class="form-horizontal" id="detailsForm">
<input type="hidden" id="id" name="id">

<div class="form-group">
<label for="name" class="control-label col-xs-3"><spring:message code="users.name"/></label>

<div class="col-xs-9">
<input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="users.name"/>">
</div>
</div>

<div class="form-group">
<label for="email" class="control-label col-xs-3"><spring:message code="users.email"/></label>

<div class="col-xs-9">
<input type="email" class="form-control" id="email" name="email" placeholder="<spring:message code="users.email"/>">
</div>
</div>

<div class="form-group">
<label for="password" class="control-label col-xs-3"><spring:message code="users.password"/></label>

<div class="col-xs-9">
<input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="users.password"/>">
</div>
</div>

<div class="form-group">
<div class="col-xs-offset-3 col-xs-9">
<button type="button" onclick="save()" class="btn btn-success">
<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
</button>
</div>
</div>
</form:form>
</div>
</div>
</div>
</div>
</body>
</html>
