package br.com.sidlar.dailyquiz.domain.ranking;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class TesteStandardCompetitionRanking {

    @Test
    public void testePosicoesIguaisOrdenadasNoPadraoStandardCompetitionRanking() {
        //Fixture
        ClassificacaoRanking classificacao1 = criaClassificacaoComPontuacao(1);
        ClassificacaoRanking classificacao2 = criaClassificacaoComPontuacao(2);
        ClassificacaoRanking classificacao3 = criaClassificacaoComPontuacao(1);
        StandardCompetitionRanking standardCompetitionRanking = new StandardCompetitionRanking(Lists.newArrayList(classificacao1,classificacao2,classificacao3));

        //Sut
        List<ClassificacaoRanking> classificacoes =  standardCompetitionRanking.posiciona();

        //Verify
        assertThat(classificacoes.get(0).getPosicao(),is(1));
        assertThat(classificacoes.get(1).getPosicao(),is(2));
        assertThat(classificacoes.get(2).getPosicao(),is(2));
    }


    @Test
    public void testeOrdenacaoNoPadraoStandardCompetitionRanking() {
        //Fixture
        ClassificacaoRanking classificacao1 = criaClassificacaoComPontuacao(1);
        ClassificacaoRanking classificacao2 = criaClassificacaoComPontuacao(2);
        ClassificacaoRanking classificacao3 = criaClassificacaoComPontuacao(3);
        StandardCompetitionRanking standardCompetitionRanking = new StandardCompetitionRanking(Lists.newArrayList(classificacao1,classificacao2,classificacao3));

        //Sut
        List<ClassificacaoRanking> classificacoes =  standardCompetitionRanking.posiciona();

        //Verify
        assertThat(classificacoes.get(0).getTotalPontos(),is(3));
        assertThat(classificacoes.get(1).getTotalPontos(),is(2));
        assertThat(classificacoes.get(2).getTotalPontos(),is(1));
    }

    private ClassificacaoRanking criaClassificacaoComPontuacao(int pontos){
        ClassificacaoRanking classificacao = new ClassificacaoRanking();
        classificacao.setTotalPontos(pontos);
        return classificacao;
    }
}