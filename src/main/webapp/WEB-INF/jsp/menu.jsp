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
                            <a class="btn btn-success btn-circle glyphicon glyphicon-ok" type="button"
                               onclick="filterTable()"></a>
                            <a class="btn btn-success btn-circle glyphicon glyphicon-plus"
                               onclick="openModal('<spring:message code="menu.editor"/>')"></a>
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
                <h3 class="modal-title" id="modalTitle"></h3>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="detailsForm">
                <input type="hidden" id="id" name="id">
                <div class="form-group">
                    <div class="col-xs-7">
                        <input class="form-control" name="filt" list="dl_continents" id="filt"
                               placeholder="<spring:message code="restaurant.add_or_choose"/>"/>
                        <datalist id="dl_continents">
                        </datalist>
                    </div>
                    <a class="btn btn-success btn-circle glyphicon glyphicon-ok" type="button"
                       onclick="getRestaurant()"></a>
                </div>
            </div>

            <div class="modal-footer">
                <div id="dishes"></div>
                </form:form>
            </div>


        </div>
    </div>
</div>
</body>
</html>
