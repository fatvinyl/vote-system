<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="footer">
    <div class="container">
        <spring:message code="app.footer"/>
    </div>
</div>

<script type="text/javascript">
    var i18n = [];

    <c:forEach var='key' items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.status",
    "users.edit", "common.search", "common.show", "common.info", "common.loading", "common.first", "common.last",
    "common.next", "common.previous", "common.voted", "common.vote_deleted", "restaurant.saved", "confirm.restaurant.create",
    "confirm.vote.add", "confirm.vote.delete", "confirm.dish.add", "confirm.dish.edit", "confirm.dish.delete", "confirm.restaurant.delete",
    "restaurant.title"}%>'>
    i18n['${key}'] = '<spring:message code="${key}"/>';
    </c:forEach>
</script>
