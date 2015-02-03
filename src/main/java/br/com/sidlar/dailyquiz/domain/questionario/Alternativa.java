package br.com.sidlar.dailyquiz.domain.questionario;

import javax.persistence.*;

/**
 * @author Admilson
 */
@Table(name="alternativa")
@Entity
public class Alternativa {
    @Id
    @GeneratedValue
    private Long id;

    private int ordem;

    private String descricao;

    public Long getId() {
        return id;
    }

    public int getOrdem() {
        return ordem;
    }

    public String getDescricao() {
        return descricao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alternativa that = (Alternativa) o;

        if (ordem != that.ordem) return false;
        if (!descricao.equals(that.descricao)) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ordem;
        result = 31 * result + descricao.hashCode();
        return result;
    }
}
