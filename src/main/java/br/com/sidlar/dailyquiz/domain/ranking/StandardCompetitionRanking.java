package br.com.sidlar.dailyquiz.domain.ranking;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Admilson
 */
public class StandardCompetitionRanking {
    private List<ClassificacaoRanking> classificacoes;

    public StandardCompetitionRanking(List<ClassificacaoRanking> classificacoes) {
        this.classificacoes = classificacoes;
    }

    public List<ClassificacaoRanking> posiciona(){
        classificacoes.sort(ClassificacaoRanking::compareTo);
        int posicaoRanking = 0;
        int totalPontosAnterior = 0;
        int contador = 0;
        for (ClassificacaoRanking classificacao : classificacoes) {
            contador++;
            if(totalPontosAnterior != classificacao.getTotalPontos()) posicaoRanking = contador;
            classificacao.setPosicao(posicaoRanking);
            totalPontosAnterior = classificacao.getTotalPontos();
        }
        return classificacoes;
    }
}
