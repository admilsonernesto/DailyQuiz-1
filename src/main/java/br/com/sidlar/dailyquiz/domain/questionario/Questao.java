package br.com.sidlar.dailyquiz.domain.questionario;

import javax.persistence.*;
import java.util.List;

/**
 * @author Admilson
 */
@Table(name="questao")
@Entity
public class Questao {
    @Id
    @GeneratedValue
    private Long id;

    private int ordem;

    private String enunciado;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "idQuestao")
    @OrderColumn(name = "ordem")
    private List<Alternativa> alternativas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAlternativa")
    private Alternativa alternativaCorreta;

    public int getOrdem() {
        return ordem;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public void setAlternativaCorreta(Alternativa alternativaCorreta) {
        this.alternativaCorreta = alternativaCorreta;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public Alternativa getAlternativaCorreta() {
        return alternativaCorreta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Questao questao = (Questao) o;

        if (ordem != questao.ordem) return false;
        if (!enunciado.equals(questao.enunciado)) return false;
        if (id != null ? !id.equals(questao.id) : questao.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ordem;
        result = 31 * result + enunciado.hashCode();
        return result;
    }
}
