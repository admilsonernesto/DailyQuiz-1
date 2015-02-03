<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<html>
<head>
</head>
<body>

    <c:if test="${not empty dashboard.mensagemParabenizacao}">
        <div class="alert alert-info">${dashboard.mensagemParabenizacao}</div>
    </c:if>

    <h3>Olá, <security:authentication property="principal.nome"/>!</h3>

    <div class="col-lg-6">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title"><span class="glyphicon glyphicon-edit"> Questionários Disponíveis</span></h3>
            </div>
            <div class="panel-body">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th></th>
                            <th style="text-align: center">Questionário</th>
                            <th style="text-align: center">Total Questões</th>
                            <th style="text-align: center">Data Publicação</th>
                            <th style="text-align: center">Tempo para Expirar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="questionario" items="${dashboard.questionariosDisponiveis}" varStatus="st">
                        <tr>
                            <td style="text-align: center">${st.index+1}</td>
                            <td style="text-align: center">
                                <a href="<c:url value='/RespostaQuestionario?idQuestionario=${questionario.id}'/>">${questionario.nome}</a>
                            </td>
                            <td style="text-align: center">${questionario.totalQuestoes}</td>
                            <td style="text-align: center"><joda:format value="${questionario.dataPublicacao}" pattern="dd/MM/yyyy"/></td>
                            <td style="text-align: center">${questionario.tempoParaExpirar}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
    <div class="col-lg-1"></div>

    <div class="col-lg-4">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><span class="glyphicon glyphicon-list-alt"> Ranking</span></h3>
            </div>
            <div class="panel-body">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th style="text-align: center">Posição</th>
                        <th style="text-align: center">Participante</th>
                        <th style="text-align: center">Total Acertos</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="classificacao" items="${dashboard.classificacoesRanking}" varStatus="st">
                        <tr>
                            <td style="text-align: center">${classificacao.posicao}</td>
                            <td style="text-align: center">${classificacao.participante.nome}</td>
                            <td style="text-align: center">${classificacao.totalAcertos}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title"><span class="glyphicon glyphicon-check"> Questionários Respondidos</span></h3>
            </div>
            <div class="panel-body">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th></th>
                        <th style="text-align: center">Questionário</th>
                        <th style="text-align: center">Acertos / Questões</th>
                        <th style="text-align: center">Data Resposta</th>
                        <th style="text-align: center">Posição</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="questionario" items="${dashboard.questionariosRespondidos}" varStatus="st">
                        <tr>
                            <td style="text-align: center">${st.index+1}</td>
                            <td style="text-align: center">
                                <a href="<c:url value='/VisualizaResposta?idRespostaQuestionario=${questionario.idRespostaQuestionario}'/>">${questionario.nome}</a>
                            </td>
                            <td style="text-align: center">${questionario.totalAcertos} / ${questionario.totalQuestoes}</td>
                            <td style="text-align: center"><joda:format value="${questionario.dataResposta}" pattern="dd/MM/yyyy"/></td>
                            <td style="text-align: center">${questionario.posicaoRanking}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>


</body>
</html>