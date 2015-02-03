package br.com.sidlar.dailyquiz.domain.resposta;

import br.com.sidlar.dailyquiz.domain.questionario.Questao;
import br.com.sidlar.dailyquiz.domain.questionario.Questionario;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * D.T.O.
 * @author Admilson
 */
public class CartaoResposta {
    private Long idQuestionario;
    private String nomeQuestionario;
    private List<ItemCartaoResposta> itensCartao;

    public CartaoResposta() {
    }

    public CartaoResposta(Questionario questionario) {
        this.nomeQuestionario = questionario.getNome();
        this.idQuestionario = questionario.getId();
        this.itensCartao = criaItensCartaoResposta(questionario);
    }

    private List<ItemCartaoResposta> criaItensCartaoResposta(Questionario questionario) {
        List<ItemCartaoResposta> itensQuestoes = Lists.newArrayList();
        for (Questao questao : questionario.getQuestoes()) {
            itensQuestoes.add(new ItemCartaoResposta(questao.getId(), questao.getEnunciado(), questao.getAlternativas()));
        }
        return itensQuestoes;
    }

    public String getNomeQuestionario() {
        return nomeQuestionario;
    }

    public void setNomeQuestionario(String nomeQuestionario) {
        this.nomeQuestionario = nomeQuestionario;
    }

    public Long getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(Long idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public List<ItemCartaoResposta> getItensCartao() {
        return itensCartao;
    }

    public void setItensCartao(List<ItemCartaoResposta> itensCartao) {
        this.itensCartao = itensCartao;
    }

}
