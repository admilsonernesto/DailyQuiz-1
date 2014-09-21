<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
</head>
<body>

    <form method="post" action="<c:url value='/Login'/>">
        <c:if test="${not empty mensagemErro}">
            <div class="alert alert-danger" style="margin-bottom: 1em;">${mensagemErro}</div>
        </c:if>
        <h2>Login</h2>
        <input type="email" name="email" placeholder="email" required autofocus>
        <input type="password" name="senha" placeholder="senha" required>
        <button type="submit">Entrar</button> <a style="margin-left: 2em" href="<c:url value='/CadastroMembro'/>">Novo Membro</a>
    </form>

</body>
</html>