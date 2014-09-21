<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
</head>
<body>

    <c:if test="${not empty mensagemParabenizacao}">
        <div class="alert alert-info">${mensagemParabenizacao}</div>
    </c:if>

    <h3>Olá, ${sessionScope.membroAutenticado.membro.nome}!</h3>
</body>
</html>