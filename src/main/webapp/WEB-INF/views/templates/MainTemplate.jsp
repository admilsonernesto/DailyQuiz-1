<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">

    <title>DailyQuiz - <decorator:title/></title>

    <script src="<c:url value="/resources/libs/jquery/2_1_1/jquery-2.1.1.min.js" />"></script>

    <link rel="stylesheet" href="<c:url value="/resources/libs/bootstrap/3_2_2/css/bootstrap.min.css"/>">
    <script src="<c:url value="/resources/libs/bootstrap/3_2_2/js/bootstrap.min.js" />"></script>

    <style>
        body {
            min-height: 2000px;
            padding-top: 70px;
        }
    </style>

    <decorator:head/>
</head>

<body>

<jsp:include page="includes/Menu.jsp"/>

    <div class="container">
        <decorator:body/>
    </div>

</body>
</html>