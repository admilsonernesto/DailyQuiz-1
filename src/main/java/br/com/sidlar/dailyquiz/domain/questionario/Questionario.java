package br.com.sidlar.dailyquiz.domain.questionario;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import br.com.sidlar.dailyquiz.infrastructure.utilitarios.FormataPeriodoUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import javax.persistence.*;
import java.util.List;

/**
 * @author Admilson
 */
@Table(name="questionario")
@Entity
public class Questionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMembro")
    private Membro membroCriador;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "idQuestionario")
    @OrderColumn(name = "ordem")
    private List<Questao> questoes;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dataDisponibilidadeInicial;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dataDisponibilidadeFinal;

    public String getNome() {
        return nome;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public DateTime getDataDisponibilidadeInicial() {
        return dataDisponibilidadeInicial;
    }

    public DateTime getDataDisponibilidadeFinal() {
        return dataDisponibilidadeFinal;
    }

    public int getTotalQuestoes(){
        return questoes.size();
    }

    public Long getId() {
        return id;
    }

    /**
     * Calcula o tempo restante que falta para expirar a disponibilidade do questionário.
     * Caso não possua data disponibilidade final deverá retornar que "nao expira"
     * <p>Exemplo:</p>
     * <ul>
     * <li>Período com 10 dias, 4 horas e  8 minutos : "10 dias"</li>
     * <li>Período com  0 dias, 4 horas e  8 minutos : "4 horas e 8 minutos"</li>
     * </ul>
     */
    public String getTempoParaExpirar(){
        if (getDataDisponibilidadeFinal()==null) return "não expira";

        Period periodoExpiracao = new Period(DateTime.now(), getDataDisponibilidadeFinal(), PeriodType.dayTime());
        return FormataPeriodoUtils.formata(periodoExpiracao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Questionario)) return false;

        Questionario that = (Questionario) o;

        if (!dataDisponibilidadeInicial.equals(that.dataDisponibilidadeInicial)) return false;
        if (!membroCriador.equals(that.membroCriador)) return false;
        if (!nome.equals(that.nome)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = membroCriador.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + dataDisponibilidadeInicial.hashCode();
        return result;
    }

    void setDataDisponibilidadeFinal(DateTime dataDisponibilidadeFinal) {
        this.dataDisponibilidadeFinal = dataDisponibilidadeFinal;
    }
}
