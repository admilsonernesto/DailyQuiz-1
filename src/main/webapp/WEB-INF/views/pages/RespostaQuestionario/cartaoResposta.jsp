<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questionario</title>
</head>
<body>

<form:form modelAttribute="cartaoResposta" method="post" action="/RespostaQuestionario">
    <c:if test="${not empty mensagemErro}">
        <div class="alert alert-danger" style="margin-bottom: 1em;">${mensagemErro}</div>
    </c:if>

    <h3>Questionário ${cartaoResposta.nomeQuestionario}</h3>
    <form:hidden path="nomeQuestionario"/>
    <form:hidden path="idQuestionario"/>
    <ol>
        <c:forEach var="itemCartao" items="${cartaoResposta.itensCartao}" varStatus="status" >
            <form:hidden path="itensCartao[${status.index}].idQuestao"/>
            <li>
                <form:hidden path="itensCartao[${status.index}].enunciado"/>
                <h4 class="list-group-item disabled">${itemCartao.enunciado}</h4>
                <ol type="A">
                    <div class="list-group">
                    <form:radiobuttons cssStyle="margin-left:6em; margin-right:1em"
                                       id="alternativaQuestao${itemCartao.idQuestao}"
                                       path="itensCartao[${status.index}].idAlternativaEscolhida"
                                       items="${itemCartao.alternativas}"
                                       itemValue="id"
                                       element="li"
                                       itemLabel="descricao"/>
                    </div>
                </ol>
            </li>
        </c:forEach>
    </ol>


    <div style="margin: 15px;" class="form-actions">
        <button style="font-weight: bold;" class="btn btn-primary" type="submit">Salvar Reposta</button>
    </div>
    <a class="glyphicon glyphicon-hand-left" style="margin-left: 2em" href="<c:url value='/'/>"> Voltar</a>
</form:form>

</body>
</html>
