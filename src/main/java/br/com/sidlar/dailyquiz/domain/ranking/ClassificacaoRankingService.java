package br.com.sidlar.dailyquiz.domain.ranking;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import br.com.sidlar.dailyquiz.domain.resposta.RespostaQuestionario;
import br.com.sidlar.dailyquiz.domain.resposta.RespostaQuestionarioRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Admilson
 */
@Component
public class ClassificacaoRankingService {

    @Autowired
    private RespostaQuestionarioRepository respostaQuestionarioRepository;

    public ClassificacaoRanking criaClassificacaoDoQuestionario(Membro participante, Long idQuestionario){
        List<RespostaQuestionario> respostas = respostaQuestionarioRepository.buscaRespostasDoQuestionario(idQuestionario);
        List<ClassificacaoRanking> classificacoes = criaClassificacoesDasRespostas(respostas);
        return (ClassificacaoRanking) classificacoes.stream().filter(classificacaoRanking -> classificacaoRanking.getParticipante().equals(participante));
    }

    public List<ClassificacaoRanking> criaClassificacaoGeral(){
        List<RespostaQuestionario> respostas = respostaQuestionarioRepository.buscaTodasRespostas();
        return criaClassificacoesDasRespostas(respostas);
    }

    private List<ClassificacaoRanking> criaClassificacoesDasRespostas(List<RespostaQuestionario> respostas) {
        List<Membro> membrosParticipantes = respostas.stream()
                                                     .map(RespostaQuestionario::getMembro)
                                                     .distinct()
                                                     .collect(Collectors.toList());
        List<ClassificacaoRanking> classificacoes = criaClassificacoesDosParticipantes(respostas, membrosParticipantes);
        return new StandardCompetitionRanking(classificacoes).posiciona();
    }

    private List<ClassificacaoRanking> criaClassificacoesDosParticipantes(List<RespostaQuestionario> respostas, List<Membro> membrosParticipantes) {
        List<ClassificacaoRanking> classificacoes = Lists.newArrayList();
        for (Membro participante : membrosParticipantes) {
            List<RespostaQuestionario> respostasDoParticipante =respostas.stream()
                                                                         .filter(respostaQuestionario -> respostaQuestionario.getMembro().equals(participante))
                                                                         .collect(Collectors.toList());
            int totalAcertos = respostasDoParticipante.stream().mapToInt(RespostaQuestionario::getTotalAcertos).sum();
            int totalPontos = respostasDoParticipante.stream().mapToInt(RespostaQuestionario::getPontuacao).sum();
            classificacoes.add(new ClassificacaoRanking(participante, totalAcertos, totalPontos));
        }
        return classificacoes;
    }


}
