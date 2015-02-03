package br.com.sidlar.dailyquiz.domain.resposta;

import br.com.sidlar.dailyquiz.domain.questionario.Alternativa;
import br.com.sidlar.dailyquiz.domain.questionario.Questao;

import javax.persistence.*;

/**
 * @author Admilson
 */
@Table(name="respostaQuestao")
@Entity
public class RespostaQuestao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idQuestao",nullable = false)
    private Questao questao;

    @ManyToOne
    @JoinColumn(name = "idAlternativa",nullable = false)
    private Alternativa alternativaEscolhida;

    public RespostaQuestao() {
    }

    public RespostaQuestao(Questao questao, Alternativa alternativaEscolhida) {
        this.questao = questao;
        this.alternativaEscolhida = alternativaEscolhida;
    }

    public Questao getQuestao() {
        return questao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public void setAlternativaEscolhida(Alternativa alternativaEscolhida) {
        this.alternativaEscolhida = alternativaEscolhida;
    }

    public Alternativa getAlternativaEscolhida() {
        return alternativaEscolhida;
    }

    public boolean acertou(){
        return getQuestao().getAlternativaCorreta().equals(getAlternativaEscolhida());
    }
}
