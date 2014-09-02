<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
</head>
<body>

    <c:url var="login" value='/EfetuaLogin'/>
    <form method="post" action="${login}">
        <c:if test="${not empty mensagemErro}">
            <div class="alert alert-danger" style="margin-bottom: 1em;">${mensagemErro}</div>
        </c:if>
        <h2>Login</h2>
        <input type="text" name="username" placeholder="username" required autofocus>
        <input type="password" name="senha" placeholder="senha" required>
        <button type="submit">Entrar</button>
    </form>

</body>
</html>