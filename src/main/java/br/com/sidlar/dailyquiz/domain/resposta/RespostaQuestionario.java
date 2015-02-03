package br.com.sidlar.dailyquiz.domain.resposta;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import br.com.sidlar.dailyquiz.domain.questionario.Questionario;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

/**
 * @author Admilson
 */
@Table(name="respostaQuestionario")
@Entity
public class RespostaQuestionario implements Comparable<RespostaQuestionario> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime data;

    @ManyToOne
    @JoinColumn(name = "idMembro")
    private Membro membro;

    @ManyToOne
    @JoinColumn(name = "idQuestionario",nullable = false)
    private Questionario questionario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "idRespostaQuestionario")
    private List<RespostaQuestao> respostaQuestoes;

    private int pontuacao;

    public RespostaQuestionario() {
    }

    public RespostaQuestionario(Membro membro, Questionario questionario, List<RespostaQuestao> respostaQuestoes, DateTime data, int pontuacao) {
        this.membro = membro;
        this.questionario = questionario;
        this.respostaQuestoes = respostaQuestoes;
        this.data = data;
        this.pontuacao = pontuacao;
    }

    public Long getId() {
        return id;
    }

    public DateTime getData() {
        return data;
    }

    public Membro getMembro() {
        return membro;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public List<RespostaQuestao> getRespostaQuestoes() {
        return respostaQuestoes;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int getTotalAcertos(){
        int totalAcertos=0;
        for (RespostaQuestao respostaQuestao : respostaQuestoes) {
            if(respostaQuestao.acertou()){
                totalAcertos++;
            }
        }
        return totalAcertos;
    }

    public int getTotalQuestoes(){
        return questionario.getTotalQuestoes();
    }

    @Override
    public int compareTo(RespostaQuestionario o) {
        return Integer.compare(this.getPontuacao(),o.getPontuacao());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RespostaQuestionario that = (RespostaQuestionario) o;

        if (!data.equals(that.data)) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!membro.equals(that.membro)) return false;
        if (!questionario.equals(that.questionario)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + data.hashCode();
        result = 31 * result + membro.hashCode();
        result = 31 * result + questionario.hashCode();
        return result;
    }
}
