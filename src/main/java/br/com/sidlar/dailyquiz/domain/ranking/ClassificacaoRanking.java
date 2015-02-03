package br.com.sidlar.dailyquiz.domain.ranking;

import br.com.sidlar.dailyquiz.domain.membro.Membro;

/**
 * @author Admilson
 */
public class ClassificacaoRanking implements Comparable<ClassificacaoRanking>{
    private Membro participante;
    private Integer posicao;
    private Integer totalAcertos;
    private Integer totalPontos;

    public ClassificacaoRanking() {
    }

    public ClassificacaoRanking(Membro participante, Integer totalAcertos, Integer totalPontos) {
        this.participante = participante;
        this.totalAcertos = totalAcertos;
        this.totalPontos = totalPontos;
    }

    public ClassificacaoRanking(Membro membro) {
        this.participante = membro;
    }

    public Membro getParticipante() {
        return participante;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public Integer getTotalAcertos() {
        return totalAcertos;
    }

    public Integer getTotalPontos() {
        return totalPontos;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public void setTotalPontos(Integer totalPontos) {
        this.totalPontos = totalPontos;
    }

    @Override
    public int compareTo(ClassificacaoRanking o) {
        return -this.getTotalPontos().compareTo(o.getTotalPontos());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassificacaoRanking that = (ClassificacaoRanking) o;

        if (!participante.equals(that.participante)) return false;
        if (posicao != null ? !posicao.equals(that.posicao) : that.posicao != null) return false;
        if (!totalPontos.equals(that.totalPontos)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = participante.hashCode();
        result = 31 * result + (posicao != null ? posicao.hashCode() : 0);
        result = 31 * result + totalPontos.hashCode();
        return result;
    }
}
