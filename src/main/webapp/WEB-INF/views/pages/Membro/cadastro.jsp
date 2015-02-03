<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<head>
    <script type="text/javascript">
        $(document).ready(function() {
            document.getElementById('nome').focus();
        });

        function limparTodosCampos(){
            document.getElementById('nome').value ="";
            document.getElementById('dataNascimento').value ="";
            document.getElementById('email').value ="";
            document.getElementById('senha').value ="";
            document.getElementById('confirmacaoSenha').value ="";
            document.getElementById('nome').focus();
        }
    </script>

    <style type="text/css">
        .error {
            color: #CC0000;
            font-weight: bold;
            text-align: center;
            font-size: 1.0em;
            padding: 10px;
        }
    </style>
</head>
<body>

    <form:form modelAttribute="form" method="post" action="/CadastroMembro">
        <c:if test="${not empty mensagemErro}">
            <div class="alert alert-danger" style="margin-bottom: 1em;">${mensagemErro}</div>
        </c:if>
        <fieldset>
            <legend>Novo Membro</legend>
            <table>
                <tr>
                    <td><label for="nome">Nome:</label></td>
                    <td><form:input path="nome" id="nome" size="30" maxlength="50" tabindex="1"/>  <form:errors path="nome" cssClass="error"/></td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td><label for="nome">Data Nascimento:</label></td>
                    <td><form:input type="date" path="dataNascimento" id="dataNascimento" size="10" maxlength="50" tabindex="2"/>  <form:errors path="dataNascimento" cssClass="error"/></td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td><label for="email">E-mail:</label></td>
                    <td><form:input type="email" path="email" id="email" size="30" maxlength="50" tabindex="3"/> <form:errors path="email" cssClass="error"/></td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td><label for="senha">Senha:</label></td>
                    <td><form:password path="senha" id="senha" size="20" maxlength="10" tabindex="4"/> <form:errors path="senha" cssClass="error"/></td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td><label for="confirmacaoSenha">Confirmação Senha:</label></td>
                    <td><form:password path="confirmacaoSenha" id="confirmacaoSenha" size="20" maxlength="10" tabindex="5"/> <form:errors path="confirmacaoSenha" cssClass="error"/></td>
                </tr>
            </table>
        </fieldset>

        <div style="margin: 15px;" class="form-actions">
            <button style="font-weight: bold;" class="btn btn-primary" type="submit">Salvar Membro</button>
            <button style="font-weight: bold;" class="btn btn-warning" type="button" onclick="limparTodosCampos()"> <i class="icon-ok icon-white"></i>Limpar</button>
            <c:if test="${not empty mensagemSucesso}">
                <a style="margin-left: 2em" href="<c:url value='/Login'/>">Efetuar Login</a>
            </c:if>
        </div>
    </form:form>

</body>
</html>

