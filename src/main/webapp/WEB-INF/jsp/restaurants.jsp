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
        <br>
        <%--<div>--%>

            <%--&lt;%&ndash;<jsp:useBean id="authorizedUser" scope="page" type="ru.fatvinyl.votesystem.AuthorizedUser"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<jsp:useBean id="user" scope="page" type="ru.fatvinyl.votesystem.model.User"/>&ndash;%&gt;--%>
            <%--<c:if test="${user.getVote().getId() != null}">--%>
            <%--<h4>--%>
                <%--Вы проголосовали за ресторан 4 &nbsp &nbsp--%>

                <%--<a class="btn btn-danger" role="button" onclick="add('<spring:message code="users.add"/>')"> <spring:message code="restaurant.deleteVote"/></a>--%>
                <%--<h4>--%>
                    <%--</c:if>--%>
        <%--</div>--%>
        <p id="deleteVote"></p>
        <br>
        <div class="shadow">

            <div class="view-box">
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
