package br.com.sidlar.dailyquiz.domain.resposta;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import br.com.sidlar.dailyquiz.domain.questionario.Questionario;
import br.com.sidlar.dailyquiz.domain.questionario.QuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Admilson
 */
@Component
public class RespostaService {

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @Autowired
    private RespostaQuestionarioRepository respostaQuestionarioRepository;

    @Autowired
    private RespostaQuestionarioFactory respostaQuestionarioFactory;


    @Transactional(readOnly = true)
    public CartaoResposta obtemCartaoResposta(Long idQuestionario){
        Questionario questionario = questionarioRepository.buscaPorId(idQuestionario);
        return new CartaoResposta(questionario);
    }

    @Transactional(readOnly = false)
    public RespostaQuestionario salvaRespostaQuestionario(CartaoResposta cartaoResposta, Membro membro) {
        RespostaQuestionario resposta = respostaQuestionarioFactory.cria(cartaoResposta, membro);
        respostaQuestionarioRepository.salva(resposta);
        return resposta;
    }


}
