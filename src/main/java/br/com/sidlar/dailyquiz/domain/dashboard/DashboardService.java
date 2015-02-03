package br.com.sidlar.dailyquiz.domain.dashboard;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import br.com.sidlar.dailyquiz.domain.membro.MembroRepository;
import br.com.sidlar.dailyquiz.domain.questionario.Questionario;
import br.com.sidlar.dailyquiz.domain.questionario.QuestionarioRepository;
import br.com.sidlar.dailyquiz.domain.ranking.ClassificacaoRanking;
import br.com.sidlar.dailyquiz.domain.ranking.ClassificacaoRankingService;
import br.com.sidlar.dailyquiz.domain.resposta.RespostaQuestionario;
import br.com.sidlar.dailyquiz.domain.resposta.RespostaQuestionarioRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Admilson
 */
@Component
public class DashboardService {

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @Autowired
    private RespostaQuestionarioRepository respostaQuestionarioRepository;

    @Autowired
    private ClassificacaoRankingService classificacaoRankingService;

    public Dashboard criaDashboard(Membro membro) {
        return new Dashboard(membro,
                buscaQuestionariosDisponiveis(membro),
                buscaQuestionariosRespondidos(membro),
                obtemClassificacoesRankingGeral()
        );
    }

    private List<QuestionarioDisponivel> buscaQuestionariosDisponiveis(Membro membro) {
        return criaQuestionariosDisponiveis(membro);
    }

    private List<QuestionarioDisponivel> criaQuestionariosDisponiveis(Membro membro) {
        List<QuestionarioDisponivel> questionarioDisponivels = Lists.newArrayList();
        List<Questionario> questionariosNaoRespondidos = questionarioRepository.buscaDisponiveis(membro);
        questionariosNaoRespondidos.forEach(q ->
                questionarioDisponivels.add(new QuestionarioDisponivel(q))
        );
        return questionarioDisponivels;
    }

    private List<QuestionarioRespondido> buscaQuestionariosRespondidos(Membro membro) {
        return obtemQuestionariosRespondidos(membro);
    }

    private List<QuestionarioRespondido> obtemQuestionariosRespondidos(Membro membro) {
        List<QuestionarioRespondido> questionariosRespondido = Lists.newArrayList();
        List<RespostaQuestionario> respostasQuestionario = respostaQuestionarioRepository.buscaUltimasCincoRespostas(membro);
        respostasQuestionario.forEach(r -> {
            ClassificacaoRanking classificacao = classificacaoRankingService.criaClassificacaoDoQuestionario(membro, r.getQuestionario().getId());
            questionariosRespondido.add(new QuestionarioRespondido(r, classificacao.getPosicao()));
        });

        return questionariosRespondido;
    }

    private List<ClassificacaoRanking> obtemClassificacoesRankingGeral() {
        return classificacaoRankingService.criaClassificacaoGeral();
    }

}
