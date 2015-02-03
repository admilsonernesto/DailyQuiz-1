<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%--@elvariable id="respostaQuestionario" type="br.com.sidlar.dailyquiz.domain.resposta.RespostaQuestionario"--%>
<html>
<head>
    <title>Visualização</title>
</head>
<body>
    <h3>Questionário ${respostaQuestionario.questionario.nome}</h3>

    <span><strong>Data Resposta :</strong> <joda:format value="${respostaQuestionario.data}" pattern="dd/MM/yyyy"/></span>
    <span style="margin-left: 2em"><strong>Pontuação :</strong> ${respostaQuestionario.pontuacao}</span>
    <ol>
        <c:forEach var="respostaQuestao" items="${respostaQuestionario.respostaQuestoes}" varStatus="status" >
            <li>
                <h4 class="list-group-item disabled">${respostaQuestao.questao.enunciado}</h4>
                <c:if test="${respostaQuestao.alternativaEscolhida.id == respostaQuestao.questao.alternativaCorreta.id}">
                    <span style="color: green" >Parabéns você acertou!!!</span>
                </c:if>
                <c:if test="${respostaQuestao.alternativaEscolhida.id != respostaQuestao.questao.alternativaCorreta.id}">
                    <span style="color: red" >Você errou!!!</span>
                </c:if>
                <ol type="A">
                    <c:forEach var="alternativa" items="${respostaQuestao.questao.alternativas}">
                        <li>
                            <c:set var="classAlternativa" value="${alternativa.id == respostaQuestao.questao.alternativaCorreta.id?'glyphicon glyphicon-ok right':''}"/>
                            <c:set var="ticado" value="${respostaQuestao.alternativaEscolhida.id == alternativa.id?'checked':''}"/>
                            <input type="radio" name="alternativa${respostaQuestao.questao.id}"  disabled="disabled" ${ticado}>
                                ${alternativa.descricao}<span class="${classAlternativa}" style="color: green; margin-left: 1em"></span>
                            </input>
                        </li>
                    </c:forEach>
                </ol>
            </li>
        </c:forEach>
    </ol>

    <a class="glyphicon glyphicon-hand-left" style="margin-left: 2em" href="<c:url value='/'/>"> Home</a>
</body>
</html>
