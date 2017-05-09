<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/notyUtil.js" defer></script>
<script type="text/javascript" src="resources/js/restaurantDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

    <div class="container">
                <table class="table table-striped table-hover" id="datatable">
                    <thead>
                    <tr class="bg-success" >
                        <th></th>
                        <th><spring:message code="restaurant.name"/></th>
                        <th><spring:message code="restaurant.menu"/></th>
                        <th><spring:message code="restaurant.price"/></th>
                        <th><spring:message code="restaurant.rating"/></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
        </div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
