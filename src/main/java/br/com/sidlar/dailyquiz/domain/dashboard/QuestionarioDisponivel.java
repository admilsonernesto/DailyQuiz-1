package br.com.sidlar.dailyquiz.domain.dashboard;

import br.com.sidlar.dailyquiz.domain.questionario.Questionario;
import org.joda.time.DateTime;

/**
 * D.T.O.
 * @author Admilson
 */
public class QuestionarioDisponivel {
    private String nome;
    private Long id;
    private int totalQuestoes;
    private DateTime dataPublicacao;
    private String tempoParaExpirar;

    public QuestionarioDisponivel(Questionario questionario) {
        this.nome = questionario.getNome();
        this.id = questionario.getId();
        this.totalQuestoes = questionario.getTotalQuestoes();
        this.dataPublicacao = questionario.getDataDisponibilidadeInicial();
        this.tempoParaExpirar = questionario.getTempoParaExpirar();
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public int getTotalQuestoes() {
        return totalQuestoes;
    }

    public DateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public String getTempoParaExpirar() {
        return tempoParaExpirar;
    }
}
