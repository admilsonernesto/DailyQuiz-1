<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
</head>
<body>

    <form name='loginForm' action="<c:url value='j_spring_security_check'/>" method="POST">
        <c:if test="${not empty mensagemErro}">
            <div class="alert alert-danger" style="margin-bottom: 1em;">${mensagemErro}</div>
        </c:if>
        <c:if test="${not empty mensagemSucesso}">
            <div class="alert alert-success" style="margin-bottom: 1em;">${mensagemSucesso}</div>
        </c:if>
        <h2>Login</h2>
        <input type="email" name="j_username" placeholder="email" required autofocus>
        <input type="password" name="j_password" placeholder="senha" required>
        <button name="submit" type="submit" value="Login" >Entrar</button>
        <a style="margin-left: 2em" href="<c:url value='/CadastroMembro'/>">Novo Membro</a>
    </form>

</body>
</html>