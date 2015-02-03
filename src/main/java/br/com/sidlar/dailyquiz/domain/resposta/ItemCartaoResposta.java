package br.com.sidlar.dailyquiz.domain.resposta;

import br.com.sidlar.dailyquiz.domain.questionario.Alternativa;

import java.util.List;

/**
 * D.T.O.
 * @author Admilson
 */
public class ItemCartaoResposta {
    private Long idQuestao;
    private Long idAlternativaEscolhida;
    private String enunciado;
    private List<Alternativa> alternativas;

    public ItemCartaoResposta() {
    }

    public ItemCartaoResposta(Long idQuestao, String enunciado, List<Alternativa> alternativas) {
        this.idQuestao = idQuestao;
        this.enunciado = enunciado;
        this.alternativas = alternativas;
    }

    public Long getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Long idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Long getIdAlternativaEscolhida() {
        return idAlternativaEscolhida;
    }

    public void setIdAlternativaEscolhida(Long idAlternativaEscolhida) {
        this.idAlternativaEscolhida = idAlternativaEscolhida;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }
}
