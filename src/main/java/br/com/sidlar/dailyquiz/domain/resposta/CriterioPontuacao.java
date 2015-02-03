package br.com.sidlar.dailyquiz.domain.resposta;

import br.com.sidlar.dailyquiz.domain.questionario.Questao;
import br.com.sidlar.dailyquiz.domain.questionario.QuestaoRepository;

/**
 * Calcula a pontuação atingida para cada cartão resposta.
 *
 * @author Admilson
 */
public class CriterioPontuacao {

    private QuestaoRepository questaoRepository;

    public CriterioPontuacao(QuestaoRepository questaoRepository) {
        this.questaoRepository = questaoRepository;
    }

    public int calculaPontuacao(CartaoResposta cartaoResposta){
        int pontuacao = 0;
        for (ItemCartaoResposta itemCartaoResposta : cartaoResposta.getItensCartao()) {
            if (acertouResposta(itemCartaoResposta)){
                pontuacao++;
            }
        }
        return pontuacao;
    }

    private boolean acertouResposta(ItemCartaoResposta itemCartaoResposta) {
        Questao questao = questaoRepository.buscaPorId(itemCartaoResposta.getIdQuestao());
        return questao.getAlternativaCorreta().getId().equals(itemCartaoResposta.getIdAlternativaEscolhida());
    }

}
