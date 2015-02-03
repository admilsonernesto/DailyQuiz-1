package br.com.sidlar.dailyquiz.domain.dashboard;

import br.com.sidlar.dailyquiz.domain.resposta.RespostaQuestionario;
import org.joda.time.DateTime;

/**
 * D.T.O.
 * @author Admilson
 */
public class QuestionarioRespondido {
    private String nome;
    private Long idRespostaQuestionario;
    private int totalQuestoes;
    private int totalAcertos;
    private DateTime dataResposta;
    private int posicaoRanking;

    public QuestionarioRespondido(RespostaQuestionario resposta, int posicaoRanking) {
        this.nome = resposta.getQuestionario().getNome();
        this.idRespostaQuestionario = resposta.getId();
        this.totalQuestoes = resposta.getTotalQuestoes();
        this.totalAcertos = resposta.getTotalAcertos();
        this.dataResposta = resposta.getData();
        this.posicaoRanking = posicaoRanking;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdRespostaQuestionario() {
        return idRespostaQuestionario;
    }

    public int getTotalQuestoes() {
        return totalQuestoes;
    }

    public int getTotalAcertos() {
        return totalAcertos;
    }

    public DateTime getDataResposta() {
        return dataResposta;
    }

    public int getPosicaoRanking() {
        return posicaoRanking;
    }
}
