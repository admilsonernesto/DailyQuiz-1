package br.com.sidlar.dailyquiz.domain.resposta;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import br.com.sidlar.dailyquiz.domain.questionario.*;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Fabrica um questionário com os dados do cartão resposta
 * @author Admilson
 */
@Component
public class RespostaQuestionarioFactory {
    @Autowired
    private QuestionarioRepository questionarioRepository;

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;


    public RespostaQuestionario cria(CartaoResposta cartaoResposta, Membro membro){
        List<RespostaQuestao> respostaQuestoes = criaRespostasQuestoes(cartaoResposta);
        Questionario questionario = questionarioRepository.buscaPorId(cartaoResposta.getIdQuestionario());
        int pontuacao = new CriterioPontuacao(questaoRepository).calculaPontuacao(cartaoResposta);

        return new RespostaQuestionario(membro,questionario,respostaQuestoes, DateTime.now(), pontuacao);
    }

    private List<RespostaQuestao> criaRespostasQuestoes(CartaoResposta cartaoResposta) {
        List<RespostaQuestao> respostaQuestoes = Lists.newArrayList();
        for (ItemCartaoResposta itemCartaoResposta : cartaoResposta.getItensCartao()) {
            Questao questao = questaoRepository.buscaPorId(itemCartaoResposta.getIdQuestao());
            Alternativa alternativaEscolhida = alternativaRepository.buscaPorId(itemCartaoResposta.getIdAlternativaEscolhida());
            respostaQuestoes.add(new RespostaQuestao(questao, alternativaEscolhida));
        }
        return respostaQuestoes;
    }
}
